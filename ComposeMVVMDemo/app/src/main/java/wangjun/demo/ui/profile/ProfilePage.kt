package wangjun.demo.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import wangjun.demo.R
import wangjun.demo.model.bean.User
import wangjun.demo.util.Preference
import wangjun.demo.view.compose.Line
import wangjun.demo.view.compose.TitleBar

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/24
 *   desc : 个人资料页
 * </pre>
 */
@Composable
fun ProfilePage() {
    val hasLogin by Preference(Preference.IS_LOGIN, false)
    val userJson by Preference(Preference.USER_GSON, "")
    var user: User? = null

    val isLogin by remember {
        mutableStateOf(hasLogin)
    }

    if (isLogin) {
        user = Gson().fromJson(userJson, User::class.java)
    }
    val current = LocalContext.current
    ProfileScreen(
        isLogin,
        user,
        onLogin = {
            if (!isLogin) {
//                Navigation.findNavController(composeView)
//                    .navigate(R.id.action_tab_to_login)
            } else {
//                current.startKtxActivity<ComposeMainActivity>()
            }
        },)
}

@Composable
fun ProfileScreen(
    isLogin: Boolean,
    user: User?,
    onLogin: () -> Unit,
) {
    Column {
        TitleBar(text = stringResource(R.string.me))
        LoginMenu(isLogin = isLogin, user, clickLogin = onLogin)
        Line()
        VersionTv()
    }
}

@Composable
fun Menu(text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onClick() }, verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = AnnotatedString(text),
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun VersionTv() {

    val context = LocalContext.current
    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxWidth().fillMaxHeight()
        .clickable {
        }) {
        Text(
            text = "V 1.0.0",
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}

@Composable
fun LoginMenu(isLogin: Boolean, user: User? = null, clickLogin: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(80.dp)
        .clickable {
            clickLogin()
        }) {

        if (isLogin) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user?.icon)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.avatar_ic),
                error = painterResource(R.drawable.avatar_ic),
                contentDescription = stringResource(R.string.login),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(48.dp)
                    .height(48.dp)
            )
        } else {
            Image(
                painter = painterResource(R.drawable.avatar_ic),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(48.dp)
                    .height(48.dp)
            )
        }

        Text(
            text = if (isLogin) user?.username ?: "" else "登录/注册", color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 24.dp)
        )


    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        false,
        null,
        onLogin = {},
        )
}

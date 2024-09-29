package wangjun.demo.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import wangjun.demo.R

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/3/2
 *   desc : 通用标题
 * </pre>
 */
@Composable
fun TitleBar(text: String, showBackButton: Boolean = false, onBack: (() -> Unit)? = null) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = colorResource(R.color.colorPrimary)),
    ) {
        val (back, title) = createRefs()
        if (showBackButton) {
            Box(
                modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
                    .clickable { onBack?.invoke() }
                    .constrainAs(back) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.ArrowBack, contentDescription = "Back",
                    tint = Color.White,
                )
            }

        }

        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.White,
            maxLines = 1,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start, 56.dp)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Preview
@Composable
fun TitleBarPreview() {
    Column {
        TitleBar(text = "标题", true) {}
        Spacer(modifier = Modifier.height(10.dp))
        Divider()
        TitleBar(text = "我是标题", true) {}
    }

}
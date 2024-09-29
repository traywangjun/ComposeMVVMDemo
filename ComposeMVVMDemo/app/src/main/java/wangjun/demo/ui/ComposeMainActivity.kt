package wangjun.demo.ui

import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import dagger.hilt.android.AndroidEntryPoint
import wangjun.demo.R
import wangjun.demo.base.BaseVMActivity
import wangjun.demo.navigation.AndroidPage
import wangjun.demo.ui.splash.SplashPage

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/25
 *   desc : 主界面
 * </pre>
 */
@AndroidEntryPoint
class ComposeMainActivity : BaseVMActivity() {

    override fun initView() {

        setContent {

            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !isSystemInDarkTheme()

            DisposableEffect(systemUiController, useDarkIcons) {
                systemUiController.setSystemBarsColor(
                    color = Color.White,
                    darkIcons = useDarkIcons
                )
                onDispose { }
            }

            Mdc3Theme {
                AppScreen()
            }
        }
    }

    override fun initData() {
    }

    override fun startObserve() {
    }
}

@Composable
fun AppScreen() {

    val colorPrimary = colorResource(R.color.colorPrimary)
    var showSplash by remember {
        mutableStateOf(true)
    }

    if (showSplash) {
        SplashPage {
            showSplash = false
        }
    } else {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setSystemBarsColor(
                color = colorPrimary,
                darkIcons = useDarkIcons
            )
            onDispose { }
        }
        AndroidPage()
    }
}

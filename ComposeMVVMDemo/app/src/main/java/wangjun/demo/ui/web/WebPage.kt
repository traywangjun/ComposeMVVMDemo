package wangjun.demo.ui.web

import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.google.accompanist.web.*
import wangjun.demo.view.compose.TitleBar

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/25
 *   desc : 网页界面
 * </pre>
 */
@Composable
fun WebPage(url: String, navController: NavController) {

    var titleState by remember {
        mutableStateOf("")
    }
    Column {
        TitleBar(text = titleState, true) { navController.popBackStack() }

        val webState = rememberWebViewState(url = url)
        WebView(state = webState,
            onCreated = {
                it.settings.run {
                    javaScriptEnabled = true
                }
            },
            client = object : AccompanistWebViewClient() {
            },
            chromeClient = object : AccompanistWebChromeClient() {

                override fun onReceivedTitle(view: WebView, title: String?) {
                    super.onReceivedTitle(view, title)
                    titleState = title ?: ""
                }
            })
    }

}


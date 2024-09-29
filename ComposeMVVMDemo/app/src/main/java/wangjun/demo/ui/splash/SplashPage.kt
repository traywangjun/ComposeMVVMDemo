package wangjun.demo.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import wangjun.demo.R

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/21
 *   desc : 启动页
 * </pre>
 */
@Composable
fun SplashPage(onFinish: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.android_logo))
        val animationState = animateLottieCompositionAsState(composition = composition)
        LottieAnimation(composition = composition, progress = { animationState.progress })
        if (animationState.isAtEnd && animationState.isPlaying) {
            onFinish()
        }
    }
}
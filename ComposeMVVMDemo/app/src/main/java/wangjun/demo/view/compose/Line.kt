package wangjun.demo.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import wangjun.demo.R

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/24
 *   desc : 通用横线UI
 * </pre>
 */
@Composable
fun Line(
    modifier: Modifier = Modifier,
    color: Color = colorResource(R.color.light_gray),
    height: Dp = 1.dp,
) {
    Spacer(
        modifier
            .background(color = color)
            .fillMaxWidth()
            .height(height)
    )
}

@Composable
@Preview
fun LinePreview() {
    Line()
}
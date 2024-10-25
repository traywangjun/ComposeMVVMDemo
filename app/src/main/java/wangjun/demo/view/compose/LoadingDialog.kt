package wangjun.demo.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import wangjun.demo.R

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/3/2
 *   desc : 加载弹窗
 * </pre>
 */
@Composable
fun LoadingDialog() {
    Dialog(onDismissRequest = { }) {
        CustomLoadingDialog()
    }
}

@Composable
fun CustomLoadingDialog(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.is_loading)
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .width(240.dp)
            .height(180.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier.background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(10.dp), strokeWidth = 2.dp)

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = text, fontSize = 18.sp)
        }
    }
}

@Preview
@Composable
fun LoadingDialogPreview() {
    LoadingDialog()
}
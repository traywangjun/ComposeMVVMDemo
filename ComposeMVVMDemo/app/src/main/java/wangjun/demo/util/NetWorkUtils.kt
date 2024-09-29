package wangjun.demo.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/21
 *   desc : 网络工具类
 * </pre>
 */
class NetWorkUtils {

    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val manager = context.applicationContext.getSystemService(
                    Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return !(null == info || !info.isAvailable)
        }
    }
}
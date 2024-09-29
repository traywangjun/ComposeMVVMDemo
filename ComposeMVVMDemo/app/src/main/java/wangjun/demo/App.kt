package wangjun.demo

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp
import wangjun.demo.model.bean.User
import kotlin.properties.Delegates

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/10
 *   desc : App上下文
 * </pre>
 */
@HiltAndroidApp
class App : Application() {

    companion object {
        var CONTEXT: Context by Delegates.notNull()
        lateinit var CURRENT_USER: User
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        MMKV.initialize(this)
    }
}
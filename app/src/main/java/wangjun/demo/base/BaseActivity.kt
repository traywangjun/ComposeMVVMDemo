package wangjun.demo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/10
 *   desc : Activity基类
 * </pre>
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initData()
    }

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()

}
package support.com.dzh.myapplication.base

import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BaseActivity : RxAppCompatActivity(), View.OnClickListener {
    var lastTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParmas(intent?.extras)
        setContentView(initLayout())
        initView()
        initDate()

    }

    abstract fun initParmas(bundle: Bundle?)
    abstract fun initLayout(): Int
    abstract fun initView()
    abstract fun initDate()
    abstract fun onWClick(view: View)


    override fun onClick(p0: View?) {
        if (fastClick()) onWClick(p0!!)
    }

    //防止重复点击
    fun fastClick(): Boolean {
        if (System.currentTimeMillis() - lastTime > 1000) {
            lastTime = System.currentTimeMillis()
            return true
        } else return false
    }



}

package support.com.dzh.myapplication.base

import android.app.Application
import android.content.Context
import com.baiiu.library.LogUtil
import support.com.dzh.myapplication.BuildConfig
import kotlin.properties.Delegates

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtil.init(BuildConfig.DEBUG)
    }

//    private object Holder {
//        val INSTANCE = BaseApplication()
//    }
//
//    companion object {
//        val singleInstance by lazy { Holder.INSTANCE }
//    }





}

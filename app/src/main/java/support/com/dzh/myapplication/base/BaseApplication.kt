package support.com.dzh.myapplication.base

import android.app.Application
import com.baiiu.library.LogUtil
import support.com.dzh.myapplication.BuildConfig

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtil.init(BuildConfig.DEBUG)
    }

}

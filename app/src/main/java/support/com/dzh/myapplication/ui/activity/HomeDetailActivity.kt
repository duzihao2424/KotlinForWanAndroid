package support.com.dzh.myapplication.ui.activity

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_home_detail.*
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.base.BaseActivity

class HomeDetailActivity : BaseActivity() {
    var url = ""
    override fun initParmas(bundle: Bundle?) {
        url = bundle!!.getString("url")
    }

    override fun initLayout(): Int {
        return R.layout.activity_home_detail
    }

    override fun initView() {
        webview.loadUrl(url)
    }

    override fun initDate() {

    }

    override fun onWClick(view: View) {

    }

}

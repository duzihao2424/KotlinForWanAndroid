package support.com.dzh.myapplication.ui.fragemt

import android.view.View
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.base.BaseFragment

class FragmentCollectArticle : BaseFragment() {
    override fun layoutId(): Int {
        return R.layout.fragment_collect_article
    }

    override fun initialized() {

    }

    override fun initListener() {

    }

    override fun onWClick(view: View) {

    }

    override fun onInvisible() {

    }

    companion object {
        fun newInstance(): FragmentCollectArticle {
            val fragment = FragmentCollectArticle()
            return fragment
        }
    }
}

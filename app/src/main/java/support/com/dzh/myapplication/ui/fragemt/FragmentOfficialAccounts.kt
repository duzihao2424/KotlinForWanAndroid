package support.com.dzh.myapplication.ui.fragemt

import android.view.View
import kotlinx.android.synthetic.main.fragment_official_accounts.view.*
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.adapter.WxTitleAdapter
import support.com.dzh.myapplication.base.BaseFragment
import support.com.dzh.myapplication.model.DataWx
import support.com.dzh.myapplication.presenter.WxArticlePresenter
import support.com.dzh.myapplication.view.WxArticleView

class FragmentOfficialAccounts : BaseFragment() {
    var presenter: WxArticlePresenter? = null
    val adapter: WxTitleAdapter by lazy {
        WxTitleAdapter(childFragmentManager, list)
    }

    var list = ArrayList<DataWx.DataBean>()

    override fun layoutId(): Int {
        return R.layout.fragment_official_accounts
    }

    override fun initialized() {
        presenter!!.getWxTitleData()
    }

    override fun initListener() {
        mView!!.viewpager.adapter = adapter
        mView!!.tabs.setupWithViewPager(mView!!.viewpager)
        mView!!.tabs.setTabsFromPagerAdapter(adapter)


        presenter = WxArticlePresenter(this, "home", object : WxArticleView {
            override fun onSuccess(data: List<DataWx.DataBean>) {
                list.clear()
                list.addAll(data)
                adapter!!.notifyDataSetChanged()
                mView!!.viewpager.visibility = View.VISIBLE
                mView!!.viewpager.offscreenPageLimit = list.size
                for (i in 0 until list.size) {
                    mView!!.tabs.addTab(mView!!.tabs.newTab().setText(list.get(i).name))//添加tab选项
                }

            }

            override fun onError() {

            }
        })
    }

    override fun onWClick(view: View) {

    }

    override fun onInvisible() {

    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): FragmentOfficialAccounts {
            val myFragment = FragmentOfficialAccounts()
            return myFragment
        }
    }

    fun scrollToTop() {
        if (adapter.count == 0) {
            return
        }
        val frg = childFragmentManager.fragments.get(mView!!.viewpager.currentItem) as FragmentWxArticle
        frg.scrollToTop()
    }
}


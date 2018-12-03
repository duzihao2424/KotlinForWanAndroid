package support.com.dzh.myapplication.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.app_bar_main2.*
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.base.BaseActivity
import support.com.dzh.myapplication.ui.fragemt.FragmentCollectArticle
import support.com.dzh.myapplication.ui.fragemt.FragmentCollectWeb

class CollectActivity : BaseActivity(), ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

    }

    override fun onPageSelected(p0: Int) {

    }

    var titleArr = arrayOf("收藏文章", "收藏网址")
    override fun initParmas(bundle: Bundle?) {

    }

    override fun initLayout(): Int {
        return R.layout.activity_collect
    }

    override fun initView() {
        tabs.setupWithViewPager(viewpager)
        viewpager.visibility = View.VISIBLE
        viewpager.offscreenPageLimit = titleArr.size
        viewpager.adapter = CAdapter(supportFragmentManager, titleArr)

        viewpager.addOnPageChangeListener(this)

        toolbar.title = "我的收藏"
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { view ->
            finish()
        }


    }

    override fun initDate() {

    }

    override fun onWClick(view: View) {

    }

}

class CAdapter(private val fm: FragmentManager, private val titleArr: Array<String>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        if (p0 == 0) return FragmentCollectArticle.newInstance() else return FragmentCollectWeb.newInstance()
    }

    override fun getCount(): Int {
        return titleArr.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleArr.get(position)
    }

}

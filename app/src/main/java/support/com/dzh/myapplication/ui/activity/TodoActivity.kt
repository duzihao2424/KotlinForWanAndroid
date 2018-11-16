package support.com.dzh.myapplication.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import kotlinx.android.synthetic.main.activity_todo.*
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.base.BaseActivity
import support.com.dzh.myapplication.ui.fragemt.FragmentTodo

class TodoActivity : BaseActivity() {

    var mAdapter: FragmentAdapter? = null

    class FragmentAdapter constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        val titles: List<String> = arrayListOf("只有一个", "工作", "学习", "生活")
        override fun getItem(p0: Int): Fragment {
            return FragmentTodo()
        }

        override fun getCount(): Int {
            return 4
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles.get(position)
        }

    }

    override fun initParmas(bundle: Bundle?) {

    }

    override fun initLayout(): Int {
        return R.layout.activity_todo
    }

    override fun initView() {
        viewpager.offscreenPageLimit = 4
        mAdapter = FragmentAdapter(supportFragmentManager)
        viewpager.adapter = mAdapter
        tabs.setViewPager(viewpager)


    }

    override fun initDate() {

    }

    override fun onWClick(view: View) {

    }




}

package support.com.dzh.myapplication.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import support.com.dzh.myapplication.model.DataWx
import support.com.dzh.myapplication.ui.fragemt.FragmentWxArticle

class WxTitleAdapter(fragmentManager: FragmentManager, private val list: List<DataWx.DataBean>) : FragmentStatePagerAdapter(fragmentManager) {
    var fragment: FragmentWxArticle? = null

    override fun getItem(p0: Int): Fragment {
        fragment = FragmentWxArticle.newInstance(list.get(p0).id)
        return fragment as FragmentWxArticle
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list.get(position).name
    }

}
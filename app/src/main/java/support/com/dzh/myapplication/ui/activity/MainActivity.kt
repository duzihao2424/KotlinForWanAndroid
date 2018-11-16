package support.com.dzh.myapplication.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.util.DisplayMetrics
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.base.BaseActivity
import support.com.dzh.myapplication.ui.fragemt.FragmentHome

class MainActivity : BaseActivity() {
    var fh: FragmentHome? = null
    override fun initParmas(bundle: Bundle?) {

    }

    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

        showFragment(0)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun initDate() {

    }

    override fun onWClick(view: View) {

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
           showFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                showFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                showFragment(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    /**
     * 展示Fragment
     * @param index
     */
    private fun showFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (index) {
            0 // 首页
            -> {
                if (fh == null) {
                    fh = FragmentHome().getInstance()
                    transaction.add(R.id.container, fh!!, "home")
                } else {
                    transaction.show(fh!!)
                }
            }
            1 // 知识体系
            -> {

                if (fh == null) {
                    fh = FragmentHome().getInstance()
                    transaction.add(R.id.container, fh!!, "home")
                } else {
                    transaction.show(fh!!)
                }
            }
            2 // 导航
            -> {

                if (fh == null) {
                    fh = FragmentHome().getInstance()
                    transaction.add(R.id.container, fh!!, "home")
                } else {
                    transaction.show(fh!!)
                }
            }

        }
        transaction.commit()
    }


    private fun hideFragments(transaction: FragmentTransaction) {
        fh?.let { transaction.hide(it)}
    }



}

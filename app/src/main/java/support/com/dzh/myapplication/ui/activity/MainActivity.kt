package support.com.dzh.myapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main2.*
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.base.BaseActivity
import support.com.dzh.myapplication.ui.fragemt.FragmentHome
import support.com.dzh.myapplication.ui.fragemt.FragmentOfficialAccounts

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    var fh: FragmentHome? = null
    var foa: FragmentOfficialAccounts? = null
    var mIndex = 0
    override fun initParmas(bundle: Bundle?) {

    }

    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

        showFragment(0)
        toolbar.title = "首页"
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        nav_view.setNavigationItemSelectedListener(this)

        floating_action_btn.setOnClickListener(this)

    }

    override fun initDate() {

    }

    override fun onWClick(view: View) {
        when (view.id) {
            R.id.floating_action_btn -> {
                when (mIndex) {
                    0 -> {
                        fh!!.scrollToTop()
                    }
                    2 -> {
                        foa!!.scrollToTop()
                    }
                }
            }
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                if (mIndex == 0) {
                    fh!!.clickRefresh()
                } else {
                    showFragment(0)
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                showFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                if (mIndex == 2) {
                    foa!!.clickRefresh()
                } else {
                    showFragment(2)
                }
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
        mIndex = index
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (index) {
            0 // 首页
            -> {
                toolbar.title = "首页"
                if (fh == null) {
                    fh = FragmentHome.getInstance()
                    transaction.add(R.id.container, fh!!, "home")
                } else {
                    transaction.show(fh!!)
                }
            }
            1
            -> {
                toolbar.title = "哈哈"
                if (fh == null) {
                    fh = FragmentHome.getInstance()
                    transaction.add(R.id.container, fh!!, "home")
                } else {
                    transaction.show(fh!!)
                }
            }
            2
            -> {
                toolbar.title = "公众号"
                if (foa == null) {
                    foa = FragmentOfficialAccounts.newInstance()
                    transaction.add(R.id.container, foa!!, "wx")
                } else {
                    transaction.show(foa!!)
                }
            }

        }
        transaction.commit()
    }


    private fun hideFragments(transaction: FragmentTransaction) {
        fh?.let { transaction.hide(it) }
        foa?.let { transaction.hide(it) }
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                intent = Intent(this@MainActivity, CollectActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}

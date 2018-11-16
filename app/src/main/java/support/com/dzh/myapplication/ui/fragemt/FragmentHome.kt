package support.com.dzh.myapplication.ui.fragemt

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.home_header_layout.view.*

import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.adapter.HomeAdapter
import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.base.BaseFragment
import support.com.dzh.myapplication.model.BannerBean
import support.com.dzh.myapplication.model.Data
import support.com.dzh.myapplication.model.HoneList
import support.com.dzh.myapplication.presenter.HomePresenter
import support.com.dzh.myapplication.ui.activity.HomeDetailActivity
import support.com.dzh.myapplication.utils.GlideImageLoader
import support.com.dzh.myapplication.utils.ToastUtil
import support.com.dzh.myapplication.view.HomeView
import java.util.*


class FragmentHome : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val intent = Intent(this@FragmentHome.activity, HomeDetailActivity::class.java)
        intent.putExtra("url", list.get(position).link)
        startActivity(intent!!)
    }

    override fun onRefresh() {
        page = 0
        list.clear()
        presenter!!.homeList(page)

    }

    override fun onLoadMoreRequested() {
        page++
        presenter!!.homeList(page)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    var presenter: HomePresenter? = null
    var adapter: HomeAdapter? = null
    var list = ArrayList<Data>()
    var page = 0
    var list1 = ArrayList<BannerBean>()
    var images = ArrayList<String>()
    var titles = ArrayList<String>()

    fun getInstance(): FragmentHome {
        val myFragment = FragmentHome()
        return myFragment
    }


    override fun initialized() {



        mView!!.refresh_layout.setOnRefreshListener(this)
        adapter = HomeAdapter(R.layout.item_home_layout, list)
        mView!!.home_recyclerview!!.layoutManager = LinearLayoutManager(this@FragmentHome.context)
        mView!!.home_recyclerview!!.adapter = adapter
        adapter!!.setOnLoadMoreListener(this)
        adapter!!.setOnItemClickListener(this)

        mView!!.fab!!.setOnClickListener{view ->
            mView!!.home_recyclerview!!.smoothScrollToPosition(0)

        }


        presenter = HomePresenter(this, object : HomeView, OnBannerListener {
            override fun OnBannerClick(position: Int) {
                val intent = Intent(this@FragmentHome.activity, HomeDetailActivity::class.java)
                intent.putExtra("url", list1.get(position).url)
                startActivity(intent!!)
            }

            override fun onBanner(data: BaseBean<List<BannerBean>>) {
                list1.clear()
                list1.addAll(data!!.data!!)

                for (i in 0..list1.size - 1) {
                    images.add(list1.get(i).imagePath)
                    titles.add(list1.get(i).title)
                }

                val hView = inflater!!.inflate(R.layout.home_header_layout, null)

                adapter!!.setHeaderView(hView)
                hView.banner.let {
                    it.setImages(images)
                    it.setImageLoader(GlideImageLoader())
                    it.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                    it.setBannerTitles(titles)
                    it.start()
                }
                hView.banner.setOnBannerListener(this)
            }

            override fun onSuccess(data: BaseBean<HoneList>) {
                list.addAll(data.data!!.datas)
                adapter!!.notifyDataSetChanged()
                adapter!!.loadMoreComplete()
                mView!!.refresh_layout.isRefreshing = false
            }


            override fun onFail() {
                ToastUtil.show(this@FragmentHome.context!!, "加载失败")
            }

        })

        presenter!!.homeList(page)
        presenter!!.getBannerData()
    }

    override fun initListener() {

    }

    override fun onWClick(view: View) {
    }

}

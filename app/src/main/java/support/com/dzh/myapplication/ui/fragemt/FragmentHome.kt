package support.com.dzh.myapplication.ui.fragemt

import android.content.Intent
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
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


class FragmentHome : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }

    override fun onInvisible() {

    }

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

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    companion object {
        fun getInstance(): FragmentHome {
            val myFragment = FragmentHome()
            return myFragment
        }
    }


    override fun initialized() {
        presenter!!.homeList(page)
        presenter!!.getBannerData()
    }

    override fun initListener() {
        mView!!.refresh_layout.setOnRefreshListener(this)
        adapter = HomeAdapter(R.layout.item_home_layout, list,0)
        mView!!.home_recyclerview!!.layoutManager = linearLayoutManager
        mView!!.home_recyclerview!!.adapter = adapter
        adapter!!.setOnLoadMoreListener(this)
        adapter!!.setOnItemClickListener(this)

        adapter!!.setOnItemChildClickListener(this)


        presenter = HomePresenter(this.context!!,this.activity as RxAppCompatActivity, object : HomeView, OnBannerListener {
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


            override fun onError(msg: String) {
                ToastUtil.show(this@FragmentHome.context!!, "加载失败")
            }

        })
    }

    override fun onWClick(view: View) {
    }

    fun scrollToTop() {
        mView!!.home_recyclerview!!.run {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
                scrollToPosition(0)
            } else {
                smoothScrollToPosition(0)
            }
        }
    }

    fun clickRefresh() {
        mView!!.refresh_layout.isRefreshing = true
        mView!!.home_recyclerview!!.scrollToPosition(0)

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                page = 0
                list.clear()
                presenter!!.homeList(page)
            }

        }, 2000)

    }


}

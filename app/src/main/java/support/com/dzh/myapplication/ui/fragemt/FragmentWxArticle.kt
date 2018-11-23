package support.com.dzh.myapplication.ui.fragemt


import android.content.Intent
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.fragment_wx_article.view.*
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.adapter.WxListAdapter
import support.com.dzh.myapplication.base.BaseFragment
import support.com.dzh.myapplication.model.wxData
import support.com.dzh.myapplication.presenter.WxArticlePresenter
import support.com.dzh.myapplication.ui.activity.HomeDetailActivity
import support.com.dzh.myapplication.view.WxArticleViewList


class FragmentWxArticle : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        var intent = Intent(activity, HomeDetailActivity::class.java)
        intent.putExtra("url", list1.get(position).link)
        startActivity(intent)
    }

    override fun onLoadMoreRequested() {
        page++
        presenter!!.getWxListData(wxid, page)
    }

    override fun onRefresh() {
        page = 0
        list1.clear()
        presenter!!.getWxListData(wxid, page)
    }

    var wxid = 0
    var page = 0
    var presenter: WxArticlePresenter? = null
    var list1 = ArrayList<wxData>()
    var adapter: WxListAdapter? = null
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_wx_article
    }

    override fun initialized() {
        presenter!!.getWxListData(wxid, page)

    }

    override fun initListener() {
        mView!!.wx_list_rview.layoutManager = linearLayoutManager
        adapter = WxListAdapter(R.layout.item_wxarticle_layout, list1)
        mView!!.wx_list_rview.adapter = adapter

        presenter = WxArticlePresenter(this.context!!,this.activity as RxAppCompatActivity, "list", object : WxArticleViewList {
            override fun onError(msg: String) {
                adapter!!.loadMoreFail()
                mView!!.wx_refresh_layout.isRefreshing = false
            }

            override fun onSuccess(list: List<wxData>) {
                if (adapter!!.isLoading) {
                    adapter!!.loadMoreComplete()
                }

                list1.addAll(list)
                adapter!!.notifyDataSetChanged()
                mView!!.wx_refresh_layout.isRefreshing = false
            }


        })

        adapter!!.setOnLoadMoreListener(this)
        adapter!!.setOnItemClickListener(this)
        mView!!.wx_refresh_layout.setOnRefreshListener(this)

    }

    override fun onWClick(view: View) {

    }

    override fun onInvisible() {

    }


    companion object {
        fun newInstance(id: Int): FragmentWxArticle {
            val fragment = FragmentWxArticle()
            fragment.wxid = id
            return fragment
        }

    }

      fun scrollToTop() {
          mView!!.wx_list_rview.run {
              if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
                  scrollToPosition(0)
              } else {
                  smoothScrollToPosition(0)
              }
          }
    }

    fun clickRefresh(){
        mView!!.wx_list_rview.scrollToPosition(0)
        mView!!.wx_refresh_layout.isRefreshing = true

        val handler = Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                page = 0
                list1.clear()
                presenter!!.getWxListData(wxid, page)
            }

        },2000)

    }
}

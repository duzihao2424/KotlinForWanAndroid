package support.com.dzh.myapplication.ui.fragemt

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.fragment_collect_article.view.*
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.adapter.HomeAdapter
import support.com.dzh.myapplication.base.BaseFragment
import support.com.dzh.myapplication.model.Data
import support.com.dzh.myapplication.presenter.CollectPresenter
import support.com.dzh.myapplication.utils.ToastUtil
import support.com.dzh.myapplication.view.CollectView

class FragmentCollectArticle : BaseFragment() {
    var presenter: CollectPresenter? = null
    var list1 = ArrayList<Data>()
    var adapter: HomeAdapter? = null
    override fun layoutId(): Int {
        return R.layout.fragment_collect_article
    }

    override fun initialized() {
        presenter!!.getCollectListData(0)

    }

    override fun initListener() {
        mView!!.rview.layoutManager = LinearLayoutManager(context!!)
        adapter = HomeAdapter(R.layout.item_home_layout, list1,1)
        mView!!.rview.adapter = adapter
        presenter = CollectPresenter(this.context!!, this.activity as RxAppCompatActivity, object : CollectView {
            override fun onSuccess(list: List<Data>) {
                list1.addAll(list)
                adapter!!.notifyDataSetChanged()
            }

            override fun onError(msg: String) {
                ToastUtil.show(context!!, msg)
            }

        })
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

package support.com.dzh.myapplication.ui.fragemt

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.fragment_collect_web.view.*
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.base.BaseFragment
import support.com.dzh.myapplication.model.BeanCollectWeb
import support.com.dzh.myapplication.model.Data
import support.com.dzh.myapplication.presenter.CollectPresenter
import support.com.dzh.myapplication.ui.activity.HomeDetailActivity
import support.com.dzh.myapplication.utils.ToastUtil
import support.com.dzh.myapplication.view.CollectView


class FragmentCollectWeb : BaseFragment(), BaseQuickAdapter.OnItemChildClickListener {
    class WebAdapter(layout: Int, data: List<BeanCollectWeb>, private val context: FragmentCollectWeb) : BaseQuickAdapter<BeanCollectWeb, BaseViewHolder>(layout, data) {
        override fun convert(helper: BaseViewHolder?, item: BeanCollectWeb?) {

            helper!!.let {
                it.setText(R.id.web_name, item!!.name)
                it.setText(R.id.web_link, item!!.link)
                it.addOnClickListener(R.id.content)
                it.addOnClickListener(R.id.right_menu_2)
                it.addOnClickListener(R.id.right_menu_1)
            }

//            helper.getView<TextView>(R.id.right_menu_1).setOnClickListener (object : View.OnClickListener{
//                override fun onClick(p0: View?) {
//                    val easySwipeMenuLayout = helper.getView<EasySwipeMenuLayout>(R.id.es)
//                    easySwipeMenuLayout.resetStatus()
//
//                }
//
//            })

        }

    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        when (view!!.id) {
            R.id.content -> {
                val intent = Intent(this.activity, HomeDetailActivity::class.java)
                intent.putExtra("url", list.get(position).link)
                startActivity(intent)
            }

            R.id.right_menu_1 ->{
                presenter!!.getDeleteCWeb(list.get(position).id)
                adapter!!.remove(position)
                adapter!!.notifyDataSetChanged()
            }

        }
    }
//    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
//        val intent = Intent(this.activity, HomeDetailActivity::class.java)
//        intent.putExtra("url", list.get(position).link)
//        startActivity(intent)
//    }

    var list = ArrayList<BeanCollectWeb>()
    var adapter: WebAdapter? = null
    var presenter: CollectPresenter? = null
    var position: Int = -1

    override fun layoutId(): Int {
        return R.layout.fragment_collect_web
    }

    override fun initialized() {
        presenter!!.getCollectWeb()

    }



    override fun initListener() {
        mView!!.web_recyclerview.layoutManager = LinearLayoutManager(this.context!!)
        adapter = WebAdapter(R.layout.item_collect_web, list, this)
        mView!!.web_recyclerview.adapter = adapter
        presenter = CollectPresenter(this.context!!, this.activity as RxAppCompatActivity, object : CollectView {
            override fun onDeleteSuccess() {

            }

            override fun onWebSuccess(data: List<BeanCollectWeb>) {
                list.clear()
                list.addAll(data)
                adapter!!.notifyDataSetChanged()
            }

            override fun onSuccess(list: List<Data>) {

            }


            override fun onError(msg: String) {
                ToastUtil.show(context!!, msg)
            }

        })
//        adapter!!.setOnItemClickListener(this)
        adapter!!.setOnItemChildClickListener(this)

    }

    override fun onWClick(view: View) {
    }

    override fun onInvisible() {
    }


    companion object {
        fun newInstance(): FragmentCollectWeb {
            val f = FragmentCollectWeb()
            return f
        }

    }



}

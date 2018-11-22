package support.com.dzh.myapplication.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.model.wxData

class WxListAdapter(layout: Int, data: List<wxData>) : BaseQuickAdapter<wxData, BaseViewHolder>(layout, data) {
    override fun convert(helper: BaseViewHolder?, item: wxData?) {
        helper!!.setText(R.id.wx_title, item!!.title)
        helper!!.setText(R.id.wx_time,item!!.niceDate)
    }
}
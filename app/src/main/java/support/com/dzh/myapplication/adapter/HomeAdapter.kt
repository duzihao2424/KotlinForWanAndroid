package support.com.dzh.myapplication.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.model.Data

class HomeAdapter(layoutId: Int, data: List<Data>) : BaseQuickAdapter<Data, BaseViewHolder>(layoutId, data) {
    override fun convert(helper: BaseViewHolder?, item: Data?) {


        helper!!.let {
            it.setText(R.id.home_title, item!!.title)
            it.setText(R.id.home_author, "作者：" + item!!.author)
            it.setText(R.id.home_type, "分类：" + item!!.superChapterName + "/" + item!!.chapterName)
        }

        if (item!!.tags.size > 0) {
            helper.getView<TextView>(R.id.is_gzh).visibility = View.VISIBLE
            helper.setText(R.id.is_gzh, item!!.tags.get(0).name)
        } else {
            helper.getView<TextView>(R.id.is_gzh).visibility = View.GONE
        }


    }
}
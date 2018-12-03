package support.com.dzh.myapplication.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.model.Data

class HomeAdapter(layoutId: Int, data: List<Data>, private val type: Int) : BaseQuickAdapter<Data, BaseViewHolder>(layoutId, data) {
    override fun convert(helper: BaseViewHolder?, item: Data?) {

        if (type == 0) {
            helper!!.let {
                it.setText(R.id.home_title, item!!.title)
                it.setText(R.id.home_author, "作者：" + item!!.author)
                it.setText(R.id.home_type, "分类：" + item!!.superChapterName + "/" + item!!.chapterName)
                it.setText(R.id.home_time, "时间：" + item!!.niceDate)
                it.addOnClickListener(R.id.collect_img)
            }

            if (item!!.tags.size > 0) {
                helper.getView<TextView>(R.id.is_gzh).visibility = View.VISIBLE
                helper.setText(R.id.is_gzh, item!!.tags.get(0).name)
            } else {
                helper.getView<TextView>(R.id.is_gzh).visibility = View.GONE
            }

            if (item!!.collect) {
                helper.setImageResource(R.id.collect_img, R.drawable.ic_like)
            } else {
                helper.setImageResource(R.id.collect_img, R.drawable.ic_like_not)
            }
        } else {
            helper!!.let {
                it.setText(R.id.home_title, item!!.title)
                it.setText(R.id.home_author, item!!.author)
                it.setText(R.id.home_time, "时间：" + item!!.niceDate)
                it.addOnClickListener(R.id.collect_img)
            }

            helper.setImageResource(R.id.collect_img, R.drawable.ic_like)

        }


    }
}
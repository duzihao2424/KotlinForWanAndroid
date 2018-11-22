package support.com.dzh.myapplication.view

import support.com.dzh.myapplication.base.BaseView
import support.com.dzh.myapplication.model.DataWx
import support.com.dzh.myapplication.model.wxData

interface WxArticleView : BaseView {
    fun onSuccess(data: List<DataWx.DataBean>)
    fun onError()
}

interface WxArticleViewList :BaseView{
    fun onSuccess(list: List<wxData>)
    fun onError()
}
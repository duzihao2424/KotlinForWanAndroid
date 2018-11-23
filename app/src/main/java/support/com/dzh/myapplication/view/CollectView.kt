package support.com.dzh.myapplication.view

import support.com.dzh.myapplication.base.BaseView
import support.com.dzh.myapplication.model.Data

interface CollectView : BaseView {
    fun onSuccess(list: List<Data>)
}
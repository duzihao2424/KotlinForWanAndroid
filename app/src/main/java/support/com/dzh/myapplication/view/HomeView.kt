package support.com.dzh.myapplication.view

import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.base.BaseView
import support.com.dzh.myapplication.model.BannerBean
import support.com.dzh.myapplication.model.HoneList

interface HomeView : BaseView {
    fun onSuccess(data: BaseBean<HoneList>)
    fun onBanner(data: BaseBean<List<BannerBean>>)
}
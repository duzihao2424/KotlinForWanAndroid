package support.com.dzh.myapplication.presenter

import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.base.BaseView
import support.com.dzh.myapplication.model.BeanWxList
import support.com.dzh.myapplication.model.DataWx
import support.com.dzh.myapplication.server.ApiErrorModel
import support.com.dzh.myapplication.server.ApiManager
import support.com.dzh.myapplication.server.ApiResponse
import support.com.dzh.myapplication.server.NetworkScheduler
import support.com.dzh.myapplication.view.WxArticleView
import support.com.dzh.myapplication.view.WxArticleViewList

class WxArticlePresenter {
    var mContext: RxFragment? = null
    var aView: WxArticleView? = null
    var wView: WxArticleViewList? = null
    var apiManager: ApiManager? = null

    constructor(context: RxFragment, tag: String, view: BaseView) {
        mContext = context
        apiManager = ApiManager(context.activity!!)
        when (tag) {
            "home" -> {
                aView = view as WxArticleView
            }
            "list" -> {
                wView = view as WxArticleViewList
            }

        }


    }

    fun getWxTitleData() {
        apiManager!!.getWxarticleData().compose(NetworkScheduler.compose()).bindToLifecycle(mContext!!).subscribe(object : ApiResponse<DataWx>(mContext!!.activity!!) {
            override fun success(data: DataWx) {
                aView!!.onSuccess(data.data!!)
            }

            override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                aView!!.onError()
            }

        })
    }

    fun getWxListData(id: Int, page: Int) {
        apiManager!!.getWxListData(id, page).compose(NetworkScheduler.compose()).bindToLifecycle(mContext!!).subscribe(object : ApiResponse<BaseBean<BeanWxList>>(mContext!!.activity!!) {
            override fun success(data: BaseBean<BeanWxList>) {
                wView!!.onSuccess(data.data!!.datas)
            }

            override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                wView!!.onError()
            }

        })
    }

}
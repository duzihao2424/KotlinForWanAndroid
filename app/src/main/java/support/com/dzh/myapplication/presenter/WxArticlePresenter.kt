package support.com.dzh.myapplication.presenter

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.base.BasePresenter
import support.com.dzh.myapplication.base.BaseView
import support.com.dzh.myapplication.model.BeanWxList
import support.com.dzh.myapplication.model.DataWx
import support.com.dzh.myapplication.server.ApiErrorModel
import support.com.dzh.myapplication.server.ApiManager
import support.com.dzh.myapplication.server.ApiResponse
import support.com.dzh.myapplication.server.NetworkScheduler
import support.com.dzh.myapplication.view.WxArticleView
import support.com.dzh.myapplication.view.WxArticleViewList

class WxArticlePresenter :BasePresenter{
    var mContext: Context? = null
    var aView: WxArticleView? = null
    var wView: WxArticleViewList? = null
    var apiManager: ApiManager? = null

    constructor(context: Context, provider: LifecycleProvider<ActivityEvent>,tag: String, view: BaseView) : super(provider) {
        mContext = context
        apiManager = ApiManager(context)
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
        apiManager!!.getWxarticleData().compose(NetworkScheduler.compose()).bindUntilEvent(provider,ActivityEvent.DESTROY).subscribe(object : ApiResponse<DataWx>(mContext!!) {
            override fun success(data: DataWx) {
                aView!!.onSuccess(data.data!!)
            }

            override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                aView!!.onError()
            }

        })
    }

    fun getWxListData(id: Int, page: Int) {
        apiManager!!.getWxListData(id, page).compose(NetworkScheduler.compose()).bindUntilEvent(provider,ActivityEvent.DESTROY).subscribe(object : ApiResponse<BaseBean<BeanWxList>>(mContext!!) {
            override fun success(data: BaseBean<BeanWxList>) {
                wView!!.onSuccess(data.data!!.datas)
            }

            override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                wView!!.onError()
            }

        })
    }

}
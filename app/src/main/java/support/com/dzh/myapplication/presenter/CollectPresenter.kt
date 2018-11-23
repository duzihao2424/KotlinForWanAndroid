package support.com.dzh.myapplication.presenter

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.base.BasePresenter
import support.com.dzh.myapplication.base.BaseView
import support.com.dzh.myapplication.model.CollectListBean
import support.com.dzh.myapplication.model.Data
import support.com.dzh.myapplication.model.HoneList
import support.com.dzh.myapplication.server.ApiErrorModel
import support.com.dzh.myapplication.server.ApiManager
import support.com.dzh.myapplication.server.ApiResponse
import support.com.dzh.myapplication.server.NetworkScheduler
import support.com.dzh.myapplication.view.CollectView

class CollectPresenter : BasePresenter {
    var mContext: Context? = null
    var cView: CollectView? = null
    var apiManager: ApiManager? = null

    constructor(context: Context, provider: LifecycleProvider<ActivityEvent>, view: BaseView) : super(provider) {
        mContext = context
        cView = view as CollectView
        apiManager = ApiManager(context)
    }

    fun getCollectListData(page: Int) {
        apiManager!!.getCollectData(page).compose(NetworkScheduler.compose()).bindUntilEvent(provider, ActivityEvent.DESTROY).subscribe(object : ApiResponse<BaseBean<HoneList>>(mContext!!) {
            override fun success(data: BaseBean<HoneList>) {
                cView!!.onSuccess(data.data!!.datas)
            }

            override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                cView!!.onError(apiErrorModel.message)
            }

        })
    }

}
package support.com.dzh.myapplication.presenter

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.base.BasePresenter
import support.com.dzh.myapplication.base.BaseView
import support.com.dzh.myapplication.model.BannerBean
import support.com.dzh.myapplication.model.HoneList
import support.com.dzh.myapplication.server.ApiErrorModel
import support.com.dzh.myapplication.server.ApiManager
import support.com.dzh.myapplication.server.ApiResponse
import support.com.dzh.myapplication.server.NetworkScheduler
import support.com.dzh.myapplication.view.HomeView

class HomePresenter : BasePresenter {
    var mContext: Context? = null
    var homeView: HomeView? = null
    var apiManager: ApiManager? = null

    constructor(context: Context, provider: LifecycleProvider<ActivityEvent>, view: BaseView) : super(provider) {
        mContext = context
        homeView = view as HomeView
        apiManager = ApiManager(context)

    }

    fun homeList(page: Int) {
        apiManager!!.homeListData(page).compose(NetworkScheduler.compose()).bindUntilEvent(provider, ActivityEvent.DESTROY).subscribe(object : ApiResponse<BaseBean<HoneList>>(mContext!!) {
            override fun success(data: BaseBean<HoneList>) {
                homeView!!.onSuccess(data)
            }

            override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {

            }

        })
    }


    fun getBannerData() {
        apiManager!!.getBannerData().compose(NetworkScheduler.compose()).bindUntilEvent(provider, ActivityEvent.DESTROY).subscribe(object : ApiResponse<BaseBean<List<BannerBean>>>(mContext!!) {
            override fun success(data: BaseBean<List<BannerBean>>) {
                homeView!!.onBanner(data)

            }

            override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {

            }

        })
    }
}

package support.com.dzh.myapplication.presenter

import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.base.BaseView
import support.com.dzh.myapplication.model.BannerBean
import support.com.dzh.myapplication.model.Data
import support.com.dzh.myapplication.model.HoneList
import support.com.dzh.myapplication.server.ApiErrorModel
import support.com.dzh.myapplication.server.ApiManager
import support.com.dzh.myapplication.server.ApiResponse
import support.com.dzh.myapplication.server.NetworkScheduler
import support.com.dzh.myapplication.view.HomeView

class HomePresenter {
    var mContext: RxFragment? = null
    var homeView: HomeView? = null
    var apiManager: ApiManager? = null

    constructor(context: RxFragment, view: BaseView) {

        mContext = context
        homeView = view as HomeView
        apiManager = ApiManager(context.activity!!)

    }

    fun homeList(page:Int) {
        apiManager!!.homeListData(page).compose(NetworkScheduler.compose()).bindToLifecycle(mContext!!).subscribe(object: ApiResponse<BaseBean<HoneList>>(mContext!!.activity!!){
            override fun success(data:BaseBean<HoneList>) {
                homeView!!.onSuccess(data)
            }

            override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {

            }

        })
    }


    fun getBannerData() {
        apiManager!!.getBannerData().compose(NetworkScheduler.compose()).bindToLifecycle(mContext!!).subscribe(object : ApiResponse<BaseBean<List<BannerBean>>>(mContext!!.activity!!){
            override fun success(data: BaseBean<List<BannerBean>>) {
                homeView!!.onBanner(data)

            }

            override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {

            }

        })
    }
}

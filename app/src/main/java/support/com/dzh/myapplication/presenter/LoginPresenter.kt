package support.com.dzh.myapplication.presenter

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.base.BasePresenter
import support.com.dzh.myapplication.base.BaseView
import support.com.dzh.myapplication.model.LoginBean
import support.com.dzh.myapplication.server.ApiErrorModel
import support.com.dzh.myapplication.server.ApiManager
import support.com.dzh.myapplication.server.ApiResponse
import support.com.dzh.myapplication.server.NetworkScheduler
import support.com.dzh.myapplication.utils.ToastUtil
import support.com.dzh.myapplication.view.LoginView

class LoginPresenter :BasePresenter{
    var mContext: Context? = null
    var mView: LoginView? = null
    var apiManager:ApiManager?=null

    constructor(context: Context,lifecycleProvider: LifecycleProvider<ActivityEvent>, view: BaseView) : super(lifecycleProvider) {
        mContext = context
        mView = view as LoginView
        apiManager = ApiManager(context)
    }

    fun login(map:Map<String,String>) {

        apiManager!!.login(map).compose(NetworkScheduler.compose()) //线程切换处理
                .bindUntilEvent(provider, ActivityEvent.DESTROY)
                .subscribe(object : ApiResponse<BaseBean<LoginBean>>(mContext!!){
                    override fun success(data: BaseBean<LoginBean>) {
                        if (data.data==null){
                            ToastUtil.show(mContext!!, data.errorMsg!!)
                        }else{
                            mView!!.onSuccess(data.data!!)
                        }

                    }

                    override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                       mView!!.onError(apiErrorModel.message)
                    }

                })

    }

}
package support.com.dzh.myapplication.server

import android.content.Context
import io.reactivex.Observable
import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.model.BannerBean
import support.com.dzh.myapplication.model.Data
import support.com.dzh.myapplication.model.HoneList
import support.com.dzh.myapplication.model.LoginBean

class ApiManager {
    var apiService: ApiService? = null
    var mContext: Context? = null

    constructor(context: Context) {
        mContext = context
        apiService = RetrofitHelper.singleInstance.getService()
    }


    fun login(map: Map<String, String>): Observable<BaseBean<LoginBean>> {
        return apiService!!.login(map)
    }

    fun homeListData(page: Int): Observable<BaseBean<HoneList>> {

        return apiService!!.homeListData(page)

    }

    fun getBannerData():Observable<BaseBean<List<BannerBean>>>{
        return apiService!!.getBannerData()
    }
}
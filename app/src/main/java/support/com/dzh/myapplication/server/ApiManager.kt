package support.com.dzh.myapplication.server

import android.content.Context
import io.reactivex.Observable
import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.model.*

class ApiManager {
    var apiService: ApiService? = null
    var mContext: Context? = null

    constructor(context: Context) {
        mContext = context
        apiService = RetrofitHelper.singleInstance.getService(mContext!!)
    }


    fun login(map: Map<String, String>): Observable<BaseBean<LoginBean>> {
        return apiService!!.login(map)
    }

    fun homeListData(page: Int): Observable<BaseBean<HoneList>> {

        return apiService!!.homeListData(page)

    }

    fun getBannerData(): Observable<BaseBean<List<BannerBean>>> {
        return apiService!!.getBannerData()
    }

    fun getCollectData(page: Int): Observable<BaseBean<HoneList>> {
        return apiService!!.getCollectData(page!!)
    }

    fun getWxarticleData(): Observable<DataWx> {
        return apiService!!.getWxarticleData()
    }

    fun getWxListData(id: Int, page: Int): Observable<BaseBean<BeanWxList>> {
        return apiService!!.getWxArtivleList(page, id)
    }


}
package support.com.dzh.myapplication.server

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap
import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.model.BannerBean
import support.com.dzh.myapplication.model.Data
import support.com.dzh.myapplication.model.HoneList
import support.com.dzh.myapplication.model.LoginBean


interface ApiService {
    @POST("user/login")
    fun login(@QueryMap map: Map<String, String>): Observable<BaseBean<LoginBean>> //每个方法的返回值即一个Observable


    @GET("article/list/{page}/json")
    fun homeListData(@Path("page") page: Int): Observable<BaseBean<HoneList>>

    @GET("banner/json")
    fun getBannerData():Observable<BaseBean<List<BannerBean>>>
}
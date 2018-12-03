package support.com.dzh.myapplication.server

import io.reactivex.Observable
import retrofit2.http.*
import support.com.dzh.myapplication.base.BaseBean
import support.com.dzh.myapplication.model.*


interface ApiService {
    @POST("user/login")
    fun login(@QueryMap map: Map<String, String>): Observable<BaseBean<LoginBean>> //每个方法的返回值即一个Observable


    @GET("article/list/{page}/json")
    fun homeListData(@Path("page") page: Int): Observable<BaseBean<HoneList>>

    @GET("banner/json")
    fun getBannerData(): Observable<BaseBean<List<BannerBean>>>

    @GET("lg/collect/list/{page}/json")
    fun getCollectData(@Path("page") page: Int): Observable<BaseBean<HoneList>>

    @GET("wxarticle/chapters/json")
    fun getWxarticleData(): Observable<DataWx>

    @GET("wxarticle/list/{id}/{page}/json")
    fun getWxArtivleList(@Path("page") page: Int, @Path("id") id: Int): Observable<BaseBean<BeanWxList>>

    @GET("lg/collect/usertools/json")
    fun getCollectWebList(): Observable<BaseBean<List<BeanCollectWeb>>>

    @POST("lg/collect/deletetool/json")
    fun getDeleteCWeb(@Query("id") id: Int):Observable<BaseBean<String>>

}
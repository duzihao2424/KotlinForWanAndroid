package support.com.dzh.myapplication.server

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    var retrofit: Retrofit? = null

    private object Holder {
        val INSTANCE = RetrofitHelper()
    }

    companion object {
        val singleInstance by lazy { Holder.INSTANCE }
    }

    fun initRetrofit(okHttpClient: OkHttpClient) {
        retrofit = null
        retrofit = Retrofit.Builder().baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }


    fun getService(context: Context): ApiService {
        if (retrofit == null) {

            var ApiClient = ApiClient(context)
            var okHttpClient = ApiClient.initService()
            initRetrofit(okHttpClient!!)
        }
        return retrofit!!.create(ApiService::class.java!!)
    }

}
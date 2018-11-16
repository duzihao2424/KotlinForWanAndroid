package support.com.dzh.myapplication.server

import android.content.Context
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    var client = ApiClient.initService()
    var retrofit : Retrofit? = null

    private object Holder {
        val INSTANCE = RetrofitHelper()
    }

    companion object {
        val singleInstance by lazy { Holder.INSTANCE }
    }

    fun initRetrofit(){
        retrofit = null
        retrofit = Retrofit.Builder().baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }


    fun getService(): ApiService {
        if (retrofit == null) {
            initRetrofit()
        }
        return retrofit!!.create(ApiService::class.java!!)
    }

}
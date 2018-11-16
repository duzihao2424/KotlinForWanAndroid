package support.com.dzh.myapplication.server

import android.util.Log
import io.reactivex.android.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*
import okhttp3.OkHttpClient.Builder
import java.util.concurrent.TimeUnit
import com.baiiu.library.okHttpLog.HttpLoggingInterceptorM
import com.baiiu.library.okHttpLog.LogInterceptor



class ApiClient {

    companion object {
        var httpClient: OkHttpClient? = null
        fun initService(): OkHttpClient? {
            if (httpClient == null) {
                val builder = Builder()
                builder.connectTimeout(10L, TimeUnit.SECONDS)
                builder.readTimeout(10L, TimeUnit.SECONDS)
                builder.writeTimeout(10L, TimeUnit.SECONDS)

                val trustAllCerts = object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?> {
                        return arrayOfNulls(0)
                    }
                }


                try {
                    val sslContext = SSLContext.getInstance("SSL")
                    sslContext.init(null as Array<KeyManager>?, arrayOf<TrustManager>(trustAllCerts), SecureRandom())
                    val sslSocketFactory = sslContext.socketFactory
                    builder.sslSocketFactory(sslSocketFactory)
                    builder.hostnameVerifier(HostnameVerifier { hostname, session -> true })
                } catch (var6: KeyManagementException) {
                    var6.printStackTrace()
                } catch (var6: NoSuchAlgorithmException) {
                    var6.printStackTrace()
                }

                val interceptor = HttpLoggingInterceptorM(LogInterceptor())
                interceptor.level = HttpLoggingInterceptorM.Level.BODY
                builder.addInterceptor(interceptor)
                httpClient = builder.build()

            }



            return httpClient
        }


    }
}
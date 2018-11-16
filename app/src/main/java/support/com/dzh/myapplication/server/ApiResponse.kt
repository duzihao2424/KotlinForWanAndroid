package support.com.dzh.myapplication.server

import android.content.Context
import android.support.annotation.StringRes
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ApiResponse<T>(private val context: Context) : Observer<T> {
    abstract fun success(data: T)
    abstract fun failure(statusCode: Int, apiErrorModel: ApiErrorModel)

    override fun onSubscribe(d: Disposable) {
//        LoadingDialog.show(context)
    }

    override fun onNext(t: T) {
        success(t)
    }

    override fun onComplete() {
//        LoadingDialog.cancel()
    }

    override fun onError(e: Throwable) {
//        LoadingDialog.cancel()
        if (e is HttpException) { //连接服务器成功但服务器返回错误状态码
            val apiErrorModel: ApiErrorModel = when (e.code()) {
                ApiErrorType.INTERNAL_SERVER_ERROR.code ->
                    ApiErrorType.INTERNAL_SERVER_ERROR.getApiErrorModel(context)
                ApiErrorType.BAD_GATEWAY.code ->
                    ApiErrorType.BAD_GATEWAY.getApiErrorModel(context)
                ApiErrorType.NOT_FOUND.code ->
                    ApiErrorType.NOT_FOUND.getApiErrorModel(context)
                else -> otherError(e)

            }
            failure(e.code(), apiErrorModel)
            return
        }

        val apiErrorType: ApiErrorType = when (e) {  //发送网络问题或其它未知问题，请根据实际情况进行修改
            is UnknownHostException -> ApiErrorType.NETWORK_NOT_CONNECT
            is ConnectException -> ApiErrorType.NETWORK_NOT_CONNECT
            is SocketTimeoutException -> ApiErrorType.CONNECTION_TIMEOUT
            else -> ApiErrorType.UNEXPECTED_ERROR
        }
        if (apiErrorType.code == 700) {
            var c = e.toString()
            failure(apiErrorType.code, apiErrorType.getApiErrorModel(context, c))
        } else {
            failure(apiErrorType.code, apiErrorType.getApiErrorModel(context))
        }

    }

    private fun otherError(e: HttpException) =
            Gson().fromJson(e.response().errorBody()?.charStream(), ApiErrorModel::class.java)
}


enum class ApiErrorType(val code: Int, @param: StringRes private val message: String) {

    //根据实际情况进行增删
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    BAD_GATEWAY(502, "服务器异常"),
    NOT_FOUND(404, "服务器无法响应"),
    CONNECTION_TIMEOUT(408, "服务器异常"),
    NETWORK_NOT_CONNECT(499, "服务器异常"),
    UNEXPECTED_ERROR(700, "服务器异常");

    private val DEFAULT_CODE = 1


    fun getApiErrorModel(context: Context, msg: String): ApiErrorModel {
        return ApiErrorModel(DEFAULT_CODE, msg)
    }

    fun getApiErrorModel(context: Context): ApiErrorModel {
        return ApiErrorModel(DEFAULT_CODE, message)
    }

}

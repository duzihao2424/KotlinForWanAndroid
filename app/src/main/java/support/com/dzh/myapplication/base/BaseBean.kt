package support.com.dzh.myapplication.base


class BaseBean<T> {
    var errorCode: Int = 0
    var errorMsg: String? = null
    var data: T? = null

    override fun toString(): String {
        return "BaseBean{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }
}

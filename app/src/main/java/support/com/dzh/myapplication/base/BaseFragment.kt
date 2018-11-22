package support.com.dzh.myapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment


abstract class BaseFragment : RxFragment(), View.OnClickListener {

    protected var mContext: RxFragment? = null
    var mView: View? = null
    var lastTime = 0L

    protected var isDestroyView: Boolean = false
    var inflater: LayoutInflater? = null

    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false
    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false


    /**
     * 布局文件ID
     *
     * @return
     */
    protected abstract fun layoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater
        val layoutId = layoutId()
        mView = inflater.inflate(layoutId, container, false)
        isViewPrepare = true
        initListener()
        lazyLoadDataIfPrepared()
        return mView
    }


    override fun onClick(p0: View?) {
        if (fastClick()) onWClick(p0!!)
    }

    //防止重复点击
    fun fastClick(): Boolean {
        if (System.currentTimeMillis() - lastTime > 1000) {
            lastTime = System.currentTimeMillis()
            return true
        } else return false
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isDestroyView = true
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onResume() {
        super.onResume()
    }


    /**
     * 初始化数据
     */
    protected abstract fun initialized()

    /**
     * 初始化监听
     */
    protected abstract fun initListener()

    protected abstract fun onWClick(view: View)

    /**
     * fragment被设置为不可见时调用
     */
    protected abstract fun onInvisible()



    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            initialized()
            hasLoadData = true
        }
    }


}

package support.com.dzh.myapplication.base

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment


abstract class BaseFragment : RxFragment(), View.OnClickListener {

    protected var mContext: RxFragment? = null
    var mView:View? = null
    var lastTime = 0L

    protected var isDestroyView: Boolean = false
    var inflater: LayoutInflater?=null


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
        isDestroyView = false


        //向用户展示信息前的准备工作在这个方法里处理
        preliminary()
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

    override fun onResume() {
        super.onResume()
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


    /**
     * 向用户展示信息前的准备工作在这个方法里处理
     */
    protected fun preliminary() {
        // 初始化数据
        initialized()
        // 初始化组件
        initListener()
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


}

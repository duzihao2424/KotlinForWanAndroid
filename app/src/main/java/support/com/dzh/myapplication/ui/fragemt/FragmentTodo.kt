package support.com.dzh.myapplication.ui.fragemt

import android.view.View
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.base.BaseFragment


class FragmentTodo : BaseFragment(){


    override fun layoutId(): Int {
        return R.layout.fragment_todo
    }



    fun getInstance(i:Int): FragmentTodo {
        val myFragment = FragmentTodo()
        return myFragment
    }


    override fun initialized() {}

    override fun initListener() {

    }

    override fun onWClick(view: View) {
    }

}

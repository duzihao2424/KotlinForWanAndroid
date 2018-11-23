package support.com.dzh.myapplication.view

import support.com.dzh.myapplication.base.BaseView
import support.com.dzh.myapplication.model.LoginBean

interface LoginView :BaseView{
    fun onSuccess(data:LoginBean)
}
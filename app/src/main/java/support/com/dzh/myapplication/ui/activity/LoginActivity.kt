package support.com.dzh.myapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import kotlinx.android.synthetic.main.activity_login.*
import support.com.dzh.myapplication.R
import support.com.dzh.myapplication.base.BaseActivity
import support.com.dzh.myapplication.model.LoginBean
import support.com.dzh.myapplication.presenter.LoginPresenter
import support.com.dzh.myapplication.utils.ToastUtil
import support.com.dzh.myapplication.view.LoginView
import java.util.*

class LoginActivity : BaseActivity() {
    override fun onWClick(view: View) {
        when (view.id) {

            R.id.btnLogin -> {
                map?.put("username", edtUsername.text.toString())
                map?.put("password", edtPwd.text.toString())
                presenter!!.login(map!!)
            }

        }
    }

    var presenter: LoginPresenter? = null
    var map: MutableMap<String, String>? = HashMap()
    override fun initParmas(bundle: Bundle?) {

    }

    override fun initLayout(): Int {
        return R.layout.activity_login

    }

    override fun initView() {
        btnLogin.setOnClickListener(this)
        btnRegist.setOnClickListener(this)
    }

    override fun initDate() {
        val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(this))
        if (cookieJar!=null){
            val intent =Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        presenter = LoginPresenter(this,this, object : LoginView {
            override fun onFail() {
                ToastUtil.show(this@LoginActivity,"登陆失败")
            }

            override fun onSuccess(date: LoginBean) {
                val intent =Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        })

    }


}

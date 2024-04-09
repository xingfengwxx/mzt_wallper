package com.hjq.demo.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.hjq.demo.R
import com.hjq.demo.aop.Log
import com.hjq.demo.app.AppActivity
import com.hjq.demo.http.api.MztApi
import com.hjq.demo.http.api.MztHomeListApi
import com.hjq.http.EasyHttp
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.ssl.HttpSslFactory
import okhttp3.OkHttpClient
import java.lang.Exception


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
class MztActivity : AppActivity() {

    companion object {

        @Log
        fun start(context: Context) {
            val intent = Intent(context, MztActivity::class.java)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.mzt_activity
    }

    override fun initView() {}

    override fun initData() {

//        EasyHttp.get(this)
//            .api(MztApi().apply {
//                setTime()
//            })
//            .request(object :  OnHttpListener<String?>{
//                override fun onSucceed(result: String?) {
//
//                }
//
//                override fun onFail(e: Exception?) {
//
//                }
//
//            })

        EasyHttp.get(this)
            .api(MztHomeListApi())
            .request(object : OnHttpListener<String?> {
                override fun onSucceed(result: String?) {

                }

                override fun onFail(e: Exception?) {
                }

            })
    }
}
package com.hjq.demo.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * author : 王星星
 * date : 2024/4/24 16:04
 * email : 1099420259@qq.com
 * description :
 */
// 定义一个扩展函数，用于在 Fragment 首次可见时执行操作
fun Fragment.doLazyLoad(action: () -> Unit) {
    viewLifecycleOwnerLiveData.observe(this) { viewLifecycleOwner ->
        viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
                // 当 Fragment 的生命周期变为 RESUME 时执行操作
                action.invoke()
                // 由于只需要执行一次，所以在执行后移除观察者
                viewLifecycleOwner.lifecycle.removeObserver(this)
            }
        })
    }
}
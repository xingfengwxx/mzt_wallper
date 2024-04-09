package com.hjq.demo.ui.fragment

import com.hjq.demo.R
import com.hjq.demo.app.AppFragment
import com.hjq.demo.ui.activity.CopyActivity

/**
 * author : 王星星
 * date : 2024/4/9 18:14
 * email : 1099420259@qq.com
 * description : 360
 */
class Img360Fragment : AppFragment<CopyActivity>() {

    companion object {

        fun newInstance(): Img360Fragment {
            return Img360Fragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.copy_fragment
    }

    override fun initView() {}

    override fun initData() {}
}
package com.hjq.demo.ui.fragment

import com.hjq.demo.R
import com.hjq.demo.app.AppFragment
import com.hjq.demo.ui.activity.CopyActivity

/**
 * author : 王星星
 * date : 2024/4/9 18:15
 * email : 1099420259@qq.com
 * description : Pixabay: https://pixabay.com/
 */
class PixabayFragment : AppFragment<CopyActivity>() {

    companion object {

        fun newInstance(): PixabayFragment {
            return PixabayFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.pixabay_fragment
    }

    override fun initView() {}

    override fun initData() {}
}
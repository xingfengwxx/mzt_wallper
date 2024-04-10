package com.hjq.demo.ui.fragment

import com.gyf.immersionbar.ImmersionBar
import com.hjq.bar.TitleBar
import com.hjq.demo.R
import com.hjq.demo.app.AppFragment
import com.hjq.demo.ui.activity.CopyActivity

/**
 * author : 王星星
 * date : 2024/4/9 18:13
 * email : 1099420259@qq.com
 * description : 手机
 */
class PhoneFragment : AppFragment<CopyActivity>() {

    companion object {
        fun newInstance(): PhoneFragment {
            return PhoneFragment()
        }
    }

    private val toolbar: TitleBar? by lazy { findViewById(R.id.title_bar) }

    override fun getLayoutId(): Int {
        return R.layout.phone_fragment
    }

    override fun initView() {
        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(getAttachActivity(), toolbar)
    }

    override fun initData() {}
}
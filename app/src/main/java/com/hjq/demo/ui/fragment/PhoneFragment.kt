package com.hjq.demo.ui.fragment

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjq.bar.TitleBar
import com.hjq.base.BaseAdapter
import com.hjq.demo.R
import com.hjq.demo.app.AppActivity
import com.hjq.demo.app.TitleBarFragment
import com.hjq.demo.extension.dp2px
import com.hjq.demo.http.api.PhoneCategoryApi
import com.hjq.demo.http.model.HttpData
import com.hjq.demo.other.GridSpaceDecoration
import com.hjq.demo.ui.adapter.PhoneCategoryAdapter
import com.hjq.http.EasyHttp
import com.hjq.http.listener.OnHttpListener
import com.hjq.widget.layout.WrapRecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 * author : 王星星
 * date : 2024/4/9 18:13
 * email : 1099420259@qq.com
 * description : 手机壁纸分类
 */
class PhoneFragment : TitleBarFragment<AppActivity>(), OnRefreshListener, BaseAdapter.OnItemClickListener {

    companion object {
        @JvmStatic
        fun newInstance(): PhoneFragment {
            return PhoneFragment()
        }
    }

    private val toolbar: TitleBar? by lazy { findViewById(R.id.title_bar) }
    private val refreshLayout: SmartRefreshLayout? by lazy { findViewById<SmartRefreshLayout>(R.id.refresh_layout) }
    private val recyclerView: WrapRecyclerView? by lazy { findViewById<WrapRecyclerView>(R.id.recycler_view) }

    private var adapter: PhoneCategoryAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.phone_fragment
    }

    override fun initView() {
        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(getAttachActivity(), toolbar)

        adapter = PhoneCategoryAdapter(getAttachActivity()!!)
        adapter?.setOnItemClickListener(this)

        recyclerView?.apply {
            adapter = this@PhoneFragment.adapter
            layoutManager = GridLayoutManager(getAttachActivity(), 3)
            addItemDecoration(GridSpaceDecoration(dp2px(8f)))
        }

        refreshLayout?.setOnRefreshListener(this)
        refreshLayout?.autoRefresh()
    }

    override fun initData() {

    }
    override fun onRefresh(refreshLayout: RefreshLayout) {
        adapter?.clearData()
        requestData()
    }

    override fun onItemClick(recyclerView: RecyclerView?, itemView: View?, position: Int) {
        toast(adapter?.getItem(position))
    }

    private fun requestData() {
        EasyHttp.get(this)
            .api(PhoneCategoryApi())
            .request(object : OnHttpListener<HttpData<PhoneCategoryApi.Category>?>{
                override fun onSucceed(result: HttpData<PhoneCategoryApi.Category>?) {
                    adapter?.setData(result?.getData()?.category)
                    refreshLayout?.finishRefresh()
                }

                override fun onFail(e: Exception?) {
                    LogUtils.e(e)
                }

            })
    }
}
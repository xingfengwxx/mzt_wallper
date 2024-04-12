package com.hjq.demo.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjq.bar.TitleBar
import com.hjq.base.BaseAdapter
import com.hjq.demo.R
import com.hjq.demo.app.TitleBarFragment
import com.hjq.demo.extension.dp2px
import com.hjq.demo.http.api.AibiziPhoneListApi
import com.hjq.demo.http.model.HttpData
import com.hjq.demo.other.LineItemDecoration
import com.hjq.demo.ui.activity.PhotoListActivity
import com.hjq.demo.ui.adapter.PhotoAdapter
import com.hjq.http.EasyHttp
import com.hjq.http.listener.OnHttpListener
import com.hjq.widget.layout.WrapRecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

/**
 * author : 王星星
 * date : 2024/4/11 17:33
 * email : 1099420259@qq.com
 * description :
 */
class PhotoListFragment : TitleBarFragment<PhotoListActivity>(), OnRefreshLoadMoreListener,
    BaseAdapter.OnItemClickListener {

    companion object {
        const val ID: String = "id"

        fun newInstance(id: String) = PhotoListFragment().apply {
            arguments = Bundle().apply {
                putString(ID, id)
            }
        }
    }

    private val toolbar: TitleBar? by lazy { findViewById(R.id.title_bar) }
    private val refreshLayout: SmartRefreshLayout? by lazy { findViewById<SmartRefreshLayout>(R.id.refresh_layout) }
    private val recyclerView: WrapRecyclerView? by lazy { findViewById<WrapRecyclerView>(R.id.recycler_view) }

    private var adapter: PhotoAdapter? = null

    private var id: String? = ""
    private var skip = 0

    override fun getLayoutId(): Int {
        return R.layout.photo_list_fragment
    }

    override fun initView() {
        arguments?.let {
            id = it.getString(ID)
        }

        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(this, toolbar)

        adapter = PhotoAdapter(requireContext())
        adapter?.setOnItemClickListener(this)

        recyclerView?.apply {
            adapter = this@PhotoListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                LineItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    dp2px(10f),
                    ColorUtils.getColor(R.color.common_window_background_color)
                )
            )
        }

        refreshLayout?.setOnRefreshLoadMoreListener(this)
        refreshLayout?.autoRefresh()
    }

    override fun initData() {}


    override fun isStatusBarEnabled(): Boolean {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled()
    }

    override fun isStatusBarDarkFont(): Boolean {
        return true
    }


    override fun onRefresh(refreshLayout: RefreshLayout) {
        skip = 0
        adapter?.clearData()
        requestData(true)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        requestData(false)
    }

    override fun onItemClick(recyclerView: RecyclerView?, itemView: View?, position: Int) {
        toast(adapter?.getItem(position))
    }

    private fun requestData(isFirstPage: Boolean) {
        EasyHttp.get(this)
            .api(AibiziPhoneListApi().apply {
                setId(id!!)
                setSkip(skip)
            })
            .request(object : OnHttpListener<HttpData<AibiziPhoneListApi.Vertical>> {
                override fun onSucceed(result: HttpData<AibiziPhoneListApi.Vertical>?) {
                    if (isFirstPage) {
                        adapter?.setData(result?.getData()?.vertical)
                        refreshLayout?.finishRefresh()
                    } else {
                        refreshLayout?.finishLoadMore()

                        adapter?.apply {
                            addData(result?.getData()?.vertical)
                            val isLastPage = result?.getData()?.vertical?.size!! < 20
                            setLastPage(isLastPage)
                            this@PhotoListFragment.refreshLayout?.setNoMoreData(isLastPage)
                        }
                    }

                    skip += 20
                }

                override fun onFail(e: Exception?) {
                    LogUtils.e(e)
                }

            })
    }
}
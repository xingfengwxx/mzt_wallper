package com.hjq.demo.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjq.bar.TitleBar
import com.hjq.base.BaseAdapter
import com.hjq.demo.R
import com.hjq.demo.aop.Log
import com.hjq.demo.app.AppActivity
import com.hjq.demo.extension.dp2px
import com.hjq.demo.http.api.AibiziPhoneListApi
import com.hjq.demo.http.model.HttpData
import com.hjq.demo.other.LineItemDecoration
import com.hjq.demo.ui.adapter.PhotoAdapter
import com.hjq.http.EasyHttp
import com.hjq.http.listener.OnHttpListener
import com.hjq.widget.layout.WrapRecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

/**
 * author : 王星星
 * date : 2024/4/10 15:53
 * email : 1099420259@qq.com
 * description : 照片列表
 */
class PhotoListActivity : AppActivity(), OnRefreshLoadMoreListener, BaseAdapter.OnItemClickListener {

    companion object {
        private const val ID: String = "id"

        @Log
        fun start(context: Context, id: String) {
            val intent = Intent(context, PhotoListActivity::class.java)
            intent.putExtra(ID, id)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    private val toolbar: TitleBar? by lazy { findViewById(R.id.title_bar) }
    private val refreshLayout: SmartRefreshLayout? by lazy { findViewById<SmartRefreshLayout>(R.id.refresh_layout) }
    private val recyclerView: WrapRecyclerView? by lazy { findViewById<WrapRecyclerView>(R.id.recycler_view) }

    private var adapter: PhotoAdapter? = null

    private var id: String? = ""
    private var skip = 0

    override fun getLayoutId(): Int {
        return R.layout.photo_list_activity
    }

    override fun initView() {
        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(this, toolbar)

        adapter = PhotoAdapter(this)
        adapter?.setOnItemClickListener(this)

        recyclerView?.apply {
            adapter = this@PhotoListActivity.adapter
            layoutManager = LinearLayoutManager(this@PhotoListActivity)
            addItemDecoration(LineItemDecoration(this@PhotoListActivity,  LinearLayoutManager.VERTICAL, dp2px(10f), ColorUtils.getColor(R.color.common_window_background_color)))
        }

        refreshLayout?.setOnRefreshLoadMoreListener(this)

    }

    override fun initData() {
        id = intent.getStringExtra(ID)
        refreshLayout?.autoRefresh()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        adapter?.clearData()
        requestDataFirst()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        requestDataMore()
    }

    override fun onItemClick(recyclerView: RecyclerView?, itemView: View?, position: Int) {
        toast(adapter?.getItem(position))
    }

    private fun requestDataFirst() {
        EasyHttp.get(this)
            .api(AibiziPhoneListApi().apply {
                setId(id!!)
                setSkip(skip)
            })
            .request(object : OnHttpListener<HttpData<AibiziPhoneListApi.Vertical>> {
                override fun onSucceed(result: HttpData<AibiziPhoneListApi.Vertical>?) {
                    adapter?.setData(result?.getData()?.vertical)
                    refreshLayout?.finishRefresh()

                    skip += 20
                }

                override fun onFail(e: Exception?) {
                    LogUtils.e(e)
                }

            })
    }

    private fun requestDataMore() {
        EasyHttp.get(this)
            .api(AibiziPhoneListApi().apply {
                setId(id!!)
                setSkip(skip)
            })
            .request(object : OnHttpListener<HttpData<AibiziPhoneListApi.Vertical>> {
                override fun onSucceed(result: HttpData<AibiziPhoneListApi.Vertical>?) {
                    refreshLayout?.finishLoadMore()

                    adapter?.apply {
                        addData(result?.getData()?.vertical)
                        val isLastPage = result?.getData()?.vertical?.size!! < 20
                        setLastPage(isLastPage)
                        this@PhotoListActivity.refreshLayout?.setNoMoreData(isLastPage)
                    }

                    skip += 20
                }

                override fun onFail(e: Exception?) {
                    LogUtils.e(e)
                }

            })
    }
}
package com.hjq.demo.ui.fragment

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjq.bar.TitleBar
import com.hjq.base.BaseAdapter
import com.hjq.demo.Const
import com.hjq.demo.R
import com.hjq.demo.app.AppActivity
import com.hjq.demo.app.TitleBarFragment
import com.hjq.demo.bean.CategoryBean
import com.hjq.demo.extension.dp2px
import com.hjq.demo.http.api.Img360CategoryApi
import com.hjq.demo.http.model.HttpData
import com.hjq.demo.other.GridSpaceDecoration
import com.hjq.demo.ui.activity.PhotoListActivity
import com.hjq.demo.ui.adapter.Img360CategoryAdapter
import com.hjq.http.EasyHttp
import com.hjq.http.listener.OnHttpListener
import com.hjq.widget.layout.WrapRecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 * author : 王星星
 * date : 2024/4/9 18:14
 * email : 1099420259@qq.com
 * description : 360
 */
class Pic360Fragment : TitleBarFragment<AppActivity>(), OnRefreshListener, BaseAdapter.OnItemClickListener {

    companion object {
        fun newInstance(): Pic360Fragment {
            return Pic360Fragment()
        }
    }

    private val toolbar: TitleBar? by lazy { findViewById(R.id.title_bar) }
    private val refreshLayout: SmartRefreshLayout? by lazy { findViewById<SmartRefreshLayout>(R.id.refresh_layout) }
    private val recyclerView: WrapRecyclerView? by lazy { findViewById<WrapRecyclerView>(R.id.recycler_view) }

    private var adapter: Img360CategoryAdapter? = null


    override fun getLayoutId(): Int {
        return R.layout.img_360_fragment
    }

    override fun initView() {
        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(getAttachActivity(), toolbar)

        adapter = Img360CategoryAdapter(getAttachActivity()!!)
        adapter?.setOnItemClickListener(this)

        recyclerView?.apply {
            adapter = this@Pic360Fragment.adapter
            layoutManager = GridLayoutManager(getAttachActivity(), 3)
            addItemDecoration(GridSpaceDecoration(dp2px(8f)))
        }

        refreshLayout?.setOnRefreshListener(this)
        refreshLayout?.autoRefresh()
    }

    override fun initData() {}

    override fun onRefresh(refreshLayout: RefreshLayout) {
        adapter?.clearData()
        requestData()
    }

    override fun onItemClick(recyclerView: RecyclerView?, itemView: View?, position: Int) {
        val categoryList = mutableListOf<CategoryBean>()
        adapter?.getData()?.forEach {
            val bean = CategoryBean(it.id, it.name)
            categoryList.add(bean)
        }

        PhotoListActivity.start(
            requireContext(),
            StringUtils.getString(R.string.home_nav_found),
            position,
            categoryList as ArrayList<CategoryBean>,
            Const.WallpaperType.TYPE_360,
        )
    }

    private fun requestData() {
        EasyHttp.get(this)
            .api(Img360CategoryApi())
            .request(object : OnHttpListener<HttpData<MutableList<Img360CategoryApi.Bean>>> {
                override fun onSucceed(result: HttpData<MutableList<Img360CategoryApi.Bean>>) {
                    adapter?.setData(result.getData())
                    refreshLayout?.finishRefresh()
                }

                override fun onFail(e: Exception?) {
                    LogUtils.e(e)
                }

            })
    }
}
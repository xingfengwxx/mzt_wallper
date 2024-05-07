package com.hjq.demo.ui.fragment

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.gyf.immersionbar.ImmersionBar
import com.hjq.bar.TitleBar
import com.hjq.base.BaseAdapter
import com.hjq.demo.Const
import com.hjq.demo.R
import com.hjq.demo.app.AppActivity
import com.hjq.demo.app.TitleBarFragment
import com.hjq.demo.bean.CategoryBean
import com.hjq.demo.extension.dp2px
import com.hjq.demo.other.GridSpaceDecoration
import com.hjq.demo.ui.activity.PhotoListActivity
import com.hjq.demo.ui.adapter.PixabayCategoryAdapter
import com.hjq.demo.util.LocalDataUtil
import com.hjq.widget.layout.WrapRecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 * author : 王星星
 * date : 2024/4/9 18:15
 * email : 1099420259@qq.com
 * description : Pixabay: https://pixabay.com/
 */
class PixabayFragment : TitleBarFragment<AppActivity>(), OnRefreshListener,
    BaseAdapter.OnItemClickListener {

    companion object {
        fun newInstance(): PixabayFragment {
            return PixabayFragment()
        }
    }

    private val toolbar: TitleBar? by lazy { findViewById(R.id.title_bar) }
    private val refreshLayout: SmartRefreshLayout? by lazy { findViewById<SmartRefreshLayout>(R.id.refresh_layout) }
    private val recyclerView: WrapRecyclerView? by lazy { findViewById<WrapRecyclerView>(R.id.recycler_view) }

    private var adapter: PixabayCategoryAdapter? = null

    private var categoryList: List<CategoryBean>? = null

    override fun getLayoutId(): Int {
        return R.layout.pixabay_fragment
    }

    override fun initView() {
        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(getAttachActivity(), toolbar)

        adapter = PixabayCategoryAdapter(getAttachActivity()!!)
        adapter?.setOnItemClickListener(this)

        recyclerView?.apply {
            adapter = this@PixabayFragment.adapter
            layoutManager = GridLayoutManager(getAttachActivity(), 3)
            addItemDecoration(GridSpaceDecoration(dp2px(8f)))
        }

        refreshLayout?.setOnRefreshListener(this)
        refreshLayout?.autoRefresh()
    }

    override fun initData() {
        test()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        adapter?.clearData()

        categoryList = LocalDataUtil.getPixabayCategoryList()
        adapter?.setData(categoryList as MutableList<CategoryBean>)
        refreshLayout?.finishRefresh()
    }

    override fun onItemClick(recyclerView: RecyclerView?, itemView: View?, position: Int) {
        PhotoListActivity.start(
            requireContext(),
            StringUtils.getString(R.string.home_nav_me),
            position,
            categoryList as ArrayList<CategoryBean>,
            Const.WallpaperType.TYPE_PIXABAY,
        )
    }

    fun test() {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.CHINESE)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        val translator = Translation.getClient(options)

        var conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        translator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
               LogUtils.i("下载语言模型成功")

                val text = "裁剪失败，当前手机不支持裁剪图片"
                translator.translate(text)
                    .addOnSuccessListener { translatedText ->
                        LogUtils.i("翻译成功：$translatedText")
                    }
                    .addOnFailureListener { exception ->
                        LogUtils.i("翻译失败：$exception")
                    }
            }
            .addOnFailureListener { exception ->
                LogUtils.i("下载语言模型失败：$exception")
            }


    }


}
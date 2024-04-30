package com.hjq.demo.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.gyf.immersionbar.ImmersionBar
import com.hjq.bar.TitleBar
import com.hjq.base.FragmentPagerAdapter
import com.hjq.demo.Const
import com.hjq.demo.R
import com.hjq.demo.aop.Log
import com.hjq.demo.app.AppActivity
import com.hjq.demo.app.AppFragment
import com.hjq.demo.bean.CategoryBean
import com.hjq.demo.ui.adapter.TabAdapter
import com.hjq.demo.ui.fragment.PhotoListFragment

/**
 * author : 王星星
 * date : 2024/4/10 15:53
 * email : 1099420259@qq.com
 * description : 照片列表
 */
class PhotoListActivity : AppActivity(), TabAdapter.OnTabListener, ViewPager.OnPageChangeListener {

    companion object {

        @Log
        fun start(context: Context, title: String, index: Int, category: ArrayList<CategoryBean>, type: Int) {
            val intent = Intent(context, PhotoListActivity::class.java)
            intent.putExtra(Const.ParamKey.TITLE, title)
            intent.putExtra(Const.ParamKey.INDEX, index)
            intent.putExtra(Const.ParamKey.TYPE, type)
            intent.putParcelableArrayListExtra(Const.ParamKey.CATEGORY, category)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    private val toolbar: TitleBar? by lazy { findViewById(R.id.title_bar) }
    private val tabView: RecyclerView? by lazy { findViewById<RecyclerView>(R.id.tab_view) }
    private val viewPager: ViewPager? by lazy { findViewById<ViewPager>(R.id.view_pager) }

    private var tabAdapter: TabAdapter? = null
    private var pagerAdapter: FragmentPagerAdapter<AppFragment<*>>? = null

    private var index = 0
    private var categoryList: ArrayList<CategoryBean>? = null

    private var type = Const.WallpaperType.TYPE_PHONE

    override fun getLayoutId(): Int {
        return R.layout.photo_list_activity
    }

    override fun initView() {
        title = intent.getStringExtra(Const.ParamKey.TITLE)
        index = intent.getIntExtra(Const.ParamKey.INDEX, 0)
        type = intent.getIntExtra(Const.ParamKey.TYPE, Const.WallpaperType.TYPE_PHONE)
        categoryList = intent.getParcelableArrayListExtra(Const.ParamKey.CATEGORY)

        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(this, toolbar)

        pagerAdapter = FragmentPagerAdapter(this)
        tabAdapter = TabAdapter(this, fixed = false)
        categoryList?.forEach {
            if (type == Const.WallpaperType.TYPE_PHONE) {
                pagerAdapter?.addFragment(PhotoListFragment.newInstance(it.id, Const.WallpaperType.TYPE_PHONE))
            } else if (type == Const.WallpaperType.TYPE_COMPUTER) {
                pagerAdapter?.addFragment(PhotoListFragment.newInstance(it.id, Const.WallpaperType.TYPE_COMPUTER))
            }
            tabAdapter?.addItem(it.name)
        }

        viewPager?.apply {
            adapter = pagerAdapter
            offscreenPageLimit = 1
            currentItem = index
            addOnPageChangeListener(this@PhotoListActivity)
        }

        tabView?.adapter = tabAdapter

        tabAdapter?.apply {
            setSelectedPosition(index)
            setOnTabListener(this@PhotoListActivity)
        }
    }

    override fun initData() {
//        BeautifulWallpaper
//        beautiful-wallpaper-wxx
    }


    override fun onTabSelected(recyclerView: RecyclerView?, position: Int): Boolean {
        viewPager?.currentItem = position
        return true
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        tabAdapter?.setSelectedPosition(position)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

}
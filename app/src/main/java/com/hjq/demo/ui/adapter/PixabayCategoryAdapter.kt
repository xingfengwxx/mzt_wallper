package com.hjq.demo.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.blankj.utilcode.util.ColorUtils
import com.hjq.demo.R
import com.hjq.demo.app.AppAdapter
import com.hjq.demo.bean.CategoryBean
import com.hjq.shape.view.ShapeTextView

/**
 * author : 王星星
 * date : 2024/4/10 14:52
 * email : 1099420259@qq.com
 * description : 
 */
class PixabayCategoryAdapter constructor(context: Context) : AppAdapter<CategoryBean>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder()
    }

    inner class ViewHolder : AppViewHolder(R.layout.img_360_item) {

        private val tvBg: ShapeTextView? by lazy { findViewById<ShapeTextView>(R.id.tv_bg) }
        private val tvName: AppCompatTextView? by lazy { findViewById<AppCompatTextView>(R.id.tv_name) }

        @SuppressLint("CheckResult")
        override fun onBindView(position: Int) {
            val bean = getItem(position)
            tvBg?.shapeDrawableBuilder?.setSolidColor(ColorUtils.getRandomColor())?.intoBackground()
            tvName?.text = bean.name
        }
    }
}
package com.hjq.demo.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.hjq.demo.R
import com.hjq.demo.app.AppAdapter
import com.hjq.demo.extension.dp2px
import com.hjq.demo.http.api.PhoneCategoryApi
import com.hjq.demo.http.glide.GlideApp
import com.hjq.demo.other.RoundedCorners2

/**
 * author : 王星星
 * date : 2024/4/10 15:10
 * email : 1099420259@qq.com
 * description : 
 */
class PhoneCategoryAdapter constructor(context: Context) : AppAdapter<PhoneCategoryApi.Bean>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder()
    }

    inner class ViewHolder : AppViewHolder(R.layout.phone_item) {

        private val ivCover: AppCompatImageView? by lazy { findViewById<AppCompatImageView>(R.id.iv_cover) }
        private val tvName: AppCompatTextView? by lazy { findViewById<AppCompatTextView>(R.id.tv_name) }

        @SuppressLint("CheckResult")
        override fun onBindView(position: Int) {
            val bean = getItem(position)

            ivCover?.let {
                GlideApp.with(it)
                    .load(bean.cover)
                    .transform(RoundedCorners2(dp2px(10f), true))
                    .into(it)
            }
            tvName?.text = bean.name
        }
    }
}
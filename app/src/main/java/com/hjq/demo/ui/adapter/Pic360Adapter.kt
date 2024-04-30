package com.hjq.demo.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.hjq.demo.R
import com.hjq.demo.app.AppAdapter
import com.hjq.demo.extension.dp2px
import com.hjq.demo.http.api.Pic360ListApi
import com.hjq.demo.http.glide.GlideApp
import com.hjq.demo.other.RoundedCorners2

/**
 * author : 王星星
 * date : 2024/4/10 15:10
 * email : 1099420259@qq.com
 * description : 
 */
class Pic360Adapter constructor(context: Context) : AppAdapter<Pic360ListApi.Bean>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder()
    }

    inner class ViewHolder : AppViewHolder(R.layout.photo_item) {

        private val ivPhoto: AppCompatImageView? by lazy { findViewById<AppCompatImageView>(R.id.iv_photo) }

        @SuppressLint("CheckResult")
        override fun onBindView(position: Int) {
            val bean = getItem(position)

            ivPhoto?.let {
                GlideApp.with(it)
                    .load(bean.url)
                    .transform(RoundedCorners2(dp2px(16f), true))
                    .into(it)
            }

        }
    }
}
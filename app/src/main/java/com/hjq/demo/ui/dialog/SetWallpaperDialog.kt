package com.hjq.demo.ui.dialog

import android.content.Context
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.hjq.base.BaseDialog
import com.hjq.base.action.AnimAction
import com.hjq.demo.Const
import com.hjq.demo.R

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
class SetWallpaperDialog {

    class Builder(context: Context) : BaseDialog.Builder<Builder>(context) {

        private val tvSetLockScreen: AppCompatTextView? by lazy { findViewById(R.id.tv_set_lock_screen) }
        private val tvSetMainScreen: AppCompatTextView? by lazy { findViewById(R.id.tv_set_main_screen) }
        private val tvSetAllScreen: AppCompatTextView? by lazy { findViewById(R.id.tv_set_all_screen) }

        private var listener: OnListener? = null

        init {
            setContentView(R.layout.set_wallpaper_dialog)
            setAnimStyle(AnimAction.ANIM_DEFAULT)
            setGravity(Gravity.CENTER)

            tvSetLockScreen?.setOnClickListener {
                listener?.onSelected(Const.ScreenType.LOCK)
                dismiss()
            }

            tvSetMainScreen?.setOnClickListener {
                listener?.onSelected(Const.ScreenType.MAIN)
                dismiss()
            }

            tvSetAllScreen?.setOnClickListener {
                listener?.onSelected(Const.ScreenType.ALL)
                dismiss()
            }
        }

        fun setListener(listener: OnListener): Builder = apply {
            this.listener = listener
        }
    }

    interface OnListener {
        fun onSelected(type: Int)
    }
}
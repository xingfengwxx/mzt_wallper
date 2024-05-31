package com.hjq.demo.ui.activity

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.ToastUtils
import com.hjq.demo.R
import com.hjq.demo.app.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * author : 王星星
 * date : 2024/5/31 17:21
 * email : 1099420259@qq.com
 * description :
 */
class ImagePreviewViewModel : ViewModel() {

    private val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(App.instance)

     fun setWallpaper(type: Int, resource: Bitmap) {
         viewModelScope.launch {
             withContext(Dispatchers.IO) {
                 try {
                     if (type and WallpaperManager.FLAG_SYSTEM == WallpaperManager.FLAG_SYSTEM) {

                     }
                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                         if (type == WallpaperManager.FLAG_LOCK || type == WallpaperManager.FLAG_SYSTEM) {
                             wallpaperManager.setBitmap(
                                 getWallpaperBitmap(resource),
                                 null,
                                 true,
                                 type
                             )
                         } else {
                             // 注意顺序，确保锁屏壁纸不会被重置
                             wallpaperManager.setBitmap(
                                 getWallpaperBitmap(resource),
                                 null,
                                 true,
                                 WallpaperManager.FLAG_SYSTEM
                             )
                             wallpaperManager.setBitmap(
                                 getWallpaperBitmap(resource),
                                 null,
                                 true,
                                 WallpaperManager.FLAG_LOCK
                             )
                         }
                     } else {
                         wallpaperManager.setBitmap(getWallpaperBitmap(resource))
                     }
                     withContext(Dispatchers.Main) {
                         ToastUtils.showShort(R.string.toast_set_wallpaper_success)
                     }
                 } catch (e: Exception) {
                     e.printStackTrace()
                     withContext(Dispatchers.Main) {
                         ToastUtils.showShort(R.string.toast_set_wallpaper_failed)
                     }
                 }
             }
         }
    }

    private fun getWallpaperBitmap(resource: Bitmap): Bitmap {

        return Bitmap.createScaledBitmap(
            resource,
            ScreenUtils.getScreenWidth(),
            ScreenUtils.getScreenHeight(),
            true
        )
    }


}
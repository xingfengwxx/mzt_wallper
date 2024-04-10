package com.hjq.demo.other

import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.bumptech.glide.util.Preconditions
import com.bumptech.glide.util.Util
import java.nio.ByteBuffer
import java.security.MessageDigest

/**
 * author : 王星星
 * date : 2024/4/10 11:37
 * email : 1099420259@qq.com
 * description : 解决设置CenterCrop后圆角失效的问题
 */
class RoundedCorners2(roundingRadius: Int, isCenterCrop: Boolean) :
    BitmapTransformation() {
    private val roundingRadius: Int
    private val isCenterCrop: Boolean

    /**
     * @param roundingRadius the corner radius (in device-specific pixels).
     * @throws IllegalArgumentException if rounding radius is 0 or less.
     */
    init {
        Preconditions.checkArgument(roundingRadius > 0, "roundingRadius must be greater than 0.")
        this.roundingRadius = roundingRadius
        this.isCenterCrop = isCenterCrop
    }

    override fun transform(
        pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int
    ): Bitmap {
        return if (isCenterCrop) {
            val bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight)
            TransformationUtils.roundedCorners(pool, bitmap, roundingRadius)
        } else {
            TransformationUtils.roundedCorners(pool, toTransform, roundingRadius)
        }
    }

    override fun equals(o: Any?): Boolean {
        if (o is RoundedCorners2) {
            return roundingRadius == o.roundingRadius
        }
        return false
    }

    override fun hashCode(): Int {
        return Util.hashCode(ID.hashCode(), Util.hashCode(roundingRadius))
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
        val radiusData = ByteBuffer.allocate(4).putInt(roundingRadius).array()
        messageDigest.update(radiusData)
    }

    companion object {
        private const val ID = "com.bumptech.glide.load.resource.bitmap.RoundedCorners"
        private val ID_BYTES = ID.toByteArray(CHARSET)
    }
}

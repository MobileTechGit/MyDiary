package com.mittal.mydiary.utils

import android.graphics.Bitmap

class BitmapUtils {

    fun scaleBitmap(bitmap: Bitmap, targetSize: Int): Bitmap? {
        var bHeight = bitmap.height
        var bWidth = bitmap.width
        val aspectRatio = bHeight.toDouble() / bWidth.toDouble()

        //Setting smallest side to target size
        if (aspectRatio > 1) {
            bWidth = targetSize
            bHeight = (bWidth * aspectRatio).toInt()
        } else {
            bHeight = targetSize
            bWidth = (bHeight / aspectRatio).toInt()
        }
        return Bitmap.createScaledBitmap(bitmap, bWidth, bHeight, true)
    }

}
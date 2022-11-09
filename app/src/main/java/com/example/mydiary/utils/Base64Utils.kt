package com.example.mydiary.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

class Base64Utils {

    fun encodeStringToBase64String(str: String): String? {
        return try {
            Base64.encodeToString(str.toByteArray(), Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun decodeStringFromBase64String(str: String?): String? {
        return try {
            val passwordDecoded = Base64.decode(str, 0)
            String(passwordDecoded)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun encodeToBase64StringJPEG(image: Bitmap): String? {
        return try {
            val baos = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val b = baos.toByteArray()
            Base64.encodeToString(b, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun encodeToBase64String(image: Bitmap): String? {
        return try {
            val baos = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val b = baos.toByteArray()
            Base64.encodeToString(b, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun decodeBase64ToBitmap(input: String?): Bitmap? {
        return try {
            val decodedByte = Base64.decode(input, 0)
            BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.size)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
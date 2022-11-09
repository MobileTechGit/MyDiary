package com.example.mydiary.utils

import android.app.Service
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class ApplicationUtils {

    fun hideSoftKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showSoftKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

}
package com.example.mydiary.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.ContextThemeWrapper
import android.view.Window
import androidx.appcompat.app.AlertDialog
import com.example.mydiary.R

object AlertDialogUtil {

    fun showAlertDialog(context: Context, mTitle: String, mMessage: String?) {
        val alertDialog = AlertDialog.Builder(context).create()

        val foregroundColorSpan = ForegroundColorSpan(Color.BLACK)
        val ssBuilder = SpannableStringBuilder(mTitle)
        ssBuilder.setSpan(foregroundColorSpan, 0, mTitle.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        alertDialog.run {
            setTitle(ssBuilder)
            setMessage(mMessage)
            setCanceledOnTouchOutside(false)
            setButton(DialogInterface.BUTTON_POSITIVE, "OK") { dialog, which ->
                dialog.dismiss()
            }
            show()
        }
    }

    fun showAlertDialogWhite(
        context: Context,
        mTitle: String?,
        mMessage: String?,
        listener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val alertDialog =
            AlertDialog.Builder(ContextThemeWrapper(context, R.style.AlertDialogWhiteTheme))
                .create()

        return alertDialog.run {
            setTitle(mTitle)
            setMessage(mMessage)
            setCanceledOnTouchOutside(false)
            setButton(
                DialogInterface.BUTTON_POSITIVE,
                context.getString(R.string.ok_text)
            ) { dialog, which ->
                dismiss()
                listener?.onClick(dialog, which)
            }
            show()
            this
        }
    }

    fun showAlertDialogWhite(
        context: Context,
        mTitle: String?,
        mMessage: String?,
        listener: DialogInterface.OnClickListener?,
        isCancelable: Boolean = true
    ): AlertDialog {
        val alertDialog =
            AlertDialog.Builder(ContextThemeWrapper(context, R.style.AlertDialogWhiteTheme))
                .create()

        return alertDialog.run {
            setTitle(mTitle)
            setMessage(mMessage)
            if (!isCancelable) {
                setCanceledOnTouchOutside(false)
                setCancelable(false)
            }
            setButton(
                DialogInterface.BUTTON_POSITIVE,
                context.getString(R.string.ok_text)
            ) { dialog, which ->
                dismiss()
                listener?.onClick(dialog, which)
            }
            show()
            this
        }
    }

    fun showAlertDialogWithTwoButtons(
        context: Context?,
        mTitle: String?,
        mMessage: String?,
        okButtonText: String?,
        cancelButtonText: String?,
        yesListener: DialogInterface.OnClickListener?,
        noListener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val alertDialog =
            AlertDialog.Builder(ContextThemeWrapper(context, R.style.AlertDialogWhiteTheme))
                .create()

        return alertDialog.run {
            setTitle(mTitle)
            setMessage(mMessage)
            setCanceledOnTouchOutside(false)
            setButton(DialogInterface.BUTTON_POSITIVE, okButtonText) { dialog, which ->
                dismiss()
                yesListener?.onClick(dialog, which)
            }
            setButton(DialogInterface.BUTTON_NEGATIVE, cancelButtonText) { dialog, which ->
                dismiss()
                noListener?.onClick(dialog, which)
            }
            show()
            this
        }
    }

    fun showFullScreenDialog(context: Context): Dialog {
        val fullScreenDialog = Dialog(context, android.R.style.Theme_Light)

        return fullScreenDialog.run {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
//            setContentView(R.layout.full_screen_dialog)
//            val locationLinkCv = findViewById<CardView>(R.id.location_link_cv)
            show()
            this
        }

    }

    fun hideDialog(dialog: Dialog?) {
        dialog?.let {
            if (dialog.isShowing) dialog.dismiss()
        }
    }
}
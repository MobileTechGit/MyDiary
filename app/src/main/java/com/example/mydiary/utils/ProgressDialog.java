package com.example.mydiary.utils;

import android.content.Context;

public class ProgressDialog {

    /*
    * ProgressDialog was deprecated in API level 26.
    * ProgressDialog is a modal dialog, which prevents the user from interacting with the app.
    * Instead of using this class, you should use a progress indicator like ProgressBar, which can be embedded in your app's UI.
    * Alternatively, you can use a notification to inform the user of the task's progress.
    * Alternatively, you can use AlertDialog as ProgressDialog
    * */
    public static android.app.ProgressDialog showProgressDialog(Context context, String message){
        android.app.ProgressDialog mDialog = new android.app.ProgressDialog(context);
        mDialog.setMessage(message);
        mDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
        mDialog.setCancelable(false);
        mDialog.show();
        return mDialog;
    }

}

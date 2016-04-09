package com.turing.base.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.turing.base.R;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-03-16  10:53.
 * @version 1.0
 * @desc
 */
public class AlertUtil {


    /**
     * 通过对话框展示信息, 包含一个按钮 “关闭”
     */

    public static void showDialogWithClose(Context context, String message) {

        new AlertDialog.Builder(context)
                .setIcon(R.drawable.flag_mark_red)
                .setTitle("提示信息")
                .setMessage(message)
                .setNegativeButton("关闭", null)
                .create()
                .show();
    }


    /**
     * 展示一个LENGTH_SHORT的Toast
     */

    public static void showToastShort(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    /**
     * 展示一个LENGTH_LONG的Toast
     */

    public static void showToastLong(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }




}

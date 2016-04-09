package com.turing.base.activity.webview.WebView_Js_inter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.turing.base.R;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-03-24  14:28.
 * @version 1.0
 *          自定义一个Object对象，js通过该类暴露的方法来调用Android
 */
public class MyObject {

    private Context context;

    public MyObject(Context context) {
        this.context = context;
    }

    /**
     * If you've set your targetSdkVersion to 17 or higher,
     * you must add the @JavascriptInterface annotation to any method
     * that you want available to your JavaScript (the method must also be public).
     * If you do not provide the annotation,
     * the method is not accessible by your web page
     * when running on Android 4.2 or higher.
     *
     * @param name
     */

    //将显示Toast和对话框的方法暴露给JS脚本调用
    @JavascriptInterface
    public void showToast(String name) {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void showDialog() {
        new AlertDialog.Builder(context)
                .setTitle("联系人列表").setIcon(R.drawable.flag_mark_blue)
                .setItems(new String[]{"111", "222", "333", "444", "555", "666"}, null)
                .setPositiveButton("确定", null)
                .create()
                .show();
    }
}

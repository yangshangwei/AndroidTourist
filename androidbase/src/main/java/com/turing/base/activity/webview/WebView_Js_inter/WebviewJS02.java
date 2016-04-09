package com.turing.base.activity.webview.WebView_Js_inter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.turing.base.R;

/**
 * HTML通过JS调用三种不同的对话框
 */
public class WebviewJS02 extends AppCompatActivity {

    private WebView wView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_js02);

        wView = (WebView) findViewById(R.id.wView);

        //获得WebSetting对象,支持js脚本,可访问文件,支持缩放,以及编码方式
        WebSettings webSettings = wView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        //设置WebChromeClient,处理网页中的各种js事件
        wView.setWebChromeClient(new MyWebChromeClient());
        wView.loadUrl("file:///android_asset/demo2.html");

    }


    //这里需要自定义一个类实现WebChromeClient类,并重写三种不同对话框的处理方法
    //分别重写onJsAlert,onJsConfirm,onJsPrompt方法
    class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message,
                                 final JsResult result) {
            //创建一个Builder来显示网页中的对话框
            new AlertDialog.Builder(WebviewJS02.this).setTitle("Alert对话框").setMessage(message)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                        }
                    }).setCancelable(false).show();
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
                                   final JsResult result) {
            new AlertDialog.Builder(WebviewJS02.this).setTitle("Confirm对话框").setMessage(message)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.cancel();
                        }
                    }).setCancelable(false).show();
            return true;
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message,
                                  String defaultValue, final JsPromptResult result) {
            //①获得一个LayoutInflater对象factory,加载指定布局成相应对象
            final LayoutInflater inflater = LayoutInflater.from(WebviewJS02.this);
            final View myview = inflater.inflate(R.layout.prompt_view, null);
            //设置TextView对应网页中的提示信息,edit设置来自于网页的默认文字
            ((TextView) myview.findViewById(R.id.text)).setText(message);
            ((EditText) myview.findViewById(R.id.edit)).setText(defaultValue);
            //定义对话框上的确定按钮
            new AlertDialog.Builder(WebviewJS02.this).setTitle("Prompt对话框").setView(myview)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //单击确定后取得输入的值,传给网页处理
                            String value = ((EditText) myview.findViewById(R.id.edit)).getText().toString();
                            result.confirm(value);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.cancel();
                        }
                    }).show();
            return true;
        }

    }
}

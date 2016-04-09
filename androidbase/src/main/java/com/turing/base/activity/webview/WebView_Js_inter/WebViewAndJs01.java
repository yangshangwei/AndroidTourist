package com.turing.base.activity.webview.WebView_Js_inter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.turing.base.R;

/**
 * 启用JavaScript支持，然后通过addJavascriptInterface暴露对象~
 */
public class WebViewAndJs01 extends AppCompatActivity {

    private WebView webView ;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_and_js01);


        webView = (WebView) findViewById(R.id.id_webview_JS);
        //加载assets目录下的html文件
        webView.loadUrl("file:///android_asset/demo1.html");

        WebSettings webSettings = webView.getSettings();
        //①设置WebView允许调用js
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        //②将object对象暴露给Js,调用addjavascriptInterface
        webView.addJavascriptInterface(new MyObject(WebViewAndJs01.this), "myObj");
    }
}

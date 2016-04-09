package com.turing.base.activity.webview;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.turing.base.R;

/**
 * 缩放
 */
public class WebViewZoomAct extends AppCompatActivity {

    private WebView webView;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_zoom);

        webView = (WebView) findViewById(R.id.id_webviewzoom);


        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//设置支持JavaScript
        // 缩放相关属性设置
        settings.setUseWideViewPort(true);//设定支持viewport
        settings.setLoadWithOverviewMode(true);   //自适应屏幕
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);// 隐藏缩放控件
        settings.setSupportZoom(true);//设定支持缩放


        webView.loadUrl("http://blog.csdn.net/yangshangwei");
        webView.setWebViewClient(new WebViewClient() {
            //在webview里打开新链接
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}

package com.turing.base.activity.webview;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.turing.base.R;

public class WebViewScrollChanged extends AppCompatActivity {

    private MyWebView myWebView;
    private Button toTopBtn;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_scroll_changed);

        // 初始化组件
        myWebView = (MyWebView) findViewById(R.id.id_webview);
        toTopBtn = (Button) findViewById(R.id.btn_icon);


        // 加载webView
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("http://sports.sina.com.cn/");

        myWebView.setWebViewClient(new WebViewClient() {
            //在webview里打开新链接
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });



        // 当页面发生滚动，显示Button
        myWebView.setOnScrollChangedCallback(new MyWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                if (dy > 0) {
                    toTopBtn.setVisibility(View.VISIBLE);
                } else {
                    toTopBtn.setVisibility(View.GONE);
                }
            }
        });

        toTopBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            @Override
            public void onClick(View v) {
                myWebView.setScrollY(0);
                toTopBtn.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

        }
    }
}

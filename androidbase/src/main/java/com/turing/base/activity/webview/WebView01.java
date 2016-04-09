package com.turing.base.activity.webview;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.base.R;

public class WebView01 extends AppCompatActivity implements View.OnClickListener {


    private Button btn_back;
    private TextView txt_title;
    private Button btn_top;
    private Button btn_refresh;
    private WebView webView;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view01);


        btn_back = (Button) findViewById(R.id.btn_back);
        txt_title = (TextView) findViewById(R.id.txt_title);
        btn_top = (Button) findViewById(R.id.btn_top);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);
        webView = (WebView) findViewById(R.id.wView);


        // 设置WebView属性，允许执行JS脚本,不然加载出来的网页很难看
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.baidu.com");
        webView.setWebChromeClient(new WebChromeClient() {
            //设置获取到的网站title
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                txt_title.setText(title);
            }
        });


        webView.setWebViewClient(new WebViewClient() {
            //在webview里打开新链接
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


        // 绑定监听事件
        btn_back.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
        btn_top.setOnClickListener(this);

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();          //关闭当前Activity
                break;
            case R.id.btn_refresh:
                webView.reload();    //刷新当前页面
                break;
            case R.id.btn_top:
                webView.setScrollY(0);   //滚动到顶部
                break;
        }
    }



    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

        }
    }

}

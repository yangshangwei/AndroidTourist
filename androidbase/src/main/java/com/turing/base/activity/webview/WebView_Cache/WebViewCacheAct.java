package com.turing.base.activity.webview.WebView_Cache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

/**
 * 开启缓存的功能，以及设置缓存模式以及缓存的数据的路径
 */
public class WebViewCacheAct extends AppCompatActivity {


    private WebView wView;
    private Button btn_clear_cache;
    private Button btn_refresh;
    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录
    private static final String URL = "http://blog.csdn.net/yangshangwei";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_cache);

        wView = (WebView) findViewById(R.id.wView);
        btn_clear_cache = (Button) findViewById(R.id.btn_clear_cache);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);
        wView.loadUrl(URL);
        wView.setWebViewClient(new WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = wView.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        settings.setDomStorageEnabled(true);
        // 开启database storage API功能
        settings.setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACHE_DIRNAME;
        LogUtils.e("cachePath:"+ cacheDirPath);
        // 设置数据库缓存路径
        settings.setAppCachePath(cacheDirPath);
        settings.setAppCacheEnabled(true);
        LogUtils.e("databasepath:"+ settings.getDatabasePath());

        // 清除缓存
        btn_clear_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wView.clearCache(true);
            }
        });
        // 刷新
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wView.reload();
            }
        });
    }

    //重写回退按钮的点击事件
    @Override
    public void onBackPressed() {
        if(wView.canGoBack()){
            wView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}

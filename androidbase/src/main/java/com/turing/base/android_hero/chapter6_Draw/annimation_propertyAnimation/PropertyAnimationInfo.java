package com.turing.base.android_hero.chapter6_Draw.annimation_propertyAnimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.turing.base.R;

public class PropertyAnimationInfo extends AppCompatActivity {


    private WebView mIdWvPaInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation_info);

        mIdWvPaInfo = (WebView) findViewById(R.id.id_wv_pa_info);

        mIdWvPaInfo.getSettings().setJavaScriptEnabled(true);
        mIdWvPaInfo.loadUrl("http://keeganlee.me/post/android/20151026");
        mIdWvPaInfo.setWebViewClient(new WebViewClient() {
            //在webview里打开新链接
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }



}

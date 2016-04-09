package com.turing.base.activity.webview.WebView_Download;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.DownloadListener;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

public class WebViewDownLoadWithSelfThread extends AppCompatActivity {

    private WebView webView;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_down_load_with_self_thread);

        webView = (WebView) findViewById(R.id.id_webview);

        webView.setWebViewClient(new WebViewClient() {
            // 设置WebView点击打开的网页在当前界面显示，而不是跳到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                LogUtils.e("onPageStarted");
                showProgressDialog();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                LogUtils.e("onPageFinished");
                closeProgressDialog();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                LogUtils.e("onReceivedError");
                closeProgressDialog();
            }
        });

        WebSettings settings = webView.getSettings();
        // 允许执行JS脚本
        settings.setJavaScriptEnabled(true);
        // 缩放相关属性设置
        settings.setUseWideViewPort(true);//设定支持viewport
        settings.setLoadWithOverviewMode(true); //自适应屏幕
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);// 隐藏缩放控件
        settings.setSupportZoom(true);//设定支持缩放


        // 载入URL
        webView.loadUrl("http://www.csdn.net/app/");
        // 然后，找到下载的地方，这个时候点击下载，调用自己写的下载程序
        //WebView默认没有开启文件下载的功能，
        // 如果要实现文件下载的功能，需要设置WebView的DownloadListener
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                // 自己实现的下载逻辑线程
                new Thread(new DownLoadThread(url)).start();
            }
        });
    }


    /**
     * 载入前加载
     */

    ProgressDialog mDialog;

    private void showProgressDialog() {

        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置风格为圆形进度条
        mDialog.setMessage("正在加载 ，请等待...");
        mDialog.setIndeterminate(false);//设置进度条是否为不明确
        mDialog.setCancelable(true);//设置进度条是否可以按退回键取消  默认true
        mDialog.setCanceledOnTouchOutside(false);//设置在点击Dialog外是否取消Dialog进度条  默认true
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                mDialog = null;
            }
        });
        mDialog.show();

    }

    private void closeProgressDialog() {
        mDialog.dismiss();
        mDialog = null;

    }
}
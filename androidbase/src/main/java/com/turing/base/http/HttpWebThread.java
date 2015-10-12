package com.turing.base.http;

import android.os.Handler;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015/10/9  23:00.
 * @version 1.0
 * @desc
 */
public class HttpWebThread extends Thread {

    private WebView webView;
    private String url;
    private Handler handler;


    public HttpWebThread(WebView webView, String url, Handler handler) {
        this.webView = webView;
        this.url = url;
        this.handler = handler;
    }

    @Override
    public void run() {
        // 根据URL加载web地址
        try {
            // 根据请求地址实例化URL
            URL httpUrl = new URL(url);
            // 根据url打开连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
            // 设置请求方法等属性
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5000);
            // 获取输入流
            InputStream ins = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            // 定义一个StringBuffer
            final StringBuffer sb = new StringBuffer();

            String line  ;
            // 循环读取缓冲区的数据
            while((line = reader.readLine()) != null){
                    sb.append(line);
            }
            // 通过handler更新主线程UI
            handler.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadData(sb.toString(), "text/html;charset=utf-8", null);
                }
            });


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}



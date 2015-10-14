package com.turing.base.http;

import android.os.Handler;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-14  22:31.
 * @version 1.0
 */

public class HttpClientThread_Get extends Thread {

    private String url;
    private Handler handler;
    private EditText et_response;

    public HttpClientThread_Get(String url, Handler handler, EditText response) {
        this.url = url;
        this.handler = handler;
        this.et_response = response;
    }

    @Override
    public void run() {
        doHttpClientGet();
    }

    /**
     * 使用23的SDK进行的编译，因为Android6.0中移除了相关对HttpClient的支持，所以这些方法都过时了。
     * 仅为演示用法，暂且忽略，可以用OKHttp代替。
     */
    private void doHttpClientGet() {
        // 创建HttpClient
        HttpClient httpClient = new DefaultHttpClient();
        // 创建HttpGet
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response;

        try {
            // 通过httpClient发送get请求
            response = httpClient.execute(httpGet);
            // 判断类型
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获取返回数据
                final String content = EntityUtils.toString(response.getEntity());
                LogUtils.d("返回内容：" + content);
                // 更新主线程UI
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        et_response.setText(content);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

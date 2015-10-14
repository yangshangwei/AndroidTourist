package com.turing.base.http;

import android.os.Handler;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015/10/13  00:06.
 * @version 1.0
 */
public class HttpThreadGetMethod extends Thread {

    private String name, age, url;
    private Handler handler;
    private EditText et_response;

    public HttpThreadGetMethod(String name, String age, String url, Handler handler, EditText response) {
        this.name = name;
        this.age = age;
        this.url = url;
        this.handler = handler;
        this.et_response = response;
    }

    @Override
    public void run() {
        doGet();
    }

    /**
     * Get请求方式   URL中可见参数
     */
    void doGet() {
        try {
            // GET 方式的参数存放在请求的url中，手机端请求的默认编码为utf-8，对于url中包含汉字的变量，要进行转码的操作，防止乱码
            url = url + "?name=" + URLEncoder.encode(name, "utf-8") + "&age=" + age;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        try {
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            // 获取服务端返回的输入流
            InputStream ins = connection.getInputStream();
            // 读取遍历
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            final StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            LogUtils.d("服务端返回:" + sb.toString());
            // 更新主线程UI
            handler.post(new Runnable() {
                @Override
                public void run() {
                    et_response.setText(sb.toString());
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

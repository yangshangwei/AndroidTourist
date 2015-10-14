package com.turing.base.http;

import android.os.Handler;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015/10/13  00:06.
 * @version 1.0
 */
public class HttpThreadPostMethod extends Thread {

    private String name, age, url;
    private Handler handler;
    private EditText et_response;

    public HttpThreadPostMethod(String name, String age, String url, Handler handler, EditText response) {
        this.name = name;
        this.age = age;
        this.url = url;
        this.handler = handler;
        this.et_response = response;
    }

    @Override
    public void run() {
        doPost();
    }


    /**
     * Post方法
     */
    void doPost() {
        try {
            // 列出手机操作系统的默认输出流  只是要看下Post方法中 是以何种编码提交的 --file.encoding=UTF-8
            Properties properties = System.getProperties();
            properties.list(System.out);

            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);

            // POST的方式是以form data的方式向服务端提交请求数据 ,首先获取输出流（以请求为出发点，向服务端发送数据，故为输出流）
            OutputStream out = connection.getOutputStream();
            // 封装请求中传递的参数
            String content = "name=" + name + "&age=" + age;
            //获取参数的直接 存入输出流
            out.write(content.getBytes());

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

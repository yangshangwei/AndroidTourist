package com.turing.base.http.httpClient;

import android.os.Handler;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-14  22:31.
 * @version 1.0
 */
public class HttpClientThread_Post extends Thread {


    private String url;
    private String name;
    private String age;
    private Handler handler;
    private EditText et_response;

    public HttpClientThread_Post(String url, String name, String age, Handler handler, EditText response) {
        this.url = url;
        this.name = name;
        this.age = age;
        this.handler = handler;
        this.et_response = response;
    }

    @Override
    public void run() {
        doHttpClientPost();
    }

    /**
     * httpClient 通过Post方式传递中文 会乱码
     */
    private void doHttpClientPost() {
        //创建HttpClient对象
        HttpClient httpclient = new DefaultHttpClient();
        // 创建HttpPost
        HttpPost httpPost = new HttpPost(url);

        // 保存请求过程中需要传递的实体参数 通过NameValuePair存储
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("name", name));
        nvps.add(new BasicNameValuePair("age", age));

        try {
            // 设置要发送的实体数据  指定编码格式 防止中文乱码
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
            // 发送post请求
            HttpResponse response = httpclient.execute(httpPost);
            // 判断状态
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获取返回数据
                final String content = EntityUtils.toString(response.getEntity());
                LogUtils.d("POST请求返回内容：" + content);
                // 主线程更新UI
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        et_response.setText(content);
                    }
                });
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

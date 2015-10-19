package com.turing.base.http.jsonHttp;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

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
 * @author Mr.Yang on 2015-10-19  20:01.
 * @version 1.0
 * @desc
 */
public class HttpGetJson extends Thread {

    private Context context;
    private String url;
    private RelativeLayout relativeLayout;
    private Handler handler;
    private LayoutInflater inflater;

    /**
     * 构造函数
     *
     * @param url
     * @param relativeLayout
     * @param handler
     */
    public HttpGetJson(Context context, String url, RelativeLayout relativeLayout, Handler handler) {
        this.context = context;
        this.url = url;
        this.relativeLayout = relativeLayout;
        this.handler = handler;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * 开启子线程 请求json
     */
    @Override
    public void run() {
        getJson();
    }

    private void getJson() {

        try {
            // 实例化URL
            URL httpUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            // 设置httpUrlConnection 属性
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5 * 1000);
            connection.setDoInput(true);
            // 获取输入流
            InputStream ins = connection.getInputStream();
            // 读取输入流
            final BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            final StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            LogUtils.d("服务端数据返回:" + sb.toString());

            // 通过handler更新主线程UI
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // 动态加载布局文件，增加到RelativeLayout中
                    View view = inflater.inflate(R.layout.activity_get_original_json, null);
                    TextView tv_orignalJsonview = (TextView) view.findViewById(R.id.id_tv_getJson);
                    tv_orignalJsonview.setText(sb.toString());
                    // 首先移除全部的组件 防止重复
                    relativeLayout.removeAllViews();
                    // 添加组件
                    relativeLayout.addView(view);
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

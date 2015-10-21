package com.turing.base.http.xmlHttp;

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
 * @author Mr.Yang on 2015-10-20  23:10.
 * @version 1.0
 * @desc
 */
public class HttpThreadGetXml extends Thread {

    private String url;
    private RelativeLayout relativeLayout;
    private Handler handler;
    private LayoutInflater inflater;
    private Context context;

    public HttpThreadGetXml(Context context, String url, RelativeLayout relativeLayout, Handler handler) {
        this.context = context;
        this.url = url;
        this.relativeLayout = relativeLayout;
        this.handler = handler;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public void run() {
        doGetXml();
    }

    /**
     * 当然了都是重复代码 ，为了巩固记忆，暂不抽取
     */
    private void doGetXml() {
        try {
            // 实例化
            URL httpUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            // 设置连接属性
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5 * 1000);
            connection.setDoInput(true);
            // 获取输入流
            InputStream ins = connection.getInputStream();
            // 读取输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            final StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            LogUtils.d("服务端返回的xml报文:" + sb.toString());

            // 通过Handler更新UI组件
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // 动态加载布局文件，增加到RelativeLayout中
                    View view = inflater.inflate(R.layout.activity_get_original_xml, null);
                    TextView tv_xml = (TextView) view.findViewById(R.id.id_tv_getXml);
                    tv_xml.setText(sb.toString());
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

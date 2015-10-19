package com.turing.base.http.jsonHttp;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.turing.base.R;
import com.turing.base.adapter.ShowJsonAdapter;
import com.turing.base.beans.ShowJsonBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-19  21:11.
 * @version 1.0
 * @desc
 */
public class HttpShowJson extends Thread {

    private Context context;
    private String url;
    private RelativeLayout relativeLayout;
    private Handler handler;
    private LayoutInflater inflater;


    private ListView listView ;
    private List<ShowJsonBean> data = new ArrayList<ShowJsonBean>();
    /**
     * 构造函数
     *
     * @param context
     * @param url
     * @param relativeLayout
     * @param handler
     */
    public HttpShowJson(Context context, String url, RelativeLayout relativeLayout, Handler handler) {
        this.context = context;
        this.url = url;
        this.relativeLayout = relativeLayout;
        this.handler = handler;
        // 实例化LayoutInflater，以方便动态加载布局为组件
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public void run() {
        doShowJsonInUniform();
    }

    private void doShowJsonInUniform() {


        try {
            // 实例化URL
            URL httpUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            // 设置connection的属性
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
            //


            // 通过Handler更新主线程的UI
            handler.post(new Runnable() {
                @Override
                public void run() {



                    // 将布局文件转换为视图
                    View view = HttpShowJson.this.inflater.inflate(R.layout.activity_show_original_json, null);

                    // 查找ListView
                    listView = (ListView)view.findViewById(R.id.id_lv_showJson);
                    // 实例化适配器
                    ShowJsonAdapter jsonAdapter = new ShowJsonAdapter(context);
                    // 设置适配器
                    listView.setAdapter(jsonAdapter);

                    //清除组件后,增加视图
                    HttpShowJson.this.relativeLayout.removeAllViews();
                    HttpShowJson.this.relativeLayout.addView(view);
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

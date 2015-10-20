package com.turing.base.http.jsonHttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-20  15:53.
 * @version 1.0
 */
public class HttpThreadGetPicByURL extends Thread {

    private ImageView imageView;
    private String url;
    private Handler handler;

    /**
     * 根据URL显示网络图片，一个imageview 一个url,由于这是在子线程，需要一个Hanlder来更新UI线程
     *
     * @param imageView
     * @param url
     * @param handler
     */
    public HttpThreadGetPicByURL(ImageView imageView, String url, Handler handler) {
        this.imageView = imageView;
        this.url = url;
        this.handler = handler;
    }

    @Override
    public void run() {
        LogUtils.d("开启子线程 run url：" + url );
        doGetPicByUrl();
    }

    /**
     * 通过URL获取网络图片
     */
    private void doGetPicByUrl() {
        try {
            // 实例化URL
            URL httpUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            // 设置连接属性
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5 * 1000);
            connection.setDoInput(true);
            // 获取输入流
            InputStream ins = connection.getInputStream();
            // 读取输入流 转换为图片
            final Bitmap bitmap = BitmapFactory.decodeStream(ins);
            // 通过Hanlder更新UI线程
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // 设置图片
                    imageView.setImageBitmap(bitmap);
                    LogUtils.d("setImageBitmap finish");
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

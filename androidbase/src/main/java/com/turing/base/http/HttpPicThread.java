package com.turing.base.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015/10/11  22:00.
 * @version 1.0
 * @desc
 */
public class HttpPicThread extends Thread {

    private ImageView imageView;
    private String url;
    private Handler handler;

    public HttpPicThread(ImageView imageView, String url, Handler hanlder) {
        this.imageView = imageView;
        this.url = url;
        this.handler = hanlder;
    }

    @Override
    public void run() {
        // 根据URL读取对应的图片 获取输入流
        try {
            //实例化URL
            URL httpUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            // 设置连接属性
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);

            // 获取输入流
            connection.setDoInput(true);
            InputStream ins = connection.getInputStream();

            // 将输入流解析为bitmap
            final Bitmap bitmap = BitmapFactory.decodeStream(ins);

            // 通过handler更新ＵＩ线程
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

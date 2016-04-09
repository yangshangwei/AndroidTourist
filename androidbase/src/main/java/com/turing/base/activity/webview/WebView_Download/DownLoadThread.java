package com.turing.base.activity.webview.WebView_Download;

import android.os.Environment;

import com.apkfuns.logutils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-03-24  17:47.
 * @version 1.0
 * @desc
 */
public class DownLoadThread implements Runnable {

    private String url;

    public DownLoadThread(String url) {
        this.url = url;
    }


    /**
     * 使用HttpURLConnection下载
     */
    @Override
    public void run() {
        // 处理下载业务逻辑
        LogUtils.e( "开始下载~~~~~" + url);
        InputStream in = null;
        FileOutputStream fout = null;
        try {
            // 实例化URl
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);


            in = conn.getInputStream();
            File downloadFile, sdFile;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                downloadFile = Environment.getExternalStorageDirectory();
                sdFile = new File(downloadFile, "dowload.apk");
                fout = new FileOutputStream(sdFile);
            }else{
                LogUtils.e("SD卡不存在或者不可读写");
            }
            // 缓冲区
            byte[] buffer = new byte[1024];
            int len;
            // 循环读取
            while ((len = in.read(buffer)) != -1) {
                fout.write(buffer, 0, len);
            }
            LogUtils.e("下载完毕~~~~");
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("下载异常~~~~");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.turing.base.http.uploadHttp;

import android.os.Handler;
import android.os.Message;

import com.apkfuns.logutils.LogUtils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-11-23  10:15.
 * @version 1.0
 * @desc
 */
public class UploadThread_HttpClient extends Thread {

    private String url ;
    private File file ;
    private Handler handler ;

    public UploadThread_HttpClient(String url,File file ,Handler handler) {
        this.url = url ;
        this.file = file ;
        this.handler = handler ;
    }


    @Override
    public void run() {

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        MultipartEntity  multipartEntity = new MultipartEntity();

        FileBody fileBody = new FileBody(file);
        // file 是服务端读取文件的 key     <input type="file" name="file" />  对应的
        multipartEntity.addPart("file", fileBody);

        httpPost.setEntity(multipartEntity);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 打印服务端返回的消息
                String retMessage = EntityUtils.toString(response.getEntity());
                LogUtils.d(retMessage);

                // 发送消息，更新主线程
                Message message = new Message();
                message.what = 2 ;
                message.obj = retMessage;
                handler.sendMessage(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

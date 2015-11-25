package com.turing.base.http.uploadHttp;

import android.os.Handler;
import android.os.Message;

import com.apkfuns.logutils.LogUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-11-19  21:44.
 * @version 1.0
 */
public class UploadThread_HttpURLConnection extends Thread {

    private String url;
    private File file;
    private Handler handler;


    // 边界标识 随机生成
    private String BOUNDARY = UUID.randomUUID().toString();
    // 前缀和换行符
    private String PREFIX = "--", LINE_END = "\r\n";
    // 内容类型
    private String CONTENT_TYPE = "multipart/form-data";

    /**
     * 构造函数
     *
     * @param url
     * @param file
     */
    public UploadThread_HttpURLConnection(String url, File file, Handler handler) {
        this.url = url;
        this.file = file;
        this.handler = handler;

    }

    @Override
    public void run() {

        try {
            // 实例化URL
            URL httpURL = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) httpURL.openConnection();
            /**设置connection属性 ，拼装HTTP请求协议**/
            //允许输入流 输出流  不使用缓存
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            // 以POST的方式提交
            connection.setRequestMethod("POST");
            // 设置超时时间
            connection.setReadTimeout(5 * 1000);
            connection.setConnectTimeout(5 * 1000);
            // 设置RequestProperty
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", CONTENT_TYPE + "; boundary=" + BOUNDARY);

            // 构造DataOutputStream
            DataOutputStream ds = new DataOutputStream(connection.getOutputStream());
            /** 模拟拼装请求正文头  在浏览器开发者工具中F12网络中可以查看
             -----------------------------7df2cd15150370  (这个BOUNDARY比请求头的多--，所以定义了个prefix)
             Content-Disposition: form-data; name="file"; filename="C:\Users\Mr.Yang\Desktop\girl.jpg"
             Content-Type: image/jpeg

             -----------------------------7df2cd15150370--
             **/

            // 模拟 -----------------------------7df2cd15150370
            ds.writeBytes(PREFIX + BOUNDARY + LINE_END);
            ds.writeBytes("Content-Disposition: form-data; "
                    + "name=\"file\";filename=\"" + file.getName() + "\"" + LINE_END);
            ds.writeBytes(LINE_END);

            // 构建要上传的文件的FileInputStream
            FileInputStream fis = new FileInputStream(file);
            // 设置每次写入1024 * 4 bytes
            byte[] buffer = new byte[1024 * 4];
            int len = -1;
            // 从文件读取数据至缓冲区
            while ((len = fis.read(buffer)) != -1) {
                // 将资料写入DataOutputStream中
                ds.write(buffer, 0, len);
            }
            // 模拟换行符
            ds.writeBytes(LINE_END);
            // 模拟结尾的信息
            ds.writeBytes(PREFIX + BOUNDARY + PREFIX + LINE_END);
            // Flushes this stream to ensure all pending data is sent out to the target
            ds.flush();


            /** 接收服务端的返回信息**/
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            LogUtils.d("服务端返回：" + sb.toString());

            // 关闭流
            if (fis != null) {
                fis.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (ds != null) {
                ds.close();
            }

            // 发送消息，在主线程更新提示信息
            Message message = new Message();
            message.what = 1;
            message.obj = sb.toString();
            handler.sendMessage(message);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

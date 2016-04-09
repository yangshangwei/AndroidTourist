package com.turing.base.activity.socket.baseuse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.turing.base.R;
import com.turing.base.utils.AlertUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Step 1：创建Socket对象，指明需要链接的服务器的地址和端号
 Step 2：链接建立后，通过输出流向服务器发送请求信息
 Step 3：通过输出流获取服务器响应的信息
 Step 4：关闭相关资源

 服务端Java工程：D:\workspace\ws-java-base\SocketServer
 */
public class SocketClientAct extends AppCompatActivity implements View.OnClickListener {

    private Button socketSend ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);

        socketSend = (Button) findViewById(R.id.id_btn_sendMsg);
        socketSend.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        AlertUtil.showToastShort(this,"观察服务端日志~");
        //Android不允许在主线程(UI线程)中做网络操作，
        // 所以这里需要我们自己 另开一个线程来连接Socket！
        new Thread() {
            @Override
            public void run() {
                try {
                    acceptServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 连接ServerSocket发送消息
     */
    private void acceptServer() throws  IOException{

        //1.创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket("192.168.56.1", 9999);
        //2.获取输出流，向服务器端发送信息
        OutputStream os = socket.getOutputStream();//字节输出流
        PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
        //获取客户端的IP地址
        InetAddress address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        // 将这个信息发送给服务端
        pw.write("客户端：~" + ip + "~ 接入服务器！！");
        pw.flush();
        socket.shutdownOutput();//关闭输出流
        socket.close();
    }
}

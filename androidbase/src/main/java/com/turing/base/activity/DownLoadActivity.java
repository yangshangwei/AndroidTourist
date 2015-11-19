package com.turing.base.activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;
import com.turing.base.http.downHttp.DownLoad;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-11-18  20:57.
 * @version 1.0
 *          开启多线程下载完文件后，更新textView 提示用户现在成功。
 */

@Fullscreen
@EActivity(R.layout.activity_download_tip)
public class DownLoadActivity extends Activity {

    @ViewById(R.id.id_btn_downLoad)
    Button downLoadBtn;

    @ViewById(R.id.id_tv_downLoad_tip)
    TextView tipTv ;

    private String url = "http://192.168.1.101:8080/HttpService/girl.jpg";

    // 定义handler 接收子线程发送来的消息。
    // 因为有3个线程下载，所以要收到了全部的线程发送的消息后，才说明文件下载完成，才可以更新提示信息
    private Handler handler = new Handler() {

        int count = 0 ;

        @Override
        public void handleMessage(Message msg) {
            int result = msg.what ;
            LogUtils.d("result:" + result);
            count += result;
            LogUtils.d("count:" + count);
            // 当三个线程均返回时
            if(count==3){
                tipTv.setText("downLoad success");
            }

        }
    };

    @Click(R.id.id_btn_downLoad)
    public void downLoadFile() {
        LogUtils.d("多线程下载开始.....");
        //应避免在主线程中直接访问网络，如需，请开启线程
        new Thread() {
            @Override
            public void run() {
                DownLoad downLoad = new DownLoad();
                downLoad.downLoadFile(url,handler);
            }
        }.start();


    }
}

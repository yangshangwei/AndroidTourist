package com.turing.base.activity.broadcastDemo;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.turing.base.R;

public class SmsMessageAct extends Activity implements View.OnClickListener {

    private EditText et_phone, et_msg;
    private Button btn_registerReceiver, btn_unRegisterReceiver;


    private ShortMessageReceiver shortMessageReceiver;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String message = (String) msg.obj;

                    String pre = "源号码:";
                    String suf = ",内容:";
                    et_phone.setText(message.substring((message.indexOf(pre) + pre.length()), message.indexOf(suf)));

                    et_msg.setText(message.substring(message.indexOf(suf) + suf.length(), message.length()));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_message);

        initView();
        initEvents();

        // 创建广播接收器的对象
        shortMessageReceiver = new ShortMessageReceiver(handler);

    }

    private void initEvents() {

        btn_registerReceiver.setOnClickListener(this);
        btn_unRegisterReceiver.setOnClickListener(this);
    }


    private void initView() {
        et_phone = (EditText) findViewById(R.id.id_et_phone);
        et_msg = (EditText) findViewById(R.id.id_et_msg);
        btn_registerReceiver = (Button) findViewById(R.id.id_btn_registerReceiver);
        btn_unRegisterReceiver = (Button) findViewById(R.id.id_btn_unRegisterReceiver);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_registerReceiver:
                registerReceiver(shortMessageReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
                Toast.makeText(this, "动态注册广播接收器成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_unRegisterReceiver:
                unregisterReceiver(shortMessageReceiver);
                Toast.makeText(this, "动态注销短信广播接收器over", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (shortMessageReceiver != null) {
            unregisterReceiver(shortMessageReceiver);
            Toast.makeText(this, "Activity onPause ,注销短信广播接收器over", Toast.LENGTH_SHORT).show();
        }

    }
}

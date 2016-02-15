package com.turing.base.activity.service.aidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.base.R;

public class AIDLActivityDemo extends AppCompatActivity implements View.OnClickListener {

    private Button btn_bindAIDL, btn_callAIDL;
    private TextView tv_aidlResult;

    private IMyService myService  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlactivity_demo);

        initView();
        initEvents();
    }


    /**
     * 初始化组件
     */
    private void initView() {
        btn_bindAIDL = (Button) findViewById(R.id.id_btn_aidl_bind);

        btn_callAIDL = (Button) findViewById(R.id.id_btn_aidl_call);
        // 现将调用AIDL按钮设置为灰色禁用，等初始化AIDL服务之后在设置为可点击
        btn_callAIDL.setEnabled(false);
        tv_aidlResult = (TextView) findViewById(R.id.id_tv_aidl_result);
    }

    /**
     * 按钮注册监听事件
     */
    private void initEvents() {
        btn_bindAIDL.setOnClickListener(this);
        btn_callAIDL.setOnClickListener(this);
        tv_aidlResult.setOnClickListener(this);
    }


    /**
     * 按钮监听事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_aidl_bind:
                bindService(new Intent("com.turing.base.activity.service.aidl.AIDLService"),
                        serviceConnection,
                        Service.BIND_AUTO_CREATE);
                break;
            case R.id.id_btn_aidl_call:
                // 调用服务端getValue方法
                try {
                    tv_aidlResult.setText(myService.getValue().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.id_tv_aidl_result:
                Toast.makeText(this,"闹着玩",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获取服务对象
            myService = IMyService.Stub.asInterface(service);
            btn_callAIDL.setEnabled(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    } ;
}

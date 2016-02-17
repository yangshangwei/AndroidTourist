package com.turing.base.activity.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

public class ServiceBindAct extends Activity implements View.OnClickListener {


    private Button  bindServiceBtn, unBindServiceBtn;
    private Intent serviceIntent ;
    private MyService_BindService myServiceBindService;

    private ServiceConnection serviceConnection = new ServiceConnection() {

        /**
         * 成功连接服务后,改方法被调用，在该方法中可以获得MyService_BindService对象
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获得MyService  向下转型
            myServiceBindService = ((MyService_BindService.MySBinder)service).getMyService();
            Toast.makeText(ServiceBindAct.this,"Service Connected",Toast.LENGTH_SHORT).show();
        }


        /**
         * Android系统在同service的连接意外丢失时调用这个．比如当service崩溃了或被强杀了
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            myServiceBindService = null;
            Toast.makeText(ServiceBindAct.this,"Service Faield",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_bind);
        setTitle("绑定Activity和Service");

        LogUtils.d("ServiceLifeCycle Activity onCreate, Thread id:" + Thread.currentThread().getId());

        // 初始化组件
        bindServiceBtn = (Button) findViewById(R.id.BindService);
        unBindServiceBtn = (Button) findViewById(R.id.unBindService);
        // 注册监听事件
        bindServiceBtn.setOnClickListener(this);
        unBindServiceBtn.setOnClickListener(this);


        serviceIntent = new Intent(ServiceBindAct.this,MyService_BindService.class);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BindService:
                bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unBindService:
                unbindService(serviceConnection);
                break;
            default:
                break;
        }
    }
}

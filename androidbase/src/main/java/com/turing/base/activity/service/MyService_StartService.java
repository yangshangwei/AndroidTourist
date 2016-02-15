package com.turing.base.activity.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.apkfuns.logutils.LogUtils;


/**
 *MyService_StartService 是一个服务类，必须从android.app.Service类继承。
 */
public class MyService_StartService extends Service {
    public MyService_StartService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null ;
    }

    /**
     * Service第一次被创建的时候调用改方法，只会调用一次。
     */
    @Override
    public void onCreate() {
        LogUtils.d("MyService_StartService  onCreate");
        super.onCreate();
    }

    /**
     * Service 开始的时候调用该方法。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.d("MyService_StartService  onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Service被销毁时调用该方法，只会调用一次。
     */
    @Override
    public void onDestroy() {
        LogUtils.d("MyService_StartService  onDestroy");
        super.onDestroy();
    }
}

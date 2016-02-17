package com.turing.base.activity.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.apkfuns.logutils.LogUtils;

public class MyService_BindService extends Service {

    private MySBinder mySBinder = new MySBinder();

    public MyService_BindService() {
    }

    @Override
    public void onCreate() {
        LogUtils.d("onCreate");
        super.onCreate();
    }

    /**
     *  返回Binder对象
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.d("onBind");
        return mySBinder ;
    }


    @Override
    public void onRebind(Intent intent) {
        LogUtils.d("onRebind");
        super.onRebind(intent);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.d("onUnbind");
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        LogUtils.d("onDestory");
        super.onDestroy();
    }


    /**
     * 定义MySBinder类，继承自Binder类，用于获取MyService_BindService类
     */
    class MySBinder extends Binder{

        public MyService_BindService getMyService(){
            return  MyService_BindService.this ;
        }
    }
}

package com.turing.base.activity.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.apkfuns.logutils.LogUtils;

public class MyService extends Service {

    private MyBinder myBinder = new MyBinder();

    public MyService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.d("MyService onBind,Thread ID: " + Thread.currentThread().getId());
        return myBinder;
    }

    /**
     * 重新绑定时 ，调用此方法
     *
     * @param intent
     */
    @Override
    public void onRebind(Intent intent) {
        LogUtils.d("MyService onRebind,Thread ID: " + Thread.currentThread().getId());
        super.onRebind(intent);
    }

    /**
     * 解除绑定时调用该方法
     *
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.d("MyService onUnbind,Thread ID: " + Thread.currentThread().getId());
        return super.onUnbind(intent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("service onCreate,Thread ID: " + Thread.currentThread().getId());
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e("service onStartCommand,Thread ID: " + Thread.currentThread().getId());
        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("service onDestory,Thread ID: " + Thread.currentThread().getId());
    }


    /**
     * 定义MyBinder类，用于获取MyService对象
     */
    class MyBinder extends Binder {

        MyService getMyService() {
            return MyService.this;
        }
    }
}

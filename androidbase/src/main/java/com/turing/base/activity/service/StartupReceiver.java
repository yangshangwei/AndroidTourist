package com.turing.base.activity.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.apkfuns.logutils.LogUtils;

/**
 * 只要完成两项工作： 启动服务 和 显示一个Activity提示服务启动成功(主题设置为Dialog的形式)
 */
public class StartupReceiver extends BroadcastReceiver {
    public StartupReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        LogUtils.e("StartupReceiver  onReceive");

        // 如果是开机启动的Action
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){

            // 启动Activity
            Intent activityIntent = new Intent(context,BootCompletedMessageAct.class);
            // 想要在Service中启动Activity，必须设置如下标志
            activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(activityIntent);

            //启动服务
            Intent serviceIntent = new Intent(context,StartupService.class);
            context.startService(serviceIntent);




        }

    }
}

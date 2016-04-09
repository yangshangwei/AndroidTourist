package com.turing.base.activity.broadcastDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenOnOffReceiver extends BroadcastReceiver {
    public ScreenOnOffReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        // 接收屏幕唤醒状态的广播
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            Log.d("screen", "ok");
        } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {

            Log.d("screen", "off");
        }
    }

}

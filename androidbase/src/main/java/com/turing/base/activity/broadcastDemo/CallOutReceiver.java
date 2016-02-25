package com.turing.base.activity.broadcastDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CallOutReceiver extends BroadcastReceiver {
    public CallOutReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        // 获取去电号码
        String outComingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        // showToast
        Toast.makeText(context, "去电......" + outComingNumber, Toast.LENGTH_SHORT).show();
        //CallInReceiver.showPopupWindowToast(context, outComingNumber);
    }
}

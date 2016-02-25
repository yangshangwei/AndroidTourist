package com.turing.base.activity.broadcastDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.turing.base.R;

public class BatteryInfoAct extends AppCompatActivity {

    private TextView tv_batteryInfo;
    private BroadcastReceiver batteryChangedReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                //level标识当前电量的值
                int level = intent.getIntExtra("level", 0);
                // scale标识电量的总刻度
                int scale = intent.getIntExtra("scale", 100);
                // 将当前电量换算成百分比的形式
                tv_batteryInfo.setText("电池用量：" + (level * 100 / scale) + "%");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_info);

        tv_batteryInfo = (TextView) findViewById(R.id.id_tv_battery);

        // 注册广播
        registerReceiver(batteryChangedReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (batteryChangedReceiver != null) {
            unregisterReceiver(batteryChangedReceiver);
        }
    }
}

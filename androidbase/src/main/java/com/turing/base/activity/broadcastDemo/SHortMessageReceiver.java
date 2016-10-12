package com.turing.base.activity.broadcastDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;

import java.util.Set;

public class ShortMessageReceiver extends BroadcastReceiver {

    private Handler handler ;

    public ShortMessageReceiver() {
    }

    public ShortMessageReceiver(Handler handler) {
        this.handler = handler ;
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();

            //查看收的广播包含哪些数据
            for (String key : keys) {
                LogUtils.e("bundele中的数据" + key);
            }

            // 获取收到的短信
            Object[] objArray = (Object[]) bundle.get("pdus");

            String message = parseMessageFromRawData(objArray);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            Message msg = new Message();
            msg.what = 1 ;
            msg.obj = message;
            handler.sendMessage(msg);
            /**
            // 定义封装短信内容的SmsMessage对象数组
            SmsMessage[] message = new SmsMessage[objArray.length];
            // 循环处理收到的所有短信
            for (int i = 0; i < objArray.length; i++) {
                // 将每条短信数据转换成SendMessage对象
                message[i] = SmsMessage.createFromPdu((byte[]) objArray[i]);
                // 获取发送短信的电话号码和短信内容
                String messageInfo = "手机号：" + message[i].getOriginatingAddress() + "\n";
                messageInfo += "短信内容：" + message[i].getDisplayMessageBody();

                //做个简单的展示
                Toast.makeText(context, messageInfo, Toast.LENGTH_SHORT).show();
            }
             **/
        }
    }

    public  String  parseMessageFromRawData(Object[] pdus) {

        if (pdus == null) return null;

        try {
            StringBuilder message = new StringBuilder();
            for (Object pdu : pdus) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                if (smsMessage == null) continue;
                message.append("源号码:" + smsMessage.getOriginatingAddress() + ",内容:" +smsMessage.getDisplayMessageBody());
            }
            return message.toString();
        } catch (Exception e) {
            LogUtils.e( "SMSBroadcastReceiver read sms failed", e);
        } catch (OutOfMemoryError oom) {
            LogUtils.e( "SMSBroadcastReceiver caused OOM =_=!", oom);
            //为了避免后续操作出现问题，gc一下
            System.gc();
            System.gc();
        }
        return null;
    }



}

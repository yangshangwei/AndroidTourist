package com.turing.base.activity.broadcastDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.base.R;

public class CallInReceiver extends BroadcastReceiver {

    private static Object obj;

    public CallInReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        // 获取电话管理服务，以便获取电话的状态
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        switch (telephonyManager.getCallState()) {
            case TelephonyManager.CALL_STATE_RINGING: // 响铃
                String incomingNumber = intent.getStringExtra("incoming_number");
                Toast.makeText(context, "电话响铃中......", Toast.LENGTH_SHORT).show();
                //showPopupWindowToast(context,incomingNumber);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK: //接听电话
                Toast.makeText(context, "电话已接通......", Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.CALL_STATE_IDLE:// 挂断电话
                Toast.makeText(context, "挂断电话......", Toast.LENGTH_SHORT).show();
                //closeToast();
            default:
                break;
        }
    }


    /**
     * 使用反射，此Toast不会关闭
     *
     * @param context
     * @param msg
     */
//    public static void showToast(Context context, String msg) {
//        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
//        try {
//            Field field = toast.getClass().getDeclaredField("mTN");
//            field.setAccessible(true);
//            obj = field.get(toast);
//            Method method = obj.getClass().getDeclaredMethod("show", null);
//            method.invoke(obj, null);
//        } catch (Exception e) {
//        }
//
//    }

    /**
     * 通过此方法关闭那个不可关闭的Toast
     */
//    public static void closeToast() {
//        if (obj != null) {
//            try {
//                Method method = obj.getClass().getDeclaredMethod("hide", null);
//                method.invoke(obj, null);
//            } catch (Exception e) {
//            }
//
//        }
//    }


    public static void showPopupWindowToast(Context context, String incomingNumber) {

        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_popupwd_toast, null);
        TextView textView = (TextView)view.findViewById(R.id.tvMsg);
        textView.setText("电话号码：" + incomingNumber);

        final PopupWindow popupWindow = new PopupWindow(view,500 ,100);
        popupWindow.setTouchable(false);
        popupWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL,20 ,0);


        // 设置定时器，5秒后自动关闭
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        } , 5*1000);
    }

}

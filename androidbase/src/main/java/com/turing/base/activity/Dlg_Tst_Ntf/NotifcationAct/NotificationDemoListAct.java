package com.turing.base.activity.Dlg_Tst_Ntf.NotifcationAct;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.SimpleAdapter;

import com.turing.base.MainActivity_;
import com.turing.base.R;
import com.turing.base.activity.Dlg_Tst_Ntf.ToastAct.ToastDemoListAct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 常用字段：
 * contentIntent  设置PendingIntent对象，点击时发送该Intent
 * defaults 添加默认效果
 * flags 设置flag位，例如FLAG_NO_CLEAR等
 * icon 设置图标
 * sound 设置声音
 * tickerText 显示在状态栏中的文字
 * when 发送此通知的时间戳
 */

public class NotificationDemoListAct extends ListActivity {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "在状态栏显示通知信息",
            "清除指定的Notification-NotificationManager.cancel(id)",
            "清除全部的Notification-NotificationManager.cancelAll()",
            "清除Notification后触发的善后工作-Notification.deleteIntent变量",
            "永久存在的Notification--Notification.flag",
            "自定义Notification-RemoteViews"
    };


    public NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initEvents();
    }


    private void initView() {

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                simulateData(arr),
                R.layout.activity_scroll_view_list_item,
                new String[]{"img", "info"},
                new int[]{R.id.id_sv_iv, R.id.id_sv_tv});

        setListAdapter(simpleAdapter);
    }


    private void initEvents() {

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:// 在状态栏显示通知信息
                        showNotification();
                        break;
                    case 1:// 清除指定的Notification-cancel(id)
                        cancelNotification(R.drawable.flag_mark_blue);
                        break;
                    case 2:// 清除全部的Notification-cancelAll()
                        cancelNotificationAll();
                        break;
                    case 3:// 清除Notification后触发的善后工作-Notification.deleteIntent变量
                        notificationDeleteIntent();
                        break;
                    case 4:// 永久存在的Notification
                        permanentNotification();
                        break;
                    case 5:// 自定义Notification
                        customNotification();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void notificationDeleteIntent() {
        // 通过getSystemservice获取NotificationManager
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // PendingIntent --getAct ,getBrc ,getSev等等
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity_.class), 0);


        // Notification
        Notification notification = NotificationUtil.createNotification(this, pendingIntent, "Title", "text", R.drawable.tag_blue);
        notification.when = System.currentTimeMillis();
        notification.tickerText = "清除Notificaiton的善后工作";

        // 清除通知,触发的操作，这里将清除Notification触发的deleteIntent设置为跳转到ToastDemoListAct，当然了也可以启动广播 服务等等
        PendingIntent deleteIntent = PendingIntent.getActivity(this, 0, new Intent(this, ToastDemoListAct.class), 0);
        notification.deleteIntent = deleteIntent;

        notificationManager.notify(R.drawable.tag_blue, notification);

    }

    /**
     *
     */
    private void cancelNotificationAll() {
        if(notificationManager != null){
            notificationManager.cancelAll();
        }

    }

    /**
     * 全部兼容
     * http://blog.csdn.net/yangshangwei/article/details/50688221
     */
    private void showNotification() {

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, getIntent(), 0);
        // 工具类判断版本，通过不同的方式获取Notification
        Notification notification = NotificationUtil.createNotification(this, pendingIntent, "您有新消息", "消息内容", R.drawable.flag_mark_blue);
        notification.tickerText = "我是提示通知时的文字内容";
        notification.when = System.currentTimeMillis();
        // 使用默认的声音
        notification.defaults = Notification.DEFAULT_SOUND;
        // 使用默认的震动 需要添加uses-permission  android.permission.VIBRATE
        notification.defaults = Notification.DEFAULT_VIBRATE;
        // 使用默认的Light
        notification.defaults = Notification.DEFAULT_LIGHTS;
        // 所有的都是用默认值
        notification.defaults = Notification.DEFAULT_ALL;

        notificationManager.notify(R.drawable.flag_mark_blue, notification);


        // 5S后，执行取消的方法，即5S后 自动清除该通知栏 ,根据需求考虑是否需要这样
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                notificationManager.cancel(R.drawable.flag_mark_blue);
//            }
//        },5*1000);

    }

    /**
     * 清除某个消息，
     *
     * @param id Notification的id
     */
    private void cancelNotification(int id) {

        notificationManager.cancel(id);
    }


    /**
     * FLAG_SHOW_LIGHTS  //控制闪光
     * <p/>
     * FLAG_ONGOING_EVENT  //将flag设置为这个属性那么通知就会像QQ一样一直在状态栏显示
     * <p/>
     * FLAG_INSISTENT   //重复发出声音，直到用户响应此通知
     * <p/>
     * FLAG_ONLY_ALERT_ONCE  //标记声音或者震动一次
     * <p/>
     * FLAG_AUTO_CANCEL  //在通知栏上点击此通知后自动清除此通知
     * <p/>
     * FLAG_NO_CLEAR    //将flag设置为这个属性那么通知栏的那个清楚按钮就不会出现
     * <p/>
     * FLAG_FOREGROUND_SERVICE//前台服务标记
     * <p/>
     * https://developer.android.com/intl/zh-cn/reference/android/app/Notification.html#FLAG_GROUP_SUMMARY
     * <p/>
     * FLAG_GROUP_SUMMARY
     * <p/>
     * FLAG_LOCAL_ONLY
     */
    private void permanentNotification() {

        // 通过getSystemService获取NotificationManager
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, getIntent(), 0);

        // Notification
        Notification notification = NotificationUtil.createNotification(this, pendingIntent, "Title", "Content", R.drawable.face);
        notification.tickerText = "清除不掉的Notification";
        notification.when = System.currentTimeMillis();

        notification.flags = Notification.FLAG_NO_CLEAR;


        // 展示Notification
        notificationManager.notify(R.drawable.face, notification);


    }

    private void customNotification() {

        // 通过getSystemService()获取NotificationManager
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,getIntent(),0);

        // Notification
        Notification notification = NotificationUtil.createNotification(this,pendingIntent,"Title","text",R.drawable.flag_mark_yellow);

        // 设置属性...
        notification.tickerText="自定义Notification";
        notification.when = System.currentTimeMillis();

        //自定义Notification布局
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.activity_custom_notification);
        remoteViews.setTextViewText(R.id.textview,"666666666");
        notification.contentView = remoteViews ;

        // notifiy
        notificationManager.notify(R.drawable.flag_mark_yellow,notification);

    }

    /**
     * 将数组转换为List<Map<String,Object>>
     *
     * @param arr
     * @return
     */
    private List<Map<String, Object>> simulateData(String[] arr) {

        List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < arr.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("img", R.drawable.tag_blue);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }

}

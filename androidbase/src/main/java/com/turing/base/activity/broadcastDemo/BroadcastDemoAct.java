package com.turing.base.activity.broadcastDemo;

import android.app.ListActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.turing.base.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BroadcastDemoAct extends ListActivity implements AdapterView.OnItemClickListener {


    // ListView中要显示的item
    private String[] arr = new String[]{
            "短信拦截",
            "用代码注册广播接收器",
            "广播接收器的优先级",
            "来去电拦截",
            "截获屏幕休眠与唤醒",
            "开机自动运行",
            "显示手机电池的当前电量",
            "发送广播",
            "验证广播接收器是否注册"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initEvents();
    }


    private void initView() {
        // Adapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, simulateData(arr),
                R.layout.activity_scroll_view_list_item,
                new String[]{"img", "info"},
                new int[]{R.id.id_sv_iv, R.id.id_sv_tv});
        // 设置Adapter
        setListAdapter(simpleAdapter);

    }

    private void initEvents() {
        // 获得ListView
        ListView listView = getListView();
        // 设置监听事件
        listView.setOnItemClickListener(this);
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
            item.put("img", R.drawable.flag_mark_yellow);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: // 短信拦截
                Toast.makeText(this,"静态注册广播接收器",Toast.LENGTH_SHORT).show();
                break;
            case 1: // 用代码注册广播接收器
                Toast.makeText(this,"用代码注册广播接收器",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SmsMessageAct.class));
                break;
            case 2:// 广播接收器的优先级
                String info = "通过<intent-filter>标签的android:priority属性来设置，值越大，优先级越高";
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                break;
            case 3:// 来去电拦截
                Toast.makeText(this,"静态的方式注册的广播接收器",Toast.LENGTH_SHORT).show();
                break;
            case 4:// 截获屏幕休眠与唤醒
                Toast.makeText(this,"只能以动态的方式注册广播接收器",Toast.LENGTH_SHORT).show();
                screenOnOff();
                break;
            case 5:// 开机自动运行
                Toast.makeText(this,"android.intent.action.BOOT_COMPLETED",Toast.LENGTH_SHORT).show();
                break;
            case 6:// 显示手机电池的当前电量
                startActivity(new Intent(this, BatteryInfoAct.class));
                break;
            case 7:// 发送广播
                break;
            case 8: // 验证广播接收器是否注册
                validateReceiver("android.intent.action.NEW_OUTGOING_CALL");
                break;
            default:
                break;
        }
    }

    private void validateReceiver(String actionName) {
        // 获取PackageManager
        PackageManager packageManager = getPackageManager();
        // 指定要查询广播的动作
        Intent intent = new Intent(actionName);
        // 返回已查到的广播接收器集合,如果没有符合条件的广播,List长度为0
        List<ResolveInfo> resolveInfos = packageManager.queryBroadcastReceivers(intent,PackageManager.GET_INTENT_FILTERS);
        // 显示查询到的广播的数量
        Toast.makeText(this,"已发现" + resolveInfos.size() + "个接收去电广播的接收器" ,Toast.LENGTH_SHORT).show();
        StringBuilder sb = new StringBuilder();
        if(resolveInfos.size() > 0 ){
            for (ResolveInfo resolveInfo : resolveInfos){
                sb.append(resolveInfo.toString());
            }
        }
        Toast.makeText(this,sb.toString() ,Toast.LENGTH_SHORT).show();
    }


    private void screenOnOff() {

        ScreenOnOffReceiver screenOnOffReceiver = new ScreenOnOffReceiver();
        IntentFilter intentFilter = new IntentFilter();
        // 设置屏幕唤醒广播的动作
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        // 设置屏幕休眠广播的动作
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        // 注册
        registerReceiver(screenOnOffReceiver,intentFilter);

    }
}

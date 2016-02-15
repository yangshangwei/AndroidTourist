package com.turing.base.activity.service;

import android.app.ActivityManager;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.turing.base.R;
import com.turing.base.activity.service.aidl.AIDLActivityDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 继承ListActivity
 */
public class ServiceDemoAct extends ListActivity {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "Service生命周期",
            "绑定Activity和Service",
            "开机启动Service",
            "判断Service是否注册",
            "判断Service是否已经开始",
            "通过startService播放背景音乐",
            "=========================",
            "跨进程访问(AIDL服务)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initEvents();
    }


    private void initView() {

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, simulateData(arr),
                R.layout.activity_scroll_view_list_item,
                new String[]{"img", "info"},
                new int[]{R.id.id_sv_iv, R.id.id_sv_tv});

        setListAdapter(simpleAdapter);

    }


    private void initEvents() {

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // Service生命周期
                        startActivity(new Intent(ServiceDemoAct.this, ServiceLifeCycle_.class));
                        break;
                    case 1: // 绑定Activity和Service
                        startActivity(new Intent(ServiceDemoAct.this, ServiceBindAct.class));
                        break;
                    case 2: // 开机启动Service
                        Toast.makeText(ServiceDemoAct.this, "通过广播监听，服务启动成功后，有Dialog主题的Activity提示", Toast.LENGTH_SHORT).show();
                        break;
                    case 3: // 判断Service是否注册
                        queryService();
                        break;
                    case 4: // 判断Service是否已经开始
                        queryRunningService();
                        break;
                    case 5: // 通过startService播放背景音乐
                        startActivity(new Intent(ServiceDemoAct.this, PlayMusicBackGroundAct.class));
                        break;
                    case 7:// 跨进程访问(AIDL服务)
                        startActivity(new Intent(ServiceDemoAct.this, AIDLActivityDemo.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 判断服务是否已经开始
     */
    private void queryRunningService() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        // 返回所有正在运行的服务信息
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = activityManager.getRunningServices(1000);

        for (int i = 0; i < runningServiceInfos.size(); i++) {
            ActivityManager.RunningServiceInfo runningServiceInfo = runningServiceInfos.get(i);
            // 通过服务的类名来判断服务是否正在运行
            if ("com.turing.base.activity.service.MyService".equals(runningServiceInfo.service.getClassName())) {

                Toast.makeText(ServiceDemoAct.this, "服务正在运行...", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(ServiceDemoAct.this, "服务没有开始", Toast.LENGTH_SHORT).show();
    }


    /**
     * 判断某个服务是否注册
     */

    private void queryService() {

        PackageManager packageManager = getPackageManager();
        // 指定要查询的Service Action
        Intent intent = new Intent("com.turing.base.activity.service.MyService");
        // 查询服务
        List<ResolveInfo> resolveInfos = packageManager.queryIntentServices(
                intent,
                PackageManager.GET_INTENT_FILTERS);

        if (resolveInfos.size() > 0) {
            ResolveInfo resolveInfo = resolveInfos.get(0);
            Toast.makeText(this, resolveInfo.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "服务未注册", Toast.LENGTH_SHORT).show();
        }

    }


    private List<Map<String, Object>> simulateData(String[] arr) {

        List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < arr.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("img", R.drawable.tag_green);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }
}

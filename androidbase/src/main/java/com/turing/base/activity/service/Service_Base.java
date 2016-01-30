package com.turing.base.activity.service;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.turing.base.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 继承ListActivity
 */
public class Service_Base extends ListActivity {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "Service生命周期",
            "绑定Activity和Service",
            "开机启动Service",
            "判断Service是否注册",
            "判断Service是否已经开始",
            "通过startService播放背景音乐"
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
                        startActivity(new Intent(Service_Base.this,ServiceLifeCycle_.class));
                        break;
                    case 1: // 绑定Activity和Service
                        startActivity(new Intent(Service_Base.this,ServiceBindAct.class));
                        break;
                    case 2: // 开机启动Service
                        break;
                    case 3: // 判断Service是否注册
                        break;
                    case 4: // 判断Service是否已经开始
                        break;
                    case 5: // 通过startService播放背景音乐
                        startActivity(new Intent(Service_Base.this,PlayMusicBackGroundAct.class));
                        break;
                    default:
                        break;
                }
            }
        });
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

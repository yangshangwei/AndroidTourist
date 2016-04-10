package com.turing.base.android_hero.chapter5_Scroll;

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

public class AndroidScroll extends ListActivity {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "Android Scroll分析-layout方法-视图坐标系的方式",
            "Android Scroll分析-layout方法-Android坐标系的方式",
            "Android Scroll分析-offsetLeftAndRight+offsetTopAndBottom",
            "Android Scroll分析-LayoutParams",
            "Android Scroll分析-scrollTo/scrollBy",
            "Android Scroll分析-Scroller平滑移动",
            "Android Scroll分析-ViewDragHelper"
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
                    case 0:// Android Scroll分析-layout方法-视图坐标系的方式
                        startActivity(new Intent(AndroidScroll.this, Scroll_Layout.class));
                        break;
                    case 1:// Android Scroll分析-layout方法-Android坐标系的方式
                        startActivity(new Intent(AndroidScroll.this, Scroll_Layout2.class));
                        break;
                    case 2:// Android Scroll分析-offsetLeftAndRight+offsetTopAndBottom
                        startActivity(new Intent(AndroidScroll.this, Scroll_offsetLRTB.class));
                        break;
                    case 3:// Android Scroll分析-LayoutParams
                        startActivity(new Intent(AndroidScroll.this, Scroll_LayoutParams.class));
                        break;
                    case 4:// Android Scroll分析-scrollTo/scrollBy
                        startActivity(new Intent(AndroidScroll.this,Scroll_scrolltoScrollBy.class));
                        break;
                    case 5: // Android Scroll分析-Scroller平滑移动
                        startActivity(new Intent(AndroidScroll.this,Scroll_Scroller.class));
                        break;
                    case 6:// Android Scroll分析-ViewDragHelper
                        startActivity(new Intent(AndroidScroll.this,DragView_ViewHelper.class));
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
            item.put("img", R.drawable.gur_project_10);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }
}

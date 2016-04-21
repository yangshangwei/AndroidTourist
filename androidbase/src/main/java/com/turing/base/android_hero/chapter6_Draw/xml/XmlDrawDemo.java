package com.turing.base.android_hero.chapter6_Draw.xml;

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

public class XmlDrawDemo extends ListActivity {
    // ListView中要显示的item
    private String[] arr = new String[]{
            "Bitmap",
            "Shape-Rectangle",
            "Shape-Oval",
            "Shape-line",
            "Shape-ring",
            "Layer",
            "Selector"
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
                    case 0://Bitmap
                        startActivity(new Intent(XmlDrawDemo.this, Xml2Bitmap.class));
                        break;
                    case 1://Shape-Rectangle
                        startActivity(new Intent(XmlDrawDemo.this, ShapeRectangleDemoAct.class));
                        break;
                    case 2:// Shape-Oval
                        startActivity(new Intent(XmlDrawDemo.this, ShapeOvalDemoAct.class));
                        break;
                    case 3:// Shape-line
                        startActivity(new Intent(XmlDrawDemo.this, ShapeLineDemoAct.class));
                        break;
                    case 4:// Shape-ring
                        startActivity(new Intent(XmlDrawDemo.this, ShapeRingDemoAct.class));
                        break;
                    case 5:// Layer
                        startActivity(new Intent(XmlDrawDemo.this, LayerListDemoAct.class));
                        break;
                    case 6:// Selector
                        startActivity(new Intent(XmlDrawDemo.this,SelectorDemoAct.class));
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
            item.put("img", R.drawable.gur_project_4);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }
}

package com.turing.base.android_hero;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.turing.base.R;
import com.turing.base.android_hero.chapter5_Scroll.AndroidScroll;
import com.turing.base.android_hero.chapter6_Draw.PaintAndCanvasDemosAct;
import com.turing.base.android_hero.chapter6_Draw.layer.LayerDemoActivity;
import com.turing.base.android_hero.chapter6_Draw.surfaceView.SimpleDrawCustomViewAct;
import com.turing.base.android_hero.chapter6_Draw.surfaceView.SimpleDrawSurfaceViewAct;
import com.turing.base.android_hero.chapter6_Draw.surfaceView.SinViewAct;
import com.turing.base.android_hero.chapter6_Draw.xml.XmlDrawDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidHeroDemoAct extends ListActivity {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "Android Scroll分析",
            "Android 三种动画(ViewAnimation/DrawableAnimation/PropertyAnimation)",
            "Android XML绘图(Canvas + Paint)",
            "Android 绘图技巧之Layer图层",
            "SurfaceView-绘画板",
            "SurfaceView-正弦曲线",
            "自定义View-绘画板"
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
                    case 0:// Scroll分析
                        startActivity(new Intent(AndroidHeroDemoAct.this, AndroidScroll.class));
                        break;
                    case 1:// Android 三种动画
                        startActivity(new Intent(AndroidHeroDemoAct.this, PaintAndCanvasDemosAct.class));
                        break;
                    case 2:// XML绘图
                        startActivity(new Intent(AndroidHeroDemoAct.this, XmlDrawDemo.class));
                        break;
                    case 3:// Android 绘图技巧之Layer图层
                        startActivity(new Intent(AndroidHeroDemoAct.this, LayerDemoActivity.class));
                        break;
                    case 4:// SurfaceView-绘画板
                        startActivity(new Intent(AndroidHeroDemoAct.this, SimpleDrawSurfaceViewAct.class));
                        break;
                    case 5:// SurfaceView-正弦曲线
                        startActivity(new Intent(AndroidHeroDemoAct.this, SinViewAct.class));
                        break;
                    case 6:// 自定义View-绘画板
                        startActivity(new Intent(AndroidHeroDemoAct.this, SimpleDrawCustomViewAct.class));
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
            item.put("img", R.drawable.gur_project_8);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }
}

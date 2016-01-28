package com.turing.base.activity.imageViewAct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.turing.base.R;
import com.turing.base.adapter.MainMenuListAdapter;
import com.turing.base.utils.ListViewDataFactory;

import java.util.List;

public class ImageViewDemoListAct extends Activity {


    private ListView listView;
    private List datalist;
    private MainMenuListAdapter adapter;

    // ListView中显示的内容
    public static String[] datas = new String[]{
            "IamgeView的基本用法",
            "显示指定区域的图片",
            "缩放和旋转图像"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_demo_list);

        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.id_imvDemoList);
        datalist = ListViewDataFactory.simulateData(datas);
        // 实例化Adapter
        adapter = new MainMenuListAdapter(this, datalist);
        // 设置adapter
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://IamgeView的基本用法
                        startActivity(new Intent(ImageViewDemoListAct.this,ImageViewBase.class));
                        break;
                    case 1://显示指定区域的图片
                        startActivity(new Intent(ImageViewDemoListAct.this,ImageViewShowPartAct.class));
                        break;
                    case 2://缩放和旋转图像
                        startActivity(new Intent(ImageViewDemoListAct.this,ImageViewMatrixAct.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

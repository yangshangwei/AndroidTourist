package com.turing.base.activity.buttonAct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;
import com.turing.base.adapter.MainMenuListAdapter;
import com.turing.base.beans.MainMenuListItemBean;
import com.turing.base.utils.ListViewDataFactory;

import java.util.List;

public class ButtonDemoAct extends AppCompatActivity {


    ListView buttonDemoList;

    List<MainMenuListItemBean> dataList;
    MainMenuListAdapter adapter;
    // ListView中显示的内容
    public static String[] datas = new String[]{
            "Button",
            "图文混排的按钮",
            "ImageButton",
            "RadioButton",
            "ToogleButton",
            "CheckBox"
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        // 初始化ListView
        buttonDemoList = (ListView) findViewById(R.id.id_lv_btnDemoList);

        // 模拟数据来源
        dataList = (List<MainMenuListItemBean>) ListViewDataFactory.simulateData(datas);
        LogUtils.d("ButtonDemo数据初始化完毕");

        // 初始化adapter
        adapter = new MainMenuListAdapter(this, dataList);
        // listView设置适配器
        buttonDemoList.setAdapter(adapter);


        // 设置监听
        buttonDemoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:// Button
                        startActivity(new Intent(ButtonDemoAct.this, ButtonAct.class));
                        break;
                    case 1: // 图文混排的按钮
                        startActivity(new Intent(ButtonDemoAct.this, TextAndPicButtonAct.class));
                        break;
                    case 2:// ImageButton
                        break;
                    case 3:// RadioButton
                        break;
                    case 4:// ToogleButton
                        break;
                    case 5:// CheckBox
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

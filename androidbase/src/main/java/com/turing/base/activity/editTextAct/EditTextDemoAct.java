package com.turing.base.activity.editTextAct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;
import com.turing.base.adapter.MainMenuListAdapter;
import com.turing.base.beans.MainMenuListItemBean;
import com.turing.base.utils.ListViewDataFactory;

import java.util.List;

public class EditTextDemoAct extends Activity {

    ListView editTextDemoList;

    List<MainMenuListItemBean> dataList;
    MainMenuListAdapter adapter;
    // ListView中显示的内容
    public static String[] datas = new String[]{
            "输入文字和图片",
            "输入特定的字符",
            "AutoCompleteTextView"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_text_demo);
        // 初始化ListView
        editTextDemoList = (ListView) findViewById(R.id.id_lv_editTextDemo);

        // 模拟数据来源
        dataList = (List<MainMenuListItemBean>) ListViewDataFactory.simulateData(datas);
        LogUtils.d("EditTextDemo数据初始化完毕");

        // 初始化adapter
        adapter = new MainMenuListAdapter(this, dataList);
        // listView设置适配器
        editTextDemoList.setAdapter(adapter);


        // 设置监听
        editTextDemoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:// 输入文字和图片
                        startActivity(new Intent(EditTextDemoAct.this, InputTextAndPicAct.class));
                        break;
                    case 1: // 输入特定的字符
                        startActivity(new Intent(EditTextDemoAct.this,SpecialCharcterEditTextAct.class));
                        break;
                    case 2:// AutoCompleteTextView
                        startActivity(new Intent(EditTextDemoAct.this,AutoCompleteTextViewAct.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

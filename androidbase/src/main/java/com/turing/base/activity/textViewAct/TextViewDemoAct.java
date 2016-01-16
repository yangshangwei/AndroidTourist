package com.turing.base.activity.textViewAct;

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

public class TextViewDemoAct extends Activity {

    ListView textViewDemoList;

    List<MainMenuListItemBean> dataList;
    MainMenuListAdapter adapter;
    // ListView中显示的内容
    public static String[] datas = new String[]{
            "显示富文本（URL、不同大小、字体、颜色的文本）",
            "在TextView中显示 表情图像和文字",
            "单击链接弹出Activity",
            "为指定文字添加背景",
            "带边框的TextView",
            "设置行间距",
            "在未显示完的文本后面加省略号(…)",
            "用TextView实现走马灯的效果",
            "垂直滚动TextView中的文本"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_text_view_demo);
        // 初始化ListView
        textViewDemoList = (ListView) findViewById(R.id.id_lv_textViweDemo);

        // 模拟数据来源
        dataList = (List<MainMenuListItemBean>) ListViewDataFactory.simulateData(datas);
        LogUtils.d("TextViewDemo数据初始化完毕");

        // 初始化adapter
        adapter = new MainMenuListAdapter(this, dataList);
        // listView设置适配器
        textViewDemoList.setAdapter(adapter);


        // 设置监听
        textViewDemoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:// 显示富文本（URL、不同大小、字体、颜色的文本
                        startActivity(new Intent(TextViewDemoAct.this, RichTextViewAct.class));
                        break;
                    case 1:// 在TextView中显示 表情图像和文字
                        startActivity(new Intent(TextViewDemoAct.this,MultTextPicAct.class));
                        break;
                    case 2://单击链接弹出Activity
                        startActivity(new Intent(TextViewDemoAct.this,Jump2Activity.class));
                        break;
                    case 3://为指定文字添加背景
                        startActivity(new Intent(TextViewDemoAct.this,AddBackgroundAct.class));
                        break;
                    case 4://带边框的TextView
                        startActivity(new Intent(TextViewDemoAct.this,BorderTextViewAct.class));
                        break;
                    case 5://设置行间距
                        startActivity(new Intent(TextViewDemoAct.this,LineSpaceTextViewAct.class));
                        break;
                    case 6://在未显示完的文本后面加省略号(…)
                        startActivity(new Intent(TextViewDemoAct.this,IgnoreTextAct.class));
                        break;
                    case 7:// 用TextView实现走马灯的效果
                        startActivity(new Intent(TextViewDemoAct.this,LampTextViewAct.class));
                        break;
                    case 8:// 垂直滚动TextView中的文本
                        startActivity(new Intent(TextViewDemoAct.this,ScrollTextViewAct.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

package com.turing.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;
import com.turing.base.activity.intentAct.Data;
import com.turing.base.activity.intentAct.GetIntentActivity;
import com.turing.base.activity.intentAct.XianSiDiaoyongAct;
import com.turing.base.activity.lifeCircle.LifeCircleActivity;
import com.turing.base.adapter.MainMenuListAdapter;
import com.turing.base.beans.MainMenuListItemBean;
import com.turing.base.utils.ListViewDataFactory;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@Fullscreen
@EActivity(R.layout.activity_ui__base)
public class UI_Base extends Activity {

    @ViewById(R.id.id_lv_ui)
    ListView lv_ui ;

    List<MainMenuListItemBean> dataList ;
    MainMenuListAdapter adapter ;
    // ListView中显示的内容
    public static String[] datas = new String[]{
            "显示调用Activity",
            "隐式调用Activity",
            "隐式调用两个符合过滤条件的Activity",
            "Activity生命周期",
            "使用Intent传递数据",
            "使用静态（static）传递数据",
            "使用剪切板（Clipboard）传递数据",
            "通过全局变量传递数据"
           };


    @AfterViews
    public void showAc(){
        // 模拟数据来源
        dataList = (List<MainMenuListItemBean>) ListViewDataFactory.simulateData(datas);
        LogUtils.d("数据初始化完毕");
        // 实例化Adapter
        adapter = new MainMenuListAdapter(this, dataList);
        // 设置adapter
        lv_ui.setAdapter(adapter);



        // 设置监听事件
        lv_ui.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: // 显示调用Activity
                        Toast.makeText(UI_Base.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
                        // 第一种方式
                        Intent intent = new Intent(UI_Base.this, XianSiDiaoyongAct.class);
                        startActivity(intent);
                        // 第二种方式
                        // Intent intent1 = new Intent();
                        // intent1.setClass(UI_Base.this,XianSiDiaoyongAct.class);
                        // startActivity(intent1);
                        break;
                    case 1:// 隐式调用Activity
                        Intent intent2 = new Intent("myAction2");
                        startActivity(intent2);
                        break;
                    case 2: // 隐式调用两个符合过滤条件的Activity
                        Intent intent3 = new Intent("myAction2");
                        intent3.addCategory("mycategory");
                        startActivity(intent3);
                        break;
                    case 3: // Activity生命周期
                        Intent intent4 = new Intent(UI_Base.this, LifeCircleActivity.class);
                        startActivity(intent4);
                        break;
                    case 4://使用Intent传递数据
                        Intent intent5 = new Intent(UI_Base.this,GetIntentActivity.class);
                        //简单类型
                        intent5.putExtra("intent_string","通过Intent传递的字符串");
                        intent5.putExtra("intent_int", 20);

                        // 可序列化的对象
                        Data data = new Data();
                        data.setId(99);
                        data.setName("ZTE");
                        intent5.putExtra("intent_object", data);

                        startActivity(intent5);
                        break;
                    case 5:// 使用静态（static）传递数据
                        break;
                    case 6://使用剪切板（Clipboard）传递数据
                        break;
                    case 7:// 通过全局变量传递数据
                        break;
                    default:
                        break;
                }
            }
        });
    }

}

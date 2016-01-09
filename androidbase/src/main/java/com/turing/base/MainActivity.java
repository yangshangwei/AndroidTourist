package com.turing.base;

import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;

import com.turing.base.activity.HttpActivity_;
import com.turing.base.activity.ListViewFunctionsActivity_;
import com.turing.base.activity.UI_Base_;
import com.turing.base.adapter.MainMenuListAdapter;
import com.turing.base.utils.ListViewDataFactory;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

/**
 * 入口类
 * 野生程序猿的入门记录
 * 请大神们自动忽略
 * 仅此作为学习记录自用
 * <p/>
 * 此demo采用AndroidAnnotations开源框架 当然了也不是全部采用，有些会使用原生的来搞
 * 日志使用LogUtils开源框架记录
 * 再此向前辈们致敬，感谢开源！
 */

@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {


    // 查找并初始化ListView组件
    @ViewById(R.id.id_lv_menu_list)
    ListView listView;


    // ListView中显示的内容
    public static String[] datas = new String[]{
                        "HTTP通信",
                        "ListView使用技巧",
                        "Android控件架构与自定义控件",
                        "我的UI我做主"};

    @AfterViews
    public void showMenuList() {

        // 实例化适配器
        MainMenuListAdapter adapter = new MainMenuListAdapter(this, ListViewDataFactory.simulateData(datas));

        listView.setAdapter(adapter);
    }

    @ItemClick(R.id.id_lv_menu_list)
    public void itemClick(int position){
        switch (position){
            case 0:
                startActivity(new Intent(MainActivity.this,HttpActivity_.class));
                break;
            case 1:
                startActivity(new Intent(MainActivity.this,ListViewFunctionsActivity_.class));
                break;
            case 2:
                // TODO
                break;
            case 3:
                startActivity(new Intent(MainActivity.this,UI_Base_.class));
                break;
            default:
                break;
        }
    }





}

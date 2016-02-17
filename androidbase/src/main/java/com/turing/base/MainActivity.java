package com.turing.base;

import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;

import com.turing.base.activity.Dlg_Tst_Ntf.Dlg_Tst_Ntf;
import com.turing.base.activity.HttpActivity_;
import com.turing.base.activity.ListViewFunctionsActivity_;
import com.turing.base.activity.UI_Base_;
import com.turing.base.activity.service.ServiceDemoAct;
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
                        "网络与通信",
                        "ListView使用技巧",
                        "Android控件架构与自定义控件",
                        "Widget详解",
                        "Service",
                        "全局事件--广播",
                        "Content Provider",
                        "Activity",
                        "友好的互动提醒--对话框、Toast、Notification",
                        "友好的菜单--Menu",
                        "移动的信息仓库--数据存储",
                        "多媒体开发",
                        "......"};

    @AfterViews
    public void showMenuList() {

        // 实例化适配器
        MainMenuListAdapter adapter = new MainMenuListAdapter(this, ListViewDataFactory.simulateData(datas));

        listView.setAdapter(adapter);
    }

    @ItemClick(R.id.id_lv_menu_list)
    public void itemClick(int position){
        switch (position){
            case 0://网络与通信
                startActivity(new Intent(MainActivity.this,HttpActivity_.class));
                break;
            case 1://ListView使用技巧
                startActivity(new Intent(MainActivity.this,ListViewFunctionsActivity_.class));
                break;
            case 2:
                // TODO
                break;
            case 3://UI
                startActivity(new Intent(MainActivity.this,UI_Base_.class));
                break;
            case 4://Service
                startActivity(new Intent(MainActivity.this,ServiceDemoAct.class));
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:// 友好的互动提醒--对话框、Toast、Notification
                startActivity(new Intent(MainActivity.this, Dlg_Tst_Ntf.class));
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            default:
                break;
        }
    }





}

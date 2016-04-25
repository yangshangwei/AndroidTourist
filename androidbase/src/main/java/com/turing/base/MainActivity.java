package com.turing.base;

import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import com.turing.base.activity.Dlg_Tst_Ntf.Dlg_Tst_Ntf;
import com.turing.base.activity.HttpActivity_;
import com.turing.base.activity.ListViewFunctionsActivity_;
import com.turing.base.activity.UI_Base_;
import com.turing.base.activity.activityDemo.ActivityDemoList;
import com.turing.base.activity.broadcastDemo.BroadcastDemoAct;
import com.turing.base.activity.dataStore.DataStoreListDemoAct;
import com.turing.base.activity.fragment.FragmentDemoAct;
import com.turing.base.activity.menu.MenuDemo;
import com.turing.base.activity.service.ServiceDemoAct;
import com.turing.base.activity.socket.SocketDemoAct;
import com.turing.base.activity.styleDevelop.StyleDevelopDemoAct;
import com.turing.base.activity.systemManager.SystemManagerDemoAct;
import com.turing.base.adapter.MainMenuListAdapter;
import com.turing.base.android_hero.AndroidHeroDemoAct;
import com.turing.module.ModuleDemoAct;
import com.turing.base.utils.ListViewDataFactory;
import com.turing.nutritiousSerial.NutritiousSerialList;

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
            "全局事件--BroadcastReceiver",
            "Content Provider",
            "Activity",
            "友好的互动提醒--对话框、Toast、Notification",
            "友好的菜单--Menu",
            "移动的信息仓库--数据存储",
            "多媒体开发",
            "Fragment",
            "Android样式",
            "图片管理策略~",
            "动画效果",
            "自定义View",
            "常用组件~",
            "Google DataBinding",
            "Socket",
            "相关系统服务",
            "AndroidHerosCode",
            "干货系列"
    };

    @AfterViews
    public void showMenuList() {

        // 实例化适配器
        MainMenuListAdapter adapter = new MainMenuListAdapter(this, ListViewDataFactory.simulateData(datas));

        listView.setAdapter(adapter);

    }

    @ItemClick(R.id.id_lv_menu_list)
    public void itemClick(int position) {
        switch (position) {
            case 0://网络与通信
                startActivity(new Intent(MainActivity.this, HttpActivity_.class));
                break;
            case 1://ListView使用技巧
                startActivity(new Intent(MainActivity.this, ListViewFunctionsActivity_.class));
                break;
            case 2:
                // TODO
                break;
            case 3://Widget详解
                startActivity(new Intent(MainActivity.this, UI_Base_.class));
                break;
            case 4://Service
                startActivity(new Intent(MainActivity.this, ServiceDemoAct.class));
                break;
            case 5:// 全局事件--广播
                startActivity(new Intent(MainActivity.this, BroadcastDemoAct.class));
                break;
            case 6: // Content Provider
                break;
            case 7: // Activity
                startActivity(new Intent(MainActivity.this, ActivityDemoList.class));
                break;
            case 8:// 友好的互动提醒--对话框、Toast、Notification
                startActivity(new Intent(MainActivity.this, Dlg_Tst_Ntf.class));
                break;
            case 9: // Menu
                startActivity(new Intent(MainActivity.this, MenuDemo.class));
                break;
            case 10: // 移动的信息仓库--数据存储
                startActivity(new Intent(MainActivity.this, DataStoreListDemoAct.class));
                break;
            case 11: // 多媒体
                break;
            case 12: // Fragment
                startActivity(new Intent(MainActivity.this, FragmentDemoAct.class));
                break;
            case 13: // Android样式
                startActivity(new Intent(MainActivity.this, StyleDevelopDemoAct.class));
                break;
            case 14:// 图片管理策略
                Toast.makeText(this,"待学习",Toast.LENGTH_SHORT).show();
                break;
            case 15:// 动画效果
                Toast.makeText(this,"待学习",Toast.LENGTH_SHORT).show();
                break;
            case 16:// 自定义View
                Toast.makeText(this,"待学习",Toast.LENGTH_SHORT).show();
                break;
            case 17:// 常用组件
                startActivity(new Intent(MainActivity.this, ModuleDemoAct.class));
                break;
            case 18:// Google DataBinding
                Toast.makeText(this,"http://blog.csdn.net/qian1127/article/details/51191956",Toast.LENGTH_SHORT).show();
                break;
            case 19: // Socket
                startActivity(new Intent(MainActivity.this, SocketDemoAct.class));
                break;
            case 20: // 系统服务
                startActivity(new Intent(MainActivity.this, SystemManagerDemoAct.class));
                break;
            case 21:// Android群英传学习代码
                startActivity(new Intent(MainActivity.this, AndroidHeroDemoAct.class));
                break;
            case 22:// 干货系列
                startActivity(new Intent(MainActivity.this, NutritiousSerialList.class));
                break;
            default:
                break;
        }
    }


}

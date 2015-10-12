package com.turing.base;

import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;

import com.turing.base.activity.HttpActivity_;
import com.turing.base.adapter.MainMenuListAdapter;
import com.turing.base.beans.MainMenuListItemBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

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
    // 数据来源
    List<MainMenuListItemBean> dataList;

    @AfterViews
    public void showMenuList() {
        // 模拟数据来源
        simulateData();
        // 实例化适配器
        MainMenuListAdapter adapter = new MainMenuListAdapter(this, dataList);

        listView.setAdapter(adapter);
    }

    @ItemClick(R.id.id_lv_menu_list)
    public void itemClick(int position){
        switch (position){
            case 0:
                startActivity(new Intent(MainActivity.this,HttpActivity_.class));
                break;
            default:
                break;
        }
    }


    /**
     * 模拟数据源
     */
    public void simulateData() {

        MainMenuListItemBean bean = new MainMenuListItemBean();
        bean.setContent("HTTP通信");
        MainMenuListItemBean bean1 = new MainMenuListItemBean();
        bean1.setContent("待补充1");
        MainMenuListItemBean bean2 = new MainMenuListItemBean();
        bean2.setContent("待补充2");
        MainMenuListItemBean bean3 = new MainMenuListItemBean();
        bean3.setContent("待补充3");
        MainMenuListItemBean bean4 = new MainMenuListItemBean();
        bean4.setContent("待补充4");
        MainMenuListItemBean bean5 = new MainMenuListItemBean();
        bean5.setContent("待补充5");
        MainMenuListItemBean bean6 = new MainMenuListItemBean();
        bean6.setContent("待补充6");

        dataList = new ArrayList<>();
        dataList.add(bean);
        dataList.add(bean1);
        dataList.add(bean2);
        dataList.add(bean3);
        dataList.add(bean4);
        dataList.add(bean5);
        dataList.add(bean6);
    }
}

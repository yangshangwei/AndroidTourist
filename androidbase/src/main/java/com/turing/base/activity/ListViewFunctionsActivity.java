package com.turing.base.activity;

import android.app.Activity;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;
import com.turing.base.adapter.MainMenuListAdapter;
import com.turing.base.beans.MainMenuListItemBean;
import com.turing.base.utils.ListViewDataFactory;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-12-20  17:46.
 * @version 1.0
 * @desc
 */

@Fullscreen
@EActivity(R.layout.activity_listview)
public class ListViewFunctionsActivity extends Activity {

    @ViewById(R.id.id_listview_functions)
    ListView listView;


    // ListView中显示的内容
    public static String[] datas = new String[]{
            "ViewHoder模式",
            "设置项目间分隔线",
            "隐藏ListView的滚动条",
            "取消ListView的Item点击效果",
            "设置ListView需要显示在第几行",
            "动态修改ListView",
            "遍历ListView中所有的Item",
            "处理空ListView",
            "ListView的滑动监听",
            "具有弹性的ListView",
            "自动隐藏、显示布局的ListView",
            "聊天ListView",
            "动态改变ListView布局"};

    @AfterViews
    public void loadListViewData() {
        // 模拟数据来源
        List<MainMenuListItemBean> dataList = (List<MainMenuListItemBean>) ListViewDataFactory.simulateData(datas);
        LogUtils.d("数据初始化完毕");
        // 实例化Adapter
        MainMenuListAdapter adapter = new MainMenuListAdapter(this, dataList);
        // 设置adapter
        listView.setAdapter(adapter);
    }

    /**
     *ListView点击监听事件
     */
    @ItemClick(R.id.id_listview_functions)
    public void itemClickHandle(int position){
        switch(position){
            case 0:
                LogUtils.d(position);
                break;
            case 1:
                LogUtils.d(position);
                break;
            case 2:
                LogUtils.d(position);
                break;
            case 3:
                LogUtils.d(position);
                break;
            case 4:
                LogUtils.d(position);
                break;
            case 5:
                LogUtils.d(position);
                break;
            case 6:
                LogUtils.d(position);
                break;
            case 7:
                LogUtils.d(position);
                break;
            case 8:
                LogUtils.d(position);
                break;
            case 9:
                LogUtils.d(position);
                break;
            case 10:
                LogUtils.d(position);
                break;
            case 11:
                LogUtils.d(position);
                break;
            case 12:
                LogUtils.d(position);
                break;
            default:
                LogUtils.d("default");
                break;
        }
    }

}

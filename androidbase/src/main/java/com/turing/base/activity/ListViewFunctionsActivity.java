package com.turing.base.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

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
import org.apache.commons.lang.ArrayUtils;

import java.util.Collection;
import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-12-20  17:46.
 * @version 1.0
 */

@Fullscreen
@EActivity(R.layout.activity_listview)
public class ListViewFunctionsActivity extends Activity {

    @ViewById(R.id.id_listview_functions)
    ListView listView;

    List<MainMenuListItemBean> dataList ;
    MainMenuListAdapter adapter ;
    // ListView中显示的内容
    public static String[] datas = new String[]{
            "ViewHoder模式",
            "设置项目间分隔线",
            "隐藏ListView的滚动条",
            "取消ListView的Item点击效果",
            "设置ListView需要显示在第几项",
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
        dataList = (List<MainMenuListItemBean>) ListViewDataFactory.simulateData(datas);
        LogUtils.d("数据初始化完毕");
        // 实例化Adapter
        adapter = new MainMenuListAdapter(this, dataList);
        // 设置adapter
        listView.setAdapter(adapter);
    }

    /**
     *ListView点击监听事件
     */
    @ItemClick(R.id.id_listview_functions)
    public void itemClickHandle(int position){
        switch(position){
            case 0: // ViewHolder
                LogUtils.d(position);
                Toast.makeText(ListViewFunctionsActivity.this,"详见本ListView加载的代码",Toast.LENGTH_LONG).show();
                break;
            case 1:// 设置项目间分隔线
                LogUtils.d(position);
                // 设置分隔线 ,必须先setDivider,然后setivederHeight
                listView.setDivider(new ColorDrawable(Color.GREEN));
                listView.setDividerHeight(3);
                // 取消分隔线
                // listView.setDivider(null);
                showInfo();
                break;
            case 2:// 隐藏ListView的滚动条
                LogUtils.d(position);
                // false-一直都显示  true-不活动时隐藏，活动时显示
                //listView.setScrollbarFadingEnabled(true);
                //false 不活动的时候隐藏，活动的时候也隐藏  true-不活动时隐藏，活动时显示
                listView.setVerticalScrollBarEnabled(false);
                // 或者在xml中 android:scrollbars="none"  ---->  不活动的时候隐藏，活动的时候也隐藏
                showInfo();
                break;
            case 3:// 取消ListView的Item点击效果
                LogUtils.d(position);
                /**
                 * 也可以在xml中 android:listSelector="@android:color/transparent"
                 * 或者android:listSelector="#00000000"实现同样的效果
                 */
                listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
                showInfo();
                break;
            case 4:// 设置ListView需要显示在第几项
                LogUtils.d(position);
                listView.setSelection(position);
                showInfo();
                break;
            case 5:// 动态修改ListView
                LogUtils.d(position);
                // 模拟增加一条数据
                datas = (String[])ArrayUtils.add(datas, "数组增加");

                dataList.clear();
                dataList.addAll((Collection<? extends MainMenuListItemBean>) ListViewDataFactory.simulateData(datas));

                adapter.notifyDataSetChanged();

                listView.setSelection(datas.length-1);

                showInfo();
                break;
            case 6:// 遍历ListView中所有的Item
                LogUtils.d(position);
               for(int i = 0 ;i < listView.getChildCount();i++){
                   View view = listView.getChildAt(i);
                   view.setBackgroundColor(Color.BLUE);
               }
                break;
            case 7:// 处理空ListView
                LogUtils.d(position);
                // 模拟数据为空的情况
                dataList.clear();
                adapter.notifyDataSetChanged();
                // 设置空的时候的提示
                listView.setEmptyView(findViewById(R.id.id_iv_empty_listView));
                break;
            case 8:// ListView的滑动监听
                LogUtils.d(position);
                listView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch(event.getAction()){
                            case MotionEvent.ACTION_DOWN:
                                LogUtils.d("down....");
                                break;
                            case MotionEvent.ACTION_MOVE:
                                LogUtils.d("move....");
                                break;
                            case MotionEvent.ACTION_UP:
                                LogUtils.d("up....");
                                break;
                            default :
                                break;
                        }
                        return false;
                    }
                });

                listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {

                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                    }
                });
                break;
            case 9:// 具有弹性的ListView
                LogUtils.d(position);
                break;
            case 10:// 自动隐藏、显示布局的ListView
                LogUtils.d(position);
                break;
            case 11:// 聊天ListView
                LogUtils.d(position);
                break;
            case 12:// 动态改变ListView布局
                LogUtils.d(position);
                break;
            default:
                LogUtils.d("default");
                break;
        }
    }

    /**
     * 提示信息
     */
    public void showInfo(){
     Toast.makeText(ListViewFunctionsActivity.this,"设置成功",Toast.LENGTH_LONG).show();
    }
}

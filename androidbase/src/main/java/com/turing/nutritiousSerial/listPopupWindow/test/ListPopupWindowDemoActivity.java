package com.turing.nutritiousSerial.listPopupWindow.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import com.turing.base.R;
import com.turing.nutritiousSerial.listPopupWindow.beans.PopupItemBean;
import com.turing.nutritiousSerial.listPopupWindow.customPopupWindow.ListPopupWindow;

import java.util.ArrayList;
import java.util.List;

public class ListPopupWindowDemoActivity extends AppCompatActivity implements ListPopupWindow.OnPopubItemClickListener, ListPopupWindow.OnBottomTextViewClickListener {

    // 定义父View即PopupWindow依赖浮动的View
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_popup_window_demo);
        // 父View
        relativeLayout = (RelativeLayout) findViewById(R.id.id_rl_relativeLayout);
    }

    /**
     * 按钮监听事件
     *
     * @param view
     */
    public void showCustomListPopupWindow(View view) {

        // 展示数据集合
        List<PopupItemBean> datalists = new ArrayList<>();
        // List集合中的数据
        PopupItemBean itemBean1 = new PopupItemBean("版本更新");
        PopupItemBean itemBean2 = new PopupItemBean("反馈");
        PopupItemBean itemBean3 = new PopupItemBean("退出QQ");

        // 添加到List集合中
        datalists.add(itemBean1);
        datalists.add(itemBean2);
        datalists.add(itemBean3);

        // 实例化自定义ListPopupWindow
        ListPopupWindow popWindow = new ListPopupWindow(ListPopupWindowDemoActivity.this,
                datalists, relativeLayout);
        // 居中并且靠底部显示
        popWindow.showAtLocation(relativeLayout, Gravity.CENTER | Gravity.BOTTOM, 0, 0);
    }


    @Override
    public void onBottomClick() {

    }

    @Override
    public void onPopupItemClick(View view, int postion) {

    }
}

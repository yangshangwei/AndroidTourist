package com.turing.nutritiousSerial.listPopupWindow.customPopupWindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.turing.base.R;
import com.turing.nutritiousSerial.listPopupWindow.adapter.CustomPopupWindowAdpater;
import com.turing.nutritiousSerial.listPopupWindow.beans.PopupItemBean;

import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-25  10:12.
 * @version 1.0
 * @desc
 */
public class ListPopupWindow extends PopupWindow {

    //上下文
    private Context context;
    //父视图
    private View parentView;
    //item数据源
    private List<PopupItemBean> dataList;
    //item列表视图
    private ListView lv;
    //适配器
    private CustomPopupWindowAdpater adapter;

    //声明接口对象
    private OnPopubItemClickListener popupItemListener;
    private OnBottomTextViewClickListener bottomTextViewListener;


    /**
     * 定义接口用于PopupItem回调点击事件处理
     */
    public interface OnPopubItemClickListener {
        void onPopupItemClick(View view, int postion);
    }

    /**
     * 定义接口用于底部TextView回调点击事件处理
     */
    public interface OnBottomTextViewClickListener {
        void onBottomClick();
    }

    /**
     * 构造函数
     */
    public ListPopupWindow(Context context, List<PopupItemBean> dataList,
                           View parentView) {
        this.context = context;
        this.dataList = dataList;
        this.parentView = parentView;


        initCustomPopupWindow();
    }


    /**
     * 初始化自定义的PopupWindow
     */
    private void initCustomPopupWindow() {

        parentView = LayoutInflater.from(context).inflate(R.layout.list_popupwindow,null);
        setContentView(parentView);


        lv = (ListView) parentView.findViewById(R.id.id_lv_popupWindowListView);

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        this.setFocusable(true);
        this.setBackgroundDrawable(new ColorDrawable(0xb0000000));

        update();


        adapter = new CustomPopupWindowAdpater(context,dataList);
        lv.setAdapter(adapter);





    }
}

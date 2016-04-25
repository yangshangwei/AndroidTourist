package com.turing.nutritiousSerial.listPopupWindow.customPopupWindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

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
    public ListPopupWindow(Context context,
                           List<PopupItemBean> dataList,
                           View parentView,
                           OnPopubItemClickListener popupItemListener,
                           OnBottomTextViewClickListener bottomTextViewListener) {

        this.context = context;
        this.dataList = dataList;
        this.parentView = parentView;

        this.popupItemListener = popupItemListener;
        this.bottomTextViewListener = bottomTextViewListener;

        initCustomPopupWindow();
    }


    /**
     * 初始化自定义的PopupWindow
     */
    private void initCustomPopupWindow() {
        // 加载自定义布局文件，转化为组件
        parentView = LayoutInflater.from(context).inflate(R.layout.list_popupwindow, null);
        // 设置显示的view
        setContentView(parentView);

        // 初始化控件
        ListView lv = (ListView) parentView.findViewById(R.id.id_lv_popupWindowListView);
        TextView tv = (TextView) parentView.findViewById(R.id.id_tv_bottom);

        // 设置弹出窗体的高
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(new ColorDrawable(0xb0000000));


        // view添加OnTouchListener监听判断获取触屏位置如果在布局外面则销毁弹出框
        parentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // getTop View自身的顶边到其父布局顶边的距离,因为是根目录所以为0
                int height = parentView.findViewById(R.id.id_rl_relativeLayout).getTop();
                // getY 点击事件距离控件顶边的举例
                int y = (int) event.getY();
                // 当抬起 并且 y>height时（也就是 只要不点击ListView范围内）,dismiss popupWindow
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        // 更新位置和大小(不加这行代码也行)
        update();
        // 实例化适配器
        adapter = new CustomPopupWindowAdpater(context, dataList);
        // 设置适配器
        lv.setAdapter(adapter);


        // ListView设置点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 回调onPopupItemClick
                popupItemListener.onPopupItemClick(view, position);
            }
        });

        // TextView设置点击事件
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 回调onBottomClick
                bottomTextViewListener.onBottomClick();
            }
        });

    }
}

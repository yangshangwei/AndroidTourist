package com.turing.module.pullToRefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-03-18  11:32.
 * @version 1.0
 * @desc
 */
public class RefreshableView extends LinearLayout {


    /**
     * 构造函数
     * @param context
     * @param attrs
     */
    public RefreshableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Called when this view should assign a size and position to all of its children
     * @param changed   该参数指出当前ViewGroup的尺寸或者位置是否发生了改变
     * @param l t  r b 当前ViewGroup相对于其父控件的坐标位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}

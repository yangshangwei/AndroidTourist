package com.turing.base.android_hero.chapter5_Scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-10  09:56.
 * @version 1.0
 * @desc
 */
public class DragView_offsetLRTB extends View {


    private int lastX;
    private int lastY;


    public DragView_offsetLRTB(Context context) {
        super(context);
        initViewBackgroundColor();
    }

    public DragView_offsetLRTB(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewBackgroundColor();
    }

    public DragView_offsetLRTB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewBackgroundColor();
    }


    /**
     * 为了便于观察,设置下View的背景颜色
     */
    public void initViewBackgroundColor() {
        setBackgroundColor(Color.BLUE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录按下时的 触摸坐标
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                // 同时对left和right进行偏移
                offsetLeftAndRight(offsetX);
                // 同时对top和bottom进行偏移
                offsetTopAndBottom(offsetY);

                break;
        }

        return true;
    }
}

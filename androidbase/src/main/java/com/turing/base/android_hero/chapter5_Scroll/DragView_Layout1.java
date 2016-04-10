package com.turing.base.android_hero.chapter5_Scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-09  21:11.
 * @version 1.0
 *          自定义View
 */
public class DragView_Layout1 extends View {

    // 定义上次触摸的位置
    private int lastX;
    private int lastY;


    /**
     * 构造函数中调用 initViewColor方法
     *
     * @param context
     */
    public DragView_Layout1(Context context) {
        super(context);
        initViewBackGroundColor();
    }


    public DragView_Layout1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewBackGroundColor();
    }

    public DragView_Layout1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewBackGroundColor();
    }


    public void initViewBackGroundColor() {
        // 给View设置背景颜色，便于观察
        setBackgroundColor(Color.BLUE);
    }


    /**
     * 重写 onTouchEvent方法
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 按下的时候 记录触摸点坐标  scrollByXY
                lastX = x;
                lastY = y;

                break;
            case MotionEvent.ACTION_MOVE:
                // 计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                // 在当前left right  top  bottom的基础上增加偏移量
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);
                break;
        }
        // 一定要返回true，否则无法移动
        return true;

    }

}

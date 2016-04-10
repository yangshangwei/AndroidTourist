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
public class DragView_Layout2 extends View {

    // 定义上次触摸的位置
    private int lastX;
    private int lastY;


    /**
     * 构造函数中调用 initViewColor方法
     *
     * @param context
     */
    public DragView_Layout2(Context context) {
        super(context);
        initViewBackGroundColor();
    }


    public DragView_Layout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewBackGroundColor();
    }

    public DragView_Layout2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewBackGroundColor();
    }


    public void initViewBackGroundColor() {
        // 给View设置背景颜色，便于观察
        setBackgroundColor(Color.BLUE);
    }


    /**
     * 重写 onTouchEvent方法
     * 使用Android坐标系 绝对坐标来计算偏移量，并移动View
     * 使用绝对坐标系，在每次执行完ACTION_MOVE的逻辑后一定要重新设置初始坐标，
     * 这样才能准确的获取到偏移量
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int rawx = (int) event.getRawX();
        int rawy = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 按下的时候 记录触摸点坐标

                lastX = rawx;
                lastY = rawy;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算偏移量
                int offsetX = rawx - lastX;
                int offsetY = rawy - lastY;

                // 增加偏移量  方法一
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);


                // 重新设置初坐标
                lastX = rawx;
                lastY = rawy;
                break;
        }
        return true;
    }
}

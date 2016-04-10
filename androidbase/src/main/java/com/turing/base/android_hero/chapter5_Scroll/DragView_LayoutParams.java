package com.turing.base.android_hero.chapter5_Scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-10  10:23.
 * @version 1.0
 * @desc
 */
public class DragView_LayoutParams extends View {

    private int lastX, lastY;

    public DragView_LayoutParams(Context context) {
        super(context);
        initViewBackgroudCoclor();
    }

    public DragView_LayoutParams(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewBackgroudCoclor();
    }

    public DragView_LayoutParams(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewBackgroudCoclor();
    }


    private void initViewBackgroudCoclor() {
        // 给View设置背景颜色，便于观察
        setBackgroundColor(Color.BLUE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录当前的触摸坐标
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:

                // 获取偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;


                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
          //      RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();

                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;

                setLayoutParams(layoutParams);

                break;
            default:
                break;
        }


        return true;
    }
}

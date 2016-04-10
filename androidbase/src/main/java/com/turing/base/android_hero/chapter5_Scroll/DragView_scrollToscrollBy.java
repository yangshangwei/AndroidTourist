package com.turing.base.android_hero.chapter5_Scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-10  12:32.
 * @version 1.0
 * @desc
 */
public class DragView_scrollToscrollBy extends View {

    private int lastX;
    private int lastY;

    public DragView_scrollToscrollBy(Context context) {
        super(context);
        initViewBackgroundColor();
    }

    public DragView_scrollToscrollBy(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewBackgroundColor();
    }

    public DragView_scrollToscrollBy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewBackgroundColor();
    }

    private void initViewBackgroundColor() {
        setBackgroundColor(Color.BLUE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ((View) getParent()).scrollBy(-offsetX,-offsetY);
                break;
        }
        return true;
    }
}

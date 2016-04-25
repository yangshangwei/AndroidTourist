package com.turing.base.android_hero.chapter6_Draw.surfaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-22  22:02.
 * @version 1.0
 * @desc
 */
public class SimpleDrawBySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    //SurfaceHolder
    private SurfaceHolder mSurfaceHolder;
    // 用于绘图的Canvas
    private Canvas mCanvas;
    // 子线程标志位
    private boolean mIsDrawing;


    private Path mPath;
    private Paint mPaint;


    /**
     * 构造函数
     *
     * @param context
     */
    public SimpleDrawBySurfaceView(Context context) {
        super(context);
        initView();
    }

    /**
     * 构造函数
     *
     * @param context
     * @param attrs
     */
    public SimpleDrawBySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     * 构造函数
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SimpleDrawBySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    /**
     * 初始化，在构造函数中调用
     */
    private void initView() {

        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setKeepScreenOn(true);
        //mSurfaceHolder.setFormat(PixelFormat.OPAQUE);

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10.0f);

    }

    /**
     * SurfaceView创建
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        mIsDrawing = true;
        new Thread(this).start();

    }

    /**
     * SurfaceView改变
     *
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    /**
     * SurfaceView销毁
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (mIsDrawing) {
            draw();
        }
        long end = System.currentTimeMillis();
        // 50 - 100
        if (end - start < 100) {
            try {
                Thread.sleep(100 - (end - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
    private void draw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            // draw something
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}

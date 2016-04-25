package com.turing.base.android_hero.chapter6_Draw.surfaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-22  15:59.
 * @version 1.0
 * @desc
 */
public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    //SurfaceHolder
    private SurfaceHolder mSurfaceHolder;
    // 用于绘图的Canvas
    private Canvas mCanvas;
    // 子线程标志位
    private boolean mIsDrawing;


    /**
     * 构造函数
     *
     * @param context
     */
    public SurfaceViewTemplate(Context context) {
        super(context);
        initView();
    }

    /**
     * 构造函数
     *
     * @param context
     * @param attrs
     */
    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
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
    public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
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
        while (mIsDrawing) {
            draw();
        }
    }

    /**
     *
     */
    private void draw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            // draw something
        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}

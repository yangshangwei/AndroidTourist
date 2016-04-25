package com.turing.base.android_hero.chapter6_Draw.surfaceView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-23  10:32.
 * @version 1.0
 *          分析：
 *          画板放在哪里？
 *          View里，我们自定义一个View，在onDraw()里完成绘制，另外View还有个onTouchEvent的方法， 我们可以在获取用户的手势操作！
 *          需要准备些什么？
 *          一只画笔(Paint)，一块画布(Canvas)，一个路径(Path)记录用户绘制路线；
 *          另外划线的时候，每次都是从上次拖动时间的发生点到本次拖动时间的发生点！
 *          那么之前绘制的 就会丢失，为了保存之前绘制的内容，
 *          我们可以引入所谓的"双缓冲"技术： 其实就是每次不是直接绘制到Canvas上，而是先绘制到Bitmap上，等Bitmap上的绘制完了， 再一次性地绘制到View上！
 *          <p/>
 *          具体的实现流程？
 *          初始化画笔，设置颜色等等一些参数；
 *          在View的onMeasure()方法中创建一个View大小的Bitmap，
 *          同时创建一个Canvas；onTouchEvent中获得X,Y坐标，做绘制连线，
 *          最后invalidate()重绘，即调用 onDraw方法将bitmap的东东画到Canvas上！
 */
public class SimpleDrawByCustomView extends View {

    // 定义画布
    private Canvas mCanvas;
    // 定义画笔
    private Paint mPaint;
    // 记录用户绘制的Path
    private Path mPath;
    //缓存绘制的内容
    private Bitmap mBitmap;

    private int mLastX;
    private int mLastY;

    /**
     * @param context
     */
    public SimpleDrawByCustomView(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public SimpleDrawByCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SimpleDrawByCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    /**
     * 初始化工作，在构造函数中调用
     */
    private void init() {

        mPaint = new Paint();
        mPath = new Path();

        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND); //结合处为圆角
        mPaint.setStrokeCap(Paint.Cap.ROUND); // 设置转弯处为圆角
        mPaint.setStrokeWidth(20);   // 设置画笔宽度

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        // 初始化bitmap,Canvas
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mBitmap);
    }

    //重写该方法，在这里绘图
    @Override
    protected void onDraw(Canvas canvas) {
        drawPath();
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    //绘制线条
    private void drawPath() {
        mCanvas.drawPath(mPath, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);
                if (dx > 3 || dy > 3)
                    mPath.lineTo(x, y);
                mLastX = x;
                mLastY = y;
                break;
        }

        invalidate();
        return true;
    }
}

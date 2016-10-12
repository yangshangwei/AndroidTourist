package com.turing.nutritiousSerial.customViewClock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.apkfuns.logutils.LogUtils;

import java.util.Calendar;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-05-03  10:48.
 * @version 1.0
 *          http://blog.csdn.net/u012702547/article/details/51030141
 */
public class ClockView extends View {

    // 绘制时钟外部圆盘的画笔
    private Paint mCirclePaint;
    // 绘制时钟圆心的画笔
    private Paint mDotPaint;
    // 绘制时钟数字的画笔
    private Paint mNumPaint;

    // 绘制时钟时针的画笔
    private Paint mHourPaint;
    // 绘制时钟分针的画笔
    private Paint mMinPaint;
    // 绘制时钟秒针的画笔
    private Paint mSecPaint;

    // 时针的颜色
    private int mHourColor;
    // 分针的颜色
    private int mMinColor;
    // 秒针的颜色
    private int mSecColor;

    // 时针的宽度
    private int mHourWidth;
    // 分针的宽度
    private int mMinWidth;
    // 秒针的宽度
    private int mSecWidth;

    // 时间
    private Calendar mCalendar;

    // View高度 默认 256dp
    private int mHeight;
    // View宽度 默认 256dp
    private int mWidth;

    /**
     * 构造函数
     *
     * @param context
     */
    public ClockView(Context context) {
        this(context, null);
    }

    /**
     * 构造函数
     *
     * @param context
     * @param attrs
     */
    public ClockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 构造函数
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 初始化
        init();
    }


    /**
     * 初始化变量
     */
    private void init() {

        // 获取当前时间
        mCalendar = Calendar.getInstance();

        // 时钟默认宽度和高度
        mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                256,
                getResources().getDisplayMetrics());

        mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                256,
                getResources().getDisplayMetrics());

        LogUtils.e("width:" + mWidth + ",height" + mHeight);

        // 初始化表针的颜色
        mHourColor = Color.RED;
        mMinColor = Color.BLUE;
        mSecColor = Color.GREEN;

        // 初始化表针的宽度
        mHourWidth = 8;
        mMinWidth = 5;
        mSecWidth = 2;

        // 初始化时钟外部圆盘的画笔
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);// 去锯齿
        mCirclePaint.setColor(Color.BLACK);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth((float) 6.0);

        // 初始化圆心的画笔
        mDotPaint = new Paint();
        mDotPaint.setAntiAlias(true);
        mDotPaint.setColor(Color.YELLOW);
        mDotPaint.setStyle(Paint.Style.FILL);

        // 初始化时针的画笔
        mHourPaint = new Paint();
        mHourPaint.setAntiAlias(true);
        mHourPaint.setColor(mHourColor);
        mHourPaint.setStyle(Paint.Style.FILL);
        mHourPaint.setStrokeWidth(mHourWidth);

        // 初始化分针的画笔
        mMinPaint = new Paint();
        mMinPaint.setAntiAlias(true);
        mMinPaint.setColor(mMinColor);
        mMinPaint.setStyle(Paint.Style.FILL);
        mMinPaint.setStrokeWidth(mMinWidth);

        // 初始化秒针的画笔
        mSecPaint = new Paint();
        mSecPaint.setAntiAlias(true);
        mSecPaint.setColor(mSecColor);
        mSecPaint.setStyle(Paint.Style.FILL);
        mSecPaint.setStrokeWidth(mSecWidth);

        // 初始化数字的画笔
        mNumPaint = new Paint();
        mNumPaint.setColor(Color.RED);
        mNumPaint.setAntiAlias(true);
        mNumPaint.setTextSize(20);
        mNumPaint.setTextAlign(Paint.Align.CENTER);   //文本对齐方式

    }

    /**
     * 绘制View的核心方法
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 定义圆形的半径
        int radius = mWidth / 2 - 10;
        // 画表盘  参数： 1.圆心X轴坐标，2.圆心Y轴坐标，3.半径，4.画笔
        canvas.drawCircle(mWidth / 2, mHeight / 2, radius, mCirclePaint);

        // 画圆心
        canvas.drawCircle(mWidth / 2, mHeight / 2, 15, mDotPaint);

        // 绘制表盘的时间刻度 (时间刻度的绘制除了数字之外，还有对应的短线)
        /**
         *  方法一： 复杂的三角函数运算
         *  方法二： 每次只在十二点的那个位置上进行绘制，
         *  如果需要绘制1点，那么把画布逆时针旋转30度到十二点的位置，
         *  然后画上1和一个短线之后再将画布顺时针旋转30度，
         *  如果是绘制2点，那么我把画布逆时针旋转60度到十二点的位置，
         *  然后绘制上2和一个短线，绘制完成之后再将画布顺时针旋转60度
         *  依次类推
         */

        for (int i = 1; i < 13; i++) {
            // 旋转之前，保存画布状态
            canvas.save();
            // 旋转 参数解释： degrees：旋转的角度，正数为顺时针方向，负数为逆时针方向。 px：旋转点的x坐标。 py：旋转点的y坐标。
            canvas.rotate(i * 30, mWidth / 2, mHeight / 2);

            //1.2表示起点坐标，3.4表示终点坐标，5.画笔
            canvas.drawLine(mWidth / 2, mHeight / 2 - radius, mWidth / 2, mHeight / 2 - radius + 10, mCirclePaint);
            //画表盘数字1.要绘制的文本，2.文本x轴坐标，3.文本基线，4.文本画笔
            canvas.drawText(i + "", mWidth / 2, mHeight / 2 - radius + 22, mNumPaint);
            //恢复画布状态
            canvas.restore();

            /**
             * 用一个循环来绘制这十二个刻度，
             * 在每次旋转画布之前，通过canvas.save()方法来保存画布当前的状态，
             * 保存之后再对画布进行旋转操作，旋转完成之后就是画线画数字.
             * 在绘制完成之后，需要调用canvas的restore()方法，解除锁定的画布，这种方法主要用在save方法之后。
             * 使用save方法锁定画布并完成操作之后，需要使用restore方法解除锁定
             * 一般情况下，canvas.save()方法和canvas.restore()方法都是成对出现的
             */
        }


        mCalendar = Calendar.getInstance();

        // 获得当前小时
        int hour = mCalendar.get(Calendar.HOUR);
        canvas.save();
        // 旋转屏幕
        canvas.rotate(hour * 30, mWidth / 2, mHeight / 2);
        // 画时针
        canvas.drawLine(mWidth / 2, mHeight / 2 + 20, mWidth / 2, mHeight / 2 - 90, mHourPaint);
        canvas.restore();
        // 获得当前分钟
        int minute = mCalendar.get(Calendar.MINUTE);
        canvas.save();
        canvas.rotate(minute * 6, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, mHeight / 2 + 30, mWidth / 2, mHeight / 2 - 110, mMinPaint);
        canvas.restore();

        // 获得当前秒
        int second = mCalendar.get(Calendar.SECOND);
        canvas.save();
        canvas.rotate(second * 6, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, mHeight / 2 + 40, mWidth / 2, mHeight / 2 - 130, mSecPaint);
        canvas.restore();
        //每隔1秒重绘View,重绘会调用onDraw()方法
        postInvalidateDelayed(1000);

    }
}

package com.turing.base.android_hero.chapter6_Draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * MyApp
 *
 * http://book.51cto.com/art/201204/328273.htm
 *
 * http://book.51cto.com/art/201204/328247.htm
 *
 *
 *
 * @author Mr.Yang on 2016-04-12  15:16.
 * @version 1.0
 *          Canvas可以绘制的对象有：弧线(arcs)、填充颜色(argb和color)、 Bitmap、圆(circle和oval)、
 *          点(point)、线(line)、矩形(Rect)、图片(Picture)、圆角矩形 (RoundRect)、文本(text)、
 *          顶点(Vertices)、路径(path)。
 *          通过组合这些对象我们可以画出一些简单有趣的界面出来，
 *          但是光有这些功能还是不够的，如果我要画一个仪表盘(数字围绕显示在一个圆圈中)呢？
 *          幸好Android还提供了一些对Canvas位置转换的方法：rorate、scale、translate、skew(扭曲)等，
 *          而且它允许你通过获得它的转换矩阵对象(getMatrix方) 直接操作它。
 *          这些操作就像是虽然你的笔还是原来的地方画，但是画纸旋转或者移动了，
 *          所以你画的东西的方位就产生变化。
 *          为了方便一些转换操作，Canvas 还提供了保存和回滚属性的方法(save和restore)，
 *          比如你可以先保存目前画纸的位置(save)，然后旋转90度，向下移动100像素后画一些图形，
 *          画完后调用restore方法返回到刚才保存的位置。
 */
public class CanvasCustomView extends View {


    Paint paint;


    public CanvasCustomView(Context context) {
        super(context);
        initPaint();
    }

    public CanvasCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CanvasCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 初始化画笔-宽度为2的蓝色画笔
     * <p/>
     * <p/>
     * <p/>
     * setStrokeMiter(float miter )是设置笔画的倾斜度
     */
    public void initPaint() {

        paint = new Paint();
        // paint 默认画笔为矩形 .
        // Paint.setStrokeJoin(Join join)设置结合处的样子，
        // Miter:结合处为锐角， Round:结合处为圆弧：BEVEL：结合处为直线。
        paint.setStrokeJoin(Paint.Join.ROUND);
        // 设置笔刷的样式 Paint.Cap.Round ,Cap.SQUARE等分别为圆形、方形
        paint.setStrokeCap(Paint.Cap.ROUND);
        // 设置笔算的宽度
        paint.setStrokeWidth((float) 2.0);
        //设置是否使用图像抖动处理，会使画笔更平滑饱满。图像更清晰
        paint.setDither(true);


        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.BLUE);                    //设置画笔颜色
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.STROKE);             //空心效果

    }


    /**
     * 核心，在画布上画图
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        /**
         * 【功能说明】该方法用于在画布上绘制圆形，通过指定圆形圆心的坐标和半径来实现。
         *
         * 该方法是绘制圆形的主要方法，同时也可以通过设置画笔的空心效果来绘制空心的圆形。

         【基本语法】public void drawCircle (float cx, float cy, float radius, Paint paint)

         参数说明

         cx：圆心的x坐标。

         cy：圆心的y坐标。

         radius：圆的半径。

         paint：绘制时所使用的画笔。
         */


        canvas.drawColor(Color.WHITE);                  //白色背景


        canvas.drawCircle(50, 50, 10, paint);           //绘制圆形
        canvas.drawCircle(100, 100, 20, paint);         //绘制圆形
        canvas.drawCircle(150, 150, 30, paint);         //绘制圆形
        canvas.drawCircle(200, 200, 40, paint);         //绘制圆形
        canvas.drawCircle(250, 250, 50, paint);         //绘制圆形
        canvas.drawCircle(300, 300, 60, paint);         //绘制圆形
        canvas.drawCircle(350, 350, 70, paint);         //绘制圆形



        canvas.drawCircle(100,100,90,paint);
    }
}

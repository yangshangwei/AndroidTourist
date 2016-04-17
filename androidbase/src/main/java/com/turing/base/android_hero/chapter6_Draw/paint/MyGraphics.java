package com.turing.base.android_hero.chapter6_Draw.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.turing.base.R;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-12  21:59.
 * @version 1.0
 *          自定义View
 */
public class MyGraphics extends View implements Runnable {
    // 声明画笔对象
    Paint paint;

    /**
     * 构造函数
     *
     * @param context
     */
    public MyGraphics(Context context) {
        super(context);
        // 初始化Paint
        paint = new Paint();
        // 开启线程 Runnable对象
        new Thread(this).start();
    }


    /**
     * 重载onDraw方法
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制直线
        //drawLineMethod(canvas);
        // 绘制多条直线
        //drawLinesMethod(canvas);
        // 绘制一个点
        //drawPointMethod(canvas);
        // 绘制多个点
        //drawPointsMethod(canvas);
        // 绘制矩形
        //drawRectMethod(canvas);
        // 绘制圆角矩形
        //drawRoundRectMethod(canvas);
        // 绘制圆形
        //drawCircleMethod(canvas);
        // 绘制椭圆
        //drawOvalMethod(canvas);
        // 绘制任意多边形
        //drawPathMethod(canvas);
        // 绘制圆弧
        //drawArcMethod(canvas);
        //绘制字符串
        //drawTextMethod(canvas);
        //在指定位置绘制文本
        drawPostTextMethod(canvas);
        // 绘制Bitmap
        //drawBitmapMetod(canvas);
        // 设置画布的显示区域
        //clipRectMethod(canvas);
        // 旋转画布
        //rotateMethod(canvas);
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            // 更新界面,会自动调用onDraw()一次  参考：http://www.jianshu.com/p/457d74f443e2
            //postInvalidate()是重绘的，也就是调用postInvalidate()后系统会重新调用onDraw方法画一次
            postInvalidate();
        }

    }

    /**
     * 【功能说明】该方法用于在画布上绘制直线，通过指定直线的两个端点坐标来绘制。
     * 该方法只能绘制单条直线；
     * 如果需要同时绘制多条直线，则可以使用drawLines方法。
     * <p/>
     * public void drawLineMethod (float startX, float startY, float stopX, float stopY, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * startX：起始端点的X坐标。
     * <p/>
     * startY：起始端点的Y坐标。
     * <p/>
     * stopX：终止端点的X坐标。
     * <p/>
     * stopY：终止端点的Y坐标。
     * <p/>
     * paint：绘制直线所使用的画笔。
     *
     * @param canvas
     */
    private void drawLineMethod(Canvas canvas) {
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //设置背景颜色
        paint.setStrokeWidth((float) 1.0);              //设置线宽
        canvas.drawLine(50, 50, 450, 50, paint);        //绘制直线
        paint.setStrokeWidth((float) 5.0);              //设置线宽
        canvas.drawLine(50, 150, 450, 150, paint);      //绘制直线
        paint.setStrokeWidth((float) 10.0);             //设置线宽
        canvas.drawLine(50, 250, 450, 250, paint);      //绘制直线
        paint.setStrokeWidth((float) 15.0);             //设置线宽
        canvas.drawLine(50, 350, 450, 350, paint);      //绘制直线
        paint.setStrokeWidth((float) 20.0);             //设置线宽
        canvas.drawLine(50, 450, 450, 450, paint);      //绘制直线
    }


    /**
     * 【功能说明】该方法用于在画布上绘制多条直线，通过指定直线的端点坐标数组来绘制。该方法可以绘制多条直线，非常灵活。
     * <p/>
     * 【基本语法】public void drawLines (float[] pts, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * pts：绘制直线的端点数组，每条直线占用4个数据。
     * <p/>
     * paint：绘制直线所使用的画笔
     *
     * @param canvas
     */
    private void drawLinesMethod(Canvas canvas) {
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        float[] pts = {50, 50, 400, 50,
                400, 50, 400, 600,
                400, 600, 50, 600,
                60, 600, 50, 50};                  //数据
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 5.0);              //线宽
        canvas.drawLines(pts, paint);                   //绘制多条直线
    }


    /**
     * 【功能说明】该方法用于在画布上绘制一个点，通过指定端点坐标来绘制。
     * 该方法只能绘制单个点；
     * 如果需要同时绘制多个点，则可以使用drawPoints方法。
     * <p/>
     * 【基本语法】public void drawPoint (float x, float y, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * x：绘制点的X坐标。
     * <p/>
     * y：绘制点的Y坐标。
     * <p/>
     * paint：绘制直线所使用的画笔。
     *
     * @param canvas
     */
    private void drawPointMethod(Canvas canvas) {

        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 20.0);         //线宽
        canvas.drawPoint(300, 300, paint);                //绘制点

    }


    /**
     * 【功能说明】该方法用于在画布上绘制多个点，通过指定端点坐标数组来绘制。
     * 该方法可以绘制多个点，同时也可以指定哪些点绘制，而哪些点不绘制，非常灵活。
     * <p/>
     * 【基本语法】drawPoints方法可以采用如下两种形式：
     * <p/>
     * public void drawPoints (float[] pts, Paint paint)
     * <p/>
     * public void drawPoints (float[] pts, int offset, int count, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * pts：绘制端点的数组，每个端点占用两个数据。
     * <p/>
     * offset：跳过的数据个数，这些数据将不参与绘制过程。
     * <p/>
     * count：实际参与绘制的数据个数。
     * <p/>
     * paint：绘制时所使用的画笔。
     *
     * @param canvas
     */
    private void drawPointsMethod(Canvas canvas) {
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        float[] pts = {50, 50,
                400, 50,
                400, 600,
                60, 600,};                      //数据
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 20.0);         //线宽
        canvas.drawPoints(pts, paint);                   //绘制点
    }


    /**
     * 【功能说明】该方法用于在画布上绘制矩形，可以通过指定矩形的四条边来实现，
     * 也可以通过指定Rect对象来实现。
     * 同时也可以通过设置画笔的空心效果来绘制空心的矩形。
     * <p/>
     * 【基本语法】drawRect方法可以采用如下几种形式：
     * <p/>
     * public void drawRect (Rect r, Paint paint)
     * <p/>
     * public void drawRect (RectF rect, Paint paint)
     * <p/>
     * public void drawRect (float left, float top, float right, float bottom, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * r：Rect对象。
     * <p/>
     * rect：RectF对象。
     * <p/>
     * left：矩形的左边位置。
     * <p/>
     * top：矩形的上边位置。
     * <p/>
     * right：矩形的右边位置。
     * <p/>
     * bottom：矩形的下边位置。
     * <p/>
     * paint：绘制时所使用的画笔。
     *
     * @param canvas
     */
    private void drawRectMethod(Canvas canvas) {

        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.FILL);                   //实心效果
        Rect r = new Rect();                          //Rect对象
        r.left = 50;                                  //左边
        r.top = 50;                                   //上边
        r.right = 450;                                    //右边
        r.bottom = 250;                                   //下边
        canvas.drawRect(r, paint);                      //绘制矩形
        canvas.drawRect(50, 400, 450, 600, paint);      //绘制矩形
    }


    /**
     * 【功能说明】该方法用于在画布上绘制圆角矩形，通过指定RectF对象以及圆角半径来实现。
     * 该方法是绘制圆角矩形的主要方法，同时也可以通过设置画笔的空心效果来绘制空心的圆角矩形。
     * <p/>
     * 【基本语法】public void drawRoundRect (RectF rect, float rx, float ry, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * rect：RectF对象。
     * <p/>
     * rx：x方向上的圆角半径。
     * <p/>
     * ry：y方向上的圆角半径。
     * <p/>
     * paint：绘制时所使用的画笔。
     *
     * @param canvas
     */
    private void drawRoundRectMethod(Canvas canvas) {
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.STROKE);                   //空心效果
        Rect r1 = new Rect();                         //Rect对象
        r1.left = 50;                                 //左边
        r1.top = 50;                                  //上边
        r1.right = 450;                                   //右边
        r1.bottom = 250;                              //下边
        canvas.drawRect(r1, paint);                 //绘制矩形
        RectF r2 = new RectF();                           //RectF对象
        r2.left = 50;                                 //左边
        r2.top = 400;                                 //上边
        r2.right = 450;                                   //右边
        r2.bottom = 600;                              //下边
        canvas.drawRoundRect(r2, 10, 10, paint);        //绘制圆角矩形
    }


    /**
     * 【功能说明】该方法用于在画布上绘制圆形，通过指定圆形圆心的坐标和半径来实现。该方法是绘制圆形的主要方法，同时也可以通过设置画笔的空心效果来绘制空心的圆形。
     * <p/>
     * 【基本语法】public void drawCircle (float cx, float cy, float radius, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * cx：圆心的x坐标。
     * <p/>
     * cy：圆心的y坐标。
     * <p/>
     * radius：圆的半径。
     * <p/>
     * paint：绘制时所使用的画笔。
     *
     * @param canvas
     */
    private void drawCircleMethod(Canvas canvas) {
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.STROKE);                   //空心效果

        canvas.drawCircle(50, 50, 10, paint);           //绘制圆形
        canvas.drawCircle(100, 100, 20, paint);         //绘制圆形
        canvas.drawCircle(150, 150, 30, paint);         //绘制圆形
        canvas.drawCircle(200, 200, 40, paint);         //绘制圆形
        canvas.drawCircle(250, 250, 50, paint);         //绘制圆形
        canvas.drawCircle(300, 300, 60, paint);         //绘制圆形
        canvas.drawCircle(350, 350, 70, paint);         //绘制圆形
    }

    /**
     * 【功能说明】该方法用于在画布上绘制椭圆形，通过指定椭圆外切矩形的RectF对象来实现。该方法是绘制椭圆形的主要方法，同时也可以通过设置画笔的空心效果来绘制空心的椭圆形。
     * <p/>
     * 【基本语法】public void drawOval (RectF oval, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * oval：椭圆外切矩形的RectF对象。
     * <p/>
     * paint：绘制时所使用的画笔。
     *
     * @param canvas
     */
    private void drawOvalMethod(Canvas canvas) {
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.STROKE);             // 空心

        RectF oval = new RectF();                     //RectF对象
        oval.left = 100;                              //左边
        oval.top = 100;                                   //上边
        oval.right = 400;                             //右边
        oval.bottom = 300;                                //下边
        canvas.drawOval(oval, paint);                   //绘制椭圆
        oval.left = 150;                              //左边
        oval.top = 400;                                   //上边
        oval.right = 350;                             //右边
        oval.bottom = 700;                                //下边
        canvas.drawOval(oval, paint);                   //绘制椭圆
    }


    /**
     * 【功能说明】该方法用于在画布上绘制任意多边形，通过指定Path对象来实现。在Path对象中规划了多边形的路径信息。该方法是绘制任意多边形的主要方法，当然也可以使用drawLines方法来实现，但是drawPath方法更为灵活、方便。
     * <p/>
     * 【基本语法】public void drawPath (Path path, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * path：包含路径信息的Path对象。
     * <p/>
     * paint：绘制时所使用的画笔。
     *
     * @param canvas
     */
    private void drawPathMethod(Canvas canvas) {
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();                     //Path对象
        path.moveTo(50, 100);                           //起始点
        path.lineTo(50, 300);                           //连线到下一点
        path.lineTo(100, 500);                      //连线到下一点
        path.lineTo(400, 500);                      //连线到下一点
        path.lineTo(300, 300);                      //连线到下一点
        path.lineTo(450, 50);                           //连线到下一点
        path.lineTo(200, 200);                      //连线到下一点
        path.lineTo(50, 100);
        canvas.drawPath(path, paint);                   //绘制任意多边形
    }

    /**
     * 【功能说明】该方法用于在画布上绘制圆弧，
     * 通过指定圆弧所在的椭圆对象、起始角度、终止角度来实现。
     * 该方法是绘制圆弧的主要方法。
     * <p/>
     * 【基本语法】public void drawArc (RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * oval：圆弧所在的椭圆对象。
     * <p/>
     * startAngle：圆弧的起始角度。
     * <p/>
     * sweepAngle：圆弧的角度。
     * <p/>
     * useCenter：是否显示半径连线，true表示显示圆弧与圆心的半径连线，false表示不显示。
     * <p/>
     * paint：绘制时所使用的画笔。
     *
     * @param canvas
     */
    private void drawArcMethod(Canvas canvas) {
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.STROKE);

        RectF oval = new RectF();                     //RectF对象
        oval.left = 100;                              //左边
        oval.top = 100;                                   //上边
        oval.right = 400;                             //右边
        oval.bottom = 300;                                //下边
        canvas.drawArc(oval, 225, 90, false, paint);    //绘制圆弧

        //RectF oval=new RectF();                       //RectF对象
        oval.left = 100;                              //左边
        oval.top = 400;                                   //上边
        oval.right = 400;                             //右边
        oval.bottom = 700;                                //下边
        canvas.drawArc(oval, 200, 135, true, paint);    //绘制圆弧
    }


    /**
     * 【功能说明】该方法用于在画布上绘制字符串，通过指定字符串的内容和显示的位置来实现。
     * 在画布上绘制字符串是经常用到的操作，
     * Android系统提供了非常灵活的绘制字符串的方法，
     * 可以根据不同的需要调用不同的方法来实现。
     * 字体的大小、样式等信息都需要在Paint画笔中来指定。
     * <p/>
     * 【基本语法】drawText方法可以采用如下几种形式：
     * <p/>
     * public void drawText (String text, float x, float y, Paint paint)
     * <p/>
     * public void drawText (char[] text, int index, int count, float x, float y, Paint paint)
     * <p/>
     * public void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
     * <p/>
     * public void drawText (String text, int start, int end, float x, float y, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * text：字符串内容，可以采用String格式，也可以采用char字符数组形式。
     * <p/>
     * x：显示位置的x坐标。
     * <p/>
     * y：显示位置的y坐标。
     * <p/>
     * index：显示的起始字符位置。
     * <p/>
     * count：显示字符的个数。
     * <p/>
     * start：显示的起始字符位置。
     * <p/>
     * end：显示的终止字符位置。
     * <p/>
     * paint：绘制时所使用的画笔。
     *
     * @param canvas
     */
    private void drawTextMethod(Canvas canvas) {
        paint.setAntiAlias(true);                                   //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                                //设置画笔颜色
        canvas.drawColor(Color.WHITE);                              //白色背景
        paint.setTextSize((float) 30.0);                            //设置字体大小

        String str = "小工匠的进阶之旅";                         //字符串
        char[] ch = {'H', 'e', 'l', 'l', 'o', ' ', 'A', 'n', 'd', 'r', 'o', 'i', 'd'};    //字符数组

        canvas.drawText(str, 50, 200, paint);                           //绘制字符串
        canvas.drawText(ch, 0, ch.length, 50, 300, paint);              //绘制字符串
        canvas.drawText(str + " API详解", 0, str.length() + 6, 50, 400, paint);//绘制字符串
        canvas.drawText(str, 7, str.length(), 50, 500, paint);          //绘制字符串
    }




    /**
     * 在指定的位置绘制文本
     * <p/>
     * void	drawPosText(char[] text, int index, int count, float[] pos, Paint paint)
     * Draw the text in the array, with each character's origin specified by the pos array.
     * <p/>
     * void	drawPosText(String text, float[] pos, Paint paint)
     * Draw the text in the array, with each character's origin specified by the pos array.
     *
     * @param canvas
     */
    private void drawPostTextMethod(Canvas canvas) {
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setTextSize((float) 30.0);


        String text = "小工匠的进阶之旅";
        canvas.drawPosText(text,
                new float[]{
                        50, 50,
                        100, 100,
                        150, 150,
                        200, 200,
                        250, 250,
                        300, 300,
                        350, 350,
                        400, 400
                },
                paint);
    }




    /**
     * 【功能说明】该方法用于在画布上绘制图像，通过指定Bitmap对象来实现。
     * 前面的各个方法都是自己绘制各个图形，
     * 但我们的应用程序往往需要直接引用一些图片资源。
     * 这个时候，便可以使用drawBitmap方法来在画布上直接显示图像。
     * <p/>
     * 【基本语法】public void drawBitmap (Bitmap bitmap, float left, float top, Paint paint)
     * <p/>
     * 参数说明
     * <p/>
     * bitmap：Bitmap对象，代表了图像资源。
     * <p/>
     * left：图像显示的左边位置。
     * <p/>
     * top：图像显示的上边位置。
     * <p/>
     * paint：绘制时所使用的画笔。
     *
     * @param canvas
     */
    private void drawBitmapMetod(Canvas canvas) {
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.STROKE);

        Bitmap bitmap = null;                         //Bitmap对象
        bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.gur_project_9)).getBitmap();
        canvas.drawBitmap(bitmap, 50, 50, null);        //绘制图像
        bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.flag_mark_blue)).getBitmap();
        canvas.drawBitmap(bitmap, 50, 150, null);       //绘制图像
        bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.gur_project_2)).getBitmap();
        canvas.drawBitmap(bitmap, 50, 450, null);       //绘制图像
    }


    /**
     * 【功能说明】该方法用于裁剪画布，也就是设置画布的显示区域。
     * 在使用时，可以使用Rect对象来指定裁剪区，也可以通过指定矩形的4条边来指定裁剪区。
     * 该方法主要用于部分显示以及对画布中的部分对象进行操作的场合。
     * <p/>
     * 【基本语法】clipRect方法有如下几种形式：
     * <p/>
     * public boolean clipRect (Rect rect)
     * <p/>
     * public boolean clipRect (float left, float top, float right, float bottom)
     * <p/>
     * public boolean clipRect (int left, int top, int right, int bottom)
     * <p/>
     * 参数说明
     * <p/>
     * rect：Rect对象，用于定义裁剪区的范围。
     * <p/>
     * left：矩形裁剪区的左边位置，可以是浮点型或者整型。
     * <p/>
     * top：矩形裁剪区的上边位置，可以是浮点型或者整型。
     * <p/>
     * right：矩形裁剪区的右边位置，可以是浮点型或者整型。
     * <p/>
     * bottom：矩形裁剪区的下边位置，可以是浮点型或者整型。
     *
     * @param canvas
     */
    private void clipRectMethod(Canvas canvas) {
        paint.setAntiAlias(true);                           //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                        //设置画笔颜色
        paint.setTextSize((float) 30.0);                    //设置字体大小

        canvas.clipRect(100, 100, 350, 600);                //设置显示范围
        canvas.drawColor(Color.WHITE);                      //白色背景
        canvas.drawText("Hello Android!", 150, 300, paint); //绘制字符串

    }


    /**
     * 【功能说明】该方法用于旋转画布，通过旋转画布，可以将画布上绘制的对象旋转。
     * 在使用这个方法的时候，将会把画布上的所有对象都旋转。
     * 为了只对某一个对象进行旋转，则可以通过save方法锁定画布，
     * 然后执行旋转操作，最后通过restore方法解锁，
     * 此后再绘制其他图形。
     * <p/>
     * 【基本语法】rotate方法有如下几种形式：
     * <p/>
     * public void rotate (float degrees)
     * <p/>
     * public final void rotate (float degrees, float px, float py)
     * <p/>
     * 参数说明
     * <p/>
     * degrees：旋转的角度，正数为顺时针方向，负数为逆时针方向。
     * <p/>
     * px：旋转点的x坐标。
     * <p/>
     * py：旋转点的y坐标。
     *
     * @param canvas
     */
    private void rotateMethod(Canvas canvas) {
        paint.setAntiAlias(true);                           //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                        //设置画笔颜色
        paint.setTextSize((float) 30.0);                    //设置字体大小
        canvas.drawColor(Color.WHITE);                      //白色背景

        canvas.clipRect(50, 50, 400, 700);                  //设置裁剪区
        canvas.save();                                  //锁定画布
        canvas.rotate(45, 230, 250);                        //旋转45
        paint.setColor(Color.BLUE);                     //设置画笔颜色
        canvas.drawText("Hello Android!", 130, 250, paint); //绘制字符串
        canvas.restore();                                   //解除锁定

        paint.setColor(Color.RED);                          //设置画笔颜色
        canvas.drawText("Hello Android!", 130, 250, paint); //绘制字符串
        RectF oval = new RectF();                         //RectF对象
        oval.left = 150;                                  //左边
        oval.top = 500;                                       //上边
        oval.right = 350;                                 //右边
        oval.bottom = 600;                                    //下边
        canvas.drawOval(oval, paint);                       //绘制椭圆

        /**
         * 首先设置了画布和画布的参数。
         * 接着设置画布的显示区，并锁定画布，将画布旋转45 ，然后在此画布上绘制字符串，
         * 最后解锁画布。
         * 此后，在同一位置绘制相同的字符串，并继续绘制了一个椭圆。
         */
    }
}

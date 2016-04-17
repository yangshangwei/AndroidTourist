package com.turing.base.android_hero.chapter6_Draw.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-13  16:59.
 * @version 1.0
 * @desc
 */
public class MyGraphicsTextAttrs extends View implements Runnable {

    Paint paint;

    public MyGraphicsTextAttrs(Context context) {
        super(context);
        // 初始化Paint
        paint = new Paint();
        // 开启线程
        new Thread(this).start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED);                      //设置画笔颜色
        paint.setTypeface(Typeface.DEFAULT_BOLD);       //设置字体样式
        paint.setTextScaleX(2);                         //设置比例因子
        paint.setARGB(255, 0, 0, 0);                    // 设置透明度和颜色
        paint.setLinearText(true);                      // 设置下画线
        paint.setTextSkewX((float) -1.0);                   //设置倾斜因子


        canvas.drawColor(Color.WHITE);                  //设置画布背景
        paint.setTextSize(20);                          //设置画笔字体的大小
        canvas.drawText("小工匠的进阶之路", 10, 50, paint);
        paint.setTextSize(30);                          //设置画笔字体的大小
        canvas.drawText("小工匠的进阶之路", 10, 150, paint);
        paint.setTextSize(40);                          //设置画笔字体的大小
        canvas.drawText("小工匠的进阶之路", 10, 250, paint);
        paint.setTextSize(50);                          //设置画笔字体的大小
        canvas.drawText("小工匠的进阶之路", 10, 350, paint);

    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.interrupted();
            }
            // 更新UI
            postInvalidate();
        }

    }


}

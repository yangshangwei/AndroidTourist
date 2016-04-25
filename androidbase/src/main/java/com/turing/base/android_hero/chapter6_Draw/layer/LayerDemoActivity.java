package com.turing.base.android_hero.chapter6_Draw.layer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LayerDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new CustomLayer(this));

    }

    /**
     * 自定义View
     */
    class CustomLayer extends View {

        private Paint mPaint;

        private static final int LAYER_FLAGS =
                Canvas.MATRIX_SAVE_FLAG |
                        Canvas.CLIP_SAVE_FLAG |
                        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                        Canvas.CLIP_TO_LAYER_SAVE_FLAG;


        public CustomLayer(Context context) {
            super(context);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 画布背景白色
            canvas.drawColor(Color.WHITE);
            // 画笔红色
            mPaint.setColor(Color.BLUE);
            // 画圆 以150,150为圆心，100为半径
            canvas.drawCircle(150, 150, 100, mPaint);
            // 具体的透明度效果 可以参考第5个参数，取值范围0~255，值越大越不透明
            canvas.saveLayerAlpha(0, 0, 400, 400, 125, LAYER_FLAGS);

            mPaint.setColor(Color.RED);
            canvas.drawCircle(200, 200, 100, mPaint);
            canvas.restore();
        }

    }
}
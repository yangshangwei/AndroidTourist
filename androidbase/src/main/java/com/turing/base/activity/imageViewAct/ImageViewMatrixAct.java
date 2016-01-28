package com.turing.base.activity.imageViewAct;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.turing.base.R;

public class ImageViewMatrixAct extends Activity implements View.OnClickListener {

    private Button btn_scale, btn_rotate, btn_translate, btn_skew;
    private ImageView iv_base, iv_after;
    private Bitmap baseBitmap;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_matrix);

        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_translate = (Button) findViewById(R.id.btn_translate);
        btn_skew = (Button) findViewById(R.id.btn_skew);

        btn_scale.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_translate.setOnClickListener(this);
        btn_skew.setOnClickListener(this);

        iv_base = (ImageView) findViewById(R.id.iv_base);
        iv_after = (ImageView) findViewById(R.id.iv_after);

        baseBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.zjl);
        iv_base.setImageBitmap(baseBitmap);

        // 设置画笔，消除锯齿
        paint = new Paint();
        paint.setAntiAlias(true);
    }


    /**
     * 缩放图片
     */
    protected void bitmapScale(float x, float y) {
        // 因为要将图片放大，所以要根据放大的尺寸重新创建Bitmap
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() * x),
                (int) (baseBitmap.getHeight() * y), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 根据传入的参数设置缩放比例
        matrix.setScale(x, y);
        // 根据缩放比例，把图片draw到Canvas上
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 倾斜图片
     */
    protected void bitmapSkew(float dx, float dy) {
        // 根据图片的倾斜比例，计算变换后图片的大小，
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth()
                + (int) (baseBitmap.getWidth() * dx), baseBitmap.getHeight()
                + (int) (baseBitmap.getHeight() * dy), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 设置图片倾斜的比例
        matrix.setSkew(dx, dy);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 图片移动
     */
    protected void bitmapTranslate(float dx, float dy) {
        // 需要根据移动的距离来创建图片的拷贝图大小
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() + dx),
                (int) (baseBitmap.getHeight() + dy), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 设置移动的距离
        matrix.setTranslate(dx, dy);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 图片旋转
     */
    protected void bitmapRotate(float degrees) {
        // 创建一个和原图一样大小的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(),
                baseBitmap.getHeight(), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 根据原图的中心位置旋转
        matrix.setRotate(degrees, baseBitmap.getWidth() / 2,
                baseBitmap.getHeight() / 2);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_scale:
                bitmapScale(2.0f, 4.0f);
                break;
            case R.id.btn_rotate:
                bitmapRotate(180);
                break;
            case R.id.btn_translate:
                bitmapTranslate(20f, 20f);
                break;
            case R.id.btn_skew:
                bitmapSkew(0.2f, 0.4f);
                break;
            default:
                break;
        }
    }
}


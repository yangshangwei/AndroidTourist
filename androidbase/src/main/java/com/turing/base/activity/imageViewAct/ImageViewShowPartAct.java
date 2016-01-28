package com.turing.base.activity.imageViewAct;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.turing.base.R;

public class ImageViewShowPartAct extends Activity implements View.OnTouchListener {

    private ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_show_part);

        initView();
    }

    private void initView() {
        imageView1 = (ImageView) findViewById(R.id.id_iv_1);
        imageView2 = (ImageView) findViewById(R.id.id_iv_2);
        // 注册监听事件
        imageView1.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

//        float scale = (float) (500.0 / 320);
//
//        int x = (int) (event.getX() * scale);
//        int y = (int) (event.getY() * scale);
//
//        int width = (int) (100 * scale);
//        int height = (int) (100 * scale);
//
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView1.getDrawable();
//        //从原图上截取指定区域的图像，并将生成的Bitmap对象设置到第二个ImageView控件中
//        imageView2.setImageBitmap(Bitmap.createBitmap(bitmapDrawable.getBitmap(), x, y, width, height));


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4 ;

        Bitmap bitmap = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/IMG_20160126_083656.jpg",options);

        imageView2.setImageBitmap(bitmap);
        return false;
    }
}

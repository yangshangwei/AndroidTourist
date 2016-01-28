package com.turing.base.activity.imageViewAct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.turing.base.R;

public class ImageViewBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_base);


        ImageView imageView = (ImageView) findViewById(R.id.id_iv_baseUse);
        // 设置ImageView的宽高
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(200, 100));
        // 获得ImageView控件的宽和高,并将获得的值显示在Activity的标题栏上
        setTitle("宽度" + imageView.getLayoutParams().width + ",高度：" + imageView.getLayoutParams().height);
    }
}

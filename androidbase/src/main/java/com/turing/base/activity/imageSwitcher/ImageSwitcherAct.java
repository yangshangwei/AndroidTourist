package com.turing.base.activity.imageSwitcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.turing.base.R;

public class ImageSwitcherAct extends Activity implements ViewSwitcher.ViewFactory, View.OnClickListener {

    private ImageSwitcher imageSwitcher;

    @Override
    public View makeView()
    {

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundColor(0xFF000000);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT));

        return imageView;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.button1:
                imageSwitcher.setImageResource(R.drawable.item1);
                break;
            case R.id.button2:
                imageSwitcher.setImageResource(R.drawable.item2);
                break;
            case R.id.button3:
                imageSwitcher.setImageResource(R.drawable.item3);
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswitcher);
        // 设置视图工厂,也就是实现ViewFactory接口的类的实例对象
        imageSwitcher.setFactory(this);
        // 设置切换图像的淡入效果
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        // 设置切换图像的淡出效果
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

    }
}

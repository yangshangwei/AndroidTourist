package com.turing.base.android_hero.chapter6_Draw.animationDrawable;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.turing.base.R;
import com.turing.base.utils.AlertUtil;

/**
 * 【功能说明】该方法用于为帧动画对象添加动画帧。该方法主要用于动态修改帧动画内容的场合，可以根据需要增加一些动画帧。
 * <p/>
 * 【基本语法】public void addFrame (Drawable frame, int duration)
 * <p/>
 * 参数说明
 * <p/>
 * frame：动画帧的Drawable对象。
 * <p/>
 * duration：动画帧的持续时间，单位为毫秒。
 */
public class AnimationDrawable_addFrame extends AppCompatActivity implements View.OnClickListener {


    private Button mIdBtnStartAnimation, id_btn_stop;
    private ImageView mIdIvHorse;

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable_add_frame);

        mIdBtnStartAnimation = (Button) findViewById(R.id.id_btn_startAnimation);
        id_btn_stop = (Button) findViewById(R.id.id_btn_stop);

        mIdIvHorse = (ImageView) findViewById(R.id.id_iv_horse);

        //声明帧动画对象
        animationDrawable = (AnimationDrawable) mIdIvHorse.getBackground();
        // 注册监听监听事件
        mIdBtnStartAnimation.setOnClickListener(this);
        id_btn_stop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_startAnimation:


//                animationDrawable.addFrame(getResources().getDrawable(R.drawable.zzlx1), 100);//添加帧
//                animationDrawable.addFrame(getResources().getDrawable(R.drawable.zzlx2), 100);//添加帧
//                animationDrawable.addFrame(getResources().getDrawable(R.drawable.zzlx3), 100);//添加帧
//                animationDrawable.addFrame(getResources().getDrawable(R.drawable.zzlx4), 100);//添加帧
//                animationDrawable.addFrame(getResources().getDrawable(R.drawable.zzlx5), 100);//添加帧
//                animationDrawable.addFrame(getResources().getDrawable(R.drawable.zzlx6), 100);//添加帧
//                animationDrawable.addFrame(getResources().getDrawable(R.drawable.zzlx7), 100);//添加帧
//                animationDrawable.addFrame(getResources().getDrawable(R.drawable.zzlx8), 100);//添加帧

                animationDrawable.setOneShot(true);
                animationDrawable.setAlpha(100);
                animationDrawable.start();  //开始动画


                int num;
                num = animationDrawable.getNumberOfFrames();                     //获取帧数
                AlertUtil.showToastShort(AnimationDrawable_addFrame.this, "当前动画需要播放" + num + "帧");                      //显示
                break;
            case R.id.id_btn_stop:
                animationDrawable.stop();
                break;
            default:
                break;
        }
    }


}

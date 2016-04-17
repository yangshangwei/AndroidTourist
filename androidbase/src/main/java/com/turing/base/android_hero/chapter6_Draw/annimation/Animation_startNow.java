package com.turing.base.android_hero.chapter6_Draw.annimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.turing.base.R;

public class Animation_startNow extends AppCompatActivity {


    private ImageView mIdIvFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_start_now);

        mIdIvFace = (ImageView) findViewById(R.id.id_iv_face);


        // 设置移动效果
        /**
         *
         * float fromXDelta 动画开始的点离当前View X坐标上的差值
         * float toXDelta 动画结束的点离当前View X坐标上的差值
         * float fromYDelta 动画开始的点离当前View Y坐标上的差值
         * float toYDelta 动画开始的点离当前View Y坐标上的差值
         */
        Animation translateAnimation = new TranslateAnimation(0, 200, 0, 200);
        // 设置动画持续时间
        translateAnimation.setDuration(3000);
        // 为ImageView设置动画效果
        mIdIvFace.setAnimation(translateAnimation);
        // 启动动画
        translateAnimation.start();
    }

}

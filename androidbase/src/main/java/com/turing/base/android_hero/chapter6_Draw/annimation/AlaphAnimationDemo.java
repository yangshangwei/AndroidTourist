package com.turing.base.android_hero.chapter6_Draw.annimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.turing.base.R;

/**
 * AlphaAnimation类是Android系统中的透明度变化动画类，用于控制View对象的透明度变化，
 * 该类继承于Animation类。
 * AlphaAnimation类中的很多方法都与Animation类一致，
 * 该类中最常用的方法便是AlphaAnimation构造方法。
 * <p/>
 * 【基本语法】public AlphaAnimation (float fromAlpha, float toAlpha)
 * <p/>
 * 参数说明
 * <p/>
 * fromAlpha：开始时刻的透明度，取值范围0~1。
 * <p/>
 * toAlpha：结束时刻的透明度，取值范围0~1。
 * <p/>
 * 【实例演示】下面通过代码来演示如何设置一个简单的渐变透明度动画效果。
 */
public class AlaphAnimationDemo extends AppCompatActivity implements View.OnClickListener {

    private Button mIdBtnStartAnimation;
    private Button mIdBtnStopAnimation;
    private ImageView mIdIvFlag;


    private AlphaAnimation alphaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alaph_animation_demo);

        assignViews();

        alphaAnimation = new AlphaAnimation(0.1f, 1.0f);//设置透明度动画效果
    }

    private void assignViews() {
        mIdBtnStartAnimation = (Button) findViewById(R.id.id_btn_startAnimation1);
        mIdBtnStopAnimation = (Button) findViewById(R.id.id_btn_stopAnimation1);
        mIdIvFlag = (ImageView) findViewById(R.id.id_iv_flag1);

        mIdBtnStartAnimation.setOnClickListener(this);
        mIdBtnStopAnimation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_startAnimation1:// 开始动画
                alphaAnimation.setDuration(3000);              //持续时间
                mIdIvFlag.setAnimation(alphaAnimation);        //设置动画
                mIdIvFlag.startAnimation(alphaAnimation);      //启动动画
                break;
            case R.id.id_btn_stopAnimation1:// 取消动画
                alphaAnimation.cancel();
                break;
            default:
                break;
        }
    }
}

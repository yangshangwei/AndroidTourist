package com.turing.base.android_hero.chapter6_Draw.annimation_ViewAnimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.turing.base.R;

/**
 * AnimationSet类是Android系统中的动画集合类，用于控制View对象进行多个动作的组合，该类继承于Animation类。AnimationSet类中的很多方法都与Animation类一致，该类中最常用的方法便是addAnimation方法，该方法用于为动画集合对象添加动画对象。
 * <p/>
 * 【基本语法】public void addAnimation (Animation a)
 * <p/>
 * 其中，参数a为Animation动画对象，可以是前述任何一种补间动作。
 */
public class AnimationSetDemo extends AppCompatActivity implements View.OnClickListener {

    private Button mIdBtnStartAnimation;
    private Button mIdBtnStopAnimation;
    private ImageView mIdIvFlag;

    Animation translateAnimation, scaleAnimation, alphaAnimation;
    AnimationSet animationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_set_demo);
        assignViews();
        //设置位置变化动画
        translateAnimation = new TranslateAnimation(0, 300, 0, 300);
        //设置尺寸变化动画
        scaleAnimation = new
                ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置透明度变化动画
        alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
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

                translateAnimation.setDuration(10000);      //设置位置变化动画的持续时间
                scaleAnimation.setDuration(10000);          //设置尺寸变化动画的持续时间
                alphaAnimation.setDuration(10000);          //设置透明度渐变动画的持续时间

                animationSet = new AnimationSet(true);      //创建动画集对象
                animationSet.addAnimation(translateAnimation);       //添加位置变化动画
                animationSet.addAnimation(scaleAnimation);           //添加尺寸变化动画
                animationSet.addAnimation(alphaAnimation);           //添加透明度渐变动画

                animationSet.setFillAfter(true);                    //停留在最后的位置
                animationSet.setFillEnabled(true);


                mIdIvFlag.setAnimation(animationSet);                    //设置动画
                // animationSet.startNow();    高版本中无效,2.3可以,不建议使用
                mIdIvFlag.startAnimation(animationSet);    //启动动画

                break;
            case R.id.id_btn_stopAnimation1:// 取消动画
                animationSet.cancel();
                break;
            default:
                break;
        }
    }
}


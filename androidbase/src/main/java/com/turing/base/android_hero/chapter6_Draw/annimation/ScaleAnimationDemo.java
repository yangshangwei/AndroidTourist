package com.turing.base.android_hero.chapter6_Draw.annimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.turing.base.R;

/**
 * ScaleAnimation类是Android系统中的尺寸变化动画类，用于控制View对象的尺寸变化，该类继承于Animation类。
 * ScaleAnimation类中的很多方法都与Animation类一致，该类中最常用的方法便是ScaleAnimation构造方法。
 * <p/>
 * 【基本语法】public ScaleAnimation (float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
 * <p/>
 * 参数说明
 * <p/>
 * fromX：起始X坐标上的伸缩尺寸。
 * <p/>
 * toX：结束X坐标上的伸缩尺寸。
 * <p/>
 * fromY：起始Y坐标上的伸缩尺寸。
 * <p/>
 * toY：结束Y坐标上的伸缩尺寸。
 * <p/>
 * pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
 * <p/>
 * pivotXValue：X坐标的伸缩值。
 * <p/>
 * pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
 * <p/>
 * pivotYValue：Y坐标的伸缩值。
 */
public class ScaleAnimationDemo extends AppCompatActivity implements View.OnClickListener {

    private Button mIdBtnStartAnimation;
    private Button mIdBtnStopAnimation;
    private ImageView mIdIvFlag;


    ScaleAnimation scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_animation_demo);

        assignViews();
        //设置尺寸变化动画对象
        scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
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
                scaleAnimation.setDuration(3000);              //持续时间
                mIdIvFlag.setAnimation(scaleAnimation);        //设置动画
                mIdIvFlag.startAnimation(scaleAnimation);      //启动动画
                break;
            case R.id.id_btn_stopAnimation1:// 取消动画
                scaleAnimation.cancel();
                break;
            default:
                break;
        }
    }
}

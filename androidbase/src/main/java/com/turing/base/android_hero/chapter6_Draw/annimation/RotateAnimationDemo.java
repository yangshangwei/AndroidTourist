package com.turing.base.android_hero.chapter6_Draw.annimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;
import com.turing.base.utils.AlertUtil;

/**
 * RotateAnimation类是Android系统中的旋转变化动画类，用于控制View对象的旋转动作,
 * 该类继承于Animation类。
 * RotateAnimation类中的很多方法都与Animation类一致，
 * 该类中最常用的方法便是RotateAnimation构造方法。
 * <p/>
 * 【基本语法】public RotateAnimation (float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
 * <p/>
 * 参数说明
 * <p/>
 * fromDegrees：旋转的开始角度。
 * <p/>
 * toDegrees：旋转的结束角度。
 * <p/>
 * pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
 * <p/>
 * pivotXValue：X坐标的伸缩值。
 * <p/>
 * pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
 * <p/>
 * pivotYValue：Y坐标的伸缩值。
 */
public class RotateAnimationDemo extends AppCompatActivity implements View.OnClickListener {


    private Button mIdBtnStartAnimation;
    private Button mIdBtnStopAnimation;
    private ImageView mIdIvFlag;


    private Animation rotateAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_animation_demo);
        // 初始组件 注册监听
        assignViews();
        //设置旋转变化动画对象
        rotateAnimation = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);


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
                AlertUtil.showToastShort(RotateAnimationDemo.this, "start");
                LogUtils.d("start");
                rotateAnimation.setDuration(3000);              //持续时间
                mIdIvFlag.setAnimation(rotateAnimation);        //设置动画
                mIdIvFlag.startAnimation(rotateAnimation);      //启动动画
                break;
            case R.id.id_btn_stopAnimation1:// 取消动画
                rotateAnimation.cancel();
                break;
            default:
                break;
        }
    }
}

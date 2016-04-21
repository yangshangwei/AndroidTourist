package com.turing.base.android_hero.chapter6_Draw.annimation_ViewAnimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.turing.base.R;

/**
 * AnimationUtils类是Android系统中的动画工具类，提供了控制View对象的一些工具。该类中最常用的方法便是loadAnimation方法，该方法用于加载XML格式的动画配置文件。在Android系统中，除了在代码中设置动画效果外，还可以在XML配置文件中设置动画的组合动作，这种方式适用性更好。
 * <p/>
 * 【基本语法】public static Animation loadAnimation (Context context, int id)
 * <p/>
 * 参数说明
 * <p/>
 * context：上下文对象。
 * <p/>
 * id：动画配置文件的ID。
 */
public class AnimationUtilsDemo extends AppCompatActivity implements View.OnClickListener {

    private Button mIdBtnStartAnimation;
    private Button mIdBtnStopAnimation;
    private ImageView mIdIvFlag;
    //动画对象
    Animation loadAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_utils_demo);

        assignViews();
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

                loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.anim);
                mIdIvFlag.setAnimation(loadAnimation);                  //为控件设置动画
                loadAnimation.setFillAfter(true);                   //停留在结束位置
                loadAnimation.setFillEnabled(true);

                mIdIvFlag.startAnimation(loadAnimation);//开始动画
                break;
            case R.id.id_btn_stopAnimation1:// 取消动画
                loadAnimation.cancel();
                break;
            default:
                break;
        }
    }


}

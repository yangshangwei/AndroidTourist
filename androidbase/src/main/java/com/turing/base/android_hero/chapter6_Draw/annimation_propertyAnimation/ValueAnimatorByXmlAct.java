package com.turing.base.android_hero.chapter6_Draw.annimation_propertyAnimation;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.turing.base.R;

public class ValueAnimatorByXmlAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator_by_xml);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onScaleWidth(final View view) {
        // 获取屏幕宽带
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int width = metrics.widthPixels;


        /**
         * 属性动画则是通过AnimatorInflater类的loadAnimation()方法获取相应的Animator类实例。
         * 另外，ValueAnimator通过添加AnimatorUpdateListener监听器监听值的变化，从而再手动更新目标对象的属性。
         * 最后，通过调用valueAnimator.start()方法启动动画。
         */

        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animator);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                // 当前动画值，即为当前宽度比例值
                int currentValue = (Integer) animator.getAnimatedValue();
                // 根据比例更改目标view的宽度
                view.getLayoutParams().width = width * currentValue / 100;
                view.requestLayout();
            }
        });

        valueAnimator.start();
    }
}

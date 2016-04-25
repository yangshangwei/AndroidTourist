package com.turing.base.android_hero.chapter6_Draw.annimation_propertyAnimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

import com.turing.base.R;

public class AnimatorSetByXmlAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set_by_xml);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onScaleWith(View view) {
        // 获取屏幕宽度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int maxWidth = displayMetrics.widthPixels;
        // 将目标view进行包装
        ViewWrapper wrapper = new ViewWrapper(view, maxWidth);
        // 将xml转化为ObjectAnimator对象
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_set);
        // 设置动画的目标对象为包装后的view
        animatorSet.setTarget(wrapper);
        // 启动动画
        animatorSet.start();
    }

    private static class ViewWrapper {
        private View target;
        private int maxWidth;

        public ViewWrapper(View target, int maxWidth) {
            this.target = target;
            this.maxWidth = maxWidth;
        }

        public int getWidth() {
            return target.getLayoutParams().width;
        }

        public void setWidth(int widthValue) {
            target.getLayoutParams().width = maxWidth * widthValue / 100;
            target.requestLayout();
        }

        public void setMarginTop(int margin) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) target.getLayoutParams();
            layoutParams.setMargins(0, margin, 0, 0);
            target.setLayoutParams(layoutParams);
        }
    }
}

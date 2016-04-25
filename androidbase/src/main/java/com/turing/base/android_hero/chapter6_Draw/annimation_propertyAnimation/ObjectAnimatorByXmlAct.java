package com.turing.base.android_hero.chapter6_Draw.annimation_propertyAnimation;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.turing.base.R;

public class ObjectAnimatorByXmlAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator_by_xml);
    }

    /**
     * Button点击响应事件
     *
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onScaleWidth(View view) {
        // 获取屏幕的宽度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int width = displayMetrics.widthPixels;

        // 将目标view进行包装
        ViewWrapper wrapper = new ViewWrapper(view, width);

        // 将xml转化为ObjectAnimator对象
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this,
                R.animator.object_animator);

        // 设置动画的目标对象为包装后的view
        objectAnimator.setTarget(wrapper);

        // 启动动画
        objectAnimator.start();

    }


    /**
     * 与<animator>的例子相比，就只是多了一个android:propertyName的属性，设置值为width。
     * 也就是说，动画改变的属性为width，值将从100逐渐减到20。
     * 另外，值是从setWidth()传递过去的，再从getWidth()获取。
     * 而且，这里设置的值代表的是比例值，因此，还需要进行计算转化为实际的宽度值。
     * 最后，对象实际的宽度值为view.getLayoutParams().width。
     * 因此，我将用一个包装类来包装原始的view对象，对其提供setWidth()和getWidth()方法
     */

    private static class ViewWrapper {

        private View target;// 目标对象
        private int maxWidth; //最长宽度值

        public ViewWrapper(View target, int maxWidth) {
            this.target = target;
            this.maxWidth = maxWidth;
        }


        public int getWidth() {
            return target.getLayoutParams().width;
        }

        public void setWidth(int widthValue) {
            //widthValue的值从100到20变化
            target.getLayoutParams().width = maxWidth * widthValue / 100;
            target.requestLayout();
        }
    }
}

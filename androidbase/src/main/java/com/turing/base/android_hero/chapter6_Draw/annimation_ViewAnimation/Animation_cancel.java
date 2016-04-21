package com.turing.base.android_hero.chapter6_Draw.annimation_ViewAnimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;
import com.turing.base.utils.AlertUtil;

public class Animation_cancel extends AppCompatActivity implements View.OnClickListener {


    private Button mIdBtnStartAnimation;
    private Button mIdBtnStopAnimation;
    private ImageView mIdIvFlag;

    Animation translateAnimation;

    boolean repeat = false;
    boolean fillBefore = false;
    boolean fillAfter = false;
    boolean repeatMode;
    boolean startOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_cancel);

        mIdBtnStartAnimation = (Button) findViewById(R.id.id_btn_startAnimation);
        mIdBtnStopAnimation = (Button) findViewById(R.id.id_btn_stopAnimation);
        mIdIvFlag = (ImageView) findViewById(R.id.id_iv_flag);

        mIdBtnStartAnimation.setOnClickListener(this);
        mIdBtnStopAnimation.setOnClickListener(this);

        // 判断通过Intent传递的参数
        repeat = getIntent().getBooleanExtra("repeat", false);
        fillBefore = getIntent().getBooleanExtra("fillBefore", false);
        fillAfter = getIntent().getBooleanExtra("fillAfter", false);
        repeatMode = getIntent().getBooleanExtra("repeatMode", false);
        startOffset = getIntent().getBooleanExtra("startOffset", false);
        LogUtils.d("repeat:" + repeat + "\nfillBefore:" + fillBefore
                + "\nfillAfter:" + fillAfter + "\nrepeatMode:" + repeatMode
                + "\nstartOffset:" + startOffset);

        // 设置 移动效果
        translateAnimation = new TranslateAnimation(0, 200, 0, 200);


        mIdIvFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertUtil.showDialogWithClose(Animation_cancel.this, "视图动画只能作用于View，而且视图动画改变的只是View的绘制效果，" +
                        "View真正的属性并没有改变。" +
                        "比如，一个按钮做平移的动画，虽然按钮的确做了平移，但按钮可点击的区域并没随着平移而改变，还是在原来的位置");
            }
        });
    }

    /**
     * 2.3的版本 translateAnimation.startNow(); 有效，高版本无效 .
     * mIdIvFlag.startAnimation(translateAnimation); 高版本和低版本都有效。
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_startAnimation:
                // 设置动画时长
                translateAnimation.setDuration(3000);
                // 为imageView设置动画
                mIdIvFlag.setAnimation(translateAnimation);
                // 开启动画
                // translateAnimation.startNow();
                mIdIvFlag.startAnimation(translateAnimation);
                // 如果设置了 repeat为true，动画将执行2+1次
                if (repeat) {
                    translateAnimation.setRepeatCount(2);
                }
                // 设置起始填充
                if (fillBefore) {
                    translateAnimation.setFillBefore(true);             //回归起始位置
                    translateAnimation.setFillEnabled(true);            //使能填充效果
                    AlertUtil.showToastShort(Animation_cancel.this, "setFillBefore");
                }
                // 设置终止填充
                if (fillAfter) {
                    translateAnimation.setFillAfter(true);             //保留在终止位置
                    translateAnimation.setFillEnabled(true);            //使能填充效果  ;
                    AlertUtil.showToastShort(Animation_cancel.this, "setFillAfter");
                }
                // 设置重复模式
                if (repeatMode) {
                    translateAnimation.setRepeatCount(2);   //设置重复次数
                    // translateAnimation.setRepeatMode(Animation.RESTART); //重新从头
                    translateAnimation.setRepeatMode(Animation.REVERSE);  //反方向执
                    AlertUtil.showToastShort(Animation_cancel.this, "repeatMode 反方向执行");
                }
                // 设置启动时间
                if (startOffset) {
                    translateAnimation.setStartOffset(3000);  //设置启动时间
                    AlertUtil.showToastShort(Animation_cancel.this, "setStartOffset 延迟3S执行动画");
                }
                break;
            case R.id.id_btn_stopAnimation:
                translateAnimation.cancel();
                break;
            default:
                break;
        }
    }
}

package com.turing.base.android_hero.chapter6_Draw.animationDrawable;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.turing.base.R;
import com.turing.base.utils.AlertUtil;

public class AnimationDrawable_start extends AppCompatActivity implements View.OnClickListener {


    private Button mIdBtnStartAnimation, id_btn_stop;
    private ImageView mIdIvHorse;

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable_start);

        mIdBtnStartAnimation = (Button) findViewById(R.id.id_btn_startAnimation);
        id_btn_stop = (Button) findViewById(R.id.id_btn_stop);

        mIdIvHorse = (ImageView) findViewById(R.id.id_iv_horse);

        //声明帧动画对象 通过imageview.getBackground
        animationDrawable = (AnimationDrawable) mIdIvHorse.getBackground();
        // 注册监听监听事件
        mIdBtnStartAnimation.setOnClickListener(this);
        id_btn_stop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_startAnimation:
                if (!animationDrawable.isRunning()) {
                    animationDrawable.start();  //开始动画
                    AlertUtil.showToastShort(AnimationDrawable_start.this, "start");
                }
                break;
            case R.id.id_btn_stop:
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                    AlertUtil.showToastShort(AnimationDrawable_start.this, "stop");
                }
                break;
            default:
                break;
        }
    }
}

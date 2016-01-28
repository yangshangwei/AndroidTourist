package com.turing.base.activity.buttonAct;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.Button;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

public class TextAndPicButtonAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.d("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_and_pic_button);

        initView();

    }

    /**
     * 第五个按钮 通过代码的方式，在Button上显示图片
     * TODO 2.3版本没问题，4.1以上版本运行报错
     */
    public void initView() {


        Button button = (Button) findViewById(R.id.button);
        // 左侧图片
        SpannableString spannableStringLeft = new SpannableString("left");
        Bitmap bitmapLeft = BitmapFactory.decodeResource(getResources(),R.drawable.flag_mark_blue);
        ImageSpan imageSpanLeft = new ImageSpan(this,bitmapLeft,DynamicDrawableSpan.ALIGN_BOTTOM);
        spannableStringLeft.setSpan(imageSpanLeft, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 右侧图片
        SpannableString spannableStringRight = new SpannableString("right");
        Bitmap bitmapRight = BitmapFactory.decodeResource(getResources(),R.drawable.flag_mark_green);
        ImageSpan imageSpanRight = new ImageSpan(this,bitmapRight,DynamicDrawableSpan.ALIGN_BOTTOM);
        spannableStringRight.setSpan(imageSpanRight, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        button.append(spannableStringLeft);
        button.append("我的按钮");
        button.append(spannableStringRight);
    }
}

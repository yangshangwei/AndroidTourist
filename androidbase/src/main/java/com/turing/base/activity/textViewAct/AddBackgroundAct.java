package com.turing.base.activity.textViewAct;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.widget.TextView;

import com.turing.base.R;

public class AddBackgroundAct extends AppCompatActivity {

    private TextView tv_addbBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_background);

        initView();
    }

    private void initView() {
        tv_addbBackground = (TextView) findViewById(R.id.id_tv_addBackground);

        String text = "<没有背景><黄色背景>\n\n<蓝色背景,红色文字>";

        SpannableString spannableString = new SpannableString(text);
        int start = 6;
        int end = 12;

        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.YELLOW);
        spannableString.setSpan(backgroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        // <蓝色背景,红色文字> (每个 “\n”算一个长度)
        start = 14;
        // 创建ColorSpan
        ColorSpan colorSpan = new ColorSpan(Color.RED, Color.BLUE);
        // 将文字转换为ColorSpan对象
        spannableString.setSpan(colorSpan, start, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_addbBackground.setText(spannableString);


        TextView tv = (TextView)findViewById(R.id.id_tv_tv2);
        tv.setText("一整个字符串的普通的背景设置,文字颜色设置");
        tv.setBackgroundColor(Color.GREEN);
        tv.setTextColor(Color.DKGRAY);
    }


    /**
     * 自定义Span类,可以同时设置文字颜色和背景色
     */
    class ColorSpan extends CharacterStyle {

        private int mTextColor;
        private int mBackgroundColor;

        public ColorSpan(int mTextColor, int mBackgroundColor) {
            this.mTextColor = mTextColor;
            this.mBackgroundColor = mBackgroundColor;
        }

        @Override
        public void updateDrawState(TextPaint tp) {
            tp.bgColor = mBackgroundColor;
            tp.setColor(mTextColor);
        }
    }
}

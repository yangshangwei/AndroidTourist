package com.turing.base.activity.drawable;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.turing.base.R;

public class ColorDrawableActivity extends AppCompatActivity {


    private TextView mIdTvColorDrawableDemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_drawable);

        mIdTvColorDrawableDemo = (TextView) findViewById(R.id.id_tv_colorDrawableDemo_javaCode);

        /**
         * setBackground 在 4.1（API16）之后增加
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            /**
             * 使用Java代码创建ColorDrawable，
             * 需要注意的是Android中使用一个int类型的数据表示颜色值，
             * 通常习惯使用十六进制格式的数据表示颜色值。
             * 一个int类型包含四个字节，
             * 分别代表颜色的4个组成部分：透明度（Alpha）、红（RED）、绿（GREEN）、蓝（BLUE），
             * 每个部分由一个字节（8个bit）表示，取值范围为0~255。
             * 在xml中使用颜色时可以省略透明度（Alpha）部分，
             * 如#ff0000表示红色。
             * 但是在代码中必须要明确指出透明度（Alpha）代表的数据，
             * 如果省略了就表示完全透明的颜色,也就是说当绘制到画布上时，看不出有任何效果。
             */
            mIdTvColorDrawableDemo.setBackground(new ColorDrawable(0xffff0000));
        }
    }
}

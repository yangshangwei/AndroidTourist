package com.turing.base.android_hero.chapter6_Draw.paint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PaintAttrsAct extends AppCompatActivity {

    //声明自定义View对象 
    private MyGraphics myGraphics;
    private MyGraphicsTextAttrs myGraphicsTextAttrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建自定义View对象
        myGraphics = new MyGraphics(this);
        // myGraphicsTextAttrs = new MyGraphicsTextAttrs(this);
        //设置显示自定义View
        setContentView(myGraphics);
        // setContentView(myGraphicsTextAttrs);
    }
}

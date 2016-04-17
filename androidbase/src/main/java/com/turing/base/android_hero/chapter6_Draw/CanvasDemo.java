package com.turing.base.android_hero.chapter6_Draw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/1118/3702.html
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2012/1212/703.html
 */
public class CanvasDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //自定义一个View
        setContentView(new CanvasCustomView(this));

    }





}

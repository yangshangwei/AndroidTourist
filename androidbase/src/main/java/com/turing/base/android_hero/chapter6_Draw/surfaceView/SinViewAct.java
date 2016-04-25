package com.turing.base.android_hero.chapter6_Draw.surfaceView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SinViewAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SinView(this));
    }
}

package com.turing.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-02-26  15:36.
 * @version 1.0
 * @desc  取消标题
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }


}

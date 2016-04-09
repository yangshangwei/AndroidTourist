package com.turing.base.activity.fragment.staticload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.turing.base.R;

/**
 * 静态加载Fragment的Activity
 */
public class FragmentStaticLoadAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_static_load);


        //静态加载时可以直接获取到 Fragment中的UI控件
        TextView tv = (TextView) findViewById(R.id.textview);
        tv.setText("我在Act中获取到了Fragment中的UI控件");
    }
}

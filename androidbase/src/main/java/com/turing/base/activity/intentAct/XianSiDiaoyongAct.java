package com.turing.base.activity.intentAct;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.turing.base.R;

public class XianSiDiaoyongAct extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_xian_si_diaoyong);



    }
}

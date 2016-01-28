package com.turing.base.activity.tabhost;

import android.app.TabActivity;
import android.os.Bundle;

import com.turing.base.R;

public class TabHostAct extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);
    }
}

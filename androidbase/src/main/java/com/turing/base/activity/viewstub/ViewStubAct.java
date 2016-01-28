package com.turing.base.activity.viewstub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import com.turing.base.R;

public class ViewStubAct extends AppCompatActivity {

    private ViewStub vs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);

        vs = (ViewStub)findViewById(R.id.id_vs_1);
    }

    /**
     * 点击按钮 将ViewStub布局展示
     * @param view
     */
    public void clickShowViewStub(View view){

        vs.setVisibility(View.VISIBLE);
        // 或者 使用 vs.inflate();


    }

    /**
     * 点击按钮 将ViewStub布局展示
     * @param view
     */
    public void clickHideViewStub(View view){

        vs.setVisibility(View.GONE);

    }
}

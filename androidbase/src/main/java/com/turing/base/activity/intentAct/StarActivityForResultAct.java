package com.turing.base.activity.intentAct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.turing.base.R;

public class StarActivityForResultAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_activity_for_result);

    }

    /**
     *  android:onClick="doSomethingThenReturn"
     *  对应xml中的属性，记得方法里面的参数，否则报错
     * @param view
     */
    public void doSomethingThenReturn(View view){

        Intent intent = new Intent();
        intent.putExtra("value","返回给前个Act的值");
        // 通过intent对象返回结果，setResult的第一个参数是响应码
        setResult(2, intent);
        // 关闭当前Activity
        finish();
    }
}

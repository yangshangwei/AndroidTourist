package com.turing.base.activity.buttonAct;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

public class ButtonAct extends Activity implements View.OnClickListener {

    private Button button1,button2 ,button3;
    // -1 缩小， 1 放大
    private int value = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button2);

        initView();
        initEvent();
    }

    private void initView() {
        button1 = (Button) findViewById(R.id.id_btn_btnDemo1);
        button2 = (Button) findViewById(R.id.id_btn_btnDemo2);
        button3 = (Button) findViewById(R.id.id_btn_btnDemo3);
    }

    private void initEvent() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {

        Button button = (Button)v;

        switch (v.getId()){
            case R.id.id_btn_btnDemo1:
                // 如果button的宽度等于屏幕的宽度
                if(value ==1 && button.getWidth() == getWindowManager().getDefaultDisplay().getWidth()){
                    value = -1 ;
                    LogUtils.d("按钮的宽度=屏幕的宽度,将value的值设置为：" + String.valueOf(value));
                }else if (value == -1 && button.getWidth() <= 100) { // 如果按钮的宽度 小于等于100,按钮开始放大
                    // 以按钮宽度和高度的10%放大按钮
                    button.setWidth(button.getWidth() + (int) (button.getWidth() * 0.1) * value);
                    button.setHeight(button.getHeight() + (int) (button.getHeight() * 0.1) * value);
                    LogUtils.d(String.valueOf(value));
                }else{
                    LogUtils.d("啥也没匹配上");
                }
                break;
            case R.id.id_btn_btnDemo2:
                break;
            case R.id.id_btn_btnDemo3:
                Toast.makeText(this,"Button Style 注意观察Button",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}

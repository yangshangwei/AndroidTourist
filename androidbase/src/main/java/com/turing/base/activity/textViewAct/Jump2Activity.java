package com.turing.base.activity.textViewAct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.turing.base.R;

public class Jump2Activity extends Activity {

    private TextView tv_jump ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_jump2);

        initData();
    }

    /**
     * 点击TextView弹出Activity
     */
    private void initData() {

        tv_jump = (TextView)findViewById(R.id.id_tv_jump2Act);
        String text = "显示Activity";

        // 将文本转换成SpannableString对象
        SpannableString spannableString = new SpannableString(text);
        // 将text中的所有文本设置成ClickableSpan对象，并实现onClick方法
        spannableString.setSpan(new ClickableSpan() {
            // 在onClick方法中可以编写单击链接时要执行的动作
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(Jump2Activity.this,JumpTerminalAct.class));
            }
        },0,text.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置控件显示内容
        tv_jump.setText(spannableString);
        // 在点击链接时凡是要有执行的动作，都必须要设置MovementMethod对象
        tv_jump.setMovementMethod(LinkMovementMethod.getInstance());


    }
}

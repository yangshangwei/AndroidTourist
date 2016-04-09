package com.turing.base.activity.popupWindowDemo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.base.R;

public class PopupWindowAct extends AppCompatActivity {


    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        mContext = PopupWindowAct.this;
    }



    public void showPopupWindow(View view){

        initPopWindow(view);

    }



    private void initPopWindow(View v) {
        // 将自定义布局转换为View
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_popup, null, false);
        TextView tv1 = (TextView) view.findViewById(R.id.textview1);
        TextView tv2 = (TextView) view.findViewById(R.id.textview2);

        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //设置加载动画
        popWindow.setAnimationStyle(R.anim.anim_pop);

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });
        //要为popWindow设置一个背景才有效
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 50, 0);


        //设置popupWindow里的按钮的事件
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PopupWindowAct.this, "你点击了选项一~", Toast.LENGTH_SHORT).show();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PopupWindowAct.this, "你点击了选项二~", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });
    }
}

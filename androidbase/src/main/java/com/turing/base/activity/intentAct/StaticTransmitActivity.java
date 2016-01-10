package com.turing.base.activity.intentAct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.turing.base.R;

public class StaticTransmitActivity extends AppCompatActivity {


    public  static String msg ;
    public  static int age ;
    public  static Data  data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_transmit);

        StringBuffer sb = new StringBuffer();
        sb.append("msg:" + msg);
        sb.append("\n");
        sb.append("age:"+age);
        sb.append("\n");
        sb.append("data name:"+data.getName());
        sb.append("\n");
        sb.append("data id:"+data.getId());

        TextView textView = (TextView)findViewById(R.id.id_tv_static);
        textView.setText(sb.toString());
    }
}

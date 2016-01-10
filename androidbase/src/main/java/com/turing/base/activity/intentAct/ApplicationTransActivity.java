package com.turing.base.activity.intentAct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.turing.base.AppContext;
import com.turing.base.R;

public class ApplicationTransActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_trans);

        // 获取全局变量
        AppContext context = (AppContext)getApplication();

        String name = context.appName;
        Data data = context.data;

        StringBuffer sb = new StringBuffer();
        sb.append("AppContext appname:" + name );
        sb.append("\n");
        sb.append("AppContext data.id:" + data.getId());
        sb.append("\n");
        sb.append("AppContext data.name:" + data.getName());

        TextView textView = (TextView) findViewById(R.id.id_tv_app_trans);
        textView.setText(sb.toString());

    }
}

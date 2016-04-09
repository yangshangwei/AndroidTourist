package com.turing.base.activity.intentAct;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

public class GetIntentActivity extends Activity {

    private TextView tv_getIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_intent);

        tv_getIntent = (TextView) findViewById(R.id.id_tv_getIntent);

        // 获取String
        String msg = getIntent().getStringExtra("intent_string");
        LogUtils.d("String:" + msg);
        // 获取Int
        int value = getIntent().getExtras().getInt("intent_int");
        LogUtils.d("int:" + value);

        int vaule2 = getIntent().getIntExtra("intent_int" , 0);
        LogUtils.d("第二种获取方式:" + vaule2);

        // 获取可序列化对象
        Data data = (Data) getIntent().getSerializableExtra("intent_object");
        LogUtils.d("Data: name" + data.getName() + ",id:" + data.getId());



        StringBuffer sb = new StringBuffer();
        sb.append("intent_string:" + msg);
        sb.append("\n");
        sb.append("intent_int:" + value);
        sb.append("\n");
        sb.append("intent_object: name-"+data.getName());
        sb.append("\n");
        sb.append("intent_object: id-"+data.getId());

        tv_getIntent.setText(sb.toString());



    }
}

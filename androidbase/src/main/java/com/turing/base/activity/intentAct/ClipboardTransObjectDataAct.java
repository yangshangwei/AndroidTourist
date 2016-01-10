package com.turing.base.activity.intentAct;

import android.annotation.TargetApi;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.TextView;

import com.turing.base.R;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ClipboardTransObjectDataAct extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipboard_trans_object_data);


        ClipboardManager cbm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 从剪切板中获取Base64编码格式的字符串
        String base64Str = cbm.getText().toString();
        // 将Base64格式的字符串还原为byte[]格式的数据
        byte[] buffer = Base64.decode(base64Str,Base64.DEFAULT);

        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);

        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            // 将byte[]数据还原为Data对象
            Data data = (Data)ois.readObject();
            // 输出
            TextView tv = (TextView)findViewById(R.id.id_tv_clipboard_trans_object);
            tv.setText(base64Str  + "\n\n data.id:" + data.getId() + "\ndata.name:"+data.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

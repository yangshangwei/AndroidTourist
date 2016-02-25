package com.turing.base.activity.dataStore.sharedPreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.base.R;

public class SpBaseUseAct extends Activity implements View.OnClickListener {

    private TextView tv_save2Sp, tv_readSp ,tv_usernmae ,tv_password;
    private EditText et_username, et_pwd;
    private LinearLayout ll_username ,ll_pwd ;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_base_use2);


        initView();
        initEvents();
    }

    private void initView() {
        tv_save2Sp = (TextView) findViewById(R.id.id_tv_save2Sp);
        tv_readSp = (TextView) findViewById(R.id.id_tv_readSp);

        et_username = (EditText) findViewById(R.id.id_et_username);
        et_pwd = (EditText) findViewById(R.id.id_et_pwd);

        ll_username = (LinearLayout) findViewById(R.id.id_layout_username);
        ll_pwd = (LinearLayout) findViewById(R.id.id_layout_pwd);


        tv_usernmae = (TextView) findViewById(R.id.id_tv_username);
        tv_password = (TextView) findViewById(R.id.id_tv_password);
    }

    private void initEvents() {
        tv_save2Sp.setOnClickListener(this);
        tv_readSp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tv_save2Sp:
                save2Sp(et_username.getText().toString(), et_pwd.getText().toString());
                break;
            case R.id.id_tv_readSp:
                readSp();
                break;
            default:
                break;
        }
    }


    private void save2Sp(String username, String pwd) {

        // 获得SharedPreferences对象
        sp = getSharedPreferences("staffInfo", Activity.MODE_PRIVATE);
        // 获得SharedPreferences.Editor对象
        editor = sp.edit();
        // 使用putXXX方法保存数据
        editor.putString("username", username);
        editor.putString("password", pwd);
        // 将数据保存到文件中
        editor.commit();

        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
    }


    private void readSp() {

        sp = getSharedPreferences("staffInfo",Activity.MODE_PRIVATE);
        String username = sp.getString("username","默认值");
        String password = sp.getString("password","默认值");

        Toast.makeText(this,"读取成功",Toast.LENGTH_SHORT).show();

        ll_username.setVisibility(View.VISIBLE);
        ll_pwd.setVisibility(View.VISIBLE);


        tv_usernmae.setText(username);
        tv_password.setText(password);


    }
}

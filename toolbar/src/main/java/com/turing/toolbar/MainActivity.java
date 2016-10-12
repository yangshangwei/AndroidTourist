package com.turing.toolbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.id_tb_baseUse).setOnClickListener(this);
        findViewById(R.id.id_tb_zhihu).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tb_baseUse:
                startActivity(new Intent(this,ToolBarBaseUseAct.class));
                break;
            case R.id.id_tb_zhihu:
                startActivity(new Intent(this,ToolBarZhiHuAct.class));
                break;
            default:
                break;
        }
    }
}

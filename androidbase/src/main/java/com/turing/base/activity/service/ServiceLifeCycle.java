package com.turing.base.activity.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_service_life_cycle)
public class ServiceLifeCycle extends AppCompatActivity {

    @ViewById(R.id.id_btn_startService)
    Button startServiceBtn ;
    @ViewById(R.id.id_btn_stopService)
    Button stopServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Service 生命周期");

        LogUtils.d("ServiceLifeCycle Activity onCreate, Thread id:" + Thread.currentThread().getId());
    }


    @Click({R.id.id_btn_startService,R.id.id_btn_stopService})
    public  void initEvents(View view) {
        switch (view.getId()){
            case R.id.id_btn_startService:
                startService(new Intent(ServiceLifeCycle.this ,MyService.class));
                break;
            case R.id.id_btn_stopService:
                stopService(new Intent(ServiceLifeCycle.this ,MyService.class));
                break;
            default:
                break;
        }

    }
}

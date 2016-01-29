package com.turing.base.activity.spinnerAct;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerAct extends Activity {

    private Spinner spinner2, spinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        initView();
        addItemsOnSp2WithArrayAdpater();
        addItemsOnSp3WithSimpleAdpater();
        addListenerOnSpinnerItemSelection();

    }


    private void initView() {
        spinner2 = (Spinner) findViewById(R.id.id_spinner_2);
        spinner3 = (Spinner) findViewById(R.id.id_spinner_3);
    }


    private void addItemsOnSp2WithArrayAdpater() {
        // 数据源 list或者数组

        List<CharSequence> datalist = new ArrayList<>();
        datalist.add("北京2");
        datalist.add("上海2");
        datalist.add("广州2");

        String[] dataArr = new String[]{"北京[]", "上海", "广州"};


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, datalist);

        // Spinner下拉显示风格simple_spinner_dropdown_item
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dataArr);


        // ArrayAdapter  createFromResource()加载 xml中的数据

        ArrayAdapter arrayAdapter3 = ArrayAdapter.createFromResource(this, R.array.city_data, android.R.layout.simple_spinner_item);


        spinner2.setAdapter(arrayAdapter3);


    }

    /**
     * 使用SimpleAdapter处理Spinner显示的数据，可以将自定义的xml布局文件作为列表项使用
     */
    private void addItemsOnSp3WithSimpleAdpater() {
        // 数据来源
        List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("ivLogo", R.drawable.flag_mark_yellow);
        item1.put("appname", "图灵机器人");

        Map<String, Object> item2 = new HashMap<String, Object>();
        item2.put("ivLogo", R.drawable.flag_mark_green);
        item2.put("appname", "谷歌无人机");

        // 将Map添加到List中
        datalist.add(item1);
        datalist.add(item2);


        SimpleAdapter simpleAdapter = new SimpleAdapter(this, datalist,
                R.layout.spinnner_item, new String[]{"ivLogo", "appname"},
                new int[]{R.id.id_iv_spinner, R.id.id_tv_spinner});

        spinner3.setAdapter(simpleAdapter);

    }


    private void addListenerOnSpinnerItemSelection() {

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map selectedMap = (Map) spinner3.getSelectedItem();
               LogUtils.d("spinner3:" +selectedMap.get("appname"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}

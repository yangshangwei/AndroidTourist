package com.turing.base.android_hero.chapter6_Draw.annimation_propertyAnimation;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.turing.base.R;
import com.turing.base.utils.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyAnimationDemoAct extends ListActivity {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "PropertyAnimation概述+两种实现方式",
            "全部的效果",
            "<animator>-xml方式",
            "<objectAnimator>-xml方式",
            "<set>-xml方式",
            "【-------------割割割割割割割割---------------------】",
            "ValueAnimator-在代码中创建PropertyAnimation",
            "ObjectAnimator-在代码中创建PropertyAnimation",
            "AnimatorSet-在代码中创建PropertyAnimation"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initEvents();
    }


    private void initView() {

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, simulateData(arr),
                R.layout.activity_scroll_view_list_item,
                new String[]{"img", "info"},
                new int[]{R.id.id_sv_iv, R.id.id_sv_tv});

        setListAdapter(simpleAdapter);

    }


    private void initEvents() {

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:// PropertyAnimation概述+两种实现方式
                        startActivity(new Intent(PropertyAnimationDemoAct.this, PropertyAnimationInfo.class));
                        break;
                    case 1:
                        startActivity(new Intent(PropertyAnimationDemoAct.this, PropertyAnimationActvity.class));
                        break;
                    case 2://<animator>-xml方式
                        startActivity(new Intent(PropertyAnimationDemoAct.this, ValueAnimatorByXmlAct.class));
                        break;
                    case 3://<objectAnimator>-xml方式
                        startActivity(new Intent(PropertyAnimationDemoAct.this, ObjectAnimatorByXmlAct.class));
                        break;
                    case 4://<set>-xml方式
                        startActivity(new Intent(PropertyAnimationDemoAct.this, AnimatorSetByXmlAct.class));
                        break;
                    case 5://
                        AlertUtil.showToastShort(PropertyAnimationDemoAct.this, "好吧 我只是个分隔线");
                        break;
                    case 6:// ValueAnimator-在代码中创建PropertyAnimation
                        break;
                    case 7:// ObjectAnimator-在代码中创建PropertyAnimation
                        break;
                    case 8:// AnimatorSet-在代码中创建PropertyAnimation
                        break;
                    case 9:
                        break;
                    default:
                        break;
                }
            }
        });
    }


    private List<Map<String, Object>> simulateData(String[] arr) {

        List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < arr.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("img", R.drawable.gur_project_10);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }
}

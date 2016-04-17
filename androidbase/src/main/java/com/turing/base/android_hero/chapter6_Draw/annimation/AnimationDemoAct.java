package com.turing.base.android_hero.chapter6_Draw.annimation;

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

public class AnimationDemoAct extends ListActivity {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "setDuration方法：设置持续时间",
            "startNow方法：立刻启动动画",
            "start方法,启动动画",
            "cancel方法：取消动画",
            "setRepeatCount方法：设置重复次数",
            "setFillEnabled方法：使能填充",
            "setFillBefore方法：设置起始填",
            "setFillAfter方法：设置终止填",
            "setRepeatMode方法：设置重复模",
            "setStartOffset方法：设置启动"
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
                    case 0://setDuration方法：设置持续时间
                        AlertUtil.showDialogWithClose(AnimationDemoAct.this, "请参考onStartNow方法");
                        break;
                    case 1://startNow方法：立刻启动动画
                        startActivity(new Intent(AnimationDemoAct.this, Animation_startNow.class));
                        break;
                    case 2:// start方法：启动动画
                        AlertUtil.showDialogWithClose(AnimationDemoAct.this, "该方法用于启动执行一个动画。该方法是启动执行动画的另一个主要方法，使用时需要先通过setAnimation方法为某一个View对象设置动画。start方法区别于startNow方法的地方在于，start方法可以用于在getTransformation方法被调用时启动动画。");
                        break;
                    case 3:// cancel方法：取消动画
                        startActivity(new Intent(AnimationDemoAct.this, Animation_cancel.class));
                        break;
                    case 4:// setRepeatCount方法：设置重复次数
                        Intent intent = new Intent(AnimationDemoAct.this, Animation_cancel.class);
                        intent.putExtra("repeat", true);
                        startActivity(intent);
                        break;
                    case 5://setFillEnabled方法：使能填充
                        AlertUtil.showDialogWithClose(AnimationDemoAct.this, "该方法用于使能填充效果。当该方法设置为true时，将执行setFillBefore和setFillAfter方法，否则将忽略setFillBefore和setFillAfter方法。");
                        break;
                    case 6://setFillBefore方法：设置起始填
                        Intent intent1 = new Intent(AnimationDemoAct.this, Animation_cancel.class);
                        intent1.putExtra("fillBefore", true);
                        startActivity(intent1);
                        break;
                    case 7://setFillAfter方法：设置终止填
                        Intent intent2 = new Intent(AnimationDemoAct.this, Animation_cancel.class);
                        intent2.putExtra("fillAfter", true);
                        startActivity(intent2);
                        break;
                    case 8://setRepeatMode方法：设置重复模
                        Intent intent3 = new Intent(AnimationDemoAct.this, Animation_cancel.class);
                        intent3.putExtra("repeatMode", true);
                        startActivity(intent3);
                        break;
                    case 9://setStartOffset方法：设置启动
                        Intent intent4 = new Intent(AnimationDemoAct.this, Animation_cancel.class);
                        intent4.putExtra("startOffset", true);
                        startActivity(intent4);
                        break;
                    case 10:
                        break;
                    case 11:
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
            item.put("img", R.drawable.tag_blue);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }

}
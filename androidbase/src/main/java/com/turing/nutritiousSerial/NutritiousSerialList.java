package com.turing.nutritiousSerial;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.turing.base.R;
import com.turing.nutritiousSerial.countDownTimer.CountDownActivity;
import com.turing.nutritiousSerial.customViewClock.CustomViewClockActivity;
import com.turing.nutritiousSerial.ipAdress.IPAdressActivity;
import com.turing.nutritiousSerial.listPopupWindow.test.ListPopupWindowDemoActivity;
import com.turing.nutritiousSerial.splashScreen.SplashScreenActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-25  14:43.
 * @version 1.0
 * http://blog.csdn.net/column/details/android-fight.html
 * 这里有很多干货教程
 * http://blog.csdn.net/column/details/hankkin-imitation.html
 */
public class NutritiousSerialList extends ListActivity {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "仿QQ菜单选项-自定义的ListPopupWindow",
            "使用CountDownTimer类轻松实现倒计时功能",
            "自定义View绘制钟表",
            "工具类-获得WIFI IP地址或者手机网络IP",
            "欢迎页面SplashScreen"
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
                    case 0:// 仿QQ菜单选项-自定义的ListPopupWindow
                        startActivity(new Intent(NutritiousSerialList.this, ListPopupWindowDemoActivity.class));
                        break;
                    case 1:// 使用CountDownTimer类轻松实现倒计时功能
                        startActivity(new Intent(NutritiousSerialList.this, CountDownActivity.class));
                        break;
                    case 2:// 自定义View绘制钟表
                        startActivity(new Intent(NutritiousSerialList.this, CustomViewClockActivity.class));
                        break;
                    case 3:// 工具类-获得WIFI IP地址或者手机网络IP
                        startActivity(new Intent(NutritiousSerialList.this, IPAdressActivity.class));
                        break;
                    case 4:// 欢迎页面 Splash Screen
                        startActivity(new Intent(NutritiousSerialList.this, SplashScreenActivity.class));
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
            item.put("img", R.drawable.flag_mark_red);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }
}

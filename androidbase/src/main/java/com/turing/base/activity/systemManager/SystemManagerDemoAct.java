package com.turing.base.activity.systemManager;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.turing.base.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemManagerDemoAct extends ListActivity implements AdapterView.OnItemClickListener {

    // ListView中要显示的item
    private String[] arr = new String[]{

            "TelephonyManager(电话管理器)",

            "SmsManager(短信管理器)",

            "AudioManager(音频管理器)",

            "Vibrator(振动器)",

            "AlarmManager(闹钟服务)",

            "PowerManager(电源服务)",

            "WindowManager(窗口管理服务)",

            "LayoutInflater(布局服务)",

            "WallpaperManager(壁纸管理器)"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initEvents();
    }


    private void initView() {
        // Adapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, simulateData(arr),
                R.layout.activity_scroll_view_list_item,
                new String[]{"img", "info"},
                new int[]{R.id.id_sv_iv, R.id.id_sv_tv});
        // 设置Adapter
        setListAdapter(simpleAdapter);

    }

    private void initEvents() {
        // 获得ListView
        ListView listView = getListView();
        // 设置监听事件
        listView.setOnItemClickListener(this);
    }

    /**
     * 将数组转换为List<Map<String,Object>>
     *
     * @param arr
     * @return
     */
    private List<Map<String, Object>> simulateData(String[] arr) {

        List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < arr.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("img", R.drawable.gur_project_4);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0: // TelephonyManager(电话管理器)
                break;
            case 1: // SmsManager(短信管理器)
                break;
            case 2: // AudioManager
                break;
            case 3:// Vibrator
                break;
            case 4:// AlarmManager
                break;
            case 5:// PowerManager
                break;
            case 6:// WindowManager
                break;
            case 7:// LayoutInflater
                break;
            case 8:// WallpaperManager
                break;

            default:
                break;
        }
    }
}

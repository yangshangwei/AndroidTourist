package com.turing.base.activity.socket;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.turing.base.R;
import com.turing.base.activity.socket.baseuse.SocketClientAct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://www.runoob.com/w3cnote/android-tutorial-socket1.html
 */
public class SocketDemoAct extends ListActivity implements AdapterView.OnItemClickListener {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "Socket基本使用",
            "Socket简易聊天室",
            "Sokcet-断点续传",
            "UDP通信"
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
            item.put("img", R.drawable.gur_project_9);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {

            case 0: //Socket基本使用
                startActivity(new Intent(this, SocketClientAct.class));
                break;
            case 1: //Socket简易聊天室
                break;
            case 2: // Sokcet-断点续传
                break;
            case 3:// UDP通信
                break;

            default:
                break;
        }
    }
}

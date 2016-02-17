package com.turing.base.activity.Dlg_Tst_Ntf;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.turing.base.R;
import com.turing.base.activity.Dlg_Tst_Ntf.AlertDialogAct.AlertDialogDemoListAct;
import com.turing.base.activity.Dlg_Tst_Ntf.NotifcationAct.NotificationDemoListAct;
import com.turing.base.activity.Dlg_Tst_Ntf.ToastAct.ToastDemoListAct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Dlg_Tst_Ntf extends ListActivity implements AdapterView.OnItemClickListener {


    // ListView中要显示的item
    private String[] arr = new String[]{
            "AlertDialog",
            "Toast",
            "Notification"
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
            item.put("img", R.drawable.flag_mark_yellow);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0: // AlertDialog
                startActivity(new Intent(Dlg_Tst_Ntf.this, AlertDialogDemoListAct.class));
                break;
            case 1: // Toast
                startActivity(new Intent(Dlg_Tst_Ntf.this, ToastDemoListAct.class));
                break;
            case 2:// Notification
                startActivity(new Intent(Dlg_Tst_Ntf.this, NotificationDemoListAct.class));
                break;
            default:
                break;
        }
    }
}

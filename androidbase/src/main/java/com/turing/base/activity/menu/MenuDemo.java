package com.turing.base.activity.menu;

import android.app.ListActivity;
import android.content.Intent;
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

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-03-12  21:18.
 * @version 1.0
 * @desc
 */
public class MenuDemo extends ListActivity implements AdapterView.OnItemClickListener {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "OptionMenu选项菜单",
            "SubMenu子菜单",
            "ContextMenu上下文菜单",
            "PopupMenu(弹出式菜单)",
            "自定义布局Menu"
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
            item.put("img", R.drawable.gur_project_3);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: //OptionMenu选项菜单
                startActivity(new Intent(this, OptionsMenuDemoAct.class));
                break;
            case 1: //SubMenu子菜单
                startActivity(new Intent(this, SubMenuDemoAct.class));
                break;
            case 2://ContextMenu上下文菜单
                startActivity(new Intent(this, ContextMenuDemoAct.class));
                break;
            case 3: // (弹出式菜单)
                startActivity(new Intent(this, PopupMenuDemoAct.class));
            case 4: // 自定义布局Menu
                startActivity(new Intent(this, CustomizeMenu.class));
                break;
            default:
                break;
        }
    }
}

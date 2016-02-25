package com.turing.base.activity.dataStore.sharedPreference;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.turing.base.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedPreferencesDemoAct extends ListActivity implements AdapterView.OnItemClickListener {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "SharedPreferences的基本用法",
            "数据的存储位置和格式",
            "存取复杂类型的数据",
            "设置数据文件的访问权限",
            "可以保存设置的Activity：PreferenceActivity"
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
            item.put("img", R.drawable.gur_project_1);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: // SharedPreferences的基本用法
                startActivity(new Intent(this, SpBaseUseAct.class));
                break;
            case 1: // 数据的存储位置和格式
                String info = "存放在手机内存(ROM)是有的目录中，/data/data/packageName/shared_prefs/*.xml";
                Toast.makeText(this, info, Toast.LENGTH_LONG).show();
                break;
            case 2:// 存取复杂类型的数据
                startActivity(new Intent(this, SpComplexDataAct.class));
                break;
            case 3: // 设置数据文件的访问权限
                createDiffPermissionSpFile();
                Toast.makeText(this, "create successfully", Toast.LENGTH_LONG).show();
                break;
            case 4:// 可以保存设置的Activity：PreferenceActivity
                startActivity(new Intent(this, PreferenceActivityDemo.class));
              //  startActivity(new Intent(this, PrefsActivityAllVersion.class)); 还有点问题
                break;
            default:
                break;
        }
    }

    /**
     * MODE_WORLD_READABLE  MODE_WORLD_READABLE
     * "This constant was deprecated in API level 17.
     * Creating world-readable files is very dangerous,
     * and likely to cause security holes in applications.
     * It is strongly discouraged; instead,
     * applications should use more formal mechanism for interactions
     * such as ContentProvider, BroadcastReceiver, and Service.
     * There are no guarantees that this access mode will remain on a file,
     * such as when it goes through a backup and restore.
     * File creation mode: allow all other applications to have read access to the created file."
     */
    private void createDiffPermissionSpFile() {

        int[] modes = new int[]{Activity.MODE_PRIVATE, Activity.MODE_APPEND,
                Activity.MODE_WORLD_READABLE,
                Activity.MODE_WORLD_READABLE};

        for (int i = 0; i < modes.length; i++) {

            SharedPreferences sharedPreferences = getSharedPreferences("data" + String.valueOf(i + 1), modes[i]);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", "不同的访问权限");
            editor.commit();

        }
    }
}

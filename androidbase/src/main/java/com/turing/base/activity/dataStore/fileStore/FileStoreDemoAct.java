package com.turing.base.activity.dataStore.fileStore;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.turing.base.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileStoreDemoAct extends ListActivity implements AdapterView.OnItemClickListener {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "openFileOutput和openFileInput方法",
            "读写SD卡中的文件",
            "SAX引擎读取XML文件的原理",
            "将XML转换为JAVA对象",
            "文件压缩（Jar、Zip）"
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
            case 0: // openFileOutput和openFileInput方法
                save2File();
                readFile();
                break;
            case 1: // 读写SD卡中的文件
               startActivity(new Intent(this,SDCardWriteReadAct.class));
                break;
            case 2:// SAX引擎读取XML文件的原理
                startActivity(new Intent(this,Xml2JavaObjectAct.class));
                break;
            case 3: // 将XML转换为JAVA对象
                startActivity(new Intent(this,Xml2JavaObjectAct.class));
                break;
            case 4:// 文件压缩（Jar、Zip）
                startActivity(new Intent(this,JarZipAct.class));
                break;
            default:
                break;
        }
    }



    /**
     * openFIleOutput ,openFileInput
     * 这两种方法同sp一样只能讲文件保存到手机内存固定的路径中，
     * 默认为 /data/data/<packageName>/files
     */
    private void save2File() {
        try {
            //向文件写入内容
            FileOutputStream os = openFileOutput("file.txt", Context.MODE_PRIVATE);
            String text = "写数据到文件";
            os.write(text.getBytes("utf-8"));

            //关闭流
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private void readFile() {

        try {
            FileInputStream ins = openFileInput("file.txt");
            byte[] buffer = new byte[100];
            int byteCount = ins.read(buffer);
            String text = new String(buffer,0,byteCount,"utf-8");
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show();

            ins.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

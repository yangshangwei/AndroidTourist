package com.turing.base.activity.listViewAct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.turing.base.R;
import com.turing.base.activity.spinnerAct.SpinnerAct;
import com.turing.base.adapter.MainMenuListAdapter;
import com.turing.base.utils.ListViewDataFactory;

import java.util.List;

public class ListViewDemoAct extends AppCompatActivity {


    private ListView listView;
    private MainMenuListAdapter adapter;
    private List datalist;

    // ListView中显示的内容
    public static String[] datas = new String[]{
            "为ListView添加复选框和选项按钮",
            "对列表进行增删改查操作",
            "改变列表项的背景色",
            "ListActivity",
            "ExpandableListView",
            "Spinner"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);

        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.id_listView_demoList);

        // 模拟数据来源
        datalist = ListViewDataFactory.simulateData(datas);
        // 实例化Adapter
        adapter = new MainMenuListAdapter(this, datalist);
        // 绑定数据到ListView
        listView.setAdapter(adapter);
        // 设置Item点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://为ListView添加复选框和选项按钮
                        startActivity(new Intent(ListViewDemoAct.this,ListViewWithRadioBtnChkBox.class));
                        break;
                    case 1://对列表进行增删改查操作
                        startActivity(new Intent(ListViewDemoAct.this,ListViewCRUD.class));
                        break;
                    case 2://改变列表项的背景色
                        startActivity(new Intent(ListViewDemoAct.this,ListViewActivityDemo.class));
                        break;
                    case 3://ListActivity
                        startActivity(new Intent(ListViewDemoAct.this,ListViewActivityDemo.class));
                        break;
                    case 4://ExpandableListView
                        startActivity(new Intent(ListViewDemoAct.this,ExpandableListViewAct.class));
                        break;
                    case 5://Spinner
                        startActivity(new Intent(ListViewDemoAct.this, SpinnerAct.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

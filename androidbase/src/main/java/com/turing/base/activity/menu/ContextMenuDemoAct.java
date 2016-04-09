package com.turing.base.activity.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.turing.base.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 长按某个View后出现的菜单，我们需要为这个View注册上下文菜单！
 * <p/>
 * 使用的流程如下：
 * Step 1：重写onCreateContextMenu()方法
 * Step 2：为view组件注册上下文菜单，使用registerForContextMenu()方法,参数是View
 * Step 3：重写onContextItemSelected()方法为菜单项指定事件监听器
 *
 *
 *
 * 上下文菜单无法显示图标！
 */
public class ContextMenuDemoAct extends AppCompatActivity {

    private ListView listView;
    private List dataList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu_demo);


        listView = (ListView) findViewById(R.id.id_listView);

        for (int i = 0; i < 20; i++) {
            dataList.add(i);
        }
        ArrayAdapter adapter = new ArrayAdapter(ContextMenuDemoAct.this,
                android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);


        registerForContextMenu(listView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ContextMenuDemoAct.this, "onItemClick", Toast.LENGTH_SHORT).show();
            }
        });


    }


    //重写上下文菜单的创建方法
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.menu_context,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }




    //上下文菜单被点击是触发该方法
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.op1:
                Toast.makeText(this,"op1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.op2:
                Toast.makeText(this,"op2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.op3:
                Toast.makeText(this,"op3",Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}

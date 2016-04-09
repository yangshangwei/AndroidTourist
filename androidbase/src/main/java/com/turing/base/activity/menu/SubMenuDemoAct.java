package com.turing.base.activity.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.base.R;

/**
 * 所谓的子菜单只是在<**item**>中又嵌套了一层<**menu**>而已
 * <p/>
 * 在Java代码中添加子菜单的话，可以调用addSubMenu()
 * 比如：SubMenu file = menu.addSubMenu("文件");file还需要addItem添加菜单项哦！
 */
public class SubMenuDemoAct extends AppCompatActivity {

    private TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu_demo);

        textView = (TextView) findViewById(R.id.id_tv_info);

        registerForContextMenu(textView);
    }


    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        //子菜单部分：
        MenuInflater inflator = new MenuInflater(this);
        inflator.inflate(R.menu.menu_sub, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one:
                Toast.makeText(SubMenuDemoAct.this, "你点击了子菜单一", Toast.LENGTH_SHORT).show();
                break;
            case R.id.two:
                item.setCheckable(true);
                Toast.makeText(SubMenuDemoAct.this, "你点击了子菜单二", Toast.LENGTH_SHORT).show();
                break;
            case R.id.three:
                Toast.makeText(SubMenuDemoAct.this, "你点击了子菜单三", Toast.LENGTH_SHORT).show();
                item.setCheckable(true);
                break;
        }
        return true;
    }


}

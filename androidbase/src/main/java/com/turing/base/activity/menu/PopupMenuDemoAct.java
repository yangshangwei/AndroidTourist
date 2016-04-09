package com.turing.base.activity.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.turing.base.R;

public class PopupMenuDemoAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu_demo);
    }




    public void showPopupMenu(View view ){
        // 初始化PopupMenu控件
        PopupMenu popup = new PopupMenu(PopupMenuDemoAct.this,view);
        // 加载菜单选项
        popup.getMenuInflater().inflate(R.menu.menu_pop, popup.getMenu());

        // 设置显示位置
        popup.setGravity(Gravity.RIGHT);

        // 设置监听事件
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.see:
                        Toast.makeText(PopupMenuDemoAct.this, "查看",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.download:
                        Toast.makeText(PopupMenuDemoAct.this,"下载",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        // 显示PopupMenu控件
        popup.show();
    }
}


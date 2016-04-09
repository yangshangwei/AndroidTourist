package com.turing.base.activity.menu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.turing.base.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomizeMenu extends AppCompatActivity {

    private boolean isMore = false;// menu菜单翻页控制
    AlertDialog menuDialog;// menu菜单Dialog
    GridView menuGrid;
    View menuView;

    private final int ITEM_SEARCH = 0;// 搜索
    private final int ITEM_FILE_MANAGER = 1;// 文件管理
    private final int ITEM_DOWN_MANAGER = 2;// 下载管理
    private final int ITEM_FULLSCREEN = 3;// 全屏
    private final int ITEM_MORE = 11;// 菜单


    /**
     * 菜单图片
     **/
    int[] menu_image_array = {
            R.drawable.menu_search,
            R.drawable.menu_filemanager, R.drawable.menu_downmanager,
            R.drawable.menu_fullscreen, R.drawable.menu_inputurl,
            R.drawable.menu_bookmark, R.drawable.menu_bookmark_sync_import,
            R.drawable.menu_sharepage, R.drawable.menu_quit,
            R.drawable.menu_nightmode, R.drawable.menu_refresh,
            R.drawable.menu_more};
    /**
     * 菜单文字
     **/
    String[] menu_name_array = {
            "搜索", "文件管理", "下载管理", "全屏", "网址", "书签",
            "加入书签", "分享页面", "退出", "夜间模式", "刷新", "更多"};
    /**
     * 菜单图片2
     **/
    int[] menu_image_array2 = {
            R.drawable.menu_auto_landscape,
            R.drawable.menu_penselectmodel, R.drawable.menu_page_attr,
            R.drawable.menu_novel_mode, R.drawable.menu_page_updown,
            R.drawable.menu_checkupdate, R.drawable.menu_checknet,
            R.drawable.menu_refreshtimer, R.drawable.menu_syssettings,
            R.drawable.menu_help, R.drawable.menu_about, R.drawable.menu_return};
    /**
     * 菜单文字2
     **/
    String[] menu_name_array2 = {
            "自动横屏", "笔选模式", "阅读模式", "浏览模式", "快捷翻页",
            "检查更新", "检查网络", "定时刷新", "设置", "帮助", "关于", "返回"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_menu);


        menuView = View.inflate(this, R.layout.gridview_menu, null);
        // 创建AlertDialog
        menuDialog = new AlertDialog.Builder(this).create();
        menuDialog.setView(menuView);
        menuDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU)// 监听按键
                    dialog.dismiss();
                return false;
            }
        });






        menuGrid = (GridView) menuView.findViewById(R.id.gridview);
        menuGrid.setAdapter(getMenuAdapter(menu_name_array, menu_image_array));
        /** 监听menu选项 **/
        menuGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                switch (arg2) {
                    case ITEM_SEARCH:// 搜索

                        break;
                    case ITEM_FILE_MANAGER:// 文件管理

                        break;
                    case ITEM_DOWN_MANAGER:// 下载管理

                        break;
                    case ITEM_FULLSCREEN:// 全屏

                        break;
                    case ITEM_MORE:// 翻页
                        if (isMore) {
                            menuGrid.setAdapter(getMenuAdapter(menu_name_array2,
                                    menu_image_array2));
                            isMore = false;
                        } else {// 首页
                            menuGrid.setAdapter(getMenuAdapter(menu_name_array,
                                    menu_image_array));
                            isMore = true;
                        }
                        menuGrid.invalidate();// 更新menu
                        menuGrid.setSelection(ITEM_MORE);
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 必须创建一项,否则不显示...
        menu.add("menu");
        return super.onCreateOptionsMenu(menu);
    }

    private SimpleAdapter getMenuAdapter(String[] menuNameArray,
                                         int[] imageResourceArray) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < menuNameArray.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", imageResourceArray[i]);
            map.put("itemText", menuNameArray[i]);
            data.add(map);
        }
        SimpleAdapter simperAdapter = new SimpleAdapter(this, data,
                R.layout.item_menu, new String[]{"itemImage", "itemText"},
                new int[]{R.id.item_image, R.id.item_text});
        return simperAdapter;
    }


    /**
     * 自定义布局
     * @param featureId
     * @param menu
     * @return
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menuDialog == null) {
            menuDialog = new AlertDialog.Builder(this).setView(menuView).show();
        } else {
            menuDialog.show();
        }
        return false;// 返回为true 则显示系统menu
    }
}

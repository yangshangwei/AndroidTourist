package com.turing.base.activity.dataStore.sqlite;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.turing.base.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLiteDemoAct extends ListActivity implements AdapterView.OnItemClickListener {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "SQLite数据库管理工具",
            "SQLiteOpenHelper类与自动升级数据库",
            "数据绑定和SimpleCursorAdapter类",
            "操作SD卡上的数据库",
            "将数据库与应用一起发布",
            "内存数据库"
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
            case 0: // SQLite数据库管理工具
                Toast.makeText(this, "介绍见博客", Toast.LENGTH_SHORT).show();
                break;
            case 1: // SQLiteOpenHelper类与自动升级数据库
                Toast.makeText(this, "介绍见博客", Toast.LENGTH_SHORT).show();
                break;
            case 2:// 数据绑定和SimpleCursorAdapter类
                ShowSimpleCursorAdapter();
                break;
            case 3: // 操作SD卡上的数据库
                operDBONSDCard();
                break;
            case 4:// 将数据库与应用一起发布
                break;
            case 5: // 内存数据库
                break;
            default:
                break;
        }
    }

    private void operDBONSDCard() {
        // 定义数据库文件的路径
        String filename = android.os.Environment.getExternalStorageDirectory()
                + "/sdcard_test.db";
        /// 定义建表语句
        String createTableSQL = "CREATE TABLE [t_test] (" + "[id] INTEGER,"
                + "[name] VARCHAR(20),[memo] TEXT,"
                + "CONSTRAINT [sqlite_autoindex_t_test_1] PRIMARY KEY ([id]))";
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        // 创建并打开数据库文件（因为前面已经删除了已经存在的同名文件）
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(filename,
                null);
        // 创建t_test表
        database.execSQL(createTableSQL);
        /// 创建一个ContentValues对象，标识要插入的记录行
        ContentValues contentValues = new ContentValues();
        // 开始设置是三个字段的值
        contentValues.put("id", 1);
        contentValues.put("name", "Mike");
        contentValues.put("memo", "Student");
        // 向t_test表插入一行记录，database_insert方法的第二个参数一般设置为null即可
        database.insert("t_test", null, contentValues);
        // 定义插入记录的SQL
        String insertSQL = "insert into t_test(id, name, memo) values(?,?,?)";
        // 插入一条记录
        database.execSQL(insertSQL, new Object[]{2, "John", "老师"});
        // 定义查询记录的SQL语句
        String selectSQL = "select name, memo from t_test where name=?";
        // 查询记录
        Cursor cursor = database.rawQuery(selectSQL, new String[]
                {"John"});
        // 将记录指针指向第一条记录
        cursor.moveToFirst();
        // 显示当前值
        Toast.makeText(this, cursor.getString(0) + "  " + cursor.getString(1),
                Toast.LENGTH_LONG).show();
        // 关闭数据库
        database.close();

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void ShowSimpleCursorAdapter() {

        //从数据库中加载数据
        DBService dbService = new DBService(this);
        final Cursor cursr = dbService.queryItems("select * from t_test", null);

        final SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1,
                cursr, new String[]{"name"}, new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);


        // 展示简单列表项
        new AlertDialog.Builder(this)
                .setTitle("SQLite中的数据")
                .setAdapter(simpleCursorAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SQLiteDemoAct.this, "显示啥来？",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();

    }
}

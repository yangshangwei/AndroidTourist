package com.turing.base.activity.dataStore.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.apkfuns.logutils.LogUtils;

import java.util.Random;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-02-23  19:34.
 * @version 1.0
 * @desc
 */
public class DBService extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "randomItem.db";


    /**
     * 构造函数
     *
     * @param context
     */
    public DBService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL
        String sql = "create table [t_test] (" + " [_id] autoinc,"
                + " [name] varchar2(20) not null on conflict fail,"
                + " constraint [sqlite_autoindex_t_test_1] primary key ([_id]))";

//        String sql = "CREATE TABLE [t_test] (" + "[_id] AUTOINC,"
//                + "[name] VARCHAR(20) NOT NULL ON CONFLICT FAIL,"
//                + "CONSTRAINT [sqlite_autoindex_t_test_1] PRIMARY KEY ([_id]))";
        // 执行SQL
        db.execSQL(sql);

        // 向 t_test表中插入5条记录
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            String s = "";
            // 随机生成长度为10的字符串
            for (int j = 0; j < 10; j++) {
                char c = (char) (97 + random.nextInt(26));
                s += c;
            }
            // 执行insert语句
            db.execSQL("insert into t_test(name) values(?)", new Object[]{s});
        }
    }

    // 因不打算对数据库进行升级,故此方法为空
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.d("onUpgrade");
    }

    /**
     * 查询数据
     * @param sql
     * @param args
     * @return
     */
    public Cursor queryItems(String sql, String[] args) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, args);
        return cursor;
    }
}

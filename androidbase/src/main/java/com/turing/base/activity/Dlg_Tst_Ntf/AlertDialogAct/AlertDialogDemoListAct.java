package com.turing.base.activity.Dlg_Tst_Ntf.AlertDialogAct;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
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
 * 因为此Activity只有一个ListView,为了方便开发简化代码，使用android提供的android.app.ListActivity
 * <p/>
 * 在ListActivity的内部通过代码创建ListView,因此并不需要布局文件
 */
public class AlertDialogDemoListAct extends ListActivity implements AdapterView.OnItemClickListener {


    // ListView中要显示的item
    private String[] arr = new String[]{
            "=====对话框的基本用法=====",
            "带2个按钮（确认、取消）的对话框",
            "带3个按钮（覆盖、忽略、取消）的对话框",
            "简单列表对话框",
            "单选列表对话框",
            "多选列表对话框",
            "进度对话框",
            "登录对话框",
            "使用Activity托管对话框",
            "=====对话框的高级应用=====",
            "阻止单击按钮关闭对话框",
            "改变对话框的显示位置",
            "在对话框按钮和内容文本中插入图像",
            "改变对话框的透明度"
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
            item.put("img", R.drawable.flag_mark_violet);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1: // 带2个按钮（确认、取消）的对话框
                showAlertDialogWith2Button();
                break;
            case 2: // 带3个按钮（覆盖、忽略、取消）的对话框
                showAlertDialogWith3Button();
                break;
            case 3:// 简单列表对话框
                showSimpleDialog();
                break;
            case 4:// 单选列表对话框
                showSingleChoiceItems();
                break;
            case 5://多选列表对话框
                break;
            case 6://进度对话框
                break;
            case 7://登录对话框
                break;
            case 8:// 使用Activity托管对话框
                break;
            default:
                break;
        }
    }

    private void showSingleChoiceItems() {

    }

    /**
     * AlertDialog.Builder类的setItems方法可以创建简单的列表对话框
     */
    private void showSimpleDialog() {

        final String[] proviences = {"北京","上海","广州","深圳","纽约","华盛顿","拉斯维加斯"};

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.flag_mark_gray)
                .setTitle("请选择省份")
                .setItems(proviences, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 选择后的提示信息  记得调用show方法，否则不显示啦
                        final AlertDialog alertDialog = new AlertDialog.Builder(AlertDialogDemoListAct.this)
                                .setMessage("选择了" + proviences[which])
                                .show();

                        // 设置定时器，5秒后,关闭AlertDialog
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() { //  alertDialog.cancel(); 也可以
                                alertDialog.dismiss();
                            }
                        },5*1000);
                    }
                })
                .show();

    }


    /**
     * AlertDialog类并没有public的构造方法，需要使用Builder类。
     */
    private void showAlertDialogWith2Button() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.flag_mark_blue)
                .setTitle("是否下载文件?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 提示信息
                        AlertDialog dialog1 = new AlertDialog.Builder(AlertDialogDemoListAct.this)
                                .setMessage("文件已下载成功")
                                .create();
                        dialog1.show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 取消提示信息
                        new AlertDialog.Builder(AlertDialogDemoListAct.this)
                                .setMessage("文件下载取消")
                                .create()
                                .show();
                    }
                })
                .show();

    }


    private void showAlertDialogWith3Button() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.flag_mark_violet)
                .setTitle("是否覆盖文件?")
                .setPositiveButton("覆盖", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AlertDialog.Builder(AlertDialogDemoListAct.this)
                                .setMessage("忽略覆盖文件操作")
                                .create()
                                .show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }
}

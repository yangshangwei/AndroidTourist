package com.turing.base.activity.Dlg_Tst_Ntf.DialogAct;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.turing.base.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * 因为此Activity只有一个ListView,为了方便开发简化代码，使用android提供的android.app.ListActivity
 * <p/>
 * 在ListActivity的内部通过代码创建ListView,因此并不需要布局文件
 */
public class DialogDemoListAct extends ListActivity implements AdapterView.OnItemClickListener {


    // ListView中要显示的item
    private String[] arr = new String[]{
            "=====对话框的基本用法=====",
            "带2个按钮（确认、取消）的对话框",
            "带3个按钮（覆盖、忽略、取消）的对话框",
            "简单列表对话框-setItems",
            "单选列表对话框-setSingleChoiceItems",
            "多选列表对话框-setMultiChoiceItems",
            "水平进度条风格对话框-ProgressDilaog.STYLE_HORIZONTAL",
            "旋转指针风格的对话框-ProgressDialog.STYLE_SPINNER",
            "登录对话框(自定义View)-setView",
            "使用Activity托管对话框",
            "=====对话框的高级应用=====",
            "阻止单击按钮关闭对话框",
            "改变对话框的显示位置-getWindow().setGravity(..)",
            "在对话框按钮和内容文本中插入图像",
            "改变对话框的透明度--alpha"
    };

    final String[] proviences = {"北京", "上海", "广州", "深圳", "纽约", "华盛顿", "拉斯维加斯"};
    // 用于保存当前列表项索引
    private int index;
    // 多选对话框中的数据lv
    private ListView lv;
    // 水平进度条的最大值
    private static final int MAX_PROGRESS = 100;
    // 默认的初始值
    private int progress = 0;

    private Handler progressDialogHandler;
    private static final int PROGRESSDIALOG_FLAG = 1;

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
                showMultiChoiceItems();
                break;
            case 6://水平进度条风格对话框
                showProgressDialog(ProgressDialog.STYLE_HORIZONTAL);
                break;
            case 7://旋转指针风格的对话框
                showProgressDialog(ProgressDialog.STYLE_SPINNER);
                break;
            case 8:// 登录对话框
                showCustomViewDialog();
                break;
            case 9: // 使用Activity托管对话框
                showActivityDialog();
                break;
            case 11: // 阻止单击按钮关闭对话框
                break;
            case 12: // 改变对话框的显示位置
//                changePostionOfDialog(Gravity.TOP);
//                changePostionOfDialog(Gravity.BOTTOM);
//                changePostionOfDialog(Gravity.LEFT);
//                changePostionOfDialog(Gravity.RIGHT);
                // 右上方
//                changePostionOfDialog(Gravity.RIGHT | Gravity.TOP);

                // 显示在任意位置
                showAnyPostionOfDilaog();

                break;
            case 13: // 在对话框按钮和内容文本中插入图像
                showTextImageDialog();
                break;
            case 14: // 改变对话框的透明度
                showTransparency_dialog(0.7f);
                break;
            default:
                break;
        }
    }

    private void showTransparency_dialog(float v) {

        // 创建对话框
        AlertDialog ad = new AlertDialog.Builder(this)
                .setTitle("改变对话框的透明度")
                .setIcon(R.drawable.tag_red)
                .setMessage("Alpha的取值范围 0~1 ,默认1完全不透明 ,我的透明度是" + v)
                .setPositiveButton("确定",null)
                .create();

        // 设置透明度
        Window window = ad.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.alpha = v ;

        window.setAttributes(lp);
        // 展示对话框
        ad.show();
    }


    private void showTextImageDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.flag_mark_blue)
                .setTitle("问候")
                .setMessage(

                        Html.fromHtml("哈哈，<img src=''/>你好.", new Html.ImageGetter() {

                            @Override
                            public Drawable getDrawable(String source) {
                                Drawable drawable = getResources().getDrawable(
                                        R.drawable.face);
                                drawable.setBounds(0, 0, 32, 32);
                                return drawable;

                            }
                        }, null))
                .setPositiveButton(
                        Html.fromHtml("<img src=''/>确定", new Html.ImageGetter() {

                            @Override
                            public Drawable getDrawable(String source) {
                                Drawable drawable = getResources().getDrawable(
                                        R.drawable.ok);
                                drawable.setBounds(0, 0, 20, 20);
                                return drawable;
                            }
                        }, null), null)
                .setNegativeButton(
                        Html.fromHtml("<img src=''/>取消", new Html.ImageGetter() {

                            @Override
                            public Drawable getDrawable(String source) {
                                Drawable drawable = getResources().getDrawable(
                                        R.drawable.cancel);
                                drawable.setBounds(0, 0, 20, 20);
                                return drawable;
                            }
                        }, null), null).create();
        alertDialog.show();
    }

    private void showAnyPostionOfDilaog() {

        AlertDialog ad = new AlertDialog.Builder(this)
                .setIcon(R.drawable.flag_mark_blue)
                .setTitle("改变位置的AlertDiaolog")
                .setMessage("我在自定义的任意位置")
                .create();

        Window window = ad.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        // 设置水平偏移量
        lp.x = -20;
        // 设置垂直偏移量
        lp.y = -120;

        window.setAttributes(lp);
        ad.show();

    }

    private void changePostionOfDialog(int postion) {

        /**
         AlertDialog alertDialog = new AlertDialog.Builder(this)
         .setIcon(R.drawable.flag_mark_blue)
         .setTitle("改变位置的AlertDiaolog")
         .setMessage("我在" + postion)
         .create();

         alertDialog.getWindow().setGravity(postion);
         alertDialog.show();


         **/

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.drawable.flag_mark_yellow);
        progressDialog.setTitle("ProgressDialog改变位置");
        progressDialog.setMessage("where am  I ?");
        progressDialog.getWindow().setGravity(postion);
        progressDialog.show();
    }

    /**
     * showDialog(int id)是在Api level 1 添加的方法。
     * Api level 13（Honeycomb 3.0） 时方法就废弃了。
     * 在程序里面调用Dialog使用新的DialogFragment类代替FragmentManager
     * 官方文档： http://android-developers.blogspot.in/2012/05/using-dialogfragments.html
     * 通过Android的兼容包，在旧平台也是可用的。
     */
    private void showActivityDialog() {
        startActivity(new Intent(this, ActivityDialog.class));
    }

    private void showCustomViewDialog() {
        // 第一种方式 将布局文件转换为view
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_alertdialog_login, null);
        // 第二种方式 因为R.layout.activity_login的根布局是LinearLayout
        //LinearLayout view = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_alertdialog_login, null);

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.tag_red)
                .setTitle("用户登录")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 登录逻辑
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 取消逻辑
                    }
                })
                .show();

    }


    private void showProgressDialog(int style) {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        //  设置提示的title的图标，默认是没有的
        progressDialog.setIcon(R.drawable.flag_mark_red);
        progressDialog.setTitle("数据处理中...");
        progressDialog.setMessage("请稍后...");
        // 设置进度对话框的风格 ,默认是圆形的
        progressDialog.setProgressStyle(style);
        // 设置是否可以通过点击Back键取消  默认true
        progressDialog.setCancelable(false);
        // 设置在点击Dialog外是否取消Dialog进度条  默认true
        progressDialog.setCanceledOnTouchOutside(false);

        // 设置最大值
        progressDialog.setMax(MAX_PROGRESS);
        // 设置暂停按钮
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "暂停", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 通过删除消息代码的方式停止定时器
                progressDialogHandler.removeMessages(PROGRESSDIALOG_FLAG);
            }
        });

        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "停止", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialogHandler.removeMessages(PROGRESSDIALOG_FLAG);
                progress = 0;
                progressDialog.setProgress(progress);
            }
        });

        // 展示
        progressDialog.show();


        progressDialogHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (progress >= MAX_PROGRESS) {
                    // 消失 并重置初始值
                    progressDialog.dismiss();
                    progress = 0;
                } else {
                    progress++;
                    progressDialog.incrementProgressBy(1);
                    // 随机设置下一次递增进度 (50 +毫秒)
                    progressDialogHandler.sendEmptyMessageDelayed(1, 50 + new Random().nextInt(500));
                }
            }
        };


        // 设置进度初始值
        progress = (progress > 0) ? progress : 0;
        progressDialog.setProgress(progress);
        // 发送消息
        progressDialogHandler.sendEmptyMessage(PROGRESSDIALOG_FLAG);

    }

    private void showMultiChoiceItems() {

        AlertDialog ad = new AlertDialog.Builder(this)
                .setIcon(R.drawable.tag_red)
                .setTitle("选择省份")
                .setMultiChoiceItems(proviences,
                        new boolean[]{false, false, false, false, false, false, false},
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            }
                        })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int count = lv.getCount();
                        String s = "您选择了：";
                        // 遍历
                        for (int i = 0; i < proviences.length; i++) {
                            if (lv.getCheckedItemPositions().get(i)) {
                                s += i + ":" + lv.getAdapter().getItem(i) + " ";
                            }
                        }

                        // 判断数量
                        if (lv.getCheckedItemPositions().size() > 0) {
                            new AlertDialog.Builder(DialogDemoListAct.this).setMessage(s).show();
                        } else {
                            new AlertDialog.Builder(DialogDemoListAct.this).setMessage("Nothing selected").show();
                        }

                    }
                })
                .setNegativeButton("取消", null)
                .create();
        // 获取lv
        lv = ad.getListView();
        // 显示AlertDialog ,show为异步方法,执行后，会继续执行下面的代码，在这里需要在最后调用
        ad.show();

    }

    private void showSingleChoiceItems() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.flag_mark_violet)
                .setTitle("选择省份")
                .setSingleChoiceItems(proviences, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 保存当前选中的列表项索引
                        index = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 提示信息
                        new AlertDialog.Builder(DialogDemoListAct.this)
                                // 使用index
                                .setMessage(index + " " + proviences[index])
                                .show();

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        new AlertDialog.Builder(DialogDemoListAct.this)
                                .setMessage("您没有选择")
                                .show();

                    }
                })
                .show();

    }

    /**
     * AlertDialog.Builder类的setItems方法可以创建简单的列表对话框
     */
    private void showSimpleDialog() {


        new AlertDialog.Builder(this)
                .setIcon(R.drawable.flag_mark_gray)
                .setTitle("请选择省份")
                .setItems(proviences, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 选择后的提示信息  记得调用show方法，否则不显示啦
                        final AlertDialog alertDialog = new AlertDialog.Builder(DialogDemoListAct.this)
                                .setMessage("选择了" + proviences[which])
                                .show();

                        // 设置定时器，5秒后,关闭AlertDialog
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() { //  alertDialog.cancel(); 也可以
                                alertDialog.dismiss();
                            }
                        }, 5 * 1000);
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
                        AlertDialog dialog1 = new AlertDialog.Builder(DialogDemoListAct.this)
                                .setMessage("文件已下载成功")
                                .create();
                        dialog1.show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 取消提示信息
                        new AlertDialog.Builder(DialogDemoListAct.this)
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
                .setMessage("覆盖后源文件将丢失...吧啦吧啦")
                .setPositiveButton("覆盖", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AlertDialog.Builder(DialogDemoListAct.this)
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

package com.turing.base.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.AppContext;
import com.turing.base.R;
import com.turing.base.activity.buttonAct.ButtonDemoAct;
import com.turing.base.activity.editTextAct.EditTextDemoAct;
import com.turing.base.activity.imageViewAct.ImageViewDemoListAct;
import com.turing.base.activity.intentAct.ApplicationTransActivity;
import com.turing.base.activity.intentAct.ClipBoardTransStringActivity;
import com.turing.base.activity.intentAct.ClipboardTransObjectDataAct;
import com.turing.base.activity.intentAct.Data;
import com.turing.base.activity.intentAct.GetIntentActivity;
import com.turing.base.activity.intentAct.StarActivityForResultAct;
import com.turing.base.activity.intentAct.StaticTransmitActivity;
import com.turing.base.activity.intentAct.XianSiDiaoyongAct;
import com.turing.base.activity.lifeCircle.LifeCircleActivity;
import com.turing.base.activity.listViewAct.ListViewDemoAct;
import com.turing.base.activity.textViewAct.TextViewDemoAct;
import com.turing.base.activity.viewstub.ViewStubAct;
import com.turing.base.adapter.MainMenuListAdapter;
import com.turing.base.beans.MainMenuListItemBean;
import com.turing.base.utils.ListViewDataFactory;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@Fullscreen
@EActivity(R.layout.activity_ui__base)
public class UI_Base extends Activity {

    @ViewById(R.id.id_lv_ui)
    ListView lv_ui ;

    List<MainMenuListItemBean> dataList ;
    MainMenuListAdapter adapter ;
    // ListView中显示的内容
    public static String[] datas = new String[]{
            "显示调用Activity",
            "隐式调用Activity",
            "隐式调用两个符合过滤条件的Activity",
            "Activity生命周期",
            "使用Intent传递数据",
            "使用静态（static）传递数据",
            "使用剪切板（Clipboard）传递String类型数据",
            "通过Clipboard传递复杂对象（通过Base64编码）",
            "通过全局变量传递数据",
            "返回数据到前一个Activity",
            "在代码中控制视图",
            "TextView",
            "EditText",
            "按钮和复选框控件",
            "ImageView",
            "时间和日期控件",
            "进度条控件",
            "列表控件",
            "滚动控件",
            "ImageSwitcher",
            "GridView",
            "TabHost",
            "ViewStub"
           };


    @AfterViews
    public void showAc(){
        // 模拟数据来源
        dataList = (List<MainMenuListItemBean>) ListViewDataFactory.simulateData(datas);
        LogUtils.d("UIBase数据初始化完毕");
        // 实例化Adapter
        adapter = new MainMenuListAdapter(this, dataList);
        // 设置adapter
        lv_ui.setAdapter(adapter);



        // 设置监听事件
        lv_ui.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: // 显示调用Activity
                        Toast.makeText(UI_Base.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
                        // 第一种方式
                        Intent intent = new Intent(UI_Base.this, XianSiDiaoyongAct.class);
                        startActivity(intent);
                        // 第二种方式
                        // Intent intent1 = new Intent();
                        // intent1.setClass(UI_Base.this,XianSiDiaoyongAct.class);
                        // startActivity(intent1);
                        break;
                    case 1:// 隐式调用Activity
                        Intent intent2 = new Intent("myAction2");
                        startActivity(intent2);
                        break;
                    case 2: // 隐式调用两个符合过滤条件的Activity
                        Intent intent3 = new Intent("myAction2");
                        intent3.addCategory("mycategory");
                        startActivity(intent3);
                        break;
                    case 3: // Activity生命周期
                        Intent intent4 = new Intent(UI_Base.this, LifeCircleActivity.class);
                        startActivity(intent4);
                        break;
                    case 4://使用Intent传递数据
                        Intent intent5 = new Intent(UI_Base.this,GetIntentActivity.class);
                        //简单类型
                        intent5.putExtra("intent_string","通过Intent传递的字符串");
                        intent5.putExtra("intent_int", 20);

                        // 可序列化的对象
                        Data data = new Data();
                        data.setId(99);
                        data.setName("ZTE");
                        intent5.putExtra("intent_object", data);

                        startActivity(intent5);
                        break;
                    case 5:// 使用静态（static）传递数据
                        Intent intent6 = new Intent(UI_Base.this,StaticTransmitActivity.class);
                        // 赋值
                        StaticTransmitActivity.msg="通过static变量来的";
                        StaticTransmitActivity.age = 88 ;
                        StaticTransmitActivity.data = new Data();
                        StaticTransmitActivity.data.setName("Jack");
                        StaticTransmitActivity.data.setId(77);

                        startActivity(intent6);
                        break;
                    case 6://使用剪切板（Clipboard）传递数据
                        Intent intent7 = new Intent(UI_Base.this, ClipBoardTransStringActivity.class);

                        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                        // api 11的方法   @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                        clipboardManager.setText("通过Clipboard传递String数据");

                        startActivity(intent7);
                        break;
                    case 7:// 通过Clipboard传递复杂对象
                        transObjectByClipBoard();
                        break;
                    case 8:// 通过全局变量来传递数据

                        AppContext context = (AppContext)getApplication();
                        context.appName = "ANDROID BASE";
                        context.data.setId(0000);
                        context.data.setName("通过全局变量来传递数据");

                        Intent intent9 = new Intent(UI_Base.this, ApplicationTransActivity.class);
                        startActivity(intent9);
                        break;
                    case 9: // 返回数据到前一个Activity
                        Intent intent10 = new Intent(UI_Base.this, StarActivityForResultAct.class);
                        startActivityForResult(intent10, 1); // 请求码1  一定要>=0
                        break;
                    case 10:// 在代码中操作视图
                        break;
                    case 11:// TextView
                        Intent textViewIntent = new Intent();
                        textViewIntent.setClass(UI_Base.this,TextViewDemoAct.class);
                        startActivity(textViewIntent);
                        break;
                    case 12:// EditText
                        startActivity(new Intent(UI_Base.this,EditTextDemoAct.class));
                        break;
                    case 13://按钮和复选框控件
                        startActivity(new Intent(UI_Base.this, ButtonDemoAct.class));
                        break;
                    case 14://ImageView
                        startActivity(new Intent(UI_Base.this, ImageViewDemoListAct.class));
                        break;
                    case 15://时间和日期控件
                        break;
                    case 16://进度条控件
                        break;
                    case 17://列表控件
                        startActivity(new Intent(UI_Base.this, ListViewDemoAct.class));
                        break;
                    case 18://滚动控件

                        break;
                    case 19://ImageSwitcher
                        break;
                    case 20://GridView
                        break;
                    case 21://TabHost
                        break;
                    case 22://ViewStub
                        startActivity(new Intent(UI_Base.this, ViewStubAct.class));
                        break;
                        // TODO
                    default:
                        break;
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){  // 请求码1
            case 1:
                switch (resultCode){ // 响应码
                    case 2:
                        Toast.makeText(UI_Base.this,data.getStringExtra("value"),Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void transObjectByClipBoard(){
        Intent intent8 = new Intent(UI_Base.this, ClipboardTransObjectDataAct.class);

        ClipboardManager cbm = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);

        // 通过Clipboard传递复杂对象
        Data  data3 = new Data();
        data3.setId(55);
        data3.setName("Clipboard传递复杂对象");

        // 将data2对象转换成Base64格式的字符串
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String base64Str = "";

        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(data3);
            // 使用Base64.encodeToString方法将byte[]数据转换为Base64字符串
            base64Str = Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 向剪切板写入Base64格式的字符串
        cbm.setText(base64Str);
        startActivity(intent8);
    }
}

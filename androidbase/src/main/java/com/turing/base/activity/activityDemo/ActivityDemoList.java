package com.turing.base.activity.activityDemo;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.turing.base.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityDemoList extends ListActivity implements AdapterView.OnItemClickListener {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "直接拨号",
            "把电话号码传入拨号程序",
            "调用拨号程序",
            "浏览网页",
            "向Email客户端传递E-mail地址",
            "发送Email",
            "查看联系人",
            "显示系统设置页面(设置主界面、Wifi设置界面)",
            "启动音频处理程序",
            "--------------------",
            "自定义Activity Action",
            "--------高级应用------------",
            "ActivityGroup",
            "自定义半透明窗口",
            "Activity之间切换的动画效果"
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
            item.put("img", R.drawable.tag_green);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: // 直接拨号
                Intent callIntent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:123456789"));
                startActivity(callIntent);
                break;
            case 1: // 把电话号码传入拨号程序
                startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:999888777")));
                break;
            case 2:// 调用拨号程序
                Intent touchDialerIntent = new Intent("com.android.phone.action.TOUCH_DIALER");
                startActivity(touchDialerIntent);
                break;
            case 3: // 浏览网页
                Intent webIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://wwww.baidu.com"));
                startActivity(webIntent);
                break;
            case 4:// 向Email客户端传递E-mail地址
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:XXX@qq.com"));
                startActivity(emailIntent);
                break;
            case 5: //发送Email
                sendEmail();
                break;
            case 6: // 查看联系人
                Intent contactIntent = new Intent("com.android.contacts.action.LIST_CONTACTS");
                startActivity(contactIntent);
                break;
            case 7: // 显示系统设置页面(设置主界面、Wifi设置界面)
                //设置主界面
                Intent settingsIntent = new Intent("android.settings.SETTINGS");
                startActivity(settingsIntent);
                //Wifi设置界面
                Intent wifiSettingIntent = new Intent("android.settings.WIFI_SETTINGS");
                startActivity(wifiSettingIntent);
                break;
            case 8: // 启动音频处理程序
                Intent audioIntent = new Intent(Intent.ACTION_GET_CONTENT);
                audioIntent.setType("audio/*");
                startActivity(Intent.createChooser(audioIntent,"选择音频程序") );
                break;
            case 9:
                break;
            case 10: // 自定义Activity Action
                break;
            case 11:
                break;
            case 12: // ActivityGroup
                break;
            case 13: //自定义半透明窗口
                startActivity(new Intent(this, TranslucenceWindowAct.class));
                break;
            case 14://  Activity之间切换的动画效果
                startActivity(new Intent(this,SecondAnimAct.class));
                // 添加动画效果 (淡入淡出 fade  hyperspace 立体飞出 )
                overridePendingTransition(R.anim.fade,R.anim.fade);
                //overridePendingTransition(R.anim.hyperspace,R.anim.hyperspace);



                break;
            default:
                break;
        }
    }


    private void sendEmail() {
        // ACTION_SEND
        Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);

        // 要发送的信息通过putExtra方法指定

        // 目标Email
        sendEmailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"123@126.com"});
        // 抄送Email
        sendEmailIntent.putExtra(Intent.EXTRA_CC,new String[]{"111@qq.com","222@163.com"});
        // Email标题
        sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT,"标题XXXXX");
        // Email内容
        sendEmailIntent.putExtra(Intent.EXTRA_TEXT,"内容XXX");
        // 指定Email的内容为纯文本
        sendEmailIntent.setType("text/plain");

        // 建立一个选择器，并由用户选择使用那个一个客户端发送消息
        startActivity(Intent.createChooser(sendEmailIntent,"选择发送消息的客户端"));

    }
}

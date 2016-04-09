package com.turing.base.activity.webview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.turing.base.R;
import com.turing.base.activity.webview.WebView_Cache.WebViewCacheAct;
import com.turing.base.activity.webview.WebView_Download.WebViewDownLoadWithSelfThread;
import com.turing.base.activity.webview.WebView_Download.WebViewDownload;
import com.turing.base.activity.webview.WebView_Js_inter.WebViewAndJs01;
import com.turing.base.activity.webview.WebView_Js_inter.WebViewReadContactsAct;
import com.turing.base.activity.webview.WebView_Js_inter.WebviewJS02;
import com.turing.base.utils.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参考文章：http://www.runoob.com/w3cnote/android-tutorial-webview.html
 */
public class WebViewDemoAct extends ListActivity implements AdapterView.OnItemClickListener {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "根据URL加载网页-直接在Activity上加载一个WebView",

            "根据URL加载网页-布局代码中设置WebView",

            "WebView滚动事件的监听",

            "滚动条的问题",

            "设置缩放以及自适应屏幕",

            "获取WebView的Cookie数据",

            "设置WebView的Cookie数据",

            "HTML通过JS显示Toast与普通列表的对话框",

            "HTML通过JS调用三种不同的对话框",

            "HTML通过JS读取Android联系人并显示",
            "webview-调用其它浏览器下载文件",
            "webview-使用自己写的线程下载文件",
            "开启webview缓存+清除webView缓存"
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
            item.put("img", R.drawable.gur_project_9);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String msg = "setHorizontalScrollBarEnabled(false);//水平不显示\n" +
                "setVerticalScrollBarEnabled(false); //垂直不显示\n" +
                "setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);//滚动条在WebView内侧显示\n" +
                "setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)//滚动条在WebView外侧显示";

        switch (position) {

            case 0: //根据URL加载网页-直接在Activity上加载一个WebView
                startActivity(new Intent(this, WebView00.class));
                break;
            case 1: //根据URL加载网页-布局代码中设置WebView
                startActivity(new Intent(this, WebView01.class));
                break;
            case 2: // WebView滚动事件的监听
                startActivity(new Intent(this, WebViewScrollChanged.class));
                break;
            case 3:// 滚动条的问题
                AlertUtil.showDialogWithClose(this, msg);
                break;
            case 4: // 设置缩放以及自适应屏幕
                startActivity(new Intent(this, WebViewZoomAct.class));
                break;
            case 5: // 获取WebView的Cookie数据
                break;
            case 6: // 设置WebView的Cookie数据
                break;
            case 7: // HTML通过JS显示Toast与普通列表的对话框
                startActivity(new Intent(this, WebViewAndJs01.class));
                break;
            case 8: // HTML通过JS调用三种不同的对话框
                startActivity(new Intent(this, WebviewJS02.class));
                break;
            case 9:// HTML通过JS读取Android联系人并显示
                startActivity(new Intent(this, WebViewReadContactsAct.class));
                break;
            case 10: // 调用其它浏览器下载文件
                startActivity(new Intent(this, WebViewDownload.class));
                break;
            case 11: // 写线程下载文件
                startActivity(new Intent(this, WebViewDownLoadWithSelfThread.class));
                break;
            case 12: // 开启webview缓存+清除webView缓存
                startActivity(new Intent(this, WebViewCacheAct.class));
                break;
            default:
                break;
        }
    }
}

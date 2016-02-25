package com.turing.base.activity.Dlg_Tst_Ntf.ToastAct;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.base.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToastDemoListAct extends ListActivity {


    // ListView中要显示的item
    private String[] arr = new String[]{
            "文字Toast提示框-makeText",
            "自定义View的Toast-setView",
            "永不关闭的Toast",
            "用PopupWindow模拟Toast信息框"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initEvents();
    }


    private void initView() {

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                simulateData(arr),
                R.layout.activity_scroll_view_list_item,
                new String[]{"img", "info"},
                new int[]{R.id.id_sv_iv, R.id.id_sv_tv});

        setListAdapter(simpleAdapter);
    }


    private void initEvents() {

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(ToastDemoListAct.this, R.string.app_name, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        showCustomToast();
                        break;
                    case 2:
                        Toast.makeText(ToastDemoListAct.this,"高版本的好像不行啊....", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        showPopupWindowToast();
                        break;
                    default:
                        break;
                }
            }
        });
    }



    private void showPopupWindowToast() {

        LayoutInflater inflater  = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_popupwd_toast, null);

        final PopupWindow popupWindow = new PopupWindow(view,500 ,200);
        popupWindow.setTouchable(false);
        popupWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL,20 ,0);


        // 设置定时器，5秒后自动关闭
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        } , 5*1000);
    }

    private void showCustomToast() {
        // 将布局文件转换为View
        View view = getLayoutInflater().inflate(R.layout.activity_custom_toast, null);
        // 设置提示文字
        TextView tv = (TextView) view.findViewById(R.id.textview);
        tv.setText("自定义Toast");
        // Toast展示
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
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


}

package com.turing.base.activity.fragment;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.turing.base.R;
import com.turing.base.activity.fragment.dynamicload.FragmentDynamicLoadAct;
import com.turing.base.activity.fragment.fragmentPractice1.BottomNvgWithTextView;
import com.turing.base.activity.fragment.fragmentPractice2.BottomNvgWithRadioButton;
import com.turing.base.activity.fragment.fragmentPractice3.BottomNvgTVwithNumberAct;
import com.turing.base.activity.fragment.fragmentPractice4.BottomNvgViewPageAct;
import com.turing.base.activity.fragment.staticload.FragmentStaticLoadAct;
import com.turing.base.utils.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://www.runoob.com/w3cnote/android-tutorial-fragment-base.html
 */
public class FragmentDemoAct extends ListActivity implements AdapterView.OnItemClickListener {


    // ListView中要显示的item
    private String[] arr = new String[]{
            "静态加载Fragment",
            "动态加载Fragment",
            "Fragment管理与Fragment事务",
            "Fragment与Activity的交互",
            "Fragment练习—底部导航栏的实现-TextView实现",
            "Fragment练习—底部导航栏的实现-RadioButton实现",
            "Fragment练习—底部导航栏的实现-TextView实现+类似QQ的红点消息提示",
            "Fragment练习—底部导航栏+ViewPager滑动切换页面",
            "TODO"
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
            item.put("img", R.drawable.gur_project_7);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String message = null;
        switch (position) {
            case 0: // 静态加载Fragment
                startActivity(new Intent(FragmentDemoAct.this, FragmentStaticLoadAct.class));
                break;
            case 1: // 动态加载Fragment
                startActivity(new Intent(FragmentDemoAct.this, FragmentDynamicLoadAct.class));
                break;
            case 2:// Fragment管理与Fragment事务
                message = "Activity管理fragment主要依靠FragmentManager.\n " +
                        "如果要增删替换Fragment,则需要借助FragmentTransaction对象,操作完成记得调用commit方法";
                AlertUtil.showDialogWithClose(FragmentDemoAct.this, message);
                break;
            case 3:// Fragment与Activity的交互
                message = "参考[动态加载Fragment中的代码,有演示~]\n" +
                        "Activity传递数据给Fragment-setArguments.\n" +
                        "Fragment传递数据给Activity-通过接口回调." +
                        "Fragment之间传递数据-找到要接收数据的fragment对象,直接调用setArguments传数据进去就可以了 ";
                AlertUtil.showDialogWithClose(FragmentDemoAct.this, message);
                break;
            case 4:// Fragment练习—底部导航栏的实现-TextView实现
                startActivity(new Intent(FragmentDemoAct.this, BottomNvgWithTextView.class));
                break;
            case 5:// Fragment练习—底部导航栏的实现-RadioButton实现
                startActivity(new Intent(FragmentDemoAct.this, BottomNvgWithRadioButton.class));
                break;
            case 6:// Fragment练习—底部导航栏的实现-TextView实现+类似QQ的红点消息提示
                startActivity(new Intent(FragmentDemoAct.this, BottomNvgTVwithNumberAct.class));
                break;
            case 7:// Fragment练习—底部导航栏+ViewPager滑动切换页面
                startActivity(new Intent(FragmentDemoAct.this, BottomNvgViewPageAct.class));
                break;
            default:
                break;
        }
    }


}

package com.turing.base.activity.scrollViewAct;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 因为只有一个ListView，因此使用ListViewActivity更加方便 ，
 * 如果不是自定义的话，setContentView(R.layout.activity_scroll_view_list_item)可以省略了
 */
public class ScrollViewDemoList extends ListActivity implements AdapterView.OnItemClickListener {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "ScrollView",
            "HorizontalScrollView",
            "可垂直和水平滚动的视图",
            "Gallery",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 使用ArrayAdapter初始化
        // initViewWithArrayAdapter();


        // 使用SimpleAdpater初始化
        // initViewWithSimpleAdapter();


        // 使用BaseAdapter初始化
        initViewWithBaseAdapter();


        // 获取ListView，设置单击事件
        getListView().setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:// ScrollView
                startActivity(new Intent(ScrollViewDemoList.this,ScrollViewAct.class));
                break;
            case 1://HorizontalScrollView
                startActivity(new Intent(ScrollViewDemoList.this,HorizontalScrollViewAct.class));
                break;
            case 2:// 可垂直和水平滚动的视图
                startActivity(new Intent(ScrollViewDemoList.this,HorVertScrollViewAct.class));
                break;
            case 3://Gallery
                startActivity(new Intent(ScrollViewDemoList.this,GalleryAct.class));
                break;
            default:
                break;
        }
    }


    private void initViewWithArrayAdapter() {

        // 数据来源d
        String[] dataArr = new String[]{"ddd", "33", "333"};
        // Adapter
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataArr);
        // 设置adapter
        setListAdapter(adapter);
    }


    private void initViewWithSimpleAdapter() {

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, simulateData(arr),
                R.layout.activity_scroll_view_list_item,
                new String[]{"img", "info"},
                new int[]{R.id.id_sv_iv, R.id.id_sv_tv});

        setListAdapter(simpleAdapter);
    }

    /**
     * 生成item
     *
     * @param arr
     * @return
     */
    private List<Map<String, Object>> simulateData(String[] arr) {

        List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < arr.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("img", R.drawable.flag_mark_blue);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    private void initViewWithBaseAdapter() {

        // 数组转List
        List list = Arrays.asList(arr);

        SVAdapter adapter = new SVAdapter(list, this);

        setListAdapter(adapter);
        // 或者 使用  getListView().setAdapter(adapter);
    }

    /**
     * 内部类
     */
    class SVAdapter extends BaseAdapter {

        private List list;
        private Context context;
        private LayoutInflater inflater;

        /**
         * 构造函数，用于初始化
         *
         * @param list
         * @param context
         */
        public SVAdapter(List list, Context context) {
            this.list = list;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            LogUtils.d("入参List的总数：" + list.size());
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                // 实例化ViewHolder
                holder = new ViewHolder();
                // 将布局文件转换为组件
                convertView = inflater.inflate(R.layout.activity_scroll_view_list_item, null);
                // ViewHolder中的变量 初始化
                holder.imageView = (ImageView) convertView.findViewById(R.id.id_sv_iv);
                holder.textView = (TextView) convertView.findViewById(R.id.id_sv_tv);
                // setTag绑定
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // ViewHolder中的变量 赋值
            holder.imageView.setImageResource(R.drawable.flag_mark_yellow);
            holder.textView.setText(list.get(position).toString());

            return convertView;
        }


        class ViewHolder {
            ImageView imageView;
            TextView textView;
        }
    }

}

package com.turing.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.turing.base.R;
import com.turing.base.beans.MainMenuListItemBean;

import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015/10/10  14:49.
 * @version 1.0
 * @desc
 */
public class MainMenuListAdapter extends BaseAdapter {

    private List<MainMenuListItemBean> mDataList;
    private Context mContext;
    private LayoutInflater mInflate;

    public MainMenuListAdapter(Context context, List<? extends Object> dataList) {
        this.mContext = context;
        this.mDataList = (List<MainMenuListItemBean>) dataList;
        this.mInflate = LayoutInflater.from(context);
    }

    //数据源中的数据对象个数
    @Override
    public int getCount() {
        return mDataList.size();
    }

    //指定位置处的数据源对象
    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    //数据源对象的Id,如果有的话
    //如果数据源对象自己没有定义Id，则可以简单地返回其在数据源中的位置
    @Override
    public long getItemId(int position) {
        return position;
    }

    //每当Android ListView需要显示一行时，它会调用此方法
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //如果没有可以重用的控件
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.activity_main_menu_list_item, null);
            //获取用于显示内容的控件的引用
            holder.idivpic = (ImageView) convertView.findViewById(R.id.id_iv_pic);
            holder.idtvcontent = (TextView) convertView.findViewById(R.id.id_tv_content);

            convertView.setTag(holder);
        } else {
            //控件己经被创建过，直接重用
            holder = (ViewHolder) convertView.getTag();
        }
        //依据位置提取相应的数据源对象
        MainMenuListItemBean bean = mDataList.get(position);

        //设置显示内容
        holder.idivpic.setImageResource(R.drawable.flag_mark_blue);
        holder.idtvcontent.setText(bean.getContent());
        return convertView;
    }

    /**
     * 创建一个内部类ViewHolder保存控件。只加载View的时候使用findViewById()方法。
     * 使用View的setTag()方法保存ViewHolder。当convertView不为空的时候，直接取出来即可
     */
    public class ViewHolder {
        public TextView idtvcontent;
        public ImageView idivpic;

        }
    }


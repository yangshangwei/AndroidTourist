package com.turing.nutritiousSerial.listPopupWindow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.turing.base.R;
import com.turing.nutritiousSerial.listPopupWindow.beans.PopupItemBean;

import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-04-25  11:04.
 * @version 1.0
 * @desc
 */
public class CustomPopupWindowAdpater extends BaseAdapter {

    private Context context;
    private List<PopupItemBean> datas;
    private LayoutInflater layoutInflater;

    /**
     * 构造函数
     */
    public CustomPopupWindowAdpater(Context context, List<PopupItemBean> datas) {
        this.context = context;
        this.datas = datas ;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 声明ViewHolder
        ViewHolder viewHolder;

        if (convertView == null) {
            // 加载Item布局，转换为View布局
            convertView = layoutInflater.inflate(R.layout.popup_item, parent, false);
            // 实例化ViewHolder
            viewHolder = new ViewHolder();
            // 查找组件赋值给ViewHolder
            viewHolder.textView = (TextView) convertView.findViewById(R.id.id_tv_popupItemText);
            // 设置TAG
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        // 设置Item中的值
        viewHolder.textView.setText(datas.get(position).getText()  );


        return convertView;
    }


    /**
     * 对应Item布局中的组件
     */
    class ViewHolder {

        private TextView textView;


    }
}

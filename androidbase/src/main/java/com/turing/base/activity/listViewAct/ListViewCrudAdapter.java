package com.turing.base.activity.listViewAct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

import java.util.List;
import java.util.Random;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-01-27  15:00.
 * @version 1.0
 */

public class ListViewCrudAdapter extends BaseAdapter {

    private Context context;
    private List textIdList;
    private LayoutInflater inflater;


    private TextView textView;
    private ImageView imageView;
    public static CheckBox tv_cb, iv_cb;



    /**
     * 构造函数
     *
     * @param context
     * @param datalist
     */
    public ListViewCrudAdapter(Context context, List datalist) {
        this.context = context;
        this.textIdList = datalist;
        // 初始化inflater，用于将动态xml加载为组件
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        LogUtils.d("调用getCount");
        return textIdList.size();
    }

    @Override
    public Object getItem(int position) {
        LogUtils.d("调用getItem");
        return textIdList.get(position);
    }

    @Override
    public long getItemId(int position) {
        LogUtils.d("调用getItemId");
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LogUtils.d("调用getView,position:" + position);

        //  TextViewHoder viewHoder;
        RelativeLayout relativeLayout = null;
        // 如果是字符串
        if (textIdList.get(position) instanceof String) {
            /**
             if (convertView == null) {
             // 实例化ViewHodler
             viewHoder = new TextViewHoder();
             // 动态加载布局文件 转换为组件
             convertView = inflater.inflate(R.layout.listview_crud_text_item, parent, false);
             // 初始化组件
             textView = (TextView) convertView.findViewById(R.id.id_tv_crud);
             // convertView 和 viewHoder 通过tag绑定
             convertView.setTag(viewHoder);
             } else {
             viewHoder = (TextViewHoder) convertView.getTag();
             LogUtils.d("复用TextViewHoder");
             }
             **/
            relativeLayout = (RelativeLayout) inflater.inflate(R.layout.listview_crud_text_item, parent, false);
            textView = (TextView) relativeLayout.findViewById(R.id.id_tv_crud);
            //tv_cb = (CheckBox) relativeLayout.findViewById(R.id.id_tv_cb_crud);

            // 设置
            textView.setText(textIdList.get(position).toString());

        }
        // 如果是整型
        if (textIdList.get(position) instanceof Integer) {

            relativeLayout = (RelativeLayout) inflater.inflate(R.layout.listview_crud_img_item, parent, false);
            imageView = (ImageView) relativeLayout.findViewById(R.id.id_iv_crud);
            iv_cb = (CheckBox) relativeLayout.findViewById(R.id.id_iv_cb_crud);

            // 设置
            imageView.setImageResource(Integer.parseInt(textIdList.get(position).toString()));
        }

        return relativeLayout;
    }


    /**
     * 将文本添加到ListView的Item中
     */
    public void addText2lv(String text) {
        textIdList.add(text);
        notifyDataSetChanged();
        LogUtils.d("Text数据更新完毕");
    }

    /**
     * 将ImageView添加到ListView中
     *
     * @param resIdByReflection
     */
    public void addImg2lv(int resIdByReflection) {

        textIdList.add(resIdByReflection);
        notifyDataSetChanged();
        LogUtils.d("IamgeView数据更新完毕");
    }

    /**
     * 清除所有的数据
     */
    public void removeAll() {
        textIdList.clear();
        notifyDataSetChanged();
    }

    /**
     * 随机删除
     */
    public void delSelected() {
        // 随机删除
        int randomNum = new Random().nextInt(textIdList.size());
        textIdList.remove(randomNum);
        notifyDataSetChanged();
        LogUtils.d("randomNum：" + randomNum + " 被删除");
    }

    /**
     *  修改用户选中的
     * @param selectedIndex
     * @param randomText
     */
    public void modify(int selectedIndex, String randomText) {
        LogUtils.d("selectedIndex------:" + selectedIndex);
        if(selectedIndex <0) return ;
        textIdList.set(selectedIndex, randomText);
        notifyDataSetChanged();

    }




    /**
     * ViewHold模式 避免重复使用findViewById，利用listview的缓存


     class TextViewHoder {
     TextView textView;
     }


     class ImageViewHolder {
     ImageView imageView;
     }
     */
}

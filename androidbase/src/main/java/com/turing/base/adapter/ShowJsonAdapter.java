package com.turing.base.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;
import com.turing.base.beans.Person;
import com.turing.base.http.jsonHttp.HttpThreadGetPicByURL;

import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-19  23:31.
 * @version 1.0
 * @desc
 */
public class ShowJsonAdapter extends BaseAdapter {

    private Context context;
    private List<Person> personList;
    private LayoutInflater inflater;

    private Handler handler = new Handler();

    public ShowJsonAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LogUtils.d("getView-position:" + String.valueOf(position));
        ViewHolder holder = null;
        if (convertView == null) {
            LogUtils.d("convertView == null,Then inflate and findViewById\"");
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.activity_show_original_json_item, null);
            // 查找组件初始化
            holder.iv_pic = (ImageView) convertView.findViewById(R.id.id_iv_showJson_url);
            holder.tv_name = (TextView) convertView.findViewById(R.id.id_tv_name);
            holder.tv_age = (TextView) convertView.findViewById(R.id.id_tv_age);
            holder.tv_school1 = (TextView) convertView.findViewById(R.id.id_tv_school1);
            holder.tv_school2 = (TextView) convertView.findViewById(R.id.id_tv_school2);
            // setTag
            convertView.setTag(holder);
        } else {
            LogUtils.d("convertView != null,Then get Holder");
            holder = (ViewHolder) convertView.getTag();
        }
        // 赋值
        Person person = personList.get(position);
        holder.tv_name.setText("姓名:" + person.getName());
        holder.tv_age.setText("年龄:" + person.getAge());

        holder.tv_school1.setText("本科:" + person.getSchoolList().get(0).getName());
        holder.tv_school2.setText("硕士:" + person.getSchoolList().get(1).getName());

        // 根据URL加载图片 当然了只是为了演示 ，没有考虑缓存等 请忽略，非本次重点
        String url = person.getUrl();
        LogUtils.d("图片地址:" + url);
        new HttpThreadGetPicByURL(holder.iv_pic ,url ,handler ).start();

        return convertView;
    }


    /**
     * 对应item布局文件中组件
     */
    class ViewHolder {
        public ImageView iv_pic;
        public TextView tv_name;
        public TextView tv_age;
        public TextView tv_school1;
        public TextView tv_school2;


    }
}

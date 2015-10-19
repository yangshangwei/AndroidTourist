package com.turing.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.turing.base.R;
import com.turing.base.beans.Person;

import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-19  23:31.
 * @version 1.0
 * @desc
 */
public class ShowJsonAdapter extends BaseAdapter {

    private Context context ;
    private List<Person> personList ;
    private LayoutInflater inflater ;

    public ShowJsonAdapter(Context context,List<Person> personList) {
        this.context = context ;
        this.personList = personList ;
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

        ViewHolder holder = null ;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.activity_show_original_json_item,null);
            // 初始化
            holder.tv_name = (TextView)convertView.findViewById(R.id.id_tv_name);
            holder.tv_age = (TextView)convertView.findViewById(R.id.id_tv_age);
           // holder.tv_url = (TextView)convertView.findViewById(R.id.);
            holder.tv_school1 = (TextView)convertView.findViewById(R.id.id_tv_school1);
            holder.tv_school2 = (TextView)convertView.findViewById(R.id.id_tv_school2);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        // 赋值
        Person person = personList.get(position);
        holder.tv_name.setText("姓名:"+person.getName());
        holder.tv_age.setText("年龄:"+person.getAge());

        holder.tv_school1.setText("本科:"+person.getSchoolList().get(0).getName());
        holder.tv_school2.setText("硕士:"+person.getSchoolList().get(1).getName());

        return convertView;
    }


    /**
     * 对应item布局文件中组件
     */
    class ViewHolder{
        public TextView tv_name ;
        public TextView tv_age ;
        public TextView tv_url ;
        public TextView tv_school1 ;
        public TextView tv_school2 ;


    }
}

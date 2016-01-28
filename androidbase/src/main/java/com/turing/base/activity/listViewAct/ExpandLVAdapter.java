package com.turing.base.activity.listViewAct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.turing.base.R;

import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-01-28  14:51.
 * @version 1.0
 * @desc
 */
public class ExpandLVAdapter extends BaseExpandableListAdapter {

    private List<GroupBean> list;
    private Context context;

    /**
     * 构造函数
     *
     * @param list
     * @param context
     */
    public ExpandLVAdapter(List<GroupBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            holder = new GroupHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group, null);
            holder.title = (TextView) convertView.findViewById(R.id.group_title);
            holder.iv = (ImageView) convertView.findViewById(R.id.group_ico);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        holder.title.setText(list.get(groupPosition).getGroupName());

        if (isExpanded) {
            holder.iv.setImageResource(R.drawable.rounds_open);
        } else {
            holder.iv.setImageResource(R.drawable.rounds_close);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = LayoutInflater.from(context).inflate( R.layout.item_child, null);
            holder.name = (TextView) convertView.findViewById(R.id.child_name);
            holder.sign = (TextView) convertView.findViewById(R.id.child_sign);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        ChildBean cb = list.get(groupPosition).getChildren().get(childPosition);
        holder.name.setText(cb.getName());
        holder.sign.setText("[签名]" + cb.getSign());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class GroupHolder {
        TextView title;
        ImageView iv;
    }

    class ChildHolder {
        TextView name, sign;
    }
}

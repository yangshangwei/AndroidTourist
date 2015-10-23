package com.turing.base.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.turing.base.beans.ClassBean;
import com.turing.base.beans.StudentBean;

import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-19  23:31.
 * @version 1.0
 * @desc
 */
public class ShowXmlAdapter extends BaseExpandableListAdapter {



    private List<ClassBean> classList;
    private Context context;

    public ShowXmlAdapter(Context context, List<ClassBean> classList) {
        this.context = context;
        this.classList = classList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<StudentBean> sList = classList.get(groupPosition).getList();
        StudentBean be = null;
        if (sList != null) {
            be = sList.get(childPosition);
        }
        return be;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        List<StudentBean> sList = classList.get(groupPosition).getList();

        if (sList != null && sList.size() > 0
                && sList.get(childPosition).getId() != null) {
            TextView tv1 = getTextView2();
            tv1.setText(sList.get(childPosition).getId());
            ll.addView(tv1);
        }

        if (sList != null && sList.size() > 0
                && sList.get(childPosition).getName() != null) {
            TextView tv2 = getTextView2();
            tv2.setText(sList.get(childPosition).getName());
            ll.addView(tv2);
        }

        if (sList != null && sList.size() > 0
                && sList.get(childPosition).getSex() != null) {
            TextView tv3 = getTextView2();
            tv3.setText(sList.get(childPosition).getSex());
            ll.addView(tv3);
        }
        return ll;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int size = 0;
        List<StudentBean> sList = classList.get(groupPosition).getList();
        if (sList != null) {
            size = sList.size();
        }
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return classList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return classList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv1 = getTextView1();
        tv1.setText(classList.get(groupPosition).getId());
        ll.addView(tv1);

        TextView tv2 = getTextView1();
        tv2.setText(classList.get(groupPosition).getName());
        ll.addView(tv2);
        return ll;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private TextView getTextView1() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 20, 0);
        TextView tv = new TextView(context);
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setPadding(36, 0, 0, 0);
        tv.setTextSize(25);
        return tv;
    }

    private TextView getTextView2() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 0, 50, 0);
        TextView tv = new TextView(context);
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setPadding(36, 0, 0, 0);
        tv.setTextSize(18);
        return tv;
    }


}

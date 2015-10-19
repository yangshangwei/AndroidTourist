package com.turing.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-19  23:31.
 * @version 1.0
 * @desc
 */
public class ShowJsonAdapter extends BaseAdapter {

    private Context context ;

    public ShowJsonAdapter(Context context) {
        this.context = context ;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

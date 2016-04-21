package com.turing.base.android_hero.chapter6_Draw.xml;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.base.R;

import java.util.ArrayList;

public class SelectorDemoAct extends AppCompatActivity implements View.OnClickListener {

    private Button activateBtn;
    private Button selectBtn;
    private ListView listView;
    private ArrayList<String> mArrayList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_demo);

        activateBtn = (Button) findViewById(R.id.btn_activate);
        activateBtn.setOnClickListener(this);

        selectBtn = (Button) findViewById(R.id.btn_selected);
        selectBtn.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.list);
        getData();
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SelectorDemoAct.this, "Item Click on " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View v) {
        if (v == activateBtn) {
            if (activateBtn.isActivated()) {
                activateBtn.setActivated(false);
                activateBtn.setText("未激活");
            } else {
                activateBtn.setActivated(true);
                activateBtn.setText("已激活");
            }
        }
        if (v == selectBtn) {
            if (selectBtn.isSelected()) {
                selectBtn.setSelected(false);
                selectBtn.setText("未选中");
            } else {
                selectBtn.setSelected(true);
                selectBtn.setText("已选中");
            }
        }
    }

    private ArrayList<String> getData() {
        mArrayList.add("测试数据0");
        mArrayList.add("测试数据1");
        mArrayList.add("测试数据2");
        mArrayList.add("测试数据3");
        mArrayList.add("测试数据4");
        mArrayList.add("测试数据5");
        return mArrayList;
    }

    class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        @Override
        public int getCount() {
            inflater = LayoutInflater.from(SelectorDemoAct.this);
            return mArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return mArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_list, parent, false);
                holder = new ViewHolder();
                holder.titleTxt = (TextView) convertView.findViewById(R.id.txt_title);
                holder.button = (Button) convertView.findViewById(R.id.btn);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.titleTxt.setText(mArrayList.get(position));
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SelectorDemoAct.this, "Button " + position + " click", Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }

        class ViewHolder {
            TextView titleTxt;
            Button button;
        }
    }
}
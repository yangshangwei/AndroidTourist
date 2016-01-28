package com.turing.base.activity.listViewAct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.turing.base.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListViewAct extends AppCompatActivity {

    private ExpandableListView mListView;
    private ExpandLVAdapter adapter;
    private List<GroupBean> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        //初始化数据
        initData();
        mListView = (ExpandableListView) this.findViewById(R.id.my_listview);
        adapter = new ExpandLVAdapter(list, this);
        mListView.setAdapter(adapter);
        // 不使用系统提供的展开和收起的图标
        mListView.setGroupIndicator(null);
        // 默认打开第一项
        mListView.expandGroup(0);

    }


    private void initData() {
        list = new ArrayList<GroupBean>();
        {
            List<ChildBean> list1 = new ArrayList<ChildBean>();
            ChildBean cb1 = new ChildBean("妈妈", "123");
            ChildBean cb2 = new ChildBean("爸爸", "456");
            ChildBean cb3 = new ChildBean("爷爷", "789");
            ChildBean cb4 = new ChildBean("奶奶", "000");
            list1.add(cb1);
            list1.add(cb2);
            list1.add(cb3);
            list1.add(cb4);
            GroupBean gb1 = new GroupBean("家", list1);
            list.add(gb1);
        }
        {
            List<ChildBean> list1 = new ArrayList<ChildBean>();
            ChildBean cb1 = new ChildBean("张三", "123");
            ChildBean cb2 = new ChildBean("李四", "456");
            ChildBean cb3 = new ChildBean("王五", "789");
            ChildBean cb4 = new ChildBean("赵六", "000");
            ChildBean cb5 = new ChildBean("风起", "1111");
            ChildBean cb6 = new ChildBean("马坝", "222");
            ChildBean cb7 = new ChildBean("迁就", "3333333");
            list1.add(cb1);
            list1.add(cb2);
            list1.add(cb3);
            list1.add(cb4);
            list1.add(cb5);
            list1.add(cb6);
            list1.add(cb7);
            GroupBean gb1 = new GroupBean("Friends", list1);
            list.add(gb1);
        }
        {
            List<ChildBean> list1 = new ArrayList<ChildBean>();
            ChildBean cb1 = new ChildBean("Tom", "123");
            ChildBean cb2 = new ChildBean("Jerry", "456");
            ChildBean cb4 = new ChildBean("Bush", "000");
            list1.add(cb1);
            list1.add(cb2);
            list1.add(cb4);
            GroupBean gb1 = new GroupBean("Foreigneers", list1);
            list.add(gb1);
        }
        {
            List<ChildBean> list1 = new ArrayList<ChildBean>();
            ChildBean cb1 = new ChildBean("石头", "123");
            ChildBean cb2 = new ChildBean("阿棍", "456");
            ChildBean cb3 = new ChildBean("大冰", "789");
            ChildBean cb4 = new ChildBean("眼镜", "000");
            ChildBean cb5 = new ChildBean("阿狄", "000");
            list1.add(cb1);
            list1.add(cb2);
            list1.add(cb3);
            list1.add(cb4);
            list1.add(cb5);
            GroupBean gb1 = new GroupBean("Brother", list1);
            list.add(gb1);
        }
    }

}

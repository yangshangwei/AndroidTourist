package com.turing.base.activity.listViewAct;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ListViewCRUD extends Activity implements View.OnClickListener {

    private Button addTextBtn, addImgBtn, delCurrentBtn, modRandomBtn, clearAllBtn;
    private ListView lv_crud;
    private TextView emptyLVHint;
    private List datalist;
    private ListViewCrudAdapter adapter;

    private int selectedIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_crud);
        setTitle("ListView CRUD");

        initView();
        initData();
        initEvents();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        addTextBtn = (Button) findViewById(R.id.id_btn_addText);
        addImgBtn = (Button) findViewById(R.id.id_btn_addImg);
        delCurrentBtn = (Button) findViewById(R.id.id_btn_del);
        modRandomBtn = (Button) findViewById(R.id.id_btn_mod);
        clearAllBtn = (Button) findViewById(R.id.id_btn_clear);
        lv_crud = (ListView) findViewById(R.id.id_lv_crud);
        emptyLVHint = (TextView) findViewById(R.id.id_tv_empty);
        // 当ListView为空时的显示 setEmptyView
        lv_crud.setEmptyView(emptyLVHint);
    }

    /**
     * 模拟数据
     */
    private void initData() {
        datalist = new ArrayList();
        // 实例化Adapter
        adapter = new ListViewCrudAdapter(this, datalist);
        // 设置adpter
        lv_crud.setAdapter(adapter);

    }

    /**
     * 按钮绑定监听事件
     */
    private void initEvents() {
        // 按钮设置点击事件
        addTextBtn.setOnClickListener(this);
        addImgBtn.setOnClickListener(this);
        delCurrentBtn.setOnClickListener(this);
        modRandomBtn.setOnClickListener(this);
        clearAllBtn.setOnClickListener(this);
        // 为ListView设置监听事件，否则选择不会触发事件
        lv_crud.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.d("onItemClick:" + position);
                ListViewCRUD.this.selectedIndex = position;
            }
        });
    }

    /**
     * 按钮单击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_addText:
                adapter.addText2lv(getRandomText());
                break;
            case R.id.id_btn_addImg:
                adapter.addImg2lv(getResIdByReflection());
                break;
            case R.id.id_btn_del:
                adapter.delSelected();
                break;
            case R.id.id_btn_mod:
                adapter.modify(selectedIndex,getRandomText());
                // 还原初始状态
                selectedIndex = -1 ;
                break;
            case R.id.id_btn_clear:
                adapter.removeAll();
                break;
            default:
                break;
        }
    }


    /**
     * 通过反射 根据资源的name,从R.drawable类中获取 对应的 resId
     */
    private int getResIdByReflection() {
        String imageName = "gur_project_" + new Random().nextInt(10);
        LogUtils.d("imageName:" + imageName);

        Field field = null;
        int resId = 0;
        try {
            field = R.drawable.class.getField(imageName);
            LogUtils.d("fieldName:" + field.getName().toString());

            resId = Integer.parseInt(field.get(null).toString());
            LogUtils.d("resId:" + resId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resId;
    }


    /**
     * 获取随机的字符串
     *
     * @return
     */
    private String getRandomText() {
        String[] texts = new String[]{"山东", "济南", "中国", "北京", "上海"};
        int randomNum = new Random().nextInt(texts.length);
        return texts[randomNum];
    }


}

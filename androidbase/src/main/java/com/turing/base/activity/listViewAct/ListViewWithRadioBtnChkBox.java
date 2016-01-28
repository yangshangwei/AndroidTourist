package com.turing.base.activity.listViewAct;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.turing.base.R;

public class ListViewWithRadioBtnChkBox extends Activity {

    private ListView lvCheckedTextView, lvRadioButton, lvCheckBox;

    private String[] datas = new String[]{"周杰伦", "周星驰", "尔康也姓周哈哈"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_with_radio_btn_chk_box);
        // 设置标题
        setTitle("单选和多项的ListView");

        initView();
    }

    private void initView() {
        lvCheckedTextView = (ListView) findViewById(R.id.lvCheckedTextView);
        lvRadioButton = (ListView) findViewById(R.id.lvRadioButton);
        lvCheckBox = (ListView) findViewById(R.id.lvCheckBox);


        // CheckedTextView
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, datas);
        lvCheckedTextView.setAdapter(adapter);
        // 设置成单选模式
        lvCheckedTextView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // RadioButton
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,datas);
        lvRadioButton.setAdapter(adapter1);
        // 设置单选模式
        lvRadioButton.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //CheckBox
        ArrayAdapter adapter2  = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,datas);
        lvCheckBox.setAdapter(adapter2);
        // 设置多选模式
        lvCheckBox.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    }
}

package com.turing.base.activity.editTextAct;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.turing.base.R;

public class AutoCompleteTextViewAct extends Activity {

    private AutoCompleteTextView actv ;
    private MultiAutoCompleteTextView mActv;

    private String[] array = new String[]{"周杰伦","周公举","周恩来","Google","Google Map","Google Android","Java"};;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);

        initView();

        initViewMulti();
    }



    private void initView() {
        actv = (AutoCompleteTextView)findViewById(R.id.id_actv);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,array);

        actv.setAdapter(adapter);
    }


    private void initViewMulti() {

        mActv = (MultiAutoCompleteTextView) findViewById(R.id.id_actv_multi);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,array);

        mActv.setAdapter(adapter);
        // 指定多个字符串的分隔符为逗号
        mActv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }



}

package com.turing.base.activity.textViewAct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.turing.base.R;

public class LineSpaceTextViewAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_space_text_view);

        TextView tv = (TextView)findViewById(R.id.id_tv_lineSpace4);

        tv.setLineSpacing(50,1.2f);
    }
}

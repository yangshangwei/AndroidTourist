package com.turing.base.activity.textViewAct;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.turing.base.R;

public class ScrollTextViewAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_text_view);

        TextView textView = (TextView) findViewById(R.id.id_tv_scroll);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

    }
}

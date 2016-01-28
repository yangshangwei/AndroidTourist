package com.turing.base.activity.buttonAct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.turing.base.R;

public class RadioButtonAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);


        final RadioButton radioButton = (RadioButton)findViewById(R.id.id_rb_1);

        radioButton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.id_rb_1:
                        boolean checked = radioButton.isChecked();
                        if(checked){
                            Toast.makeText(RadioButtonAct.this,"选中1",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
    }
}

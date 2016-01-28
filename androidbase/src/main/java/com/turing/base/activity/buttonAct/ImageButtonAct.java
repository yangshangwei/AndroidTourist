package com.turing.base.activity.buttonAct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.turing.base.R;

public class ImageButtonAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_button);

        ImageButton imageButton =(ImageButton) findViewById(R.id.id_imgBtn);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageButtonAct.this,"ImageButton is clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

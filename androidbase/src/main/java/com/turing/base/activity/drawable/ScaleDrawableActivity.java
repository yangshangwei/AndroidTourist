package com.turing.base.activity.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.turing.base.R;

public class ScaleDrawableActivity extends AppCompatActivity {

    private ImageView mIdShowScaleDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_drawable);

        mIdShowScaleDrawable = (ImageView) findViewById(R.id.id_show_scaleDrawable);

        Resources res = getResources();
        BitmapDrawable bd = (BitmapDrawable) res.getDrawable(R.drawable.zjl);
        Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(),
                (int) (bd.getIntrinsicHeight() * 0.5),
                (int) (bd.getIntrinsicWidth() * 0.5),
                false);


        mIdShowScaleDrawable.setImageBitmap(b);


    }
}

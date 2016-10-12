package com.turing.base.activity.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.turing.base.R;
import com.turing.base.utils.AlertUtil;

public class BitmapDrawableActivity extends AppCompatActivity {

    private ImageView mIdBitmapDrawableXml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_drawable);

        mIdBitmapDrawableXml = (ImageView) findViewById(R.id.id_bitmapDrawable_xml);

        // 通过代码的方式创建BitmapDrawable
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zjl);
        BitmapDrawable mBitmapDrawable = new BitmapDrawable(mBitmap);
        mBitmapDrawable.setTileModeXY(Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        mBitmapDrawable.setAntiAlias(true);
        mBitmapDrawable.setDither(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mIdBitmapDrawableXml.setBackground(mBitmapDrawable);
//            mIdBitmapDrawableXml.setImageDrawable(mBitmapDrawable);

        } else {
            AlertUtil.showDialogWithClose(this, "API<16 不支持setBackground属性设置");
        }
    }
}

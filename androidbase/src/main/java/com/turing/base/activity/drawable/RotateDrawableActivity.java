package com.turing.base.activity.drawable;

import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.turing.base.R;

public class RotateDrawableActivity extends AppCompatActivity {
    private ImageView mIdIvRotateDrawable;

    private RotateDrawable rotateDrawable;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x110) {
                // level 增加
                rotateDrawable.setLevel(rotateDrawable.getLevel() + 500);
                Message message = Message.obtain();
                message.what = 0x110;
                handler.sendMessageDelayed(message, 500);

                if (rotateDrawable.getLevel() > 10000) {
                    handler.removeMessages(0x110);
                    Toast.makeText(RotateDrawableActivity.this, "转完啦",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_drawable);

        mIdIvRotateDrawable = (ImageView) findViewById(R.id.id_iv_rotateDrawable);
        // 实例化 RotateDrawable
        rotateDrawable = (RotateDrawable) mIdIvRotateDrawable.getDrawable();

        handler.sendEmptyMessage(0x110);
    }
}

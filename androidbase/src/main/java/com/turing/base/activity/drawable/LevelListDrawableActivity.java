package com.turing.base.activity.drawable;

import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.turing.base.R;

public class LevelListDrawableActivity extends AppCompatActivity {

    private ImageView imageView;

    private LevelListDrawable levelListDrawable;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x110:
                    if (levelListDrawable.getLevel() > 10000) {
                        // 设置为0 ，循环展示
                        levelListDrawable.setLevel(0);
                        imageView.setImageLevel(0);
                        //移除消息
                        // handler.removeMessages(0x110);
                    }
                    imageView.setImageLevel(levelListDrawable.getLevel() + 2000);
                    Message message = Message.obtain();
                    message.what = 0x110;
                    handler.sendMessageDelayed(message, 500);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_list_drawable);

        imageView = (ImageView) findViewById(R.id.id_iv_levellist);

        levelListDrawable = (LevelListDrawable) imageView.getDrawable();
        imageView.setImageLevel(0);

        handler.sendEmptyMessage(0x110);
    }
}

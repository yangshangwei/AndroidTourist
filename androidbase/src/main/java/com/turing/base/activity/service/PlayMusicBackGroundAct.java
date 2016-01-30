package com.turing.base.activity.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.turing.base.R;

/**
 * 通过播放背景音乐的简单示例，演示startService的基本使用流程。
 * 通过操纵Activity的按钮控制MusicService播放或停止播放音乐。
 * 将一个名为sound.mp3的放到资源目录/res/raw文件夹下面，(As中没有的话，res右键new 选择android resource directory)
 * 这样我们在程序中就可以通过R.raw.sound引用该音乐文件，放入/res/raw文件夹中的资源文件会保持原来的面貌不会被编译成二进制。
 */
public class PlayMusicBackGroundAct extends AppCompatActivity  implements View.OnClickListener{

    private Button btn_playMusic ,btn_stopMusic ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music_back_ground);

        initView();
        initEvents();
    }


    private void initEvents() {
        btn_playMusic.setOnClickListener(this);
        btn_stopMusic.setOnClickListener(this);
    }

    private void initView() {
        btn_playMusic = (Button) findViewById(R.id.id_btn_playMusic);
        btn_stopMusic = (Button) findViewById(R.id.id_btn_stopMusic);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.id_btn_playMusic:
                startService(new Intent(PlayMusicBackGroundAct.this,PlayMusicService.class));
                this.finish();
                break;
            case R.id.id_btn_stopMusic:
                stopService(new Intent(PlayMusicBackGroundAct.this,PlayMusicService.class));
                break;
            default:
                break;
        }
    }
}

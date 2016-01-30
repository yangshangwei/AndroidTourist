package com.turing.base.activity.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

import java.io.IOException;

public class PlayMusicService extends Service {


    private MediaPlayer mediaPlayer ;
    // 是否正在播放,默认值false
    private boolean isReady = false ;

    public PlayMusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // 该Service中没有bindService方法，所以此处直接返回null
        return null ;
    }


    /**
     *  onCreate在Service的生命周期中只会调用一次
     *  初始化的工作可以放到这里来做
     */
    @Override
    public void onCreate() {
        LogUtils.d("PlayMusic Service onCreate");
        super.onCreate();
        // 初始化媒体播放器
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        // 非空判断
        if(mediaPlayer == null){
            return ;
        }

        mediaPlayer.stop();
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.release();
                stopSelf();
                return false;
            }
        });

        try {
            mediaPlayer.prepare();
            isReady = true ;
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(isReady){
            //将背景音乐设置为循环播放
            mediaPlayer.setLooping(true);
        }

    }

    /**
     *  每次调用Context的startService都会触发onStartCommand回调方法
     *  所以onStartCommand在Service的生命周期中可能会被调用多次
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.d("PlayMusic Service onStartCommand");
        // 如果 isReady 并且 多媒体没有在播放，则开始播放
        if(isReady && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
            Toast.makeText(this, "开始播放背景音乐", Toast.LENGTH_LONG).show();
        }

        return START_STICKY ;
    }


    /**
     * 当调用Context的stopService或Service内部执行stopSelf方法时就会触发onDestroy回调方法
     * 一般在此方法中释放资源
     */
    @Override
    public void onDestroy() {
        LogUtils.d("PlayMusic Service onDestroy");
        super.onDestroy();
        // 如果mediaPlayer不为空
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                // 如果正在播放，停止播放音乐
                mediaPlayer.stop();
            }
            // 释放多媒体资源
            mediaPlayer.release();
        }
    }
}

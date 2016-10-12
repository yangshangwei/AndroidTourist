package com.turing.nutritiousSerial.countDownTimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.turing.base.R;


/**
 * 倒计时演示
 * <p/>
 * Android中有个countDownTimer类，
 * 从名字上就可以看出来，它的功能是记录下载时间，
 * 将后台线程的创建和Handler队列封装成为了一个方便的调用.
 * <p/>
 * CountDownTimer由系统提供,果断抛弃了自己以前使用Handler更新UI的做法
 */
public class CountDownActivity extends AppCompatActivity {

    private MyCountDownTimer mc;
    private Button countBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        countBtn = (Button) findViewById(R.id.show);
        // 共计30S,1S调用一次onTick
        mc = new MyCountDownTimer(30000, 1000);
        mc.start();


    }

    public void oncancel(View view) {
        mc.cancel();
    }

    public void restart(View view) {
        mc.start();
    }


    /**
     * 自定义倒计时类
     */
    class MyCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    表示以毫秒为单位 倒计时的总数
         *                          <p/>
         *                          例如 millisInFuture=1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
         *                          <p/>
         *                          例如: countDownInterval =1000 ;
         *                          表示每1000毫秒调用一次onTick()
         */

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            countBtn.setText("倒计时(" + millisUntilFinished / 1000 + ")...");
        }

        @Override
        public void onFinish() {
            countBtn.setText("done");
        }
    }
}

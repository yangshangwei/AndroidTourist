package com.turing.nutritiousSerial.splashScreen;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.turing.base.MainActivity_;
import com.turing.base.R;

/**
 * 欢迎页面
 *
 * http://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/
 * http://blog.csdn.net/qwm8777411/article/details/46295317
 *
 */
public class SplashScreenActivity extends AppCompatActivity {


    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /**
         *  第一种:没有网络请求的情况
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity_.class));
                finish();
            }
        },SPLASH_TIME_OUT);


        /**
         * 第二种：从网络上获取json
         * json地址： http://api.androidhive.info/game/game_stats.json
         */
        new PrefetchData().execute();


    }


    private class PrefetchData extends AsyncTask<Void ,Void ,Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
        }

        @Override
        protected Void doInBackground(Void... params) {
             /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            // After completing http call
            // will close this activity and lauch main activity
            super.onPostExecute(aVoid);
        }
    }

}

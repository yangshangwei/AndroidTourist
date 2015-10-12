package com.turing.base.activity;

import android.app.Activity;
import android.os.Handler;
import android.webkit.WebView;

import com.turing.base.R;
import com.turing.base.http.HttpWebThread;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015/10/11  10:43.
 * @version 1.0
 */

@EActivity(R.layout.activity_load_web)
public class LoadWebActivity extends Activity {
    @ViewById(R.id.id_wv_web)
    WebView webView ;


    private static final String url = "http://www.baidu.com" ;
    private Handler handler = new Handler();

    @AfterViews
    public void loadWeb(){
        new HttpWebThread(webView,url,handler).start();
    }

}

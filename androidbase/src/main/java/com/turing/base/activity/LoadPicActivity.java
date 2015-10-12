package com.turing.base.activity;

import android.app.Activity;
import android.os.Handler;
import android.widget.ImageView;

import com.turing.base.R;
import com.turing.base.http.HttpPicThread;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015/10/11  21:37.
 * @version 1.0
 */

@EActivity(R.layout.activity_load_pic)
public class LoadPicActivity extends Activity {

    @ViewById(R.id.id_iv_pic)
    ImageView imageView ;
    // http://image.hnol.net/c/2014-02/11/22/201402112217383863-2381913.jpg
    private static final String url = "http://image.hnol.net/c/2014-02/11/22/201402112217383863-2381913.jpg" ;
    private Handler handler = new Handler();


    @AfterViews
    public void loadPic(){
        new HttpPicThread(imageView,url , handler).start();
    }

}

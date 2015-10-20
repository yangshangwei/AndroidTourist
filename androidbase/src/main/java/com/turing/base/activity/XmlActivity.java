package com.turing.base.activity;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;
import com.turing.base.http.xmlHttp.HttpThreadGetXml;
import com.turing.base.http.xmlHttp.HttpThreadShowXml;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-20  22:29.
 * @version 1.0
 * @desc
 */

@Fullscreen
@EActivity(R.layout.activity_xml)
public class XmlActivity extends Activity {

    @ViewById(R.id.id_btn_getXml)
    Button btn_getXml ;
    @ViewById(R.id.id_btn_showXml)
    Button btn_showXml ;
    @ViewById(R.id.id_btn_clearXml)
    Button btn_clearXml ;

    @ViewById(R.id.id_layout_body)
    RelativeLayout relativeLayout ;

    private String url = "http://192.168.1.105:8080/HttpService/skills.xml" ;
    private Handler handler = new Handler();

    @Click({R.id.id_btn_getXml,R.id.id_btn_showXml,R.id.id_btn_clearXml})
    public void handleClick(View view){
        switch (view.getId()){
            case R.id.id_btn_getXml:
                LogUtils.d("getXml");
                new HttpThreadGetXml(this,url ,relativeLayout, handler).start();
                break;
            case R.id.id_btn_showXml:
                new HttpThreadShowXml().start();
                break;
            case R.id.id_btn_clearXml:
                this.relativeLayout.removeAllViews();
                break;
            default:
                break ;
        }

    }


}

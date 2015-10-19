package com.turing.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015/10/10  22:17.
 * @version 1.0
 * @desc
 */

@Fullscreen
@EActivity(R.layout.activity_http)
public class HttpActivity extends Activity {

    @ViewById(R.id.id_btn_loadWeb)
    Button btn_loadWeb;

    @ViewById(R.id.id_btn_loadPic)
    Button btn_loadPic;

    @ViewById(R.id.id_btn_httpUrlConnectionOper)
    Button btn_HttpUrlConnection_GetPost;

    @ViewById(R.id.id_btn_HttpClientOper)
    Button btn_HttpUrlClient_GetPost;

    @ViewById(R.id.id_btn_JsonModule)
    Button btn_json;

    @ViewById(R.id.id_btn_XmlModule)
    Button btn_xml;

    @ViewById(R.id.id_btn_multiThread)
    Button btn_multi;

    @ViewById(R.id.id_btn_httpUpLoad)
    Button btn_httpUpload;

    @ViewById(R.id.id_btn_httpClientUpload)
    Button btn_hcUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Click({R.id.id_btn_loadWeb, R.id.id_btn_loadPic,
            R.id.id_btn_httpUrlConnectionOper, R.id.id_btn_HttpClientOper,
            R.id.id_btn_JsonModule, R.id.id_btn_XmlModule,
            R.id.id_btn_multiThread, R.id.id_btn_httpUpLoad, R.id.id_btn_httpClientUpload})
    public void loadByURL(View clickedView) {
        switch (clickedView.getId()) {
            case R.id.id_btn_loadWeb:
                LogUtils.d("loadWeb Button is clicked");
                startActivity(new Intent(this, LoadWebActivity_.class));
                break;
            case R.id.id_btn_loadPic:
                LogUtils.d("loadPic Button is clicked");
                startActivity(new Intent(this, LoadPicActivity_.class));
                break;
            case R.id.id_btn_httpUrlConnectionOper:
                // 向自建的服务端通过Get的方式请求 其实就是一个servlet+tomcat搭建的超级入门的login，提交显示，仅仅为了演示demo 仅此而已
                // 不能在UI线程做访问网络的耗时操作，切记！
                // 跳转到登陆Activity，输入姓名和年龄，点击提交时，开启子线程访问网络
                startActivity(new Intent(this, LoginActivity_.class));
                break;
            case R.id.id_btn_HttpClientOper:
                startActivity(new Intent(this, LoginActivity_.class));
                break;
            case R.id.id_btn_JsonModule:
                startActivity(new Intent(this,JsonActivity_.class));
                break;
            case R.id.id_btn_XmlModule:
                break;
            case R.id.id_btn_multiThread:
                break;
            case R.id.id_btn_httpUpLoad:
                break;
            case R.id.id_btn_httpClientUpload:
                break;
            default:
                break;
        }
    }


}

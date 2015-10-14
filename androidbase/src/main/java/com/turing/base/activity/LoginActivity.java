package com.turing.base.activity;

import android.app.Activity;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;
import com.turing.base.http.HttpClientThread_Get;
import com.turing.base.http.HttpClientThread_Post;
import com.turing.base.http.HttpThreadGetMethod;
import com.turing.base.http.HttpThreadPostMethod;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015/10/12  23:18.
 * @version 1.0
 * @desc
 */
@Fullscreen
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    @ViewById(R.id.id_et_name)
    EditText et_name;
    @ViewById(R.id.id_et_age)
    EditText et_age;
    @ViewById(R.id.id_et_response)
    EditText et_response;

    @ViewById(R.id.id_btn_get_submit)
    Button btn_GetSubmit;

    @ViewById(R.id.id_btn_post_submit)
    Button btn_PostSubmit;


    @ViewById(R.id.id_btn_httpClient_get_submit)
    Button btn_hc_get;

    @ViewById(R.id.id_btn_httpClient_post_submit)
    Button Btn_hc_post;


    private String url = "http://192.168.1.106:8080/HttpService/MyServlet";
    private Handler handler = new Handler();


    @Click(R.id.id_btn_get_submit)
    public void getSubmitData() {
        // 开启子线程 进行网络请求的耗时操作
        new HttpThreadGetMethod(
                et_name.getText().toString(),
                et_age.getText().toString(),
                url, handler, et_response).start();
    }


    @Click(R.id.id_btn_post_submit)
    public void postSubmitData() {
        // 开启子线程 进行网络请求的耗时操作
        new HttpThreadPostMethod(
                et_name.getText().toString(),
                et_age.getText().toString(),
                url, handler, et_response).start();
    }


    @Click(R.id.id_btn_httpClient_get_submit)
    public void hcGetSubmit() {
        try {
            url = url + "?name=" + URLEncoder.encode(et_name.getText().toString(), "utf-8") + "&age=" + et_age.getText().toString();
            LogUtils.d("url:" + url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        new HttpClientThread_Get(url, handler, et_response).start();
        url = "http://192.168.1.106:8080/HttpService/MyServlet" ;
        LogUtils.d("调用结束，url还原为初始的url");
    }

    @Click(R.id.id_btn_httpClient_post_submit)
    public void hcPostSubmit() {
        new HttpClientThread_Post(url, et_name.getText().toString(),
                et_age.getText().toString(),handler, et_response).start();

    }


}

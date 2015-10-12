package com.turing.base.activity;

import android.app.Activity;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import com.turing.base.R;
import com.turing.base.http.HttpThreadDiffMethod;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

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
    EditText et_response ;

    @ViewById(R.id.id_btn_submit)
    Button btn_submit ;



    private String url = "http://192.168.1.106:8080/HttpService/MyServlet";
    private Handler handler = new Handler();


    @Click(R.id.id_btn_submit)
    public void submitData() {
        // 开启子线程 进行网络请求的耗时操作
        new HttpThreadDiffMethod(
                et_name.getText().toString(),
                et_age.getText().toString(),
                url ,handler, et_response).start();
    }
}

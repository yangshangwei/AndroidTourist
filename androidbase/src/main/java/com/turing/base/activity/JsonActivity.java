package com.turing.base.activity;

import android.app.Activity;
import android.os.Handler;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.turing.base.R;
import com.turing.base.http.jsonHttp.HttpGetJson;
import com.turing.base.http.jsonHttp.HttpShowJson;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

@Fullscreen
@EActivity(R.layout.activity_json)
public class JsonActivity extends Activity {

    private static final String url = "http://192.168.1.105:8080/HttpService/MyJsonServlet";
    // 公司本机IP
   // private static final String url = "http://172.20.212.198:8080/HttpService/MyJsonServlet";
    private Handler handler = new Handler() ;


    @ViewById(R.id.id_btn_getJson)
    Button btn_getJson ;
    @ViewById(R.id.id_btn_showJson)
    Button btn_showJson ;
    @ViewById(R.id.id_btn_clearJson)
    Button btn_clearJson ;

    @ViewById(R.id.id_rlLayout_content)
    RelativeLayout relativeLayout ;

    /**
     * 请求Json原生数据，网络请求的耗时操作需开启子线程后台查询
     */
    @Click(R.id.id_btn_getJson)
    public void getJson(){
        // 请求的url地址, 需要更新的组件 ,和更新组件的handler
        new HttpGetJson(this ,url ,relativeLayout , handler).start();
    }

    @Click(R.id.id_btn_showJson)
    public void showJson(){
        new HttpShowJson(this ,url ,relativeLayout , handler).start();
    }

    /**
     * 清除RelativeLayout的显示内容
     */
    @Click(R.id.id_btn_clearJson)
    public void clearJson(){
        this.relativeLayout.removeAllViews();
    }
}

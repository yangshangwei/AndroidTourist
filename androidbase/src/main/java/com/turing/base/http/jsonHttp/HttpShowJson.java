package com.turing.base.http.jsonHttp;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;
import com.turing.base.adapter.ShowJsonAdapter;
import com.turing.base.beans.Person;
import com.turing.base.beans.School;
import com.turing.base.beans.ShowJsonBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-19  21:11.
 * @version 1.0
 * @desc
 */
public class HttpShowJson extends Thread {

    private Context context;
    private String url;
    private RelativeLayout relativeLayout;
    private Handler handler;
    private LayoutInflater inflater;


    private ListView listView;
    private List<ShowJsonBean> data = new ArrayList<ShowJsonBean>();

    /**
     * 构造函数
     *
     * @param context
     * @param url
     * @param relativeLayout
     * @param handler
     */
    public HttpShowJson(Context context, String url, RelativeLayout relativeLayout, Handler handler) {
        this.context = context;
        this.url = url;
        this.relativeLayout = relativeLayout;
        this.handler = handler;
        // 实例化LayoutInflater，以方便动态加载布局为组件
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public void run() {
        doShowJsonInUniform();
    }

    private void doShowJsonInUniform() {


        try {
            // 实例化URL
            URL httpUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            // 设置connection的属性
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5 * 1000);
            connection.setDoInput(true);
            // 获取输入流
            InputStream ins = connection.getInputStream();
            // 读取输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            final StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            // 将获取的JSON格式的数据 转化为JavaBean
            final List<Person> personList = json2bean(sb.toString());
            LogUtils.d("size:" +personList.size());
            // 遍历
            for(Iterator it2 = personList.iterator();it2.hasNext();){
                LogUtils.d("遍历list中的元素:" + it2.next());
            }
            // 通过Handler更新主线程的UI
            handler.post(new Runnable() {
                @Override
                public void run() {

                    // 将布局文件转换为视图
                    View view = HttpShowJson.this.inflater.inflate(R.layout.activity_show_original_json, null);

                    // 查找ListView
                    listView = (ListView) view.findViewById(R.id.id_lv_showJson);
                    // 实例化适配器
                    ShowJsonAdapter jsonAdapter = new ShowJsonAdapter(context,personList);
                    // 设置适配器
                    listView.setAdapter(jsonAdapter);

                    //清除组件后,增加视图
                    HttpShowJson.this.relativeLayout.removeAllViews();
                    HttpShowJson.this.relativeLayout.addView(view);
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 将服务端获取的JSON数据 转换为Bean实体数据 ,返回personList供后续调用
     *
     * @param jsonData
     */
    private List<Person>  json2bean(String jsonData) {

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String result = jsonObject.getString("result");

            // result = 1成功
            if ("" != result && "1".equals(result)) {
                JSONArray personJsonArray = jsonObject.getJSONArray("personData");

                List<Person> personList = new ArrayList<Person>();
                // 遍历personJsonArray
                for (int i = 0; i < personJsonArray.length(); i++) {

                    JSONObject personJson = personJsonArray.getJSONObject(i);

                    String name = personJson.getString("name");
                    String age = personJson.getString("age");
                    String url = personJson.getString("url");
                    LogUtils.d("name:" + name + ",age:" + age + ",url:" + url);

                    Person person = new Person();
                    person.setName(name);
                    person.setAge(age);
                    person.setUrl(url);

                    List<School> schoolList = new ArrayList<School>();

                    person.setSchoolList(schoolList);

                    personList.add(person);
                    // 获取学校 数字
                    JSONArray schoolJsonArray = personJson.getJSONArray("schoolInfo");
                    for (int j = 0; j < schoolJsonArray.length(); j++) {
                        JSONObject schoolJsonObject = schoolJsonArray.getJSONObject(j);
                        String schoolName = schoolJsonObject.getString("name");
                        LogUtils.d("schoolName" + schoolName);

                        School school = new School();
                        school.setName(schoolName);
                        schoolList.add(school);
                    }
                }
                return personList ;
            }else{
                // TODO
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.d("发生了异常,返回null");
        return null ;
    }
}
/**
 *
 * {
 "result": "1",
 "personData": [
     {
     "name": "Jack",
     "url": "http://f8.topit.me/8/d3/36/110211575441036d38l.jpg",
     "age": "20",
     "schoolInfo": [
         {
         "name": "清华本科"
         },
         {
         "name": "北大研究生"
         }
        ]
     },
     {
     "name": "Tom",
     "url": "http://pic.pp3.cn/uploads//201407/1402453425452.jpg",
     "age": "10",
     "schoolInfo": [
         {
         "name": "人大本科"
         },
         {
         "name": "浙大研究生"
         }
        ]
     }
     ]
 }
 **/
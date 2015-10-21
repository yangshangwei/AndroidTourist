package com.turing.base.http.xmlHttp;

import android.content.Context;
import android.os.Handler;
import android.util.Xml;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.beans.ClassBean;
import com.turing.base.beans.StudentBean;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-10-20  23:34.
 * @version 1.0
 */


public class HttpThreadShowXml extends Thread {


    private String url;
    private RelativeLayout relativeLayout;
    private Handler handler;
    private LayoutInflater inflater;
    private Context context;

    public HttpThreadShowXml(Context context, String url, RelativeLayout relativeLayout, Handler handler) {
        this.context = context;
        this.url = url;
        this.relativeLayout = relativeLayout;
        this.handler = handler;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public void run() {
        doShowXml();
    }

    private void doShowXml() {
        try {
            // 实例化
            URL httpUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            // 设置连接属性
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5 * 1000);
            connection.setDoInput(true);
            // 获取输入流
            InputStream ins = connection.getInputStream();
            // 获取到输入流以后，不要对输入流做操作，否则改变了，
            // 再作为入参传递给其他方法时，这个时候 就会出现走不到START_TAG里的很奇怪的问题
            // 切记啊
            /** 读取输入流  不要读啊 不要改变流
//            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
//            final StringBuffer sb = new StringBuffer();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//            LogUtils.d("服务端返回的xml报文:" + sb.toString());
**/
            List<ClassBean>  classBeanList = xml2Bean(ins);
            LogUtils.d("个数：" + classBeanList.size());

            // 通过Handler更新UI组件
            handler.post(new Runnable() {
                @Override
                public void run() {

                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private List<ClassBean> xml2Bean(InputStream ins) {
        LogUtils.d("ins:" +ins);
        List<ClassBean> list = null;
        ClassBean bean = null;
        List<StudentBean> sList = null;
        StudentBean be = null;
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(ins, "utf-8");
            int event = parser.getEventType();
            LogUtils.d("event 原始：" + event);
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        LogUtils.d("2 START_DOCUMENT" + XmlPullParser.START_DOCUMENT);
                        list = new ArrayList<ClassBean>();
                        break;
                    case XmlPullParser.START_TAG:
                        LogUtils.d("2 START_TAG" + XmlPullParser.START_TAG);
                        if ("class".equals(parser.getName())) {
                            bean = new ClassBean();
                            bean.setId(parser.getAttributeValue(0));
                            bean.setName(parser.getAttributeValue(1));
                            sList = new ArrayList<StudentBean>();
                        } else if ("student".equals(parser.getName())) {
                            be = new StudentBean();
                            be.setId(parser.getAttributeValue(0));
                            be.setName(parser.nextText());
                            sList.add(be);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        LogUtils.d("2 END_TAG" + XmlPullParser.END_TAG);
                        if ("class".equals(parser.getName())) {
                            bean.setList(sList);
                            list.add(bean);
                            bean = null;
                        }
                        break;
                }
                event = parser.next();
                LogUtils.d("event" + event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}

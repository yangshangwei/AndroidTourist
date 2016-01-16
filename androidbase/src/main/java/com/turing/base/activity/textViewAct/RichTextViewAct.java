package com.turing.base.activity.textViewAct;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.turing.base.R;

public class RichTextViewAct extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich_text_view);

        init();


    }

    private void init() {

        TextView textView1 = (TextView) findViewById(R.id.id_tv_richText1);
        TextView textView2 = (TextView) findViewById(R.id.id_tv_richText2);


        String html = "<font color='red'>I Love Android</font><br>";
        html += "<font color='#0000FF'><big><i>I Love Android</i></big></font><p>";
        html += "<font color='@" + android.R.color.holo_green_dark + "'><tt><b><big><u>I Love Android</u></big></b></tt></font><p>";
        html += "<big><a href='http://www.baidu.com'>百度</a></big>";

        Log.e("html内容：", html);

        // 将预定义标签的字符串转换成CharSequence对象
        CharSequence charSequence = Html.fromHtml(html);
        Log.e("charSequence：", charSequence.toString());
        // 为第一个TextView设置文本
        textView1.setText(charSequence);
        // 这行代码只管重要，没有，无法单击链接调用浏览器显示网页
        textView1.setMovementMethod(LinkMovementMethod.getInstance());


        // 第二个TextVie要显示的文本
        StringBuffer sb = new StringBuffer();
        sb.append("我的URL： http://www.baidu.com\n");
        sb.append("我的Email: tttt@gmail.com\n");
        sb.append("我的电话：12345678909");


        textView2.setText(sb.toString());
        // 因上述文本没有<a>标签，经验证可以不用setMovementMethod(LinkMovementMethod.getInstance())
        //textView2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}

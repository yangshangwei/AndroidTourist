package com.turing.base.activity.textViewAct;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Window;
import android.widget.TextView;

import com.turing.base.R;

import java.lang.reflect.Field;

public class MultTextPicAct extends Activity {

    private TextView tv_textAndPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mult_text_pic);

        initView();

    }

    /**
     * 初始化视图
     */
    private void initView() {
        // 组件初始化
        tv_textAndPic = (TextView) findViewById(R.id.id_tv_textAndPic);
        tv_textAndPic.setTextSize(20f);
        tv_textAndPic.setBackgroundColor(Color.WHITE);
        // 定义图文混编
        String html = "图片1<img src='flag_mark_blue'/>图片2<img src='flag_mark_gray'/><p>";
        html += "图片3<img src='tag_blue'/><br>";
        html += "图片4<a href='http://www.baidu.com'><img src='tag_orange'/></a><p>";
        html += "图片5<img src='tag_red'/>";
        //使用Html.fromHtml()转换包含Html标签的文本，需要指定第二个参数
        CharSequence charSequence = Html.fromHtml(html, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                // 装载图像资源
                Drawable drawable = getResources().getDrawable(getResourceId(source));
                // 第三个图片按50%等比压缩 ,其余按原大小显示
                if ("tag_blue".equals(source)) {
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2, drawable.getIntrinsicHeight() / 2);
                } else {
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                }
                return drawable;
            }
        }, null);

        tv_textAndPic.setText(charSequence);
        // 有<a>超链接标签，需设置LinkMovementMethod,否则点击无相应
        tv_textAndPic.setMovementMethod(LinkMovementMethod.getInstance());
    }


    /**
     * 利用反射从R.drawable类中通过图像资源名称获取对应的图像资源ID
     *
     * @param name 表示res/drawable中的图像文件名（不含扩展名）
     * @return 图像资源ID
     */
    private int getResourceId(String name) {

        try {
            // 根据资源的ID变量名（也就是图像资源的文件名）,获得Field字段
            Field field = R.drawable.class.getField(name);
            // 取得并返回资源ID（静态变量）的值
            return Integer.valueOf(field.get(null).toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 0;
    }
}

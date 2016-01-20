package com.turing.base.activity.editTextAct;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

import java.lang.reflect.Field;
import java.util.Random;

public class InputTextAndPicAct extends Activity {

    private EditText et_input;
    private Button btn_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_text_and_pic);

        initView();
    }

    private void initView() {

        et_input = (EditText) findViewById(R.id.id_et_inputMess);
        btn_insert = (Button) findViewById(R.id.id_btn_insertPic);

    }

    /**
     * 在xml中配置了android:onClick属性，监听点击事件
     *
     * @param view
     */
    public void insertPic(View view) {
        // 随机产生1到10的整数
        int random = 1 + new Random().nextInt(10);
        LogUtils.d("随机产生的整数：" + random);

        try {
            // 根据随机产生的整数从R.drawable类中获取相应的资源ID的Field对象
            Field field = R.drawable.class.getField("gur_project_" + random);
            // 获取资源id，也就是静态变量的值
            int resourceId = Integer.parseInt(field.get(null).toString());
            // 根据资源ID获取图像的Bitmap对象
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
            // 根据Bitmap创建ImageSpan对象
            ImageSpan imageSpan = new ImageSpan(this,bitmap);
            // 创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
            String text = "gur_project_";
            SpannableString spannableString = new SpannableString(text);
            // 用ImageSpan替换gur-project-
            spannableString.setSpan(imageSpan,0,text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            // 将随机获取的图像追加到EditText控件后
            et_input.append(spannableString);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

package com.turing.base.activity.dataStore.sharedPreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.turing.base.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 虽然可以采用编码的方式通过SharedPreferences保存任何类型的数据，
 * 但是不建议使用SharedPreferences保存大量的数据
 */
public class SpComplexDataAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_complex_data);
    }


    public void onClick_Save_Image(View view) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("base64", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // 读取和压缩R.drawable.item10,并将其压缩结果保存在ByteArrayOutputStream中
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapFactory.decodeResource(getResources(), R.drawable.item10).compress(Bitmap.CompressFormat.JPEG, 50, baos);
            // 对压缩后的字节进行Base6编码
            String imageBase64 = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            // 保存转换后的Base64格式字符串
            editor.putString("image", imageBase64);
            editor.commit();
            // 关闭输出流
            baos.close();

            Toast.makeText(this, "onClick_Save_Image 成功", Toast.LENGTH_LONG).show();

        } catch (Exception e) {

        }

    }

    public void onClick_Read_Image(View view) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("base64", Activity.MODE_PRIVATE);
            // 读取Base64格式的图片数据
            String imageBase64 = sharedPreferences.getString("image", "");
            // 对Base64格式的字符串进行解码，还原成字节数组
            byte[] imageBytes = Base64.decode(imageBase64.getBytes(), Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            // 在控件上显示图像
            ImageView imageView = (ImageView) findViewById(R.id.image);
            imageView.setImageDrawable(Drawable.createFromStream(bais, "image"));
            // 关闭输入流
            bais.close();

            Toast.makeText(this, "onClick_Read_Image 成功", Toast.LENGTH_LONG).show();


        } catch (Exception e) {

        }
    }

    public void onClick_Save_Serializable_Object(View view) {
        try {
            Product product = new Product();

            product.name = "如来神掌";
            product.price = 1500;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将Product对象保存到ObjectOutputStream中
            oos.writeObject(product);

            SharedPreferences sharedPreferences = getSharedPreferences("base64", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            // 将Product对象转换成byte数组，并将其进行Base64编码
            String productBase64 = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            // 将编码后的字符串保存到base64.xml中
            editor.putString("product", productBase64);
            editor.commit();
            // 输出流关闭
            oos.close();

            Toast.makeText(this, "onClick_Save_Serializable_Object 成功", Toast.LENGTH_LONG).show();
        } catch (Exception e) {

        }

    }

    public void onClick_Read_Serializable_Object(View view) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("base64", Activity.MODE_PRIVATE);
            // 读取Product对象的Base64格式的字符串
            String base64Product = sharedPreferences.getString("product", "");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //将base64格式字符串还原成byte数组
            byte[] productBytes = Base64.decode(base64Product.getBytes(), Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(productBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            // 将byte数组转换成Product对象
            Product product = (Product) ois.readObject();
            Toast.makeText(this,
                    "name:" + product.name + "\nprice：" + product.price,
                    Toast.LENGTH_LONG).show();
            // 关闭输入流
            ois.close();

        } catch (Exception e) {

        }
    }
}

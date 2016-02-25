package com.turing.base.activity.dataStore.fileStore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class SDCardWriteReadAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard_write_read);
    }

    public void onClick_SaveImageToSDCard(View view) {
        try {
            // 判断是否有SD卡
            LogUtils.d(ExistSDCard());
            LogUtils.d("true:表示SD卡是虚拟的,false则表示是虚拟的||------" + Environment.isExternalStorageRemovable());
            if (ExistSDCard()) {
                // 创建用于将图片保存到SD卡上的FileOutputStream
                FileOutputStream fos = new FileOutputStream(
                        android.os.Environment.getExternalStorageDirectory()
                                + "/image.jpg");
                // 打开assets目录中的image.jpg,并返回inputstream
                // 在Android Studio中添加assets目录，目录的位置在src\main目录下
                InputStream is = getResources().getAssets().open("image.jpg");
                // 定义一个数据，用于保存每次向SD卡写入的数据,最多8K
                byte[] buffer = new byte[8192];
                int count = 0;
                // 循环写入数据
                while ((count = is.read(buffer)) >= 0) {
                    fos.write(buffer, 0, count);
                }
                // 关闭流
                fos.close();
                is.close();
                Toast.makeText(this, "已成功将图像文件写到SD卡上.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "无SD卡.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "exception:" +e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void onClick_ReadImageFromSDCard(View view) {
        // 判断是否有SD卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 指定SD卡中的文件名称
            String filename = android.os.Environment.getExternalStorageDirectory()
                    + "/image.jpg";

            if (!new File(filename).exists()) {
                Toast.makeText(this, "还没有往SD卡写入图像文件", Toast.LENGTH_LONG).show();
                return;
            }
            ImageView imageView = (ImageView) findViewById(R.id.imageview);
            try {
                FileInputStream fis = new FileInputStream(filename);
                // 从文件的输入流装载Bitmap对象
                Bitmap bitmap = BitmapFactory.decodeStream(fis);
                // 设置图像
                imageView.setImageBitmap(bitmap);

                fis.close();
            } catch (Exception e) {
                Toast.makeText(this, "exception:" +e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "文件不出在", Toast.LENGTH_LONG).show();
        }
    }

    private boolean ExistSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

}

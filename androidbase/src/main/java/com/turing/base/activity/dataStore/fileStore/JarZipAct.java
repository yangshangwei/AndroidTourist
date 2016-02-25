package com.turing.base.activity.dataStore.fileStore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.turing.base.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class JarZipAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jar_zip);
    }

    public void onClick_Jar_Compress(View view) {
        try {
            // 使用FileOutputStream对象指定一个要输出的压缩文件(file.jar)
            FileOutputStream fos = new FileOutputStream(
                    android.os.Environment.getExternalStorageDirectory()
                            + "/file.jar");
            // 第一步 创建JarOutputStream对象
            JarOutputStream jos = new JarOutputStream(fos);
            // 第二步 创建一个JarEntry对象，并指定待压缩文件在压缩包中的文件名
            JarEntry jarEntry = new JarEntry("strings.xml");
            jos.putNextEntry(jarEntry);

            InputStream is = getResources().getAssets().open("strings.xml");
            byte[] buffer = new byte[8192];
            int count = 0;
            // 第四步 写入数据
            while ((count = is.read(buffer)) >= 0) {
                jos.write(buffer, 0, count);
            }
            // 第五步 关闭当前的JarEntry等对象
            is.close();
            jos.closeEntry();
            jos.close();
            Toast.makeText(this, "成功将strings.xml文件以jar格式压缩.", Toast.LENGTH_LONG)
                    .show();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void onClick_Jar_Uncompress(View view) {
        try {
            // 定义要解压的文件
            String filename = android.os.Environment
                    .getExternalStorageDirectory() + "/file.jar";
            if (!new File(filename).exists()) {
                Toast.makeText(this, "压缩文件不存在.", Toast.LENGTH_LONG).show();
                return;
            }
            //  使用FileInputStream对象指定要解压的对象
            FileInputStream fis = new FileInputStream(filename);
            // 1 创建JarInputStream对象来读取压缩文件（file.jar）
            JarInputStream jis = new JarInputStream(fis);
            // 2 调用getNextJarEntry方法打开压缩包中的第一个文件 ，如果有多个，多次调用该方法
            JarEntry jarEntry = jis.getNextJarEntry();
            // 3 输出已解压的文件
            FileOutputStream fos = new FileOutputStream(
                    android.os.Environment.getExternalStorageDirectory()
                            + "/" + jarEntry.getName());

            byte[] buffer = new byte[8192];
            int count = 0;
            // 4 输出已解压的字节流
            while ((count = jis.read(buffer)) >= 0) {
                fos.write(buffer, 0, count);
            }
            // 5 关闭
            jis.closeEntry();
            jis.close();
            fos.close();

            Toast.makeText(this, "成功解压jar格式的文件.", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void onClick_Zip_Compress(View view) {
        try {
            // 指定了2个待压缩的w文件，都在assets目录中
            String[] filenames = new String[]
                    {"main.xml", "strings.xml"};
            FileOutputStream fos = new FileOutputStream(
                    android.os.Environment.getExternalStorageDirectory()
                            + "/file.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            int i = 1;
            //枚举filenames中的所有待压缩文件
            while (i <= filenames.length) {
                // 从filenames数组中取出当前待压缩的温佳明，作为压缩后的文件名,以保持要说前后文件名称一致
                ZipEntry zipEntry = new ZipEntry(filenames[i - 1]);
                // 打开当前的ZipEntry对象
                zos.putNextEntry(zipEntry);


                InputStream is = getResources().getAssets().open(
                        filenames[i - 1]);
                byte[] buffer = new byte[8192];
                int count = 0;
                // 写入数据
                while ((count = is.read(buffer)) >= 0) {
                    zos.write(buffer, 0, count);
                }
                zos.flush();
                // 关闭当前的ZipEntry对象
                zos.closeEntry();
                is.close();
                i++;

            }
            zos.finish();
            zos.close();
            Toast.makeText(this, "成功将main.xml、strings.xml文件以zip格式压缩.",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void onClick_Zip_Uncompress(View view) {
        try {

            // 指定待解压的文件
           String filename = android.os.Environment
                    .getExternalStorageDirectory() + "/file.zip";
            if (!new File(filename).exists()) {
                Toast.makeText(this, "压缩文件不存在.", Toast.LENGTH_LONG).show();
                return;
            }
            FileInputStream fis = new FileInputStream(filename);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry zipEntry = null;
            // 通过不断调用getNextEntry方法来解压file.zip中所有的文件
            while ((zipEntry = zis.getNextEntry()) != null) {
                FileOutputStream fos = new FileOutputStream(
                        android.os.Environment.getExternalStorageDirectory()
                                + "/" + zipEntry.getName());

                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = zis.read(buffer)) >= 0) {
                    fos.write(buffer, 0, count);
                }
                zis.closeEntry();
                fos.close();
            }
            zis.close();

            Toast.makeText(this, "成功解压jar格式的文件.", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

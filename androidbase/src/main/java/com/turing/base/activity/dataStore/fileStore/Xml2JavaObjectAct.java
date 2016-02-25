package com.turing.base.activity.dataStore.fileStore;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;

import com.turing.base.R;

import java.io.InputStream;
import java.util.List;

public class Xml2JavaObjectAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml2_java_object);
    }


    public void onClick_XMLToObject(View view) {
        try {
            //  打开资源文件
            InputStream is = getResources().openRawResource(R.raw.products);
            XML2Product xml2Product = new XML2Product();
            // 开始分析priducts.xml文件
            android.util.Xml.parse(is, Xml.Encoding.UTF_8, xml2Product);
            // 输出转换后的java对象
            List<Product> products = xml2Product.getProducts();
            String msg = "共" + products.size() + "个产品\n";
            for (Product product : products) {
                msg += "id:" + product.getId() + "  产品名：" + product.getName()
                        + "  价格：" + product.getPrice() + "\n";
            }
            // 弹出对话框
            new AlertDialog.Builder(this).setTitle("产品信息").setMessage(msg)
                    .setPositiveButton("关闭", null).show();
        } catch (Exception e) {

        }

    }
}

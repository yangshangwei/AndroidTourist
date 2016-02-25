package com.turing.base.activity.dataStore.fileStore;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XML2Product extends DefaultHandler {
    private List<Product> products;
    private Product product;


    private StringBuffer buffer = new StringBuffer();

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        buffer.append(ch, start, length);
        super.characters(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException {
        // 开始分析xml文件,创建List对象用于保存分析完的Product对象
        products = new ArrayList<Product>();

    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        if (localName.equals("product")) {
            // 如果分析的是<product>标签，则创建一个Product对象
            product = new Product();
        }
        super.startElement(uri, localName, qName, attributes);
    }


    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (localName.equals("product")) {
            // 处理完 <product>标签后 将product对象添加到products中
            products.add(product);
        } else if (localName.equals("id")) {
            // 设置id属性的值
            product.setId(Integer.parseInt(buffer.toString().trim()));
            // 将标签内容的缓存区清空
            buffer.setLength(0);
        } else if (localName.equals("name")) {
            product.setName(buffer.toString().trim());
            buffer.setLength(0);
        } else if (localName.equals("price")) {
            product.setPrice(Float.parseFloat(buffer.toString().trim()));
            buffer.setLength(0);
        }
        super.endElement(uri, localName, qName);
    }
}

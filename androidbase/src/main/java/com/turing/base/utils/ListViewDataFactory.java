package com.turing.base.utils;

import com.turing.base.beans.MainMenuListItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-12-20  19:19.
 * @version 1.0
 * @desc
 */
public class ListViewDataFactory {


    // 数据来源
    public static List<MainMenuListItemBean> dataList;

    /**
     * 模拟数据
     */
    public static List<? extends Object> simulateData(String[] datas) {

        dataList = new ArrayList<>();
        /**
         * 遍历数组
         */
        for(int i= 0 ; i<datas.length ;i++){
            MainMenuListItemBean bean = new MainMenuListItemBean();
            bean.setContent(datas[i]);
            dataList.add(bean);

        }
        return dataList ;
    }
}

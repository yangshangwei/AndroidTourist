package com.turing.base.activity.dataStore.sharedPreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

import com.turing.base.R;

/**
 * 3.0版本以后就需要使用PreferenceFragment，这里用过时的也没关系
 */
public class PreferenceActivityDemo extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 改变保存数据的xml使用的名称，默认是<package_name>_preferences.xml
        getPreferenceManager().setSharedPreferencesName("setting");
        // 从xml文件中加载布局
        addPreferencesFromResource(R.xml.preference_setting);
        // 获取"姓名"列表项对应的Preference对象
        Preference individualNamePreference = findPreference("individual_name");
        // 获得指向setting.xml文件的SharedPreferences对象
        SharedPreferences sharedPreferences = individualNamePreference.getSharedPreferences();
        // 设置列表项的Summary
        individualNamePreference.setSummary(sharedPreferences.getString("individual_name", ""));
        //设置"姓名"列表项是否可用
        if (sharedPreferences.getBoolean("yesno_save_individual_info", false))
            individualNamePreference.setEnabled(true);
        else
            individualNamePreference.setEnabled(false);
        // 设置包含onPreferenceChange事件的对象实例
        individualNamePreference.setOnPreferenceChangeListener(this);

        //mobile

        // 获取"mobile"列表项对应的Preference对象
        Preference mobilePreference = findPreference("mobile");
        // 获得指向setting.xml文件的SharedPreferences对象
        SharedPreferences mobileSP = mobilePreference.getSharedPreferences();
        // 设置列表项的Summary
        mobilePreference.setSummary(mobileSP.getString("mobile", ""));
        // 设置包含onPreferenceChange事件的对象实例
        mobilePreference.setOnPreferenceChangeListener(this);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        // 设置"姓名"列表项中Summary的值
        preference.setSummary(String.valueOf(newValue));
        // 必须返回为true，否则无法保存设置的值
        return true;
    }

    /**
     * 状态改变后的自动文件存储 需要继承PreferenceActivity的类和实现OnPreferenceChangeListener接口,
     * 重写onPreferenceTreeClick方法进行业务逻辑处理
     *
     * @param preferenceScreen
     * @param preference
     * @return
     */
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        // 判断选中的是否为"是否保存个人信息"列表项的复选框
        if ("yesno_save_individual_info".equals(preference.getKey())) {
            // 设置姓名为可选或者不可选
            findPreference("individual_name").setEnabled(!findPreference("individual_name").isEnabled());
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
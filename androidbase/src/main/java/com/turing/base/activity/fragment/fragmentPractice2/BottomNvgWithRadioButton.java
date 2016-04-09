package com.turing.base.activity.fragment.fragmentPractice2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.turing.base.R;

/**
 * 我们使用LinearLayout + TextView实现了底部导航栏的效果，每次点击我们都要重置 所有TextView的状态，
 * 然后选中点击的TextView，有点麻烦是吧，
 * 接下来我们用另一种方法： RadioGroup + RadioButton来实现同样的效果
 */
public class BottomNvgWithRadioButton extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{


    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;

    //Fragment Object
    private Fragment_btm_nvg_rb_context fg1,fg2,fg3,fg4;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nvg_with_radio_button);

        fManager = getSupportFragmentManager();
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
        rb_channel.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        // FragmentTransaction只能使用一次，
        // 每次使用都要调用FragmentManager 的beginTransaction()方法获得FragmentTransaction事务对象
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (checkedId){
            case R.id.rb_channel:
                if(fg1 == null){
                    fg1 = new Fragment_btm_nvg_rb_context("第一个Fragment");
                    fTransaction.add(R.id.ly_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.rb_message:
                if(fg2 == null){
                    fg2 = new Fragment_btm_nvg_rb_context("第二个Fragment");
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.rb_better:
                if(fg3 == null){
                    fg3 = new Fragment_btm_nvg_rb_context("第三个Fragment");
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
            case R.id.rb_setting:
                if(fg4 == null){
                    fg4 = new Fragment_btm_nvg_rb_context("第四个Fragment");
                    fTransaction.add(R.id.ly_content,fg4);
                }else{
                    fTransaction.show(fg4);
                }
                break;
        }
        fTransaction.commit();
    }


    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }
}

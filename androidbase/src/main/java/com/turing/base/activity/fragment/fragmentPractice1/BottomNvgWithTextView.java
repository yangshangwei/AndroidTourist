package com.turing.base.activity.fragment.fragmentPractice1;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.turing.base.R;

/**
 * http://www.runoob.com/w3cnote/android-tutorial-fragment-demo1.html
 * <p/>
 * 以底部导航栏为例子,练习下Fragment的使用
 * <p/>
 * 需要解决的关键问题：
 * 1):Fragment什么时候初始化和add到容器中？什么时候hide和show？
 * 2):如何让TextView被选中？选中一个TextView后，要做一些什么操作？
 * 3):刚进入MainActivity怎么样让一个TextView处于Selected的状态？
 * <p/>
 * 思路：
 * 1):我们选中TextView后对对应的Fragment进行判空，如果为空，初始化，并添加到容器中；
 * 而hide的话，我们定义一个方法hide所有的Fragment，每次触发点击事件就先调用这个hideAll方法，
 * 将所有Fragment隐藏起来，然后如果TextView对应的Fragment不为空，我们就将这个Fragment显示出来；
 * 2):这个我们通过点击事件来实现，点击TextView后先重置所有TextView的选中状态为false，
 * 然后设置点击的 TextView的选中状态为true；
 * 3):这个更简单，我们是通过点击事件来设置选中的，
 * 那么在onCreate()方法里加个触发点击事件的 方法不就可以了嘛~ txt_channel.performClick();
 */
public class BottomNvgWithTextView extends AppCompatActivity implements View.OnClickListener {


    //UI Object
    private TextView txt_topbar;
    private TextView txt_channel;
    private TextView txt_message;
    private TextView txt_better;
    private TextView txt_setting;
    private FrameLayout ly_content;

    //Fragment Object
    private Fragment_btm_nvg_tv_context fg1, fg2, fg3, fg4;

    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment__bottom_nvg_with_text_view);

        fManager = getSupportFragmentManager();
        bindViews();
        //模拟一次点击，既进去后选择第一项
        txt_channel.performClick();
    }


    /**
     * UI组件初始化与事件绑定
     */

    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        txt_channel = (TextView) findViewById(R.id.txt_channel);
        txt_message = (TextView) findViewById(R.id.txt_message);
        txt_better = (TextView) findViewById(R.id.txt_better);
        txt_setting = (TextView) findViewById(R.id.txt_setting);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_channel.setOnClickListener(this);
        txt_message.setOnClickListener(this);
        txt_better.setOnClickListener(this);
        txt_setting.setOnClickListener(this);
    }

    /**
     * 重置所有文本的选中状态
     */

    private void setSelected() {
        txt_channel.setSelected(false);
        txt_message.setSelected(false);
        txt_better.setSelected(false);
        txt_setting.setSelected(false);
    }

    /**
     * 隐藏所有Fragment
     */

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) fragmentTransaction.hide(fg1);
        if (fg2 != null) fragmentTransaction.hide(fg2);
        if (fg3 != null) fragmentTransaction.hide(fg3);
        if (fg4 != null) fragmentTransaction.hide(fg4);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()) {
            case R.id.txt_channel:
                setSelected();
                txt_channel.setSelected(true);
                if (fg1 == null) {
                    fg1 = new Fragment_btm_nvg_tv_context("第一个Fragment");
                    fTransaction.add(R.id.ly_content, fg1);
                } else {
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_message:
                setSelected();
                txt_message.setSelected(true);
                if (fg2 == null) {
                    fg2 = new Fragment_btm_nvg_tv_context("第二个Fragment");
                    fTransaction.add(R.id.ly_content, fg2);
                } else {
                    fTransaction.show(fg2);
                }
                break;
            case R.id.txt_better:
                setSelected();
                txt_better.setSelected(true);
                if (fg3 == null) {
                    fg3 = new Fragment_btm_nvg_tv_context("第三个Fragment");
                    fTransaction.add(R.id.ly_content, fg3);
                } else {
                    fTransaction.show(fg3);
                }
                break;
            case R.id.txt_setting:
                setSelected();
                txt_setting.setSelected(true);
                if (fg4 == null) {
                    fg4 = new Fragment_btm_nvg_tv_context("第四个Fragment");
                    fTransaction.add(R.id.ly_content, fg4);
                } else {
                    fTransaction.show(fg4);
                }
                break;
        }
        fTransaction.commit();
    }
}

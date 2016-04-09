package com.turing.base.activity.fragment.fragmentPractice4;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.turing.base.R;

/**
 * http://www.runoob.com/w3cnote/android-tutorial-fragment-demo4.html
 * 第二个实例的基础上 加上ViewPager来实现滑动切换页面的效果
 * <p/>
 * ViewPager
 * 一个页面切换的组件，我们可以往里面填充多个View，
 * 然后我们可以通过触摸屏幕左右滑动 切换不同的View，
 * 和前面学习的ListView一样，我们需要一个Adapter(适配器)，
 * 将要显示的View和 我们的ViewPager进行绑定，
 * 而ViewPager有他自己特定的Adapter——PagerAdapter！
 * 另外，Google 官方是建议我们使用Fragment来填充ViewPager的,
 * 这样可以更加方便的生成每个Page以及管理 每个Page的生命周期!
 * 当然它给我们提供了两个不同的Adapter，
 * 他们分别是： FragmentPageAdapter和FragmentStatePagerAdapter！
 * 而我们本节用到的则是前者：FragmentPageAdapter！
 * 另外要说一点的是ViewPager的缓存机制：
 * ViewPager会缓存当前页，前一页，以及后一页，
 * 比如有1，2，3，4这四个页面： 当我们处于第一页：缓存1，2
 * ——> 处于第二页：缓存 1，2，3
 * ——> 处于第三页：缓存2，3，4
 * ——> 处于第四页缓存3，4这样！
 * <p/>
 * <p/>
 * 使用PagerAdapter要重写相关方法：
 * getCount( ):获得viewpager中有多少个view
 * destroyItem( ):移除一个给定位置的页面。适配器有责任从容器中删除这个视图。这是为了确保 在finishUpdate(viewGroup)返回时视图能够被移除。
 * instantiateItem( ):①将给定位置的view添加到ViewGroup(容器)中,创建并显示出来 ②返回一个代表新增页面的Object(key),通常都是直接返回view本身就可以了, 当然你也可以自定义自己的key,但是key和每个view要一一对应的关系
 * isViewFromObject( ):判断instantiateItem(ViewGroup, int)函数所返回来的Key与一个页面视图是否是 代表的同一个视图(即它俩是否是对应的，对应的表示同一个View),通常我们直接写 return view == object；就可以了,至于为什么要这样讲起来比较复杂,后面有机会进行了解吧 貌似是ViewPager中有个存储view状态信息的ArrayList,根据View取出对应信息的吧!
 * PS:不一定要重写所有方法~
 */
public class BottomNvgViewPageAct extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {


    //UI Objects
    private TextView txt_topbar;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    private RadioButton rb_message;
    private RadioButton rb_better;
    private RadioButton rb_setting;
    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;


    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nvg_view_page);

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_channel.setChecked(true);
    }


    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
        rb_message = (RadioButton) findViewById(R.id.rb_message);
        rb_better = (RadioButton) findViewById(R.id.rb_better);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.rb_channel:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_message:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_better:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_setting:
                vpager.setCurrentItem(PAGE_FOUR);
                break;
        }

    }

    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕

        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_channel.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_message.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_better.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rb_setting.setChecked(true);
                    break;
            }
        }
    }
}

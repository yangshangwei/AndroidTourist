package com.turing.base.activity.menu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.base.R;

/**
 * 最常用的就是选项菜单(optionsMenu),
 * 该菜单在点击 menu 按键 后会在对应的Activity底部显示出来。（4.4以前）
 * Activity菜单机制 ,Activity有一套机制来实现对菜单的管理 就是下面重写的几个方法
 */
public class OptionsMenuDemoAct extends AppCompatActivity {

    private TextView textView;



    //1.定义不同颜色的菜单项的标识:
    final private int RED = 110;
    final private int GREEN = 111;
    final private int BLUE = 112;
    final private int YELLOW = 113;
    final private int GRAY= 114;
    final private int CYAN= 115;
    final private int BLACK= 116;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu_demo);

        textView = (TextView) findViewById(R.id.id_tv_optionMenuText);
    }


    /**
     * 两种方式  1.通过menu的xml加载，2 通过代码动态创建
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
         * (只会在第一次初始化菜单时调用)
         * Inflate the menu; this adds items to the action bar  if it is present.
         */


        /**
         * 通过xml加载菜单
         *  getMenuInflater().inflate(R.menu.options_menu, menu);
         调用Activity的getMenuInflater()得到一个MenuInflater，
         使用inflate方法来把布局文件中的定义的菜单 加载给 第二个参数所对应的menu对象

         如果需要设置图片，可以在xml中设置 android:icon="@drawable/setting"
         高版本的SDK，即使设置了 Icon,也不会显示的。低版本的会显示
         */

        //getMenuInflater().inflate(R.menu.menu_main,menu);


        /**
         *
         * 第二种方式： 通过代码动态创建
         *
         *  menu.add((int groupId, int itemId, int order, charsequence title) .setIcon(drawable ID)

         add()方法的四个参数，依次是：

         1、组别，如果不分组的话就写Menu.NONE,

         2、Id，这个很重要，Android根据这个Id来确定不同的菜单

         3、顺序，哪个菜单项在前面由这个参数的大小决定

         4、文本，菜单项的显示文本

         add()方法返回的是MenuItem对象，调用其setIcon()方法，为相应MenuItem设置Icon
         高版本的SDK，即使设置了 Icon,也不会显示的。2.3低版本的会显示
         */

        // 高版本的SDK，即使设置了 Icon,也不会显示的。低版本的会显示
        menu.add(1,RED,4,"红色").setIcon(R.drawable.flag_mark_red);
        menu.add(1,GREEN,2,"绿色").setIcon(R.drawable.flag_mark_green);
        menu.add(1,BLUE,3,"蓝色").setIcon(R.drawable.flag_mark_blue);
        menu.add(1,YELLOW,1,"黄色").setIcon(R.drawable.flag_mark_yellow);
        menu.add(1,GRAY,5,"灰色").setIcon(R.drawable.flag_mark_gray);
        menu.add(1,CYAN,6,"蓝绿色").setIcon(R.drawable.gur_project_10);
        menu.add(1,BLACK,7,"黑色").setIcon(R.drawable.gur_project_5);



        // 返回true则显示该menu,false 则不显示;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /**
         * 在onCreateOptionsMenu执行后，菜单被显示前调用；
         * 如果菜单已经被创建，则在菜单显示前被调用。
         * 同样的，返回true则显示该menu,false 则不显示;
         * （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等）
         */
        Toast.makeText(this,
                "选项菜单显示之前onPrepareOptionsMenu方法会被调用，你可以用此方法来根据打当时的情况调整菜单",
                Toast.LENGTH_LONG).show();

        // 如果返回false，此方法就把用户点击menu的动作给拦截了，onCreateOptionsMenu方法将不会被调用

        //return true;
        // super.onPrepareOptionsMenu(menu) 父类返回的也是true
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public void onOptionsMenuClosed(Menu menu) {
        /**
         * 每次菜单被关闭时调用.
         * （菜单被关闭有三种情形，menu按钮被再次点击、back按钮被点击或者用户选择了某一个菜单项）
         */
        super.onOptionsMenuClosed(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * 菜单项被点击时调用，也就是菜单项的监听方法。
         * 通过这几个方法，可以得知，对于Activity，同一时间只能显示和监听一个Menu 对象。
         */

        switch ( item.getItemId()){
            case RED:  //对应的ID就是在add方法中所设定的Id
                textView.setTextColor(Color.RED);
                break;
            case GREEN:
                textView.setTextColor(Color.GREEN);
                break;
            case BLUE:
                textView.setTextColor(Color.BLUE);
                break;
            case YELLOW:
                textView.setTextColor(Color.YELLOW);
                break;
            case GRAY:
                textView.setTextColor(Color.GRAY);
                break;
            case CYAN:
                textView.setTextColor(Color.CYAN);
                break;
            case BLACK:
                textView.setTextColor(Color.BLACK);
                break;
        }

        /**
         * 如果是通过xml加载的菜单选项，那么Id就是布局文件中定义的Id，在用R.id.XXX的方法获取出来
         */


        return super.onOptionsItemSelected(item);
    }
}

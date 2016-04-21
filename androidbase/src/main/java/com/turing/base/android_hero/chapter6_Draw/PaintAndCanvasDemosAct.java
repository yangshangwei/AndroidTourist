package com.turing.base.android_hero.chapter6_Draw;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.turing.base.R;
import com.turing.base.android_hero.chapter6_Draw.animationDrawable.AnimationDrawable_addFrame;
import com.turing.base.android_hero.chapter6_Draw.animationDrawable.AnimationDrawable_start;
import com.turing.base.android_hero.chapter6_Draw.annimation_ViewAnimation.AlaphAnimationDemo;
import com.turing.base.android_hero.chapter6_Draw.annimation_ViewAnimation.AnimationDemoAct;
import com.turing.base.android_hero.chapter6_Draw.annimation_ViewAnimation.AnimationSetDemo;
import com.turing.base.android_hero.chapter6_Draw.annimation_ViewAnimation.AnimationUtilsDemo;
import com.turing.base.android_hero.chapter6_Draw.annimation_ViewAnimation.RotateAnimationDemo;
import com.turing.base.android_hero.chapter6_Draw.annimation_ViewAnimation.ScaleAnimationDemo;
import com.turing.base.android_hero.chapter6_Draw.paint.PaintAttrsAct;
import com.turing.base.android_hero.chapter6_Draw.annimation_propertyAnimation.PropertyAnimationDemoAct;
import com.turing.base.utils.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaintAndCanvasDemosAct extends ListActivity {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "Android绘图-功能演示",
            "Android动画效果-Animation-是Android系统的一个动画抽象类，所有其他一些动画类都要继承该类中的实现方法。Animation类主要用于补间动画效果，提供了动画启动、停止、重复、持续时间等方法。Animation类中的方法适用于任何一种补间动画对象",
            "Android动画效果-TranslateAnimation类：位置变化",
            "RotateAnimation类：旋转变化动画类",
            "ScaleAnimation类：尺寸变化动画",
            "AlphaAnimation类：透明度变化动",
            "AnimationSet类：动画集合类",
            "AnimationUtils类：动画工具类",
            "【以上是补间动画Tween Animation(SDK中称为View Animation),下面演示帧动画Frame Animation(SDK中称为DrawableAnimation)】",
            "AnimationDrawable-start方法：开始动画",
            "AnimationDrawable-stop方法：停止动画",
            "AnimationDrawable-addFrame方法：添加动画帧",
            "AnimationDrawable-setOneShot方法：设置播放方式",
            "AnimationDrawable-setAlpha方法：设置透明度",
            "AnimationDrawable-getNumberOfFrames方法：获取帧",
            "【下面演示3.0中新增的属性动画PropertyAnimation,低版本可以使用nineoldandroids动画库来使用】",
            "属性动画-PropertyAnimation"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initEvents();
    }


    private void initView() {

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, simulateData(arr),
                R.layout.activity_scroll_view_list_item,
                new String[]{"img", "info"},
                new int[]{R.id.id_sv_iv, R.id.id_sv_tv});

        setListAdapter(simpleAdapter);

    }


    private void initEvents() {

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:// Paint和Canvas各种API
                        startActivity(new Intent(PaintAndCanvasDemosAct.this, PaintAttrsAct.class));
                        break;
                    case 1:// Android动画 Animation
                        startActivity(new Intent(PaintAndCanvasDemosAct.this, AnimationDemoAct.class));
                        break;
                    case 2: //Android动画效果-TranslateAnimation类：位置变化
                        AlertUtil.showDialogWithClose(PaintAndCanvasDemosAct.this, "用法详见 Android动画 Animation");
                        break;
                    case 3://RotateAnimation类：旋转变化动画类
                        startActivity(new Intent(PaintAndCanvasDemosAct.this, RotateAnimationDemo.class));
                        break;
                    case 4:// ScaleAnimation类：尺寸变化动画
                        startActivity(new Intent(PaintAndCanvasDemosAct.this, ScaleAnimationDemo.class));
                        break;
                    case 5:// AlphaAnimation类：透明度变化动
                        startActivity(new Intent(PaintAndCanvasDemosAct.this, AlaphAnimationDemo.class));
                        break;
                    case 6:// AnimationSet类：动画集合类
                        startActivity(new Intent(PaintAndCanvasDemosAct.this, AnimationSetDemo.class));
                        break;
                    case 7:// AnimationUtils类：动画工具类
                        startActivity(new Intent(PaintAndCanvasDemosAct.this, AnimationUtilsDemo.class));
                        break;
                    case 8://【以上是补间动画Tween Animation,下面演示帧动画AnimationDrawable】
                        AlertUtil.showDialogWithClose(PaintAndCanvasDemosAct.this, "以上是补间动画Tween Animation,下面演示帧动画AnimationDrawable");
                        break;
                    case 9://AnimationDrawable-start方法：开始动画
                        startActivity(new Intent(PaintAndCanvasDemosAct.this, AnimationDrawable_start.class));
                        break;
                    case 10://AnimationDrawable-stop方法：停止动画
                        AlertUtil.showDialogWithClose(PaintAndCanvasDemosAct.this, "参考start方法中的 停止功能 ");
                        break;
                    case 11://AnimationDrawable-addFrame方法：添加动画帧
                        startActivity(new Intent(PaintAndCanvasDemosAct.this, AnimationDrawable_addFrame.class));
                        break;
                    case 12://AnimationDrawable-setOneShot方法：设置播放方式
                        AlertUtil.showDialogWithClose(PaintAndCanvasDemosAct.this, "代码都写在了addFrame中 ");
                        break;
                    case 13://AnimationDrawable-setAlpha方法：设置透明度
                        AlertUtil.showDialogWithClose(PaintAndCanvasDemosAct.this, "代码都写在了addFrame中 ");
                        break;
                    case 14://AnimationDrawable-getNumberOfFrames方法：获取帧
                        AlertUtil.showDialogWithClose(PaintAndCanvasDemosAct.this, "代码都写在了addFrame中 ");
                        break;
                    case 16:// PropertyAnimation
                        startActivity(new Intent(PaintAndCanvasDemosAct.this, PropertyAnimationDemoAct.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }


    private List<Map<String, Object>> simulateData(String[] arr) {

        List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < arr.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("img", R.drawable.flag_mark_green);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }
}

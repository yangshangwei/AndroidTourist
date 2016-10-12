package com.turing.base.activity.drawable;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.turing.base.R;
import com.turing.base.android_hero.chapter6_Draw.animationDrawable.AnimationDrawable_start;
import com.turing.base.android_hero.chapter6_Draw.xml.SelectorDemoAct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://blog.csdn.net/ouyang_peng/article/details/8800743
 * http://www.runoob.com/w3cnote/android-tutorial-drawable2.html
 * <p/>
 * <p/>
 * Android把可绘制的对象抽象为Drawable，不同的图形图像资源就代表着不同的drawable类型。
 * Android FrameWork提供了一些具体的Drawable实现，
 * 通常在代码中都不会直接接触Drawable的实现类。
 * <p/>
 * 在实际的开发过程中，会把使用到的资源都放置在res/drawable目录，
 * 剩下的工作交给Android SDK 就行了，
 * 当需要使用图片资源的时候，可以使用@drawable标志在xml中引用drawable资源就行，
 * 也可以在代码中使用id引用这些drawable资源。
 * <p/>
 * <p/>
 * 在使用drawable资源的时，有一点需要注意，
 * drawable默认是内存共享的，也就说在不同的地方使用了同一个drawable，它们都指向相同的资源，而且具有相同的状态，如果在一个地方修改了这个drawable，所有使用它的地方都会改变。
 * <p/>
 * Android内置了如下几种Drawable类型：
 * ColorDrawable、GradientDrawable、BitmapDrawable、
 * NinePatchDrawable、InsetDrawable、ClipDrawable、
 * ScaleDrawable、RotateDrawable、AnimationDrawable、
 * LayerDrawable、LevelListDrawable、StateListDrawable、
 * TransitionDrawable。
 * <p/>
 * 除了这些预置的drawable实现类以外，也可以自定义drawable的实现类型，
 * 大部分情况都不需要自定义drawable类型，
 * 使用系统提供的这些drawable实现类型已经覆盖了很多情况。
 * 在实际的编程过程中也很少会接触这些具体drawable实现类型，
 * 因为编写android应用程序使用xml可以很容易的创建drawable，
 * 只有在程序中需要修改drawable的属性时，才需要使用具体的drawable类型提供的方法来处理。
 * 下面就来逐个认识这些Drawable类型。
 */
public class DrawableDemoList extends ListActivity implements AdapterView.OnItemClickListener {

    // ListView中要显示的item
    private String[] arr = new String[]{
            "ColorDrawable",
            "GradientDrawable",
            "BitmapDrawable",
            "NinePatchDrawable",
            "InsetDrawable",
            "ClipDrawable",
            "RotateDrawable",
            "AnimationDrawable",
            "LayerDrawable",
            "LevelListDrawable",
            "StateListDrawable",
            "TransitionDrawable",
            "ScaleDrawable"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initEvents();
    }


    private void initView() {
        // Adapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, simulateData(arr),
                R.layout.activity_scroll_view_list_item,
                new String[]{"img", "info"},
                new int[]{R.id.id_sv_iv, R.id.id_sv_tv});
        // 设置Adapter
        setListAdapter(simpleAdapter);

    }

    private void initEvents() {
        // 获得ListView
        ListView listView = getListView();
        // 设置监听事件
        listView.setOnItemClickListener(this);
    }

    /**
     * 将数组转换为List<Map<String,Object>>
     *
     * @param arr
     * @return
     */
    private List<Map<String, Object>> simulateData(String[] arr) {

        List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < arr.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("img", R.drawable.gur_project_4);
            item.put("info", arr[i]);
            // 将Map添加到List
            datalist.add(item);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:// ColorDrawable
                startActivity(new Intent(DrawableDemoList.this, ColorDrawableActivity.class));
                break;
            case 1:// GradientDrawable
                startActivity(new Intent(DrawableDemoList.this, GradientDrawableActivity.class));
                break;
            case 2:// BitmapDrawable
                startActivity(new Intent(DrawableDemoList.this, BitmapDrawableActivity.class));
                break;
            case 3:// NinePatchDrawable
                break;
            case 4:// InsetDrawable
                startActivity(new Intent(DrawableDemoList.this, InsetDrawableActivity.class));
                break;
            case 5:// ClipDrawable
                startActivity(new Intent(DrawableDemoList.this, ClipDrawableActvity.class));
                break;
            case 6:// RotateDrawable
                startActivity(new Intent(DrawableDemoList.this, RotateDrawableActivity.class));
                break;
            case 7:// AnimationDrawable
                startActivity(new Intent(DrawableDemoList.this, AnimationDrawable_start.class));
                break;
            case 8:// LayerDrawable
                startActivity(new Intent(DrawableDemoList.this, LayerDrawableActivity.class));
                break;
            case 9:// LevelListDrawable
                startActivity(new Intent(DrawableDemoList.this, LevelListDrawableActivity.class));
                break;
            case 10:// StateListDrawable
                startActivity(new Intent(DrawableDemoList.this, SelectorDemoAct.class));
                break;
            case 11:// TransitionDrawable
                break;
            case 12:// ScaleDrawable
                startActivity(new Intent(DrawableDemoList.this, ScaleDrawableActivity.class));
                break;
            default:
                break;
        }
    }
}

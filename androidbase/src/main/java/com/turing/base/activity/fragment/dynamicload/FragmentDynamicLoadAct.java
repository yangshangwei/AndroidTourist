package com.turing.base.activity.fragment.dynamicload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.base.R;

public class FragmentDynamicLoadAct extends AppCompatActivity {

    // 屏幕宽高
    int width, height;

    FragementFirst fragementFirst;
    FragmentSecond fragmentSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_dynamic_load);

        // 获取屏幕的宽高
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;


        // 根据宽高，加载不同的Fragment
        if (width < height) {

            fragementFirst = new FragementFirst();
            // 使用V4的包就使用getSupportFragmentManager ，如果使用app下的Fragment，就使用getFragmentManager
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentDynamicLoad, fragementFirst)
                    .commit();


            // Activity传递数据给Fragment
            Bundle bundle = new Bundle();
            bundle.putString("key", "这是Activity传递给Fragment的数据");
            // setArguments
            fragementFirst.setArguments(bundle);

            //使用接口回调方法读数据(Activity中)
            fragementFirst.getData(new FragementFirst.FragmentCallBack() {
                @Override
                public void getResult(String result) {
                    Toast.makeText(FragmentDynamicLoadAct.this, result, Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            fragmentSecond = new FragmentSecond();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentDynamicLoad, fragmentSecond)
                    .commit();
        }
    }


    /**
     * 重写onStart()方法，
     * 因为从fragment的生命周期可以知道当Activity的onCreate(Bundle savedInstanceState)中
     * 还无法获取fragment的布局的组件
     */

    @Override
    protected void onStart() {
        super.onStart();


        /**
         * 可以直接通过findViewById()获取fragment的组件，
         * 因为fragment本身就是Activity的一部分（“碎片”/“片段”）；
         * 因为Activity和fragment要从fragment的onActivityCreate()生命周期方法之后
         * 才能相互获取对方布局中的组件，
         *　所以在fragment中获取Activity的组件最早只能在onActivityCreate()中获取，
         * 而Activity最早只能在onStart()中获取；
         */

        // 获取Fragment中的ＵＩ组件
        if (width < height) {
            TextView textView = (TextView) findViewById(R.id.fragmentFirst);
            textView.setText("~~~~~First");
        } else {
            TextView textView = (TextView) findViewById(R.id.fragmentSecond);
            textView.setText("~~~~~Second");
        }
    }
}

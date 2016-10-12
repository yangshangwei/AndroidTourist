package com.turing.base.activity.drawable;

import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.turing.base.R;

/**
 * 表示把一个Drawable嵌入到另外一个Drawable的内部，并且在内部留一些间距,
 * 类似与Drawable的padding属性,
 * 但padding表示的是Drawable的内容与Drawable本身的边距!
 * 而InsetDrawable表示的是两个Drawable与容器之间的边距,
 * 当控件需要的背景比实际的边框 小的时候,比较适合使用InsetDrawable
 * <p/>
 * 比如使用这个可以解决我们自定义Dialog与屏幕之间 的一个间距问题,
 * 相信做过的朋友都知道,即使我们设置了layout_margin的话也是没用的,
 * 这个 时候就可以用到这个InsetDrawable了!
 * 只需为InsetDrawable设置一个insetXxx设置不同 方向的边距,
 * 然后为设置为Dialog的背景即可！
 */
public class InsetDrawableActivity extends AppCompatActivity {


    private ImageView mMIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inset_drawable);

        mMIv = (ImageView) findViewById(R.id.mIv);


        // JavaCode
        InsetDrawable insetDrawable = new InsetDrawable(getResources()
                .getDrawable(R.drawable.zjl), 30, 60, 30, 60);

        // 设置
        mMIv.setImageDrawable(insetDrawable);
    }
}

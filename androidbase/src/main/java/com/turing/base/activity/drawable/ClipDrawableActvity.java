package com.turing.base.activity.drawable;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.turing.base.R;

/**
 * ClipDrawable 是对一个Drawable进行剪切操作，可以控制这个drawable的剪切区域，以及相相对于容器的对齐方式.
 * android中的进度条就是使用一个ClipDrawable实现效果的，
 * 它根据level的属性值，决定剪切区域的大小。
 * <p/>
 * 在xml文件中使用clip作为根节点定义ClipDrawable。
 * <p/>
 * 需要注意的是ClipDrawable是根据level的大小控制图片剪切操作的，
 * 官方文档的note中提到：The drawable is clipped completely and not visible when the level is 0 and fully revealed when the level is 10,000。也就是level的大小从0到10000，level为0时完全不显示，为10000时完全显示。是用Drawable提供的setLevel（int level）方法来设置剪切区域。
 * <p/>
 * <p/>
 * ![这里写图片描述](http://img.blog.csdn.net/20160505100345913)
 * <p/>
 * - clipOrietntion:设置剪切的方向,可以设置水平和竖直2个方向
 * - gravity:从那个位置开始裁剪
 * - drawable:引用的drawable资源,为空的话需要有一个Drawable类型的子节点 .
 * <p/>
 * <p/>
 * 核心：通过代码修改ClipDrawable的level的值！Level的值是0~10000！
 */
public class ClipDrawableActvity extends AppCompatActivity {

    private ImageView mIdIvClipdrawable;

    private ClipDrawable clipDrawable;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x110) {
                // level 增加
                clipDrawable.setLevel(clipDrawable.getLevel() + 500);
                Message message = Message.obtain();
                message.what = 0x110;
                handler.sendMessageDelayed(message, 500);

                if (clipDrawable.getLevel() > 10000) {
                    handler.removeMessages(0x110);
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_drawable_actvity);

        mIdIvClipdrawable = (ImageView) findViewById(R.id.id_iv_clipdrawable);
        // 初始化
        clipDrawable = (ClipDrawable) mIdIvClipdrawable.getDrawable();

        handler.sendEmptyMessage(0x110);

    }


}

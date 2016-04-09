package com.turing.base.activity.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-03-24  09:37.
 * @version 1.0
 *          监听滚动事件一般都是设置setOnScrollChangedListener，
 *          可惜的是 WebView并没有给我们提供这样的方法，
 *          但是我们可以重写WebView，覆盖里面的一个方法：
 *          protected void onScrollChanged(final int l, final int t,
 *          final int oldl,final int oldt){}
 *          然后再对外提供一个接口
 */
public class MyWebView extends WebView {

    /**
     * 接口对象
     */
    private OnScrollChangedCallback onScrollChangedCallback;


    /**
     * 构造函数
     *
     * @param context
     */
    public MyWebView(Context context) {
        super(context);
    }

    /**
     * 构造函数
     *
     * @param context
     */
    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 构造函数
     *
     * @param context
     */
    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * get方法
     *
     * @return
     */
    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return onScrollChangedCallback;
    }

    /**
     * set方法
     *
     * @param onScrollChangedCallback
     */
    public void setOnScrollChangedCallback(OnScrollChangedCallback onScrollChangedCallback) {
        this.onScrollChangedCallback = onScrollChangedCallback;
    }


    /**
     * 重写onScrollChanged方法
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     *
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollChangedCallback != null) {
            onScrollChangedCallback.onScroll(l - oldl, t - oldt);
        }
    }


    /**
     * 对外接口
     */
    public static interface OnScrollChangedCallback {
        //这里的dx和dy代表的是x轴和y轴上的偏移量，也可以自己把l, t, oldl, oldt四个参数暴露出来
        public void onScroll(int dx, int dy);
    }
}

package com.turing.base.activity.scrollViewAct;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.turing.base.R;

public class GalleryAct extends AppCompatActivity {


    private Gallery gallery;
    private ImageAdapter imageAdapter;

    private int[] resIds = new int[]
            { R.drawable.item1, R.drawable.item2, R.drawable.item3, R.drawable.item4,
                    R.drawable.item5, R.drawable.item6, R.drawable.item7,
                    R.drawable.item8, R.drawable.item9, R.drawable.item10,
                    R.drawable.item11, R.drawable.item12, R.drawable.item13,
                    R.drawable.item14, R.drawable.item15 };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        initView();
    }

    private void initView() {
        gallery = (Gallery) findViewById(R.id.gallery);
        imageAdapter = new ImageAdapter(this);
        gallery.setAdapter(imageAdapter);

    }


    class ImageAdapter extends BaseAdapter {


        int mGalleryItemBackground;
        private Context mContext;

        public ImageAdapter(Context context) {
            mContext = context;
            TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
            mGalleryItemBackground = typedArray.getResourceId(
                    R.styleable.Gallery_android_galleryItemBackground, 0);
        }

        public int getCount() {
            return Integer.MAX_VALUE;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);
            // 通过取余的方式的获取图像资源的id
            imageView.setImageResource(resIds[position % resIds.length]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setBackgroundResource(mGalleryItemBackground);
            return imageView;
        }
    }
}

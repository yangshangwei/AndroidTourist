package com.turing.base.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.turing.base.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.io.FileNotFoundException;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015-11-19  21:52.
 * @version 1.0
 * @desc
 */


@EActivity(R.layout.activity_upload)
@Fullscreen
public class Upload_HttpUrlConnection_Activity extends Activity {


    @ViewById(R.id.id_btn_choose_local_pic)
    Button btn_choose_pic;
    @ViewById(R.id.id_btn_submit_local_pic)
    Button btn_submit_pic;

    @ViewById(R.id.id_iv_show_local_pic)
    ImageView iv_local_pic;


    @Click({R.id.id_btn_choose_local_pic, R.id.id_btn_submit_local_pic})
    public void chooseLocalPic(View view) {
        switch (view.getId()) {
            case R.id.id_btn_choose_local_pic:
                chooseSinglePicFromPhone();
                break;
            case R.id.id_btn_submit_local_pic:
                upload2Server();
                break;
            default:
                break;
        }
    }


    /**
     * 从手机选择单张图片
     */
    private void chooseSinglePicFromPhone() {
        Intent intent = new Intent();
        // 开启Pictures画面Type设定为image
        intent.setType("image/*");
        // 使用Intent.ACTION_GET_CONTENT这个Action
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // 取得相片后返回本画面 回调onActivityResult
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            LogUtils.d("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                /* 将Bitmap设定到ImageView */
                iv_local_pic.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                LogUtils.e("Exception", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 将图片上传到服务端
     */
    private void upload2Server() {



    }

}

package com.turing.base.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.turing.base.R;
import com.turing.base.http.uploadHttp.UploadThread_HttpClient;
import com.turing.base.http.uploadHttp.UploadThread_HttpURLConnection;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.io.File;
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
public  class Upload_HttpUrlConnection_Activity extends Activity {


    //private String url = "http://172.20.212.198:8080/UpLoadService_Servlet3Tomcat7/UpLoad";
    private String url = "http://192.168.1.105:8080/UpLoadService_Servlet3Tomcat7/UpLoad";
    // 文件或者图片的存放路径
    private String path = null;

    // 使用Handler更新提示信息
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String response = null;
            switch (msg.what){
                // HttpURLConnection
                case 1 :
                    response = msg.obj.toString();
                    Toast.makeText(Upload_HttpUrlConnection_Activity.this,"httpURLConnection  " +response,Toast.LENGTH_LONG).show();
                    Upload_HttpUrlConnection_Activity.this.finish();
                    break;
                // HttpClient
                case 2:
                    response = msg.obj.toString();
                    Toast.makeText(Upload_HttpUrlConnection_Activity.this,"httpClient " + response,Toast.LENGTH_LONG).show();
                    Upload_HttpUrlConnection_Activity.this.finish();
                    break;
                default:
                    break;
            }
        }
    };




    @ViewById(R.id.id_btn_choose_local_pic)
    Button btn_choose_pic;
    @ViewById(R.id.id_btn_submit_local_pic)
    Button btn_submit_pic;
    @ViewById(R.id.id_btn_submit2_local_pic)
    Button btn_submit_httpClient ;

    @ViewById(R.id.id_iv_show_local_pic)
    ImageView iv_local_pic;


    @Click({R.id.id_btn_choose_local_pic, R.id.id_btn_submit_local_pic,R.id.id_btn_submit2_local_pic})
    public void chooseLocalPic(View view) {
        switch (view.getId()) {
            case R.id.id_btn_choose_local_pic:
                chooseSinglePicFromPhone();
                break;
            case R.id.id_btn_submit_local_pic:
                httpURLConnectionUpload2Server();
                break;
            case R.id.id_btn_submit2_local_pic:
                httpClientUplaod();
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
            Log.e("URI:", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                /* 将Bitmap设定到ImageView */
                iv_local_pic.setImageBitmap(bitmap);

                int sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
                Log.d("sdkVersion:", String.valueOf(sdkVersion));
                Log.d("KITKAT:", String.valueOf(Build.VERSION_CODES.KITKAT));
                if (sdkVersion >= 19) {  // 或者 android.os.Build.VERSION_CODES.KITKAT这个常量的值是19
                    path = uri.getPath();//5.0直接返回的是图片路径 Uri.getPath is ：  /document/image:46 ，5.0以下是一个和数据库有关的索引值
                    System.out.println("path:" + path);
                    // path_above19:/storage/emulated/0/girl.jpg 这里才是获取的图片的真实路径
                    path = getPath_above19(Upload_HttpUrlConnection_Activity.this, uri);
                    System.out.println("path_above19:" + path);
                } else {
                    path = getFilePath_below19(uri);

                }
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * @param uri
     */
    private String getFilePath_below19(Uri uri) {
        //这里开始的第二部分，获取图片的路径：低版本的是没问题的，但是sdk>19会获取不到
        String[] proj = {MediaStore.Images.Media.DATA};
        //好像是android多媒体数据库的封装接口，具体的看Android文档
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        //获得用户选择的图片的索引值
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        System.out.println("***************" + column_index);
        //将光标移至开头 ，这个很重要，不小心很容易引起越界
        cursor.moveToFirst();
        //最后根据索引值获取图片路径   结果类似：/mnt/sdcard/DCIM/Camera/IMG_20151124_013332.jpg
        String path = cursor.getString(column_index);
        System.out.println("path:" + path);
        return path;
    }


    /**
     * APIlevel 19以上才有
     * 创建项目时，我们设置了最低版本API Level，比如我的是10，
     * 因此，AS检查我调用的API后，发现版本号不能向低版本兼容，
     * 比如我用的“DocumentsContract.isDocumentUri(context, uri)”是Level 19 以上才有的，
     * 自然超过了10，所以提示错误。
     * 添加    @TargetApi(Build.VERSION_CODES.KITKAT)即可。
     *
     * @param context
     * @param uri
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public  static String getPath_above19(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    /**
     * 通过HttpURLConnection的方式将图片上传到服务端
     */
    private void httpURLConnectionUpload2Server() {
        if(path !=null){
            File file = new File(path);
            // 启动子线程
            new UploadThread_HttpURLConnection(url, file,handler).start();
        } else{
            Toast.makeText(Upload_HttpUrlConnection_Activity.this, "请选择图片",Toast.LENGTH_LONG).show();
        }


    }
    /**
     * 通过HttpURLConnection的方式将图片上传到服务端
     */
    private void httpClientUplaod() {
        if(path !=null){
            File file = new File(path);
            // 启动子线程
            new UploadThread_HttpClient(url, file, handler).start();
        } else{
            Toast.makeText(Upload_HttpUrlConnection_Activity.this, "请选择图片",Toast.LENGTH_LONG).show();
        }

    }

}

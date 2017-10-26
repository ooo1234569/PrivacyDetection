package com.example.bingnanfeng02.privacydetection.util;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.example.bingnanfeng02.privacydetection.Activity.ResultActivity;

import java.io.File;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class Photo {
    private Context context;
    public Uri iamgeuri;
    public static  final  int REQUEST_CAMERA=1;
    public static  final int OPEN_ALBUM=2;
    public Photo(Context context){
        this.context=context;
    }
    public void takePhoto(){
        Intent open = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();
        if(Build.VERSION.SDK_INT>=24){
            iamgeuri = FileProvider.getUriForFile(context, "camera", file);
        }else {
            iamgeuri= Uri.fromFile(file);
        }
        open.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        open.putExtra(MediaStore.EXTRA_OUTPUT, iamgeuri);
        ((Activity)context).startActivityForResult(open, REQUEST_CAMERA);
    }
    public void openAlbum(){
        Intent open =new Intent("android.intent.action.GET_CONTENT");
        open.setType("image/*");
        ((Activity)context).startActivityForResult(open,OPEN_ALBUM);
    }
    public String handleimage(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
       return imagePath;
    }
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}

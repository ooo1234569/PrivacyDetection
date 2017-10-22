package com.example.bingnanfeng02.privacydetection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.FileNotFoundException;

/**
 * Created by bingnanfeng02 on 2017/10/20.
 */

public class GetBitmap1 implements GetBitmap {
    BitmapFactory.Options options;
    public GetBitmap1(){
        options = new BitmapFactory.Options();
    }
    public Bitmap get(Context context){
        try {
            return BitmapFactory.decodeStream((context).getContentResolver().openInputStream((Uri) ((Activity)context).getIntent().getParcelableExtra("uri")),null,options).copy(Bitmap.Config.ARGB_8888, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.example.bingnanfeng02.privacydetection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

/**
 * Created by bingnanfeng02 on 2017/10/20.
 */

public class GetBitmap2 implements GetBitmap {
    BitmapFactory.Options options;
    public GetBitmap2(){
        options = new BitmapFactory.Options();
    }
    public Bitmap get(Context context){
        String path=((Activity)context).getIntent().getStringExtra("uri");
        if (path != null) {
           return BitmapFactory.decodeFile(path,options).copy(Bitmap.Config.ARGB_8888, true);
        } else {
            return null;
        }
    }
}

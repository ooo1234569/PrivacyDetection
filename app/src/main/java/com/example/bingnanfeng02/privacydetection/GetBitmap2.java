package com.example.bingnanfeng02.privacydetection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bingnanfeng02.privacydetection.View.GetBm;

import java.io.FileNotFoundException;

/**
 * Created by bingnanfeng02 on 2017/10/20.
 */

public class GetBitmap2 extends GetBm {
    public Bitmap get(Context context,int width,int height){
        String path=((Activity)context).getIntent().getStringExtra("uri");
        if (path != null) {
            BitmapFactory.decodeFile(path,options);
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            options.inSampleSize = calculateInSampleSize(width, height);
            options.inJustDecodeBounds = false;
           return BitmapFactory.decodeFile(path,options).copy(Bitmap.Config.ARGB_4444, true);
        } else {
            return null;
        }
    }
}

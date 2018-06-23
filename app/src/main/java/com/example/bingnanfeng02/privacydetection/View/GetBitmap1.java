package com.example.bingnanfeng02.privacydetection.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.FileNotFoundException;

/**
 * Created by bingnanfeng02 on 2017/10/20.
 */

public class GetBitmap1 extends GetBm {
    public Bitmap get(Context context,int width,int height){
        try {
            BitmapFactory.decodeStream((context).getContentResolver().openInputStream((Uri) ((Activity)context).getIntent().getParcelableExtra("uri")),null,options);
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            options.inSampleSize = calculateInSampleSize(width, height);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream((context).getContentResolver().openInputStream((Uri) ((Activity)context).getIntent().getParcelableExtra("uri")),null,options).copy(Bitmap.Config.ARGB_4444, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

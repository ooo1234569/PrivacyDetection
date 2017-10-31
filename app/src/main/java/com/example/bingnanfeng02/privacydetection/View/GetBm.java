package com.example.bingnanfeng02.privacydetection.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by bingnanfeng02 on 2017/10/31.
 */

public abstract class GetBm {
    protected BitmapFactory.Options options;
    public GetBm(){
        options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
    }
    public abstract Bitmap get(Context context, int width, int height);
    public int calculateInSampleSize(int reqWidth, int reqheight) {
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;
        int inSampleSize = 1;
        if (originalWidth > reqWidth || originalHeight > reqheight) {
            int halfWidth = originalWidth / 2;
            int halfHeight = originalHeight / 2;
            while ((halfWidth / inSampleSize > reqWidth)
                    &&(halfHeight / inSampleSize > reqheight)) {
                inSampleSize *= 2;

            }
        }
        Log.d("ssss",inSampleSize+"");
        return inSampleSize;
    }
}

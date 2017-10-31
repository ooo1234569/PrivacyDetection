package com.example.bingnanfeng02.privacydetection.util;

import android.content.Context;

/**
 * Created by bingnanfeng02 on 2017/10/31.
 */

public class GetpxOrdp {
    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}

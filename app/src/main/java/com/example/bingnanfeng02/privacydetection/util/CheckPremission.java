package com.example.bingnanfeng02.privacydetection.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * Created by fengbingnan on 2017/10/26.
 */

public class CheckPremission {
    public static final int REQUEST_CODE_PERMISSION = 1;
    private static String[] PERMISSIONS_REQ = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    private static boolean verifyPermissions(Activity activity) {
        for(int i=0;i<PERMISSIONS_REQ.length;i++){
            if(ActivityCompat.checkSelfPermission(activity,PERMISSIONS_REQ[i])!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                        activity,
                        PERMISSIONS_REQ,
                        REQUEST_CODE_PERMISSION
                );
                return false;
            }
        }
        return true;
    }
    public static void initPremission(Activity activity){
        boolean avialbe_permission = true;
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.M ) {
            avialbe_permission = verifyPermissions(activity);
        }
        if (avialbe_permission ) {

        }
    }
}

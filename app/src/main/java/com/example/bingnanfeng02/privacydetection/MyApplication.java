package com.example.bingnanfeng02.privacydetection;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class MyApplication extends Application {
    String ip="";
    String port="";
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        ip=pref.getString("ip","");
        port=pref.getString("port","");
    }
}

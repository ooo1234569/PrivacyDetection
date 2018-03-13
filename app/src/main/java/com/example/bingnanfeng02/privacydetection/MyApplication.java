package com.example.bingnanfeng02.privacydetection;

import android.app.Application;
import android.content.SharedPreferences;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class MyApplication extends Application {
    public  int i=0;
    public String email="";
    public String touxiang="";
    public String ip="";
    public String port="";
    public boolean auto_fill;
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        ip=pref.getString("sendweibo","");
        port=pref.getString("port","");
        auto_fill=isAuto_fill();
    }
    boolean isAuto_fill(){
        SharedPreferences preferences=getSharedPreferences("auto",MODE_PRIVATE);
        if(preferences.getString("auto","").equals("")||preferences.getString("auto","").equals("no")){
            return false;
        }
        return true;
    }
    public void change(boolean auto_fill){
        this.auto_fill=auto_fill;
    }
}

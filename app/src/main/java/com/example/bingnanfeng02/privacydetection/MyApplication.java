package com.example.bingnanfeng02.privacydetection;

import android.app.Application;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.widget.Toast;


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
//    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        ip=pref.getString("sendweibo","100.69.158.98");
        port=pref.getString("port","8000");
        Constant.ipp=ip;
        Constant.ip="http://"+Constant.ipp;
        Constant.port=Integer.valueOf(port);
        auto_fill=isAuto_fill();
        //refWatcher= setupLeakCanary();
        Constant.toast=Toast.makeText(this,"",Toast.LENGTH_SHORT);
        Constant.toast.setGravity(Gravity.CENTER,0,0);
    }
    boolean isAuto_fill(){
        SharedPreferences preferences=getSharedPreferences("auto",MODE_PRIVATE);
        if(preferences.getString("auto","").equals("")||preferences.getString("auto","").equals("no")){
            return false;
        }
        return true;
    }
//    private RefWatcher setupLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return RefWatcher.DISABLED;
//        }
//        return LeakCanary.install(this);
//    }
//
//    public static RefWatcher getRefWatcher(Context context) {
//        MyApplication leakApplication = (MyApplication) context.getApplicationContext();
//        return leakApplication.refWatcher;
//    }
    public void change(boolean auto_fill){
        this.auto_fill=auto_fill;
    }
}

package com.example.bingnanfeng02.privacydetection;

import android.widget.Toast;

/**
 * Created by bingnanfeng02 on 2018/3/11.
 */

public class Constant {
    public static String cookie="";
    public static String email="Alice";
    public static String password="12345";
    public static String ipp="100.69.158.98";
    public static String ip="http://"+ipp;
    public static int port=8000;
    public static String sendweibo =ip+":"+port+"/user/microblog/send";
    public static String denglu =ip+":"+port+"/auth/login";
    public static String dengchu =ip+":"+port+"/auth/logout";
    public static String addfriend =ip+":"+port+"/user/friend/add";
    public static String managefriend=ip+":"+port+"/user/friend/all";
    public static String updatepremi=ip+":"+port+"/user/friend/level";
    public static String checkpyq=ip+":"+port+"/user/friend/microblog";
    public static String detect=ip+":"+port+"/user/microblog/detect";
    public static String wangzhi= ip +":"+port;
    public static String sendpyq=ip+":"+port+"/user/microblog/send/";
    public static String addrule=ip+":"+port+"/user/rule/add";
    public static String getrule=ip+":"+port+"/user/rule/all";
    public static String deleterule=ip+":"+port+"/user/rule/remove/";
    public static String updaterule=ip+":"+port+"/user/rule/update";
    public static final String[] TEXTS={"删除"};
    public static int size=1;
    public static Toast toast;
    public static void show(String s){
        toast.setText(s);
        toast.show();
    }
}

package com.example.bingnanfeng02.privacydetection;

/**
 * Created by bingnanfeng02 on 2018/3/11.
 */

public class Constant {
    public static String ip="http://100.69.220.61";
    public static int port=8000;
    public static String sendweibo =ip+":"+port+"/user/microblog/send/";
    public static String denglu =ip+":"+port+"/auth/login";
    public static String dengchu =ip+":"+port+"/auth/logout";
    public static String wangzhi= sendweibo +":"+port;
    public static String sendpyq=wangzhi;
    public static int size=1;
}

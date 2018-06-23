package com.example.bingnanfeng02.privacydetection.Task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.data.DengluReturn;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2018/3/12.
 */

public class LoginTask extends AsyncTask {
    private Handler handler;
    private String password;
    private String email;
    public LoginTask(String email, String password, Handler handler, Context context){
        this.handler=handler;
        this.email=email;
        this.password=password;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", email)
                .addFormDataPart("password",password)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS)
                .build();
        Log.d("denglu",Constant.denglu);
        Request request = new Request.Builder()
                .url(Constant.denglu)
                .post(multipartBody)
                .build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            Constant.cookie=response.header("Set-Cookie");
            String s=response.body().string();
            Log.d("denglujson",s);
            parseJson(s);
        } catch (IOException e) {
            if(e instanceof SocketTimeoutException){
                parseJson(null);
                Log.d("ss","timele");
            }
        }
        return null;
    }
    void parseJson(String json){
        Gson gson = new Gson();
        DengluReturn dengluReturn;
        try {
            dengluReturn=gson.fromJson(json,DengluReturn.class);
        }catch (Exception e){
            dengluReturn=null;
        }
        Message message=new Message();
        message.arg1=1;
        message.obj=dengluReturn;
        handler.sendMessage(message);
    }
}

package com.example.bingnanfeng02.privacydetection.Task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.bingnanfeng02.privacydetection.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2018/3/12.
 */

public class SignOutTask extends AsyncTask {
    private Handler handler;

    public SignOutTask(Handler handler, Context context){
        this.handler=handler;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(Constant.dengchu)
                .build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            String s=response.body().string();
            Log.d("dengchujson",s);
            parseJson(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    void parseJson(String json){
        Message message=new Message();
        message.obj=json;
        handler.sendMessage(message);
    }
}

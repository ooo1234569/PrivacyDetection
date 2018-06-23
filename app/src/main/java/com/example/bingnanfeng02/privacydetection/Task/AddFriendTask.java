package com.example.bingnanfeng02.privacydetection.Task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.data.AddFriendReturn;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2018/3/13.
 */

public class AddFriendTask  extends AsyncTask{
    private Handler handler;
    private String id;
    public AddFriendTask(Context context, Handler handler,String id){
        this.handler=handler;
        this.id=id;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id",id)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS)
                .build();
        Log.d("addfriend", Constant.addfriend);
        Request request = new Request.Builder()
                .url(Constant.denglu)
                .addHeader("cookie",Constant.cookie)
                .post(multipartBody)
                .build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            String s=response.body().string();
            Log.d("addfriendjson",s);
            parseJson(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    void parseJson(String json){
        Gson gson=new Gson();
        AddFriendReturn addFriendReturn;
        try {
            addFriendReturn=gson.fromJson(json, AddFriendReturn.class);
        }catch (Exception e){
            addFriendReturn=null;
        }
        Message message=new Message();
        message.arg1=2;
        message.obj=addFriendReturn;
        handler.sendMessage(message);
    }
}

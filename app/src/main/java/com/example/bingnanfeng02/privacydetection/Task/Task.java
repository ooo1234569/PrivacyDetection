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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2018/3/13.
 */


//    private Context context;
//    private Handler handler;
//    public HashMap<String,String> hashMap;
//    public Object object;
//    public  Task(Context context, Handler handler,HashMap<String,String> hashMap,Object o,String url){
//        this.context=context;
//        this.handler=handler;
//        this.hashMap=hashMap;
//        this.object=o;
//    }
public  class Task extends AsyncTask {
    private  Message message;
    @Override
    protected Object doInBackground(Object[] params) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.SECONDS)
                .connectTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .build();


        Log.d((String)params[5], (String)params[4]);

        Request.Builder re=new Request.Builder().url((String)params[4]);
        Request request;

        if(params[2]!=null){
            MultipartBody.Builder multipartBody=new MultipartBody.Builder();
            multipartBody.setType(MultipartBody.FORM);
            Iterator iter = ((HashMap<String,String>)params[2]).entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                multipartBody.addFormDataPart((String) entry.getKey(),(String) entry.getValue());
            }
            re.post(multipartBody.build());
        }

         request=re.build();

        try {
            Response response=okHttpClient.newCall(request).execute();
            String s=response.body().string();
            Log.d(params[5]+"json",s);
            parseJson(s,(Handler)params[1],params[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void parseJson(String json,Handler handler,Object object){
        message=new Message();
        Gson gson=new Gson();
        object=gson.fromJson(json,object.getClass());
        message.obj=object;
        handler.sendMessage(message);
    }
}

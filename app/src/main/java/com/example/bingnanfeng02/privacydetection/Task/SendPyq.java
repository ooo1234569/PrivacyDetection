package com.example.bingnanfeng02.privacydetection.Task;

/**
 * Created by bingnanfeng02 on 2017/10/6.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.data.Bbox;
import com.example.bingnanfeng02.privacydetection.data.DetectPyqReturn;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SendPyq extends AsyncTask {
    private Bitmap bitmap;
    private static Bitmap bitmap2;
    private Handler handler;
    private Paint paint;
    private ArrayList<Bbox> bboxes=new ArrayList<>();
    private static File file;
    private String txt;
    public SendPyq(Bitmap bitmap,String txt, Handler handler, Context context){
        this.bitmap=bitmap;
        this.handler=handler;
        this.txt=txt;
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(12.5f/Constant.size);
        paint.setTextSize(100/Constant.size);
    }


    @Override
    protected Object doInBackground(Object[] params) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS)
                .build();
        Request.Builder builder=new Request.Builder();
        Request request;
        Log.d("tag","bug");

        if((int)params[0]==1){
            Log.d("tag","bug2");

            try {
                changeBitmap2File(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody multipartBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("text", txt)
                    .addFormDataPart("images", "perimission.jpg", requestBody)
                    .build();
            request=builder.url(Constant.detect).addHeader("cookie",Constant.cookie).post(multipartBody).build();
            Log.d("detectweibo"+params[0],Constant.detect);
        }else {
            request=builder.url(Constant.sendpyq+params[1]).addHeader("cookie",Constant.cookie).build();
            Log.d("sendweibo"+params[0],Constant.sendweibo);
        }


        try {
            Response response=okHttpClient.newCall(request).execute();
            String s=response.body().string();
            Log.d("json",s);

            parseJson(s,(int)params[0]);
            Log.d("sendweibojson",s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //mmDialog.dismiss();
        return null;
    }
    void parseJson(String json,int flag){
        Log.d("json",json);
        Gson gson = new Gson();
        Message message=new Message();
        if(flag==1){
            DetectPyqReturn detectPyqReturn =gson.fromJson(json,DetectPyqReturn.class);
            message.obj= detectPyqReturn;
        }else {
            message.obj=json;
        }
        message.arg1=flag;
        handler.sendMessage(message);
        //mmDialog.dismiss();
    }
    public static void  changeBitmap2File(Bitmap bitmap) throws IOException {
        bitmap2=bitmap;
        file = new File(Environment.getExternalStorageDirectory().getPath(), "perimission.jpg");
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);//压缩成图片  保存在fos
    }

    public static Bitmap returnBitmap(){
        return bitmap2;
    }

    void initData(){
        Bbox bbox=new Bbox();
        bbox.x=50/Constant.size;
        bbox.y=50/Constant.size;
        bbox.width=500/Constant.size;
        bbox.height=500/Constant.size;
        bbox.classes="object";
        bboxes.add(bbox);
    }
    void draw(Bbox bbox){
        float pts[] =new float[16];
        pts[0]=bbox.x;
        pts[1]=bbox.y;
        pts[2]=bbox.x;
        pts[3]=bbox.y+bbox.height;

        pts[4]=bbox.x;
        pts[5]=bbox.y;
        pts[6]=bbox.x+bbox.width;
        pts[7]=bbox.y;

        pts[8]=bbox.x;
        pts[9]=bbox.y+bbox.height;
        pts[10]=bbox.x+bbox.width;
        pts[11]=bbox.y+bbox.height;

        pts[12]=bbox.x+bbox.width;
        pts[13]=bbox.y;
        pts[14]=bbox.x+bbox.width;
        pts[15]=bbox.y+bbox.height;
//        canvas.drawLines(pts,paint);
//        canvas.drawText(bbox.classes,bbox.x,bbox.y-(paint.getStrokeWidth()/Constant.size)-5/Constant.size,paint);
    }
}

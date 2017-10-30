package com.example.bingnanfeng02.privacydetection.Task;

/**
 * Created by bingnanfeng02 on 2017/10/6.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.bingnanfeng02.privacydetection.Data;
import com.example.bingnanfeng02.privacydetection.data.Bbox;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class GetBitmapTask  extends AsyncTask {
    private ProgressDialog mmDialog;
    private Bitmap bitmap;
    private static Bitmap bitmap2;
    private Handler handler;
    private Context context;
    private Canvas canvas;
    private Paint paint;
    private ArrayList<Bbox> bboxes=new ArrayList<>();
    private int size;
    private String url;
    private static File file;
    public GetBitmapTask(Bitmap bitmap, Handler handler, Context context,int size,String url){
        this.bitmap=bitmap;
        this.handler=handler;
        this.context=context;
        this.size=size;
        this.url=url;
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(12.5f/size);
        paint.setTextSize(100/size);
        canvas=new Canvas(bitmap);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mmDialog = ProgressDialog.show(context, "请等待","正在识别中", true);
    }
    @Override
    protected Object doInBackground(Object[] params) {
        try {
            changeBitmap2File(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "perimission.jpg", requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.SECONDS)
                .connectTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            String s=response.body().string();
            parseJson(s);
            Log.d("json",s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mmDialog.dismiss();
        return null;
    }
    void parseJson(String json){
        try {
            JSONObject jsonObject=new JSONObject(json);
            Data data=new Data();
            data.setImagename((String)jsonObject.get("imagename"));
            data.setTextname((String)jsonObject.get("textname"));
            Log.d("ssss",data.getTextname());
            Message message=new Message();
            message.obj=data;
            handler.sendMessage(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        bbox.x=50/size;
        bbox.y=50/size;
        bbox.width=500/size;
        bbox.height=500/size;
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
        canvas.drawLines(pts,paint);
        canvas.drawText(bbox.classes,bbox.x,bbox.y-(paint.getStrokeWidth()/size)-5/size,paint);
    }
}

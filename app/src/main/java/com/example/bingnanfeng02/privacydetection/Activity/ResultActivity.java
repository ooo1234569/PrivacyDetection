package com.example.bingnanfeng02.privacydetection.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.Task.GetBitmapTask;

import java.io.FileNotFoundException;

/**
 * Created by bingnanfeng02 on 2017/10/4.
 */

public class ResultActivity extends AppCompatActivity {
    PhotoView photo;
    String path;
    Bitmap bitmap;
    long starttime;
    long stoptime;
    GetBitmapTask bitmapTask;
    private static  final int size=1;
    Handler handler=new Handler(){
        public void handleMessage(Message msg_main) {
            photo.setImageBitmap((Bitmap)(msg_main.obj));
            stoptime = System.currentTimeMillis();
            Toast.makeText(ResultActivity.this, "检测花费了" + String.valueOf((double) (stoptime - starttime) / 1000) + "秒", Toast.LENGTH_SHORT).show();
        }
    };

    BitmapFactory.Options options = new BitmapFactory.Options();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        starttime=System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        setStatusBarColor();
        setContentView(R.layout.activity_result);
        photo=(PhotoView) findViewById(R.id.photo);
        options.inSampleSize = size;
        if(getIntent().getIntExtra("sdk",0)==1){
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream((Uri)getIntent().getParcelableExtra("uri")),null,options).copy(Bitmap.Config.ARGB_8888, true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            photo.enable();
            photo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }else {
            path=getIntent().getStringExtra("uri");
            displayImage(path);
        }
        bitmapTask=new GetBitmapTask(bitmap,handler,this,size,"http://"+getIntent().getStringExtra("ip")+":"+getIntent().getStringExtra("port")+"/detect");
        bitmapTask.execute();
    }

    void setStatusBarColor(){
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    private void displayImage(String imagePath) {//uri转换为bitmap
        if (imagePath != null) {
            bitmap = BitmapFactory.decodeFile(imagePath,options).copy(Bitmap.Config.ARGB_8888, true);
            photo.enable();
            photo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        bitmap.recycle();
        bitmap=null;
        System.gc();
    }
}

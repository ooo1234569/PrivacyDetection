package com.example.bingnanfeng02.privacydetection.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.example.bingnanfeng02.privacydetection.Adapter.ViewImageAdapter;
import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.R;

import java.util.ArrayList;


/**
 * Created by bingnanfeng02 on 2017/7/26.
 */
public class ViewImageActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private ViewPager viewPager;
    private ArrayList<String> imageUrls=new ArrayList<>();
    private GestureDetector gestureDetector;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery2);
        gestureDetector=new GestureDetector(this);
        viewPager=(ViewPager)findViewById(R.id.vp);
        Bundle bundle = this.getIntent().getExtras();
        viewPager.setAdapter(new ViewImageAdapter(imageUrls=bundle.getStringArrayList("urls") ,this));
        Constant.show("shabi");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float distance=e1.getY()-e2.getY();
        Log.d("tag",distance+"");
        Log.d("tag2",getWindow().getAttributes().alpha+"");
        if (distance> 20) {
            Log.i("MYTAG", "向上滑...");
//            WindowManager.LayoutParams lp = getWindow().getAttributes();
//             lp.alpha = (distance)/1920; //0.0-1.0
//            getWindow().setAttributes(lp);
            Display display = getWindowManager().getDefaultDisplay(); // 为获取屏幕宽、高
            Window window = getWindow();
            WindowManager.LayoutParams windowLayoutParams = window.getAttributes(); // 获取对话框当前的参数值
            windowLayoutParams.width = (int) (display.getWidth() * 0.7); // 宽度设置为屏幕的0.95
            windowLayoutParams.height = (int) (display.getHeight() * 0.1); // 高度设置为屏幕的0.6
            windowLayoutParams.alpha = 0.5f;// 设置透明度
            return true;
        }
        return false;
    }

}

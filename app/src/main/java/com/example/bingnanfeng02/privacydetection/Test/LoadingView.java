package com.example.bingnanfeng02.privacydetection.Test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by fengbingnan on 2017/11/1.
 */

public class LoadingView extends View {
    Paint paint;
    int banjing=0;
    int centerX;
    int centerY;
    float percent=0.01f;
    private static final  int margin=10;
    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
        paint.setColor(Color.WHITE);
    }
    public LoadingView(Context context){
        super(context);
        paint=new Paint();
        paint.setColor(Color.WHITE);
    }

    public LoadingView(Context context,AttributeSet paramAttributeSet) {
        super(context,paramAttributeSet);
        paint=new Paint();
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth((float) 3.0);
            if(getMeasuredHeight()>getMeasuredWidth()){
                banjing=getMeasuredWidth()/2;
            }else {
                banjing=getMeasuredHeight()/2;
            }
            centerX = getLeft() + banjing;
            centerY = getTop() + banjing;
            canvas.drawCircle(centerX,centerY,banjing,paint);
        initInnerCircle(percent,canvas);
    }
    void initInnerCircle(float percent,Canvas canvas){
        paint.setStyle(Paint.Style.FILL);
        RectF rectF=new RectF(getLeft()+margin,getTop()+margin,getRight()-margin,getBottom()-margin);
        canvas.drawArc(rectF,270,(percent*360)%360,true,paint);
    }
    public void change(float percent){
        this.percent=percent;
        invalidate();
    }

}

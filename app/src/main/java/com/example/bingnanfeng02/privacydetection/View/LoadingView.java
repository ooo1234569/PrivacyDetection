package com.example.bingnanfeng02.privacydetection.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.app.
/**
 * Created by fengbingnan on 2017/11/1.
 */

public class LoadingView extends View {
    Paint paint;
    int banjing=0;
    int centerX;
    int centerY;
    boolean isDrawOutnnerCircle=false;
    private static final  int margin=5;
    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isDrawOutnnerCircle){
            paint.setStyle(Paint.Style.STROKE);                   //空心效果
            paint.setStrokeWidth((float) 3.0);              //线宽
            if(getMeasuredHeight()>getMeasuredWidth()){
                banjing=getMeasuredWidth()/2;
            }else {
                banjing=getMeasuredHeight()/2;
            }
            centerX = getLeft() + banjing;
            centerY = getTop() + banjing;
            canvas.drawCircle(centerX,centerY,banjing,paint);
        }
        initInnerCircle(0.2f,canvas);

    }
    void initInnerCircle(float percent,Canvas canvas){
        paint.setStyle(Paint.Style.FILL);
        RectF rectF=new RectF(getLeft()+margin,getTop()+margin,getRight()-margin,getBottom()-margin);
        canvas.drawArc(rectF,270,(percent*360)%360,true,paint);
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }
}

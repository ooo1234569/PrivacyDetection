package com.example.bingnanfeng02.privacydetection.Test;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by bingnanfeng02 on 2017/11/5.
 */

public class LoadingImageView extends AppCompatImageView {
    ArrayList<Ball> balls;
    Paint paint;
    float radius=0;
    float prix=0;
    float priy=0;
    float nxtx;
    float nxty;
    int distance=500;
    Random random;
    int one[] ={-1,1};
    String[] color={"#5383ed","#d8503f","#f2bf42","#58a65c"};
    public LoadingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(balls.size()==0){
            int temp=Math.abs(random.nextInt()%100);
            for (int i=0;i<temp;i++){
                Ball ball=new Ball(getMeasuredWidth(),getMeasuredHeight());
                ball.setColor(color[Math.abs(random.nextInt()%4)]);
                balls.add(ball);
            }
        }
//        Drawable drawable = getDrawable();
//        //空值判断，必要步骤，避免由于没有设置src导致的异常错误
//        if (drawable == null) {
//            return;
//        }
//        //必要步骤，避免由于初始化之前导致的异常错误
//        if (getWidth() == 0 || getHeight() == 0) {
//            return;
//        }
//        if (!(drawable instanceof BitmapDrawable)) {
//            return;
//        }
//        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
//        if (null == b) {
//            return;
//        }
//        canvas.drawBitmap(b, 0, 0, paint);
        for (int i=0;i<balls.size();i++){
            Ball ball=balls.get(i);
            paint.setColor(Color.parseColor(ball.getColor()));
            canvas.drawCircle(ball.getNxtx(),ball.getNxty(),ball.getRadius(),paint);
        }
    }
    void init(){
        paint=new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        random=new Random();
        balls=new ArrayList<>();
    }
    public void draw(){
        ValueAnimator animator = ValueAnimator.ofFloat(0,12);
        animator.setDuration(200);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius=((Float)animation.getAnimatedValue());
                for (int i=0;i<balls.size();i++){
                    balls.get(i).setRadius(radius);
                }
                invalidate();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                go();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    public void go(){
        ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.setDuration(1000);
        animator.start();
        for (int i = 0; i < balls.size(); i++) {
                balls.get(i).init();
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cval=((Float)animation.getAnimatedValue());
                for(int i=0;i<balls.size();i++){
                    balls.get(i).draw(cval);
                }
                invalidate();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                 end();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
   void  end(){
       ValueAnimator animator = ValueAnimator.ofFloat(12,0);
       animator.setDuration(200);
       animator.start();
       animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
           @Override
           public void onAnimationUpdate(ValueAnimator animation) {
               float cval=((Float)animation.getAnimatedValue());
               for(int i=0;i<balls.size();i++){
                   balls.get(i).setRadius(cval);
               }
               invalidate();
           }
       });
   }

}

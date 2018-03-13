package com.example.bingnanfeng02.privacydetection.Test;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.bingnanfeng02.privacydetection.R;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;

    View view1;
    View view2;
    View view3;
    View view4;

    long duration=300;
    float rotate_radius;
    float distance;

    boolean lock=false;
    boolean lock2=false;
    ArrayList<ImageView> imageViews=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        img1=(ImageView)findViewById(R.id.img1);
        img2=(ImageView)findViewById(R.id.img2);
        img3=(ImageView)findViewById(R.id.img3);
        img4=(ImageView)findViewById(R.id.img4);
        imageViews.add(img1);
        imageViews.add(img2);
        imageViews.add(img3);
        imageViews.add(img4);
        findViewById(R.id.btn).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        rotate_radius =(img2.getX()-img1.getX())/2;
        distance =(img4.getX()-img1.getX());
        start();
    }
    void start(){
        lock=false;
        lock2=false;
        view1=imageViews.get(0);
        view2=imageViews.get(1);
        view3=imageViews.get(2);
        view4=imageViews.get(3);
        log();
        move(view4);
        rotate(view3);
    }
    void move(final View view){
        final float x_coordinate=view.getX();
        final ObjectAnimator animator = ObjectAnimator.ofFloat(view, "fbn", 0, 1f);
        animator.setDuration(duration);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress=(Float) animation.getAnimatedValue();
                view.setX(x_coordinate- distance *progress);
                if(!lock){
                    if(progress>0.25){
                        rotate(view2);
                        lock=true;
                    }
                }
                if(!lock2){
                    if(progress>0.55){
                        rotate(view1);
                        lock2=true;
                    }
                }
            }
        });
    }

    void rotate(final View view){
        final float x_coordinate=view.getX();
        final float y_coordinate=view.getY();
        final ObjectAnimator animator = ObjectAnimator.ofFloat(view, "fbn2", 0, 1f);
        animator.setDuration(duration);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (Float) animation.getAnimatedValue();
                view.setX(x_coordinate+ rotate_radius*progress*2);
                view.setY(y_coordinate- computeY(rotate_radius*progress*2));
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                 if(view==view1){
                     reset();
                     start();
                 }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    float computeY(float x){
        return (float)Math.sqrt(2*x* rotate_radius -x*x);
    }

    void reset(){
        ImageView temp=imageViews.get(imageViews.size()-1);
        for(int i=imageViews.size()-1;i>0;i--){
            imageViews.set(i,imageViews.get(i-1));
        }
        imageViews.set(0,temp);
    }
    void log(){
        for(int i=0;i<imageViews.size();i++){
            Log.d("ibj"+i,imageViews.get(i).getId()+"");
        }
    }
}

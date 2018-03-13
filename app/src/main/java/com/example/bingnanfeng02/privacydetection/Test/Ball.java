package com.example.bingnanfeng02.privacydetection.Test;

import android.util.Log;

import java.util.Random;

/**
 * Created by bingnanfeng02 on 2017/11/6.
 */

public class Ball {

    private float prix;
    private float priy;
    private float nxtx;
    private  float nxty;
    private int distance;
    private float radius=0;
    private Random random;
    double height;
    double width;
    int x_fangxiang;
    int y_fangxiang;
    int one[] ={-1,1};
    private String color;
    public  Ball(float width,float height){
        random=new Random();
        prix=Math.abs(random.nextInt()%width);
        priy=Math.abs(random.nextInt()%height);
        if(width>height){
            distance= (int) Math.abs(random.nextInt()%(height));
        }else {
            distance= (int) Math.abs(random.nextInt()%(width));
        }
        Log.d("distance",prix+"");
        nxtx=prix;
        nxty=priy;
    }
    public void init(){
        int corner = random.nextInt(90)%(91);
         height=Math.sin(Math.toRadians(corner))*distance;
        width=Math.cos(Math.toRadians(corner))*distance;
        x_fangxiang=one[Math.abs(random.nextInt()%2)];
        y_fangxiang=one[Math.abs(random.nextInt()%2)];
    }
    public  void draw(float cval){
        nxtx=prix+x_fangxiang*(float)width*cval;
        nxty=priy+y_fangxiang*(float)height*cval;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPriy() {
        return priy;
    }

    public void setPriy(float priy) {
        this.priy = priy;
    }

    public float getNxtx() {
        return nxtx;
    }

    public void setNxtx(float nxtx) {
        this.nxtx = nxtx;
    }

    public float getNxty() {
        return nxty;
    }

    public void setNxty(float nxty) {
        this.nxty = nxty;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

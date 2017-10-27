package com.example.bingnanfeng02.privacydetection.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bingnanfeng02.privacydetection.GetBitmap;
import com.example.bingnanfeng02.privacydetection.GetBitmap1;
import com.example.bingnanfeng02.privacydetection.GetBitmap2;
import com.example.bingnanfeng02.privacydetection.R;

public class SendActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String[] dengji={"公开","1级以上","2级以上","3级以上","4级以上","5级","仅自己"};
    GetBitmap getBitmap;
    Bitmap bitmap;
    ImageView img;
    ImageView img_juece;
    int temp;
    int temp2=0;
    private TextView tx_permission;
    private EditText add_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        img=(ImageView)findViewById(R.id.img);
        img_juece=(ImageView)findViewById(R.id.img_juece2);
        tx_permission=(TextView)findViewById(R.id.tx_permission);
        img_juece.setBackgroundResource(R.drawable.jinru);
        add_content=(EditText)findViewById(R.id.add_content);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.send).setOnClickListener(this);
        findViewById(R.id.who).setOnClickListener(this);
        findViewById(R.id.juece).setOnClickListener(this);
        if(getIntent().getIntExtra("sdk",0)==1){
            getBitmap=new GetBitmap1();
        }else {
            getBitmap=new GetBitmap2();
        }
        bitmap=getBitmap.get(this);
        img.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                finish();
                break;
            case R.id.send:
                Toast.makeText(this, "功能暂未开放", Toast.LENGTH_LONG).show();
                break;
            case R.id.who:
                ckeck_premission();
                break;
            case R.id.juece:
                juece();
                break;
        }
    }
    void juece(){
        img_juece.setBackgroundResource(R.drawable.loading);
        final AnimationDrawable animationDrawable=(AnimationDrawable)img_juece.getBackground();
        animationDrawable.start();
        new Handler().postDelayed(new Runnable(){
            public void run() {
                findViewById(R.id.ll_result).setVisibility(View.VISIBLE);
                animationDrawable.stop();
                img_juece.setBackgroundResource(R.drawable.jinru);
            }
        }, 5000);
    }
    void ckeck_premission(){
        temp=temp2;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(dengji,temp2,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                temp2=which;
            }
        });
        builder.setTitle("设置此条朋友圈权限等级").setNegativeButton(
                "取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                temp2=temp;
            }
        });
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       tx_permission.setText(dengji[temp2]);
                    }
                });
        builder.show();
    }
}

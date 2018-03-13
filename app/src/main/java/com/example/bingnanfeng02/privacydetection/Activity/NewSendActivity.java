package com.example.bingnanfeng02.privacydetection.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.bingnanfeng02.privacydetection.GetBitmap1;
import com.example.bingnanfeng02.privacydetection.GetBitmap2;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.Task.SendPyq;
import com.example.bingnanfeng02.privacydetection.View.GetBm;
import com.example.bingnanfeng02.privacydetection.data.SendPyqReturn;
import com.example.bingnanfeng02.privacydetection.util.GetpxOrdp;

import java.util.ArrayList;

public class NewSendActivity extends AppCompatActivity implements View.OnClickListener {
    Handler handler=new Handler(){
        public void handleMessage(Message msg_main) {
            findViewById(R.id.ll_result).setVisibility(View.VISIBLE);
            SendPyqReturn spq=(SendPyqReturn)msg_main.obj;
            ratingBar.setRating(s.getLevel());
            content.setText(s.getContent());
            fenlei.setText(s.getPrivacy());
            advice_group.setText(s.getPrivacy());
            s=spq;
        }
    };
    private static final String[] dengji={"公开","1级以上","2级以上","3级以上","4级以上","5级","仅自己"};
    GetBm getBitmap;
    Bitmap bitmap;
    ImageView img;
    ImageView img_juece;
    int temp;
    int temp2=-1;
    private TextView tx_permission;
    private RatingBar ratingBar;
    private TextView advice_group;
    private EditText add_content;
    private TextView content;
    private TextView fenlei;
    private TextView lookmore;
    private SendPyqReturn s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_send);
        findid();
        setListener();
        if(getIntent().getIntExtra("sdk",0)==1){
            getBitmap=new GetBitmap1();
        }else {
            getBitmap=new GetBitmap2();
        }
        bitmap=getBitmap.get(this, GetpxOrdp.dip2px(this,75),GetpxOrdp.dip2px(this,75));
        img.setImageBitmap(bitmap);
        tx_permission.setText("已开启自动分组");
    }
    void findid(){
        advice_group=(TextView)findViewById(R.id.advice_group);
        img=(ImageView)findViewById(R.id.img);
        img_juece=(ImageView)findViewById(R.id.img_juece2);
        tx_permission=(TextView)findViewById(R.id.tx_permission);
        img_juece.setBackgroundResource(R.drawable.jinru);
        add_content=(EditText)findViewById(R.id.add_content);
        ratingBar=(RatingBar)findViewById(R.id.ratingbar);
        content=(TextView)findViewById(R.id.content);
        fenlei=(TextView)findViewById(R.id.fenlei);
        lookmore=(TextView)findViewById(R.id.lookmore);
    }
    void setListener(){
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.send).setOnClickListener(this);
        findViewById(R.id.who).setOnClickListener(this);
        findViewById(R.id.juece).setOnClickListener(this);
        findViewById(R.id.lookmore).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                finish();
                break;
            case R.id.send:
                send();
                break;
            case R.id.who:
                ckeck_premission();
                break;
            case R.id.juece:
                juece();
                break;
            case R.id.lookmore:
                lookmore();
                break;
        }
    }

    private void send() {
    }
    private void lookmore() {
        Intent i = new Intent(this, ViewImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("urls", (ArrayList<String>) s.getStringList());
        i.putExtras(bundle);
        startActivity(i);
    }
    void juece(){
        SendPyq sendPyq =new SendPyq(bitmap,add_content.getText().toString(),handler,this);
        sendPyq.execute();
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
                        temp=temp2;
                    }
                });
        builder.show();
    }
}

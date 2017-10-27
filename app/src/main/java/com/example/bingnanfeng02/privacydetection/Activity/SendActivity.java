package com.example.bingnanfeng02.privacydetection.Activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bingnanfeng02.privacydetection.GetBitmap;
import com.example.bingnanfeng02.privacydetection.GetBitmap1;
import com.example.bingnanfeng02.privacydetection.GetBitmap2;
import com.example.bingnanfeng02.privacydetection.R;

public class SendActivity extends AppCompatActivity implements View.OnClickListener {
    GetBitmap getBitmap;
    Bitmap bitmap;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        img=(ImageView)findViewById(R.id.img);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.send).setOnClickListener(this);
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
        }
    }
}

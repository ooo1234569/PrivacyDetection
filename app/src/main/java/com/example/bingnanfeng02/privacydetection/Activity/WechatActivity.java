package com.example.bingnanfeng02.privacydetection.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.example.bingnanfeng02.privacydetection.R;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class WechatActivity extends BackActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        initback("发现");
        findViewById(R.id.pyq).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.pyq:
                startActivity(new Intent(WechatActivity.this,PyqActivity.class));
        }
    }
}

package com.example.bingnanfeng02.privacydetection.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.bingnanfeng02.privacydetection.R;


/**
 * Created by bingnanfeng02 on 2017/8/27.
 */
//需要用ToolBar（带返回键的）的Activity继承此Activity，调用initback并传入toolbar标题栏的标题即可设置好toolbar
public class BackActivity extends AppCompatActivity {
    public Toolbar toolbar;

    //初始化toolbar
    void initback(String titile){
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(titile);
        toolbar.setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,R.anim.activity_out);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_in,0);
    }
}

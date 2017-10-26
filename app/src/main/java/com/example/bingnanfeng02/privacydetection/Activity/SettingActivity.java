package com.example.bingnanfeng02.privacydetection.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bingnanfeng02.privacydetection.Adapter.SettingAdapter;
import com.example.bingnanfeng02.privacydetection.R;

public class SettingActivity extends BackActivity {
    private static final String[] texts={"设置隐私规则","管理好友分组","设置服务器IP地址和端口号"};
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initback("设置");
        rv=(RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        SettingAdapter settingAdapter=new SettingAdapter(texts,this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(settingAdapter);
    }
}

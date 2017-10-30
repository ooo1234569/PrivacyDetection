package com.example.bingnanfeng02.privacydetection.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.bingnanfeng02.privacydetection.Adapter.SettingAdapter;
import com.example.bingnanfeng02.privacydetection.MyApplication;
import com.example.bingnanfeng02.privacydetection.R;

public class SettingActivity extends BackActivity implements CompoundButton.OnCheckedChangeListener {
    private static final String[] texts={"设置隐私规则","管理好友分组","设置服务器IP地址和端口号"};
    private RecyclerView rv;
    private Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        aSwitch=(Switch)findViewById(R.id.auto_switch);
        initback("设置");
        rv=(RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        SettingAdapter settingAdapter=new SettingAdapter(texts,this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(settingAdapter);
        aSwitch.setOnCheckedChangeListener(this);
        aSwitch.setChecked(((MyApplication)getApplication()).auto_fill);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences.Editor preferences=getSharedPreferences("auto",MODE_PRIVATE).edit();
        if(isChecked){
            preferences.putString("auto","yes");
            ((MyApplication)getApplication()).change(true);
        }else {
            preferences.putString("auto","no");
            ((MyApplication)getApplication()).change(false);
        }
        preferences.apply();
    }

}

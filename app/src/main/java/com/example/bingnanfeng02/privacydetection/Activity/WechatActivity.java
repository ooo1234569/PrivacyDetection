package com.example.bingnanfeng02.privacydetection.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bingnanfeng02.privacydetection.MyApplication;
import com.example.bingnanfeng02.privacydetection.util.CheckPremission;
import com.example.bingnanfeng02.privacydetection.R;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class WechatActivity extends BackActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        CheckPremission.initPremission(this);
        initback("发现");
        findViewById(R.id.pyq).setOnClickListener(this);
        findViewById(R.id.setting).setOnClickListener(this);
        findViewById(R.id.add_friend).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.pyq:
                startActivity(new Intent(WechatActivity.this,PyqActivity.class));
                break;
            case  R.id.setting:
                startActivity(new Intent(WechatActivity.this,SettingActivity.class));
                break;
            case  R.id.add_friend:
                add_friend();
                break;
        }
    }
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        try {
            if (requestCode == CheckPremission.REQUEST_CODE_PERMISSION) {
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){

                }else{
                    finish();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }


    }
    void add_friend(){
        final View  inputServer= LayoutInflater.from(this).inflate(R.layout.view_add_friend,null);
        //final EditText portet=(EditText)inputServer.findViewById(R.id.et_add_friend);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加好友").setView(inputServer).setNegativeButton(
                "取消", null);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }
}

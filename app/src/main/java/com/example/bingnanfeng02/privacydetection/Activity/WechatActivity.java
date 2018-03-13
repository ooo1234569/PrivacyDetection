package com.example.bingnanfeng02.privacydetection.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.MyApplication;
import com.example.bingnanfeng02.privacydetection.Task.AddFriendTask;
import com.example.bingnanfeng02.privacydetection.Task.DengluTask;
import com.example.bingnanfeng02.privacydetection.data.AddFriendReturn;
import com.example.bingnanfeng02.privacydetection.data.DengluReturn;
import com.example.bingnanfeng02.privacydetection.util.CheckPremission;
import com.example.bingnanfeng02.privacydetection.R;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class WechatActivity extends BackActivity implements View.OnClickListener {
    private int temp=0;
    MyApplication myApplication;
    Handler handler=new Handler(){
        public void handleMessage(Message msg_main) {
            switch (msg_main.arg1){
                case 1:
                    DengluReturn dengluReturn=(DengluReturn)msg_main.obj;
                    if(dengluReturn!=null){
                        Toast.makeText(WechatActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        myApplication.email=dengluReturn.getUsername();
                        myApplication.touxiang=dengluReturn.getAvatar();
                    }else {
                        Toast.makeText(WechatActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    AddFriendReturn addFriendReturn=(AddFriendReturn) msg_main.obj;
                    if(addFriendReturn!=null){
                        Toast.makeText(WechatActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(WechatActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        myApplication=((MyApplication)getApplication());
        CheckPremission.initPremission(this);
        initback("发现");
        findid();
        DengluTask dengluTask=new DengluTask(Constant.email,Constant.password,handler,this);
        dengluTask.execute();
    }
    void findid(){
        findViewById(R.id.pyq).setOnClickListener(this);
        findViewById(R.id.setting).setOnClickListener(this);
        findViewById(R.id.add_friend).setOnClickListener(this);
        findViewById(R.id.change_count).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.pyq:
                if(myApplication.email.equals("")){
                    Toast.makeText(WechatActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(WechatActivity.this,PyqActivity.class));
                }
                break;
            case  R.id.setting:
                startActivity(new Intent(WechatActivity.this,SettingActivity.class));
                break;
            case  R.id.add_friend:
                add_friend();
                break;
            case R.id.change_count:
                change_count();
                break;
        }
    }
    void change_count(){
        final View  inputServer= LayoutInflater.from(this).inflate(R.layout.view_change_account,null);
        final EditText inputid=(EditText)inputServer.findViewById(R.id.idet);
        final EditText password=(EditText)inputServer.findViewById(R.id.password);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("切换账号").setView(inputServer).setNegativeButton(
                "取消", null);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(!inputid.getText().toString().equals("")&&!password.getText().toString().equals("")){
                            DengluTask dengluTask=new DengluTask(inputid.getText().toString(),password.getText().toString(),handler,WechatActivity.this);
                            dengluTask.execute();
                        }

                    }
                });
        builder.show();
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
        final EditText inputid=(EditText)inputServer.findViewById(R.id.idet);
        final EditText permiset=(EditText)inputServer.findViewById(R.id.permiset);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加好友").setView(inputServer).setNegativeButton(
                "取消", null);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(!inputid.getText().toString().equals("")&&!permiset.getText().toString().equals("")){
                            AddFriendTask addFriendTask=new AddFriendTask(WechatActivity.this,handler,inputid.getText().toString());
                            addFriendTask.execute();
                        }
                    }
                });
        builder.show();
    }
}

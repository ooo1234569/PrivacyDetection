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

import com.example.bingnanfeng02.privacydetection.util.BaseHandler;
import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.MyApplication;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.Task.LoginTask;
import com.example.bingnanfeng02.privacydetection.Task.Task;
import com.example.bingnanfeng02.privacydetection.data.DengluReturn;
import com.example.bingnanfeng02.privacydetection.util.CheckPremission;

import java.util.HashMap;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class WechatActivity extends BackActivity implements View.OnClickListener,BaseHandler.BaseHandlerCallBack {
    private int temp=0;
    MyApplication myApplication;
    private String TAG="test";
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        handler=new BaseHandler<>(this);
        myApplication=((MyApplication)getApplication());
        CheckPremission.initPremission(this);
        initback("发现");
        findid();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(myApplication.email.equals("")){
            LoginTask loginTask =new LoginTask(Constant.email,Constant.password,handler,this);
            loginTask.execute();
        }
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
                    Constant.show("登录失败");
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
                            LoginTask loginTask =new LoginTask(inputid.getText().toString(),password.getText().toString(),handler,WechatActivity.this);
                            loginTask.execute();
                        }

                    }
                });
        builder.show();
    }
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        try {
            if (requestCode == CheckPremission.REQUEST_CODE_PERMISSION) {
                if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){

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
                            HashMap<String,String> hashMap=new HashMap<String, String>();
                            hashMap.put("username",inputid.getText().toString());
                            hashMap.put("level",permiset.getText().toString());
                            Task task=new Task();
                            task.execute(this,handler,hashMap,null,Constant.addfriend,2,"addfriend");
                        }
                    }
                });
        builder.show();
    }

    @Override
    public void callBack(Message msg_main) {
        switch (msg_main.arg1){
            case 1:
                DengluReturn dengluReturn=(DengluReturn)msg_main.obj;
                if(dengluReturn!=null){
                    Constant.show("登录成功");
                    myApplication.email=dengluReturn.getUsername();
                    myApplication.touxiang=dengluReturn.getAvatar();
                }else {
                    Constant.show("登录失败");
                }
                break;
            case 2:
                if(msg_main.obj.equals("success")){
                    Constant.show("添加成功");
                }else {
                    Constant.show("添加失败");
                }
                break;
        }
    }
}

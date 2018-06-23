package com.example.bingnanfeng02.privacydetection.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bingnanfeng02.privacydetection.Adapter.ManageFriendAdapter;
import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.Task.Task;
import com.example.bingnanfeng02.privacydetection.data.CCFriends;
import com.example.bingnanfeng02.privacydetection.util.BaseHandler;

public class ManageFriendActivity extends BackActivity implements BaseHandler.BaseHandlerCallBack{
    RecyclerView recyclerView;
    ManageFriendAdapter manageFriendAdapter;
    CCFriends ccFriends;
    public Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_friend);
        handler= new BaseHandler(this);
        initback("管理好友分组");
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    void init(){
        ccFriends=new CCFriends();
        Task task=new Task();
        task.execute(this,handler,null, ccFriends,Constant.managefriend,1,"managefriend");
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }


    @Override
    public void callBack(Message msg_main) {
        switch (msg_main.arg1){
            case 1:
                if(msg_main!=null&&msg_main.obj!=null){
                    ccFriends=(CCFriends)msg_main.obj;
                    manageFriendAdapter=new ManageFriendAdapter(ccFriends.getFriends(),ManageFriendActivity.this);
                    recyclerView.setAdapter(manageFriendAdapter);
                }else {
                    Constant.show("获取失败");
                }
                break;
            case 2:
                if(((String)msg_main.obj).equals("success")){
                    Constant.show("修改成功");
                    init();
                }else {
                    Constant.show("修改失败");
                }
                break;
        }
    }
}

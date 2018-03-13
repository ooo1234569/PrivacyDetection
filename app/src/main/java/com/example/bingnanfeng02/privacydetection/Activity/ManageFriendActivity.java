package com.example.bingnanfeng02.privacydetection.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bingnanfeng02.privacydetection.Adapter.ManageFriendAdapter;
import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.MyApplication;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.Task.Task;
import com.example.bingnanfeng02.privacydetection.data.AddFriendReturn;
import com.example.bingnanfeng02.privacydetection.data.CheckFriend;
import com.example.bingnanfeng02.privacydetection.data.DengluReturn;

import java.util.List;

public class ManageFriendActivity extends BackActivity {
    RecyclerView recyclerView;
    ManageFriendAdapter manageFriendAdapter;
    List<CheckFriend> checkFriends;
    public Handler handler=new Handler(){
        public void handleMessage(Message msg_main) {
            switch (msg_main.arg1){
                case 1:
                    if(msg_main!=null){
                        checkFriends=(List<CheckFriend>)msg_main.obj;
                        manageFriendAdapter=new ManageFriendAdapter(checkFriends,ManageFriendActivity.this);
                        recyclerView.setAdapter(manageFriendAdapter);
                    }else {
                        Toast.makeText(ManageFriendActivity.this,"获取失败",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:

                    break;
            }


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_friend);
        initback("管理好友分组");
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Task task=new Task();
        task.execute(this,handler,null, checkFriends,Constant.managefriend,"managefriend");
    }
}

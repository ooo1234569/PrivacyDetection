package com.example.bingnanfeng02.privacydetection.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bingnanfeng02.privacydetection.Adapter.ManageFriendAdapter;
import com.example.bingnanfeng02.privacydetection.MyApplication;
import com.example.bingnanfeng02.privacydetection.R;

public class ManageFriendActivity extends BackActivity {
    RecyclerView recyclerView;
    ManageFriendAdapter manageFriendAdapter;
    //private static final String[] friend={"炳楠","可俊"};
    private static final int[] permission={1,5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_friend);
        initback("管理好友分组");
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        manageFriendAdapter=new ManageFriendAdapter(((MyApplication)getApplication()).friend,((MyApplication)getApplication()).perimission,this);
        recyclerView.setAdapter(manageFriendAdapter);
    }
}

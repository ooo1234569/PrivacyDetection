package com.example.bingnanfeng02.privacydetection.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.bingnanfeng02.privacydetection.Adapter.SetRuleAdapter;
import com.example.bingnanfeng02.privacydetection.util.BaseHandler;
import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.Task.Task;
import com.example.bingnanfeng02.privacydetection.data.PPPrule;
import com.example.bingnanfeng02.privacydetection.data.PrivacyRule;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bingnanfeng02 on 2017/11/24.
 */

public class SetRuleActivity extends BackActivity {
    private RecyclerView recyclerView;
    public PPPrule ppPrule;
    private SetRuleAdapter setRuleAdapter;
    public Handler handler=new BaseHandler<>(new BaseHandler.BaseHandlerCallBack(){
        @Override
        public void callBack(Message msg_main) {
            switch (msg_main.arg1){
                case 1:
                    if(msg_main!=null&&msg_main.obj!=null){
                            ppPrule=(PPPrule) msg_main.obj;
                            Constant.show("获取成功");
                            setRuleAdapter=new SetRuleAdapter((ArrayList<PrivacyRule>) ppPrule.getRules(),SetRuleActivity.this);
                            recyclerView.setAdapter(setRuleAdapter);
                    }else {
                        Constant.show("获取失败");
                    }
                    break;
                case 2:
                    if(msg_main.obj.equals("success")){
                        Constant.show("添加成功");
                        initdata();

                    }else {
                        Constant.show("添加失败");
                    }
                    break;
                case 3:
                    if(msg_main.obj.equals("success")){
                        Constant.show("修改成功");
                        initdata();

                    }else {
                        Constant.show("修改失败");
                    }
                    break;
                case 4:
                    if(msg_main.obj.equals("success")){
                        Constant.show("删除成功");
                        initdata();
                    }else {
                        Constant.show("删除失败");
                    }
                    break;
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_privacy_rule);
        initback("管理隐私规则");
        ppPrule=new PPPrule();
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(setRuleAdapter);
        findViewById(R.id.addrule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addrule();
            }
        });
    }
    void initdata(){
        Task task=new Task();
        task.execute(this,handler,null,ppPrule, Constant.getrule,1,"getrule");
    }
    void addrule(){
        final View  inputServer= LayoutInflater.from(this).inflate(R.layout.view_add_rule,null);
        final EditText inputid=(EditText)inputServer.findViewById(R.id.ruleet);
        final EditText permiset=(EditText)inputServer.findViewById(R.id.permiset);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("添加规则").setView(inputServer).setNegativeButton(
                "取消", null);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(!inputid.getText().toString().equals("")&&!permiset.getText().toString().equals("")){
                            HashMap<String,String> hashMap=new HashMap<String, String>();
                            hashMap.put("title",inputid.getText().toString());
                            hashMap.put("level",permiset.getText().toString());
                            Task task=new Task();
                            task.execute(this,handler,hashMap,null,Constant.addrule,2,"addrule");
                        }
                    }
                });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initdata();
    }
}

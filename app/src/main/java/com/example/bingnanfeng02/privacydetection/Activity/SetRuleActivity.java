package com.example.bingnanfeng02.privacydetection.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bingnanfeng02.privacydetection.Adapter.SetRuleAdapter;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.data.Rule;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/11/24.
 */

public class SetRuleActivity extends BackActivity {
    private RecyclerView recyclerView;
    private ArrayList<Rule> rules=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_privacy_rule);
        initback("管理隐私规则");
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        initdata();
        SetRuleAdapter setRuleAdapter=new SetRuleAdapter(rules,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(setRuleAdapter);
    }
    void initdata(){
        Rule rule=new Rule();
        rule.setObj1("person");
        rule.setPerdicate("behindof");
        rule.setObj2("person");
        rule.setLevel(2);
        rules.add(rule);
        Rule rule2=new Rule();
        rule2.setObj1("kid");
        rule2.setPerdicate("behindof");
        rule2.setObj2("house");
        rule2.setLevel(5);
        rules.add(rule2);
    }
}

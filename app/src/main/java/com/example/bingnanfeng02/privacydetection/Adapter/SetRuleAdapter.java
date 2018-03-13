package com.example.bingnanfeng02.privacydetection.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.data.Rule;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/11/24.
 */

public class SetRuleAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Rule> rules;
    Context context;
     public SetRuleAdapter(ArrayList<Rule> rules, Context context){
        this.rules=rules;
         this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_privacy_rule,null,false);
        RuleView ruleView=new RuleView(view);
        return ruleView;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Rule rule=rules.get(position);
        ((RuleView)holder).obj1.setText(rule.getObj1());
        ((RuleView)holder).predicate.setText(rule.getPerdicate());
        ((RuleView)holder).obj2.setText(rule.getObj2());
        ((RuleView)holder).level.setText(rule.getLevel()+"级以上");
        Log.d("sssss","1124");
    }

    @Override
    public int getItemCount() {
        return rules.size();
    }
    class RuleView extends RecyclerView.ViewHolder {
        TextView obj1;
        TextView predicate;
        TextView obj2;
        TextView level;
        public RuleView(View itemView) {
            super(itemView);
            obj1=(TextView)itemView.findViewById(R.id.obj1);
            predicate=(TextView)itemView.findViewById(R.id.predicate);
            obj2=(TextView)itemView.findViewById(R.id.obj2);
            level=(TextView)itemView.findViewById(R.id.level);
        }
    }
}

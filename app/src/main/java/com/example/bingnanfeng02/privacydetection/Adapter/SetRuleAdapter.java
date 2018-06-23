package com.example.bingnanfeng02.privacydetection.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bingnanfeng02.privacydetection.Activity.SetRuleActivity;
import com.example.bingnanfeng02.privacydetection.CallBack.SelectPositionListener;
import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.Task.Task;
import com.example.bingnanfeng02.privacydetection.View.SelectView;
import com.example.bingnanfeng02.privacydetection.data.PrivacyRule;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bingnanfeng02 on 2017/11/24.
 */

public class SetRuleAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SelectPositionListener{
    ArrayList<PrivacyRule> rules;
    Context context;
    int temp;
    SelectView selectView;
     public SetRuleAdapter(ArrayList<PrivacyRule> rules, Context context){
        this.rules=rules;
         this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(context).inflate(R.layout.item_privacy_rule,null,false);
        final RuleView ruleView=new RuleView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updaterule(ruleView.getAdapterPosition());
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                temp=rules.get(ruleView.getAdapterPosition()).getId();
                selectView=new SelectView(context,Constant.TEXTS);
                selectView.setListener(SetRuleAdapter.this);
                selectView.setView();
                selectView.showAtLocation(((SetRuleActivity)context).findViewById(R.id.rl), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                return true;
            }
        });
        return ruleView;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PrivacyRule rule=rules.get(position);
        ((RuleView)holder).obj1.setText(rule.getTitle());
//        ((RuleView)holder).predicate.setText(rule.getPerdicate());
//        ((RuleView)holder).obj2.setText(rule.getObj2());
        ((RuleView)holder).level.setText(rule.getLevel()+"级以上");
    }

    @Override
    public int getItemCount() {
        return rules.size();
    }
    void updaterule(final int position){
        final View  inputServer= LayoutInflater.from(context).inflate(R.layout.view_add_rule,null);
        final EditText inputid=(EditText)inputServer.findViewById(R.id.ruleet);
        final EditText permiset=(EditText)inputServer.findViewById(R.id.permiset);
        inputid.setText(rules.get(position).getTitle());
        permiset.setText(rules.get(position).getLevel()+"");
        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle("修改规则").setView(inputServer).setNegativeButton(
                "取消", null);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(!inputid.getText().toString().equals("")&&!permiset.getText().toString().equals("")){
                            HashMap<String,String> hashMap=new HashMap<String, String>();
                            hashMap.put("title",inputid.getText().toString());
                            hashMap.put("level",permiset.getText().toString());
                            hashMap.put("id",String.valueOf(rules.get(position).getId()));
                            Task task=new Task();
                            task.execute(this,((SetRuleActivity)context).handler,hashMap,null, Constant.updaterule,3,"updaterule");
                        }
                    }
                });
        builder.show();

    }

    @Override
    public void positionSelected(int position) {
        if(selectView.isShowing()){
            selectView.dismiss();
        }if(position!=-1){
            Task task=new Task();
            task.setdelete();
            Log.d("ss","bug");
            task.execute(context,((SetRuleActivity)context).handler,null,null,Constant.deleterule+temp,4,"deleterule");
        }

    }

    class RuleView extends RecyclerView.ViewHolder {
        TextView obj1;
        TextView predicate;
        TextView obj2;
        TextView level;
        public RuleView(View itemView) {
            super(itemView);
            obj1=(TextView)itemView.findViewById(R.id.obj1);
//            predicate=(TextView)itemView.findViewById(R.id.predicate);
//            obj2=(TextView)itemView.findViewById(R.id.obj2);
            level=(TextView)itemView.findViewById(R.id.detected_level);
        }
    }
}

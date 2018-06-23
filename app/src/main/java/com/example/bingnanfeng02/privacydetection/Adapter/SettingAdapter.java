package com.example.bingnanfeng02.privacydetection.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bingnanfeng02.privacydetection.Activity.ManageFriendActivity;
import com.example.bingnanfeng02.privacydetection.Activity.SetRuleActivity;
import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.MyApplication;
import com.example.bingnanfeng02.privacydetection.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fengbingnan on 2017/10/26.
 */

public class SettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private String[] texts;
    private Context context;
    public SettingAdapter(String[] texts, Context context){
        this.texts=texts;
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_setting,null,false);
        final Setting setting=new Setting(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=setting.getAdapterPosition();
                switch (i){
                    case 0:
                        context.startActivity(new Intent(context, SetRuleActivity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, ManageFriendActivity.class));
                        break;
                    case 2:
                        inputTitleDialog();
                        break;
                }
            }
        });
        return setting;
    }

    private void inputTitleDialog() {
        final View  inputServer=(LinearLayout)((Activity) context).getLayoutInflater().inflate(R.layout.view_input_ip_and_port,null);
        final EditText ipet=(EditText)inputServer.findViewById(R.id.ipet);
        final EditText portet=(EditText)inputServer.findViewById(R.id.portet);
        ipet.setText(((MyApplication)((Activity) context).getApplication()).ip);
        portet.setText(((MyApplication)((Activity) context).getApplication()).port);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("设置IP地址和端口号").setView(inputServer).setNegativeButton(
                "取消", null);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(!ipet.getText().toString().equals("")&&!portet.getText().toString().equals("")){
                            SharedPreferences.Editor editor=context.getSharedPreferences("data",MODE_PRIVATE).edit();
                            Constant.ipp=ipet.getText().toString();
                            Constant.ip="http://"+Constant.ipp;
                            Constant.port=Integer.valueOf(portet.getText().toString());
                            ((MyApplication)((Activity) context).getApplication()).ip=Constant.ipp;
                            ((MyApplication)((Activity) context).getApplication()).port=Constant.port+"";
                            editor.putString("sendweibo",((MyApplication)((Activity) context).getApplication()).ip);
                            editor.putString("port",((MyApplication)((Activity) context).getApplication()).port);
                            editor.apply();
                            Constant.sendweibo=Constant.ip+":"+Constant.port+"/user/microblog/send";
                            Constant.denglu =Constant.ip+":"+Constant.port+"/auth/login";
                            Constant.dengchu =Constant.ip+":"+Constant.port+"/auth/logout";
                            Constant.addfriend =Constant.ip+":"+Constant.port+"/user/friend/add";
                            Constant.managefriend=Constant.ip+":"+Constant.port+"/user/friend/all";
                            Constant.updatepremi=Constant.ip+":"+Constant.port+"/user/friend/level";
                            Constant.checkpyq=Constant.ip+":"+Constant.port+"/user/friend/microblog";
                            Constant.detect=Constant.ip+":"+Constant.port+"/user/microblog/detect";
                            Constant.wangzhi= Constant.ip +":"+Constant.port;
                            Constant.sendpyq=Constant.ip+":"+Constant.port+"/user/microblog/send/";
                            Constant.addrule=Constant.ip+":"+Constant.port+"/user/rule/add";
                            Constant.getrule=Constant.ip+":"+Constant.port+"/user/rule/all";
                            Constant.deleterule=Constant.ip+":"+Constant.port+"/user/rule/remove/";
                            Constant.updaterule=Constant.ip+":"+Constant.port+"/user/rule/update";
                        }


                    }
                });
        builder.show();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof Setting ){
            ((Setting)holder).text.setText(texts[position]);
        }

    }

    @Override
    public int getItemCount() {
        return texts.length;
    }
    class Setting extends RecyclerView.ViewHolder {
        TextView text;
        public Setting(View itemView) {
            super(itemView);
            text=(TextView)itemView.findViewById(R.id.text);
        }
    }
}

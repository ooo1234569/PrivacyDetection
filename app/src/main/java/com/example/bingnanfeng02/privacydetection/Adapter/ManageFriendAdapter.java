package com.example.bingnanfeng02.privacydetection.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bingnanfeng02.privacydetection.MyApplication;
import com.example.bingnanfeng02.privacydetection.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by bingnanfeng02 on 2017/10/26.
 */

public class ManageFriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String[] dengji={"0","1","2","3","4","5"};
    String[] friends;
    Context context;
    int[][] permission;
    int temp;
    ArrayList<String> strings=new ArrayList<>();
    ArrayList<Integer> integers=new ArrayList<>();
    public ManageFriendAdapter(String[] friends,int[][] permission, Context context){
        this.friends=friends;
        this.permission=permission;
        this.context=context;
        for(int i=0;i<friends.length;i++){
            if(friends[i]==((MyApplication)((Activity)context).getApplication()).friend[((MyApplication)((Activity)context).getApplication()).i]){
                continue;
            }
            strings.add(friends[i]);
            integers.add(i);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_manage_friend,null,false);
        final Friend friend=new Friend(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     inputpremission(friend.getAdapterPosition());
            }
        });
        return friend;
    }

    void inputpremission(final int i){
        temp=i;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setSingleChoiceItems(dengji,permission[((MyApplication)((Activity) context).getApplication()).i][integers.get(i)],new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                temp=which;
            }
        });
        builder.setTitle("设置"+friends[i]+"的朋友圈权限等级").setNegativeButton(
                "取消", null);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(i!=temp){
                            permission[((MyApplication)((Activity) context).getApplication()).i][integers.get(i)]=temp;
                            notifyDataSetChanged();
                        }
                    }
                });
        builder.show();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         Glide.with(context).load(((MyApplication)((Activity)context).getApplication()).headimg[integers.get(position)]).into(((Friend)holder).headimg);
        ((Friend)holder).name.setText(strings.get(position));
        ((Friend)holder).permission.setText(permission[((MyApplication)((Activity) context).getApplication()).i][integers.get(position)]+"级");
    }

    @Override
    public int getItemCount() {
        return permission.length-1;
    }
    class Friend extends RecyclerView.ViewHolder {
        TextView name;
        TextView permission;
        ImageView headimg;
        public Friend(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            permission=(TextView)itemView.findViewById(R.id.permission);
            headimg=(ImageView)itemView.findViewById(R.id.img_headimg);
        }
    }
}

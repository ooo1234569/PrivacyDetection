package com.example.bingnanfeng02.privacydetection.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bingnanfeng02.privacydetection.R;

/**
 * Created by bingnanfeng02 on 2017/10/26.
 */

public class ManageFriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] friends;
    Context context;
    int[] permission;
    public ManageFriendAdapter(String[] friends,int[] permission, Context context){
        this.friends=friends;
        this.permission=permission;
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_manage_friend,null,false);
        Friend friend=new Friend(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return friend;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Friend)holder).name.setText(friends[position]);
        ((Friend)holder).permission.setText(permission[position]+"级");
    }

    @Override
    public int getItemCount() {
        return friends.length;
    }
    class Friend extends RecyclerView.ViewHolder {
        TextView name;
        TextView permission;
        public Friend(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            permission=(TextView)itemView.findViewById(R.id.permission);
        }
    }
}

package com.example.bingnanfeng02.privacydetection.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bingnanfeng02.privacydetection.Activity.ManageFriendActivity;
import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.Task.Task;
import com.example.bingnanfeng02.privacydetection.data.CheckFriend;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bingnanfeng02 on 2017/10/26.
 */

public class ManageFriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    int temp;
    List<CheckFriend> friends;
    public ManageFriendAdapter(List<CheckFriend> friends, Context context){
        this.friends=friends;
        this.context=context;
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
        final EditText editText=new EditText(context);
        editText.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(editText);
        builder.setTitle("设置"+friends.get(i).getUsername()+"的朋友圈权限等级").setNegativeButton(
                "取消", null);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                            if(!editText.getText().toString().equals("")){
                                Task task=new Task();
                                HashMap<String,String> hashMap=new HashMap<String, String>();
                                hashMap.put("id",friends.get(i).getId()+"");
                                hashMap.put("level",editText.getText().toString());
                                task.execute(context,((ManageFriendActivity)context).handler,hashMap,null,Constant.updatepremi,2,"updatepremi");
                        }
                    }
                });
        builder.show();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         Glide.with(context).load(Constant.wangzhi+friends.get(position).getAvatar()).into(((Friend)holder).headimg);
        ((Friend)holder).name.setText(friends.get(position).getUsername());
        ((Friend)holder).permission.setText(friends.get(position).getLevel()+"");
    }

    @Override
    public int getItemCount() {
        return friends.size();
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

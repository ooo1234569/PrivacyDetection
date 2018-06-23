package com.example.bingnanfeng02.privacydetection.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bingnanfeng02.privacydetection.Activity.ViewImageActivity;
import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.MyApplication;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.data.Pyq;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class PyqAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Pyq> pyqs;
    int flag;
    public PyqAdapter(Context context, List<Pyq> pyqs,int flag){
        this.context=context;
        this.pyqs=pyqs;
        this.flag=flag;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(flag == 0){
            if(viewType==0){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pyq_head,parent,false);
                Aboutme aboutme=new Aboutme(view);
                return aboutme;
            }else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pyq,parent,false);
                final Pyq2 pyq2=new Pyq2(view);
                pyq2.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, ViewImageActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("urls", (ArrayList)pyqs.get(pyq2.getAdapterPosition()-1).getImages());
                        i.putExtras(bundle);
                        context.startActivity(i);
                    }
                });
                return pyq2;
            }
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pass_pyq,parent,false);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.width=(int)(getScreenWidth()*0.8);
            view.setLayoutParams(params);
            Pyq2 pyq2=new Pyq2(view);
            return pyq2;
        }

    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if(holder instanceof Pyq2){
            ((Pyq2)holder).img.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("sss",position+"");
          if(holder instanceof Aboutme){
              Glide.with(context).load(Constant.wangzhi+((MyApplication)((Activity)context).getApplication()).touxiang).into(((Aboutme)holder).bg);
              Glide.with(context).load(Constant.wangzhi+((MyApplication)((Activity)context).getApplication()).touxiang).into(((Aboutme)holder).headimg);
              ((Aboutme)holder).myname.setText(((MyApplication)((Activity)context).getApplication()).email);
          }else {
              if(pyqs.get(position+flag-1).getImages().size()!=0){
                  Glide.with(context).load(Constant.wangzhi+pyqs.get(position+flag-1).getImages().get(0)).into(((Pyq2)holder).img);
              }else {
                  ((Pyq2)holder).img.setVisibility(View.GONE);
              }
              Glide.with(context).load(Constant.wangzhi+pyqs.get(position+flag-1).getAvatar()).into(((Pyq2)holder).headimg);
              ((Pyq2)holder).name.setText(pyqs.get(position+flag-1).getUsername());
              ((Pyq2)holder).text.setText(pyqs.get(position+flag-1).getText());
              ((Pyq2) holder).permission.setText(pyqs.get(position + flag - 1).getLevel() + "级权限");
              ((Pyq2) holder).time.setText(pyqs.get(position + flag - 1).getPublished_time()+"");
              ((Pyq2) holder).permission.setVisibility(View.VISIBLE);

          }
    }

    @Override
    public int getItemCount() {
        return pyqs.size()+1-flag;
    }

    @Override
    public int getItemViewType(int position) {
        if(flag ==0){
            if(position==0){
                return 0;
            }else {
                return 1;
            }
        }else {
            return 1;
        }

    }
    class Pyq2 extends RecyclerView.ViewHolder {
        ImageView headimg;
        ImageView img;
        TextView text;
        TextView name;
        TextView permission;
        TextView time;
        public Pyq2(View itemView) {
            super(itemView);
            headimg=(ImageView)itemView.findViewById(R.id.headimg);
            img=(ImageView)itemView.findViewById(R.id.img);
            name=(TextView)itemView.findViewById(R.id.username);
            text=(TextView)itemView.findViewById(R.id.text);
            permission=(TextView)itemView.findViewById(R.id.detected_level);
            time=(TextView)itemView.findViewById(R.id.time);
        }
    }
    class Aboutme extends RecyclerView.ViewHolder {
        ImageView bg;
        ImageView headimg;
        TextView myname;
        public Aboutme(View itemView) {
            super(itemView);
            headimg=(ImageView)itemView.findViewById(R.id.headimg);
            bg=(ImageView)itemView.findViewById(R.id.bg);
            myname=(TextView)itemView.findViewById(R.id.myname);
        }
    }
    public int getScreenWidth() {
        Point point = new Point();
        ((Activity)context).getWindowManager().getDefaultDisplay().getSize(point);
        return point.x;
    }
}

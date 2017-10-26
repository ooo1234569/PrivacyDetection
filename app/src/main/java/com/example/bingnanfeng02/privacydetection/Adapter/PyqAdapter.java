package com.example.bingnanfeng02.privacydetection.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bingnanfeng02.privacydetection.R;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class PyqAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    public PyqAdapter(Context context){
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pyq_head,parent,false);
            Aboutme aboutme=new Aboutme(view);
            return aboutme;
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pyq,parent,false);
            Pyq pyq=new Pyq(view);
            return pyq;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          if(holder instanceof Aboutme){
              Glide.with(context).load(R.drawable.img_headimg).into(((Aboutme)holder).bg);
              Glide.with(context).load(R.drawable.img_headimg).into(((Aboutme)holder).headimg);
          }else {
              Glide.with(context).load(R.drawable.img_headimg).into(((Pyq)holder).img);
              Glide.with(context).load(R.drawable.img_headimg).into(((Pyq)holder).headimg);
          }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else {
            return 1;
        }
    }
    class Pyq extends RecyclerView.ViewHolder {
        ImageView headimg;
        ImageView img;
        TextView text;
        TextView name;
        public Pyq(View itemView) {
            super(itemView);
            headimg=(ImageView)itemView.findViewById(R.id.headimg);
            img=(ImageView)itemView.findViewById(R.id.img);
        }
    }
    class Aboutme extends RecyclerView.ViewHolder {
        ImageView bg;
        ImageView headimg;
        public Aboutme(View itemView) {
            super(itemView);
            headimg=(ImageView)itemView.findViewById(R.id.headimg);
            bg=(ImageView)itemView.findViewById(R.id.bg);
        }
    }
}

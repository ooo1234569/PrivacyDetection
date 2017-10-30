package com.example.bingnanfeng02.privacydetection.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bingnanfeng02.privacydetection.MyApplication;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.Task.GetBitmapTask;
import com.example.bingnanfeng02.privacydetection.data.Pyq;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class PyqAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Pyq> pyqs;
    public PyqAdapter(Context context, ArrayList<Pyq> pyqs){
        this.context=context;
        this.pyqs=pyqs;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pyq_head,parent,false);
            Aboutme aboutme=new Aboutme(view);
            return aboutme;
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pyq,parent,false);
            Pyq2 pyq2=new Pyq2(view);
            return pyq2;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          if(holder instanceof Aboutme){
              Glide.with(context).load(((MyApplication)((Activity)context).getApplication()).headimg[((MyApplication)((Activity)context).getApplication()).i]).into(((Aboutme)holder).bg);
              Glide.with(context).load(((MyApplication)((Activity)context).getApplication()).headimg[((MyApplication)((Activity)context).getApplication()).i]).into(((Aboutme)holder).headimg);
              ((Aboutme)holder).myname.setText(((MyApplication)((Activity)context).getApplication()).friend[((MyApplication)((Activity)context).getApplication()).i]);
          }else {
              if(pyqs.get(position-1).istest()){
                  Glide.with(context).load(R.drawable.img_headimg).into(((Pyq2)holder).img);
              }else {
                  ByteArrayOutputStream baos = new ByteArrayOutputStream();
                  Bitmap bitmap=GetBitmapTask.returnBitmap();
                  if(bitmap==null){
                      bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/a.jpg");
                  }
                  bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                  byte[] bytes=baos.toByteArray();
                  Glide.with(context)
                          .load(bytes)
                          .into(((Pyq2)holder).img);
              }
              Glide.with(context).load(((MyApplication)((Activity)context).getApplication()).headimg[pyqs.get(position-1).getId()]).into(((Pyq2)holder).headimg);
              ((Pyq2)holder).name.setText(pyqs.get(position-1).getName());
              ((Pyq2)holder).text.setText(pyqs.get(position-1).getText());
              if(((MyApplication)((Activity)context).getApplication()).i==pyqs.get(position-1).getId()){
                  ((Pyq2)holder).permission.setText(pyqs.get(position-1).getPermission()+"级权限");
                  ((Pyq2)holder).permission.setVisibility(View.VISIBLE);
              }
          }
    }

    @Override
    public int getItemCount() {
        return pyqs.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
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
        public Pyq2(View itemView) {
            super(itemView);
            headimg=(ImageView)itemView.findViewById(R.id.headimg);
            img=(ImageView)itemView.findViewById(R.id.img);
            name=(TextView)itemView.findViewById(R.id.name);
            text=(TextView)itemView.findViewById(R.id.text);
            permission=(TextView)itemView.findViewById(R.id.permission);
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
}

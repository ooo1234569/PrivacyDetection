package com.example.bingnanfeng02.privacydetection.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.bingnanfeng02.privacydetection.Activity.ViewImageActivity;
import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.R;


import java.util.ArrayList;


/**
 * Created by bingnanfeng02 on 2017/7/26.
 */
public class ViewImageAdapter extends PagerAdapter {
    private ArrayList<String> url;
    private Context context;
    public ViewImageAdapter(ArrayList<String> url, Context context){
        this.url=url;
        this.context=context;
    }
    @Override
    public int getCount() {
        return url.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView view = new PhotoView(context);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewImageActivity)context).finish();
            }
        });
        view.enable();
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
       // view.setImageResource(R.drawable.anzhuang);
                Glide.with(context)
                .load(Constant.wangzhi+url.get(position))
                .crossFade()
                .into(view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

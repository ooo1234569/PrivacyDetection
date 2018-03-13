package com.example.bingnanfeng02.privacydetection.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.example.bingnanfeng02.privacydetection.Adapter.ViewImageAdapter;
import com.example.bingnanfeng02.privacydetection.R;

import java.util.ArrayList;


/**
 * Created by bingnanfeng02 on 2017/7/26.
 */
public class ViewImageActivity extends AppCompatActivity{
    private ViewPager viewPager;
    private ArrayList<String> imageUrls=new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery2);
        viewPager=(ViewPager)findViewById(R.id.vp);
        Bundle bundle = this.getIntent().getExtras();
        viewPager.setAdapter(new ViewImageAdapter(imageUrls=bundle.getStringArrayList("urls") ,this));
    }
}

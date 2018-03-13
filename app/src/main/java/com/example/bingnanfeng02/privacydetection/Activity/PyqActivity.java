package com.example.bingnanfeng02.privacydetection.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.example.bingnanfeng02.privacydetection.Adapter.PyqAdapter;
import com.example.bingnanfeng02.privacydetection.CallBack.SelectPositionListener;
import com.example.bingnanfeng02.privacydetection.MyApplication;
import com.example.bingnanfeng02.privacydetection.data.Pyq;
import com.example.bingnanfeng02.privacydetection.util.Photo;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.View.SelectView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class PyqActivity extends BackActivity implements View.OnClickListener, SelectPositionListener {
    SelectView selectView;
    private static final String[] TEXTS={"拍照","相册"};
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Photo photo;
    private ArrayList<Pyq> pyqs=new ArrayList<>();
    //private MyApplication myApplication;
    private PyqAdapter pyqAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pyq);
        //myApplication=(MyApplication)getApplication();
        photo=new Photo(this);
        initback("朋友圈");
        initsenddata();
        inittestdata();
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
//        pyqAdapter=new PyqAdapter(this,pyqs,0);
//        recyclerView.setAdapter(pyqAdapter);
        findViewById(R.id.camera).setOnClickListener(this);
    }
    void initsenddata(){
//        SharedPreferences pref=getSharedPreferences("pyq",MODE_PRIVATE);
//        Pyq pyq=new Pyq();
//        pyq.setName(pref.getString("name",""));
//        if(pyq.getName().equals("")){
//
//        }else {
//            pyq.setText(pref.getString("text",""));
//            pyq.setPermission(Integer.valueOf(pref.getString("permission","0")));
//            pyq.setId(Integer.valueOf(pref.getString("id","0")));
//            if(!pyq.getName().equals(myApplication.friend[((MyApplication)getApplication()).i])){
//                if(myApplication.perimission[pyq.getId()][myApplication.i]>pyq.getPermission()){
//                    pyqs.add(pyq);
//                }
//            }else {
//                pyqs.add(pyq);
//            }
//        }
    }
    void inittestdata(){
//        for(int i=0;i<5;i++){
//            Pyq pyq=new Pyq();
//            pyq.setName("Alice");
//            pyq.setText("test");
//            pyq.setPermission(0);
//            pyq.setId(0);
//            pyq.setIstest(true);
//            pyqs.add(pyq);
//        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.camera:
                selectView = new SelectView(this,TEXTS);
                selectView.setPositionListener(this);
                selectView.setView();
                selectView.showAtLocation(findViewById(R.id.ll), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    @Override
    public void positionSelected(int position) {
        if(selectView.isShowing()){
            selectView.dismiss();
        }
        switch (position){
            case 0:
                photo.takePhoto();
                break;
            case 1:
                photo.openAlbum();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imgpath;
        Intent result=new Intent(this,NewSendActivity.class);
        if (requestCode == photo.REQUEST_CAMERA) {
            result.putExtra("uri",photo.iamgeuri);
            result.putExtra("sdk",1);
            try {
                if(getContentResolver().openInputStream(photo.iamgeuri)!=null){
                    startActivity(result);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if(requestCode==photo.OPEN_ALBUM){
            if(Build.VERSION.SDK_INT>=19){
                if(data!=null){
                   imgpath=photo.handleimage(data);
                    result.putExtra("uri",imgpath);
                    startActivity(result);
                }
            }
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    void refresh(){
//        pyqs.clear();
//        initsenddata();
//        inittestdata();
//        pyqAdapter.notifyDataSetChanged();
    }
}

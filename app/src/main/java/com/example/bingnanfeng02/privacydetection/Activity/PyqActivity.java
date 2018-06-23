package com.example.bingnanfeng02.privacydetection.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.bingnanfeng02.privacydetection.Adapter.PyqAdapter;
import com.example.bingnanfeng02.privacydetection.CallBack.SelectPositionListener;
import com.example.bingnanfeng02.privacydetection.Constant;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.Task.Task;
import com.example.bingnanfeng02.privacydetection.View.SelectView;
import com.example.bingnanfeng02.privacydetection.data.PPPyq;
import com.example.bingnanfeng02.privacydetection.util.BaseHandler;
import com.example.bingnanfeng02.privacydetection.util.Photo;

import java.io.FileNotFoundException;

/**
 * Created by bingnanfeng02 on 2017/10/24.
 */

public class PyqActivity extends BackActivity implements View.OnClickListener, SelectPositionListener,BaseHandler.BaseHandlerCallBack {
    SelectView selectView;
    private static final String[] TEXTS={"拍照","相册"};
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Photo photo;
    private PyqAdapter pyqAdapter;
    private PPPyq ppPyq;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pyq);
        handler=new BaseHandler<>(this);
        photo=new Photo(this);
        initback("朋友圈");
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        findViewById(R.id.camera).setOnClickListener(this);
    }
    void init(){
        ppPyq=new PPPyq();
        if(!Constant.cookie.equals("")){
            Task task=new Task();
            task.execute(this,handler, null,ppPyq,Constant.checkpyq,1,"checkpyq");
        }

    }
    void initsenddata(){
//        SharedPreferences pref=getSharedPreferences("pyq",MODE_PRIVATE);
//        Pyq pyq=new Pyq();
//        pyq.setUsername(pref.getString("name",""));
//        if(pyq.getUsername().equals("")){
//
//        }else {
//            pyq.setText(pref.getString("text",""));
//            pyq.setDetected_level(Integer.valueOf(pref.getString("permission","0")));
//            pyq.setId(Integer.valueOf(pref.getString("id","0")));
//            if(!pyq.getUsername().equals(myApplication.friend[((MyApplication)getApplication()).i])){
//                if(myApplication.perimission[pyq.getId()][myApplication.i]>pyq.getDetected_level()){
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
//            pyq.setUsername("Alice");
//            pyq.setText("test");
//            pyq.setDetected_level(0);
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
                selectView.setListener(this);
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
        Intent result=new Intent(this,SendActivity.class);
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
        init();
    }


    @Override
    public void callBack(Message msg_main) {
        switch (msg_main.arg1){
            case 1:
                if(ppPyq!=null&&msg_main.obj!=null){
                    ppPyq=(PPPyq)msg_main.obj;
                    //Toast.makeText(PyqActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
                    pyqAdapter=new PyqAdapter(PyqActivity.this,ppPyq.getMicroblogs(),0);
                    recyclerView.setAdapter(pyqAdapter);
                }else {
                    Toast.makeText(PyqActivity.this,"获取失败",Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:

                break;
        }
    }
}

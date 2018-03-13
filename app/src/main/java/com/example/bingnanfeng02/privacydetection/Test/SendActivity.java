//package com.example.bingnanfeng02.privacydetection.Activity;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.SharedPreferences;
//import android.graphics.Bitmap;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//import com.example.bingnanfeng02.privacydetection.GetBitmap1;
//import com.example.bingnanfeng02.privacydetection.GetBitmap2;
//import com.example.bingnanfeng02.privacydetection.MyApplication;
//import com.example.bingnanfeng02.privacydetection.R;
//import com.example.bingnanfeng02.privacydetection.Task.SendPyq;
//import com.example.bingnanfeng02.privacydetection.View.GetBm;
//import com.example.bingnanfeng02.privacydetection.data.Pyq;
//import com.example.bingnanfeng02.privacydetection.util.GetpxOrdp;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class SendActivity extends AppCompatActivity implements View.OnClickListener {
//    private static final String[] dengji={"公开","1级以上","2级以上","3级以上","4级以上","5级","仅自己"};
//    GetBm getBitmap;
//    Bitmap bitmap;
//    ImageView img;
//    ImageView img_juece;
//    int temp;
//    int temp2=-1;
//    private TextView tx_permission;
//    private EditText add_content;
//    //private MyApplication myApplication;
//    private RatingBar ratingBar;
//    private TextView advice_group;
//    //private RecyclerView recyclerView;
//    private ArrayList<Pyq> pyqs=new ArrayList<>();
//    int s=-1;
//    Handler handler=new Handler(){
//        public void handleMessage(Message msg_main) {
//
//        }
//    };
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        setContentView(R.layout.activity_send);
//        //myApplication=(MyApplication)getApplication();
//        findid();
//        setListener();
////        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
////        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
////        recyclerView.setLayoutManager(linearLayoutManager);
////        initsenddata();
////        inittestdata();
//        //PyqAdapter pyqAdapter=new PyqAdapter(this,pyqs,1);
//        //recyclerView.setAdapter(pyqAdapter);
//        if(getIntent().getIntExtra("sdk",0)==1){
//            getBitmap=new GetBitmap1();
//        }else {
//            getBitmap=new GetBitmap2();
//        }
//        bitmap=getBitmap.get(this, GetpxOrdp.dip2px(this,75),GetpxOrdp.dip2px(this,75));
//        img.setImageBitmap(bitmap);
//        tx_permission.setText("已开启自动分组");
////        if(myApplication.auto_fill){
////
////        }
//    }
////    void initsenddata(){
////        SharedPreferences pref=getSharedPreferences("pyq",MODE_PRIVATE);
////        Pyq pyq=new Pyq();
////        pyq.setName(pref.getString("name",""));
////        if(pyq.getName().equals("")){
////
////        }else {
////            pyq.setText(pref.getString("text",""));
////            pyq.setPermission(Integer.valueOf(pref.getString("permission","0")));
////            pyq.setId(Integer.valueOf(pref.getString("id","0")));
////            if(!pyq.getName().equals(myApplication.friend[((MyApplication)getApplication()).i])){
////                if(myApplication.perimission[pyq.getId()][myApplication.i]>pyq.getPermission()){
////                    pyqs.add(pyq);
////                }
////            }else {
////                pyqs.add(pyq);
////            }
////        }
////    }
////    void inittestdata(){
////        for(int i=0;i<5;i++){
////            Pyq pyq=new Pyq();
////            pyq.setName("Alice");
////            pyq.setText("test");
////            pyq.setPermission(0);
////            pyq.setId(0);
////            pyq.setIstest(true);
////            pyqs.add(pyq);
////        }
////    }
//    void findid(){
//        advice_group=(TextView)findViewById(R.id.advice_group);
//        img=(ImageView)findViewById(R.id.img);
//        img_juece=(ImageView)findViewById(R.id.img_juece2);
//        tx_permission=(TextView)findViewById(R.id.tx_permission);
//        img_juece.setBackgroundResource(R.drawable.jinru);
//        add_content=(EditText)findViewById(R.id.add_content);
//        ratingBar=(RatingBar)findViewById(R.id.ratingbar);
//        //recyclerView=(RecyclerView)findViewById(R.id.rv);
//    }
//    void setListener(){
//        findViewById(R.id.cancel).setOnClickListener(this);
//        findViewById(R.id.send).setOnClickListener(this);
//        findViewById(R.id.who).setOnClickListener(this);
//        findViewById(R.id.juece).setOnClickListener(this);
//    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.cancel:
//                finish();
//                break;
//            case R.id.send:
//                send();
//                break;
//            case R.id.who:
//                ckeck_premission();
//                break;
//            case R.id.juece:
//                juece();
//                break;
//        }
//    }
//    int random(){
//        Random random = new Random();
//        return random.nextInt(5)%(6);
//    }
//    void send(){
//
////        try {
////            GetBitmapTask.changeBitmap2File(bitmap);
////            if(temp2!=-1){
////                save(myApplication.friend[myApplication.i],add_content.getText().toString(),temp2);
////            }else {
////                if(myApplication.auto_fill){
////                    if(s==-1){
////                        s=random();
////                    }
////                    save(myApplication.friend[myApplication.i],add_content.getText().toString(),s);
////                }else {
////                    save(myApplication.friend[myApplication.i],add_content.getText().toString(),0);
////                }
////            }
////            finish();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }
//    void save(String name,String text,int permission){
//        SharedPreferences.Editor editor=getSharedPreferences("pyq",MODE_PRIVATE).edit();
//        editor.putString("name",name);
//        editor.putString("text",text);
//        editor.putString("permission",permission+"");
//        editor.putString("id",((MyApplication)getApplication()).i+"");
//        editor.apply();
//    }
//    void juece(){
//        SendPyq sendPyq =new SendPyq(bitmap,handler,this);
//        sendPyq.execute();
////        s = random() ;
////        ratingBar.setRating((float)s);
////        advice_group.setText(s+"级以上权限");
////        img_juece.setBackgroundResource(R.drawable.loading);
////        final AnimationDrawable animationDrawable=(AnimationDrawable)img_juece.getBackground();
////        animationDrawable.start();
////        new Handler().postDelayed(new Runnable(){
////            public void run() {
////                findViewById(R.id.ll_result).setVisibility(View.VISIBLE);
////                animationDrawable.stop();
////                img_juece.setBackgroundResource(R.drawable.jinru);
////            }
////        }, 2000);
//    }
//    void ckeck_premission(){
//        temp=temp2;
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setSingleChoiceItems(dengji,temp2,new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                temp2=which;
//            }
//        });
//        builder.setTitle("设置此条朋友圈权限等级").setNegativeButton(
//                "取消", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                temp2=temp;
//            }
//        });
//        builder.setPositiveButton("确定",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                       tx_permission.setText(dengji[temp2]);
//                        temp=temp2;
//                    }
//                });
//        builder.show();
//    }
//}

package com.example.bingnanfeng02.privacydetection.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bingnanfeng02.privacydetection.CallBack.SelectPositionListener;
import com.example.bingnanfeng02.privacydetection.R;
import com.example.bingnanfeng02.privacydetection.View.SelectView;

import java.io.File;
import java.io.FileNotFoundException;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements SelectPositionListener, View.OnClickListener {
    CardView object_detection;
    CardView relationship_detection;
    View view;
    SelectView selectView;
    File file;
    ImageView setting;
    String ip="";
    String port="";
    private static  final  int REQUEST_CAMERA=1;
    private static  final int OPEN_ALBUM=2;
    private static final int REQUEST_CODE_PERMISSION = 1;
    private static String[] PERMISSIONS_REQ = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    private static final String[] TEXTS={"拍照","相册"};
    Uri iamgeuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPremission();
        setStatusBarColor();
        setContentView(R.layout.activity_main);
        object_detection=(CardView)findViewById(R.id.object_detection);
        relationship_detection=(CardView)findViewById(R.id.relationship_detection);
        setting=(ImageView)findViewById(R.id.setting);
        view=findViewById(R.id.rl);
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        ip=pref.getString("ip","");
        port=pref.getString("port","");
        initOnClick();
    }
    void initPremission(){
        boolean avialbe_permission = true;
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.M ) {
            avialbe_permission = verifyPermissions(this);
        }
        if (avialbe_permission ) {

        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.object_detection:
                if(ip.equals("")||port.equals("")) {
                    Toast.makeText(MainActivity.this,"IP地址和端口号不得为空",LENGTH_SHORT).show();
                }else {
                    setWindow();
                }
                break;
            case R.id.relationship_detection:
                Toast.makeText(MainActivity.this,"功能尚未开放",LENGTH_SHORT).show();
                break;
            case R.id.setting:
                inputTitleDialog();
                break;
        }
    }
    private void inputTitleDialog() {
        final View  inputServer=(LinearLayout) getLayoutInflater().inflate(R.layout.view_input_ip_and_port,null);
        final EditText ipet=(EditText)inputServer.findViewById(R.id.ipet);
        final EditText portet=(EditText)inputServer.findViewById(R.id.portet);
        ipet.setText(ip);
        portet.setText(port);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置IP地址和端口号").setView(inputServer).setNegativeButton(
                "取消", null);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                        ip=ipet.getText().toString();
                        port=portet.getText().toString();
                        editor.putString("ip",ip);
                        editor.putString("port",port);
                        editor.apply();

                    }
                });
        builder.show();
    }
    private static boolean verifyPermissions(Activity activity) {
        // Check if we have write permission
        int write_permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read_persmission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int camera_permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (write_permission != PackageManager.PERMISSION_GRANTED ||
                read_persmission != PackageManager.PERMISSION_GRANTED ||
                camera_permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_REQ,
                    REQUEST_CODE_PERMISSION
            );
            return false;
        } else {
            return true;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        try {
            if (requestCode == REQUEST_CODE_PERMISSION) {
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }else{
                    finish();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
    void setStatusBarColor(){
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    void initOnClick(){
        object_detection.setOnClickListener(this);
        relationship_detection.setOnClickListener(this);
        setting.setOnClickListener(this);
    }
    private  void setWindow() {
        selectView = new SelectView(this,TEXTS);
        selectView.setPositionListener(this);
        selectView.setView();
        selectView.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void positionSelected(int position) {
        if(selectView.isShowing()){
            selectView.dismiss();
        }
        switch (position){
            case 0:
                takePhoto();
                break;
            case 1:
                openAlbum();
        }
    }
    private void takePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();
        if(Build.VERSION.SDK_INT>=24){
            iamgeuri = FileProvider.getUriForFile(this, "camera", file);
        }else {
            iamgeuri=Uri.fromFile(file);
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, iamgeuri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }
    private void openAlbum(){
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,2);
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA) {
            Log.e("TAG", "---------" + FileProvider.getUriForFile(this, "camera", file));
            Intent intent=new Intent(this,ResultActivity.class);
            intent.putExtra("uri",iamgeuri);
            intent.putExtra("sdk",1);
            intent.putExtra("ip",ip);
            intent.putExtra("port",port);
            try {
                if(getContentResolver().openInputStream(iamgeuri)!=null){
                    startActivity(intent);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if(requestCode==OPEN_ALBUM){
            if(Build.VERSION.SDK_INT>=19){
                if(data!=null){
                    handleimage(data);
                }
            }
        }
    }
    void handleimage(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        Intent intent=new Intent(this,ResultActivity.class);
        intent.putExtra("uri",imagePath);
        intent.putExtra("ip",ip);
        intent.putExtra("port",port);
        startActivity(intent);
    }
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


}

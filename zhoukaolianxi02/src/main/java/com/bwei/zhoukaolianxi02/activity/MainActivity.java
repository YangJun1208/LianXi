package com.bwei.zhoukaolianxi02.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.zhoukaolianxi02.R;
import com.bwei.zhoukaolianxi02.bean.DengLuBean;
import com.bwei.zhoukaolianxi02.persenter.IPersenterImpl;
import com.bwei.zhoukaolianxi02.view.IView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {

    private boolean isUserNameEnable = false, mima = false;

    private EditText edit_zhang;
    private EditText edit_pwd;
    private CheckBox check_jz,check_zd;
    private Button button_dl,button_zc;
    private IPersenterImpl iPersenter;
    private ImageView image_more,image_QQ,image_hui;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String pwd;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取资源ID
        edit_pwd=findViewById(R.id.deng_pwd);
        edit_zhang=findViewById(R.id.deng_name);
        check_jz=findViewById(R.id.check_jz);
        check_zd=findViewById(R.id.check_zd);
        button_dl=findViewById(R.id.button_denglu);
        button_zc=findViewById(R.id.button_zhuce);
        image_more=findViewById(R.id.more);
        //image_hui=findViewById(R.id.image_hui);
        iPersenter=new IPersenterImpl(this);
        image_QQ=findViewById(R.id.QQ);

        //点击事件
        button_zc.setOnClickListener(this);
        button_dl.setOnClickListener(this);
        image_QQ.setOnClickListener(this);

        //记住登陆
        sharedPreferences=getSharedPreferences("Users",MODE_MULTI_PROCESS);
        editor=sharedPreferences.edit();
        //第二次登陆的时候登陆按钮可以点击将点击事件放在记住密码的上面
        edit_zhang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                isUserNameEnable = isMobile(String.valueOf(s));
           //    mima=isPassword(String.valueOf(s));
                if(isUserNameEnable&&mima){
                    button_dl.setEnabled(true);
                }else{
                    button_dl.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edit_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               mima=isPassword(String.valueOf(s));
                if(isUserNameEnable&&mima){
                    button_dl.setEnabled(true);
                }else{
                    button_dl.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //取出记住密码的状态 将自动登录设为true
        boolean j_check = sharedPreferences.getBoolean("j_check", false);
        if(j_check){
            String name = sharedPreferences.getString("name", null);
            String pwd = sharedPreferences.getString("pwd", null);
            edit_zhang.setText(name);
            edit_pwd.setText(pwd);
            check_jz.setChecked(true);
        }

        //取出自动登陆的状态
        boolean z_check = sharedPreferences.getBoolean("z_check", false);
        if(z_check){
            Intent intent = new Intent(MainActivity.this, ThreeActivity.class);
            startActivity(intent);
            finish();
        }

        //点击自动登陆，记住密码也为true
        check_zd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    check_jz.setChecked(true);
                }else{
                    editor.clear();
                    editor.commit();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_zhuce:
                //跳转到注册页面
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.button_denglu:

                pwd = edit_pwd.getText().toString();
                name = edit_zhang.getText().toString();

                //将账号密码存入进去
                if(check_jz.isChecked()){
                    editor.putString("name", name);
                    editor.putString("pwd", pwd);
                    editor.putBoolean("j_check",true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }
                //存入自动登陆的状态
                if(check_zd.isChecked()){
                    editor.putBoolean("z_check",true);
                    editor.commit();
                }
                iPersenter.getRequest("http://www.zhaoapi.cn/user/login?Mobile="+ name +"&password="+ pwd,DengLuBean.class);
                break;
            case R.id.QQ:
                UMShareAPI shareAPI = UMShareAPI.get(MainActivity.this);
                UMShareConfig config=new UMShareConfig();
                config.isNeedAuthOnGetUserInfo(true);
                UMShareAPI.get(MainActivity.this).setShareConfig(config);
                shareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.i("TAG","onStart");
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                       Log.i("TAG",map+"");
                        String imageUrl = map.get("profile_image_url");
                        String screen_name = map.get("screen_name");
                        ImageLoader.getInstance().displayImage(imageUrl,image_QQ);
                        Intent intent = new Intent(MainActivity.this, ThreeActivity.class);
                        intent.putExtra("name",screen_name);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Log.i("TAG","onError");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Log.i("TAG","onCancel");
                    }
                });
                break;
                default:
                    break;

        }
    }

    @Override
    public void onSuccess(Object data) {
        DengLuBean dengLuBean= (DengLuBean) data;
        //登陆成功
        if(dengLuBean.getMsg().equals("登录成功")){
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, ThreeActivity.class);
            String username = dengLuBean.getData().getMobile();
            intent.putExtra("name",username);
            startActivity(intent);
        }else{
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPersenter.deCatch();
    }

    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    public static final String REGEX_PASSWORD = "^[0-9]{6}$";
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }



}

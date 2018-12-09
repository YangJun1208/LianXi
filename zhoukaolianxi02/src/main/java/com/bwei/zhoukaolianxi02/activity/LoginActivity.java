package com.bwei.zhoukaolianxi02.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.zhoukaolianxi02.R;
import com.bwei.zhoukaolianxi02.bean.ZhuCeBean;
import com.bwei.zhoukaolianxi02.persenter.IPersenterImpl;
import com.bwei.zhoukaolianxi02.view.IView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private EditText edit_phone,edit_pwd;
    private Button button_fan,button_zhuce;
    private IPersenterImpl iPersenter;
   // private String dataUrl="http://www.zhaoapi.cn/user/reg?Mobile=%d&password=%d";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        edit_phone=findViewById(R.id.zhu_name);
        edit_pwd=findViewById(R.id.zhu_pwd);

        button_fan=findViewById(R.id.fan);
        button_zhuce=findViewById(R.id.zhuce);
        button_zhuce.setOnClickListener(this);
        iPersenter=new IPersenterImpl(this);
        button_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce:
                String pwd = edit_pwd.getText().toString();
                String phone = edit_phone.getText().toString();
                iPersenter.getRequest("http://www.zhaoapi.cn/user/reg?Mobile="+phone+"&password="+pwd,ZhuCeBean.class);
                break;
        }
    }

    @Override
    public void onSuccess(Object data) {
        ZhuCeBean zhuCeBean= (ZhuCeBean) data;
        if(zhuCeBean.getMsg().equals("注册成功")){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else if(zhuCeBean.getMsg().equals("天呢！用户已注册")){
            Toast.makeText(this, zhuCeBean.getMsg()+"", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }
}

package com.bwei.recycleview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.bwei.recycleview.Apks;
import com.bwei.recycleview.R;
import com.bwei.recycleview.adapter.LinearAdapter;
import com.bwei.recycleview.bean.User;
import com.bwei.recycleview.bean.UsersBean;
import com.bwei.recycleview.persenter.IPensenterImpl;
import com.bwei.recycleview.view.IView;

import java.util.HashMap;

/**
 * @author Lenovo
 */
public class LinearLayoutActivity extends AppCompatActivity implements IView {

    private IPensenterImpl iPensenter;
    private RecyclerView recyclerView;
    private LinearAdapter linearAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liear);

        recyclerView = findViewById(R.id.recyclerview);
        iPensenter=new IPensenterImpl(this);
        //写一个布局管理器，写一个线性管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置方向，垂直的方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        linearAdapter = new LinearAdapter(this);
        recyclerView.setAdapter(linearAdapter);

       // recyclerView.setAdapter(linearAdapter);

        iPensenter.getRequest(Apks.TYPE_IMAGE,new HashMap<String, String>(),UsersBean.class);

        DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.recycler_divider_horizontal));
        recyclerView.addItemDecoration(decoration);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof UsersBean){
            UsersBean usersBean= (UsersBean) data;



            linearAdapter.addItem(usersBean.getData());
        }
    }
}

package com.bwei.recycleview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.bwei.recycleview.Apks;
import com.bwei.recycleview.R;
import com.bwei.recycleview.adapter.GrideAdapter;
import com.bwei.recycleview.bean.User;
import com.bwei.recycleview.bean.UsersBean;
import com.bwei.recycleview.persenter.IPensenterImpl;
import com.bwei.recycleview.view.DividerGridItemDecoration;
import com.bwei.recycleview.view.IView;

import java.util.HashMap;

/**
 * @author Lenovo
 */
public class GrideLayoutActivity extends AppCompatActivity implements IView {

    private GrideAdapter grideAdapter;
    private IPensenterImpl iPensenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        iPensenter=new IPensenterImpl(this);
        RecyclerView recyclerView=findViewById(R.id.recyclerview);

        GridLayoutManager layoutManager = new GridLayoutManager(this,3);

        layoutManager.setOrientation(OrientationHelper.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        grideAdapter = new GrideAdapter(this);


        /*for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("张" + i);
            grideAdapter.addItem(user);
        }*/

        //设置Adapter
        recyclerView.setAdapter(grideAdapter);

        iPensenter.getRequest(Apks.TYPE_IMAGE,new HashMap<String, String>(),UsersBean.class);
        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(this);
        recyclerView.addItemDecoration(dividerGridItemDecoration);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /*findViewById(R.id.button_recycler_grid_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName("老戴");
                grideAdapter.addData(0,user);
            }
        });*/

        grideAdapter.setClickListener(new GrideAdapter.Click() {
            @Override
            public void OnClick(int position) {
                Log.i("dj", "OnClick in activity " + position);
                grideAdapter.removeData(position);
            }

            @Override
            public void OnLongClick(int position) {
                Log.i("dj", "OnLongClick in activity " + position);
            }
        });
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof UsersBean){
            UsersBean usersBean= (UsersBean) data;
            grideAdapter.addItem(usersBean.getData());

        }
    }
}

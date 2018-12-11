package com.bwei.recycleview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bwei.recycleview.Apks;
import com.bwei.recycleview.R;
import com.bwei.recycleview.adapter.StaggeredAdapter;
import com.bwei.recycleview.bean.User;
import com.bwei.recycleview.bean.UsersBean;
import com.bwei.recycleview.persenter.IPensenterImpl;
import com.bwei.recycleview.view.DividerGridItemDecoration;
import com.bwei.recycleview.view.IView;

import java.util.HashMap;

/**
 * @author Lenovo
 */
public class StaggerActivity extends AppCompatActivity implements IView {

    private StaggeredAdapter staggeredAdapter;
    private IPensenterImpl iPensenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagger);
        iPensenter=new IPensenterImpl(this);
        init();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        //使用瀑布流布局,第一个参数 spanCount 一行几个,第二个参数 orentation 排列方向
        StaggeredGridLayoutManager recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        //设置布局管理器
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        staggeredAdapter = new StaggeredAdapter(this);

        /*int[] avatarArray = new int[]{
                R.drawable.timg, R.drawable.timg1, R.drawable.timg3};
        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setName("张" + i);
            user.setAvatar(avatarArray[i % avatarArray.length]);
            staggeredAdapter.addItem(user);
        }*/

        //设置Adapter
        recyclerView.setAdapter(staggeredAdapter);
        iPensenter.getRequest(Apks.TYPE_IMAGE,new HashMap<String, String>(),UsersBean.class);
        //设置分隔线
        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(this);
        recyclerView.addItemDecoration(dividerGridItemDecoration);

        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof UsersBean){
            UsersBean usersBean= (UsersBean) data;
            staggeredAdapter.addItem(usersBean.getData());
        }
    }
}

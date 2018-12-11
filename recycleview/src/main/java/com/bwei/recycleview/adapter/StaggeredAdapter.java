package com.bwei.recycleview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.recycleview.R;
import com.bwei.recycleview.bean.User;
import com.bwei.recycleview.bean.UsersBean;

import java.util.ArrayList;
import java.util.List;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder> {

    private Context context;
    private List<UsersBean.DataBean> mDatas;

    public StaggeredAdapter(Context context) {
            this.mDatas = new ArrayList<>();
            this.context = context;
            }
    public void addItem(List<UsersBean.DataBean> user) {
        if (user != null) {
                mDatas.addAll(user);
            }
            notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final ImageView avatar;
        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tv_recycle_staggered);
            avatar = v.findViewById(R.id.iv_recycle_staggered);
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.stagger_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredAdapter.ViewHolder viewHolder, int i) {
        UsersBean.DataBean user = mDatas.get(i);
        viewHolder.title.setText(user.getName());
        //Glide.with(context).load(user.getAvatar()).into(viewHolder.avatar);
    }

}


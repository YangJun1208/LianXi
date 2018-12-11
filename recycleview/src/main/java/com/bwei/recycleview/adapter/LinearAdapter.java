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

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.ViewHolder> {

    private List<UsersBean.DataBean> mDatas;
    private Context mContext;

    public LinearAdapter(Context context) {
        mContext=context;
        mDatas=new ArrayList<>();
    }

    /*public void addItem(UsersBean user){
        if(user!=null) {
            mDatas.add(user);
        }
        notifyDataSetChanged();
    }*/

    public void addItem(List<UsersBean.DataBean> datas) {
       // this.mDatas = mDatas;
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.linear_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
       // UsersBean usersBean = mDatas.get(i);
        //viewHolder.title.setText(user.getName());
        UsersBean.DataBean usersBean = mDatas.get(i);
        viewHolder.title.setText(usersBean.getName());
        Glide.with(mContext).load(usersBean.getIcon()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     *静态内部类 ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView title;
        public final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tv_recycle_linear);
            imageView=itemView.findViewById(R.id.iv_recycle_linear);
        }
    }
}

package com.bwei.recycleview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.recycleview.R;
import com.bwei.recycleview.bean.User;
import com.bwei.recycleview.bean.UsersBean;

import java.util.ArrayList;
import java.util.List;

public class GrideAdapter extends RecyclerView.Adapter<GrideAdapter.ViewHolder> {

    private List<UsersBean.DataBean> mDatas;
    private Context mContext;

    public GrideAdapter(Context context) {
        mContext=context;
        this.mDatas =new ArrayList<>();
    }

    public void addItem(List<UsersBean.DataBean> datas){
        if(datas!=null){
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        UsersBean.DataBean user = mDatas.get(i);
        viewHolder.title.setText(user.getName());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dj", "click linearLayout");
                if(mClick != null){
                    mClick.OnClick(i);
                }
            }
        });


        viewHolder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mClick != null){
                    mClick.OnLongClick(i);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
       // public final ImageView avatar;
        public final LinearLayout linearLayout;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tv_recycle_grid);
         //   avatar = v.findViewById(R.id.iv_recycle_grid);
            linearLayout = v.findViewById(R.id.ll_recycle_grid);
        }
    }


    /**
     * 增加数据，传入添加的位置和数据
     */
    /*public void addData(int position, User user) {
        mDatas.add(position, user);
        //必须使用notifyItemInserted 才能加载添加动
        notifyItemInserted(position);
        notifyItemRangeChanged(position,mDatas.size());
    }*/


     // 移除数据

    public void removeData(int position) {
        mDatas.remove(position);
        //必须使用notifyItemRemoved 才能加载移除动画
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mDatas.size());
    }

    Click mClick;

    public void setClickListener(Click click){
        mClick = click;
    }

    public interface Click{
        void OnClick(int position);
        void OnLongClick(int position);
    }
}

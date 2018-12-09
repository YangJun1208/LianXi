package com.bwei.zhoukaolianxi02.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.zhoukaolianxi02.R;
import com.bwei.zhoukaolianxi02.bean.TuPianBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<TuPianBean.DataBean> data;

    public MyAdapter(Context context) {
        this.context = context;
        data=new ArrayList<>();
    }

    public void setDatas(List<TuPianBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public TuPianBean.DataBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.activity_item,parent,false);
            vh=new ViewHolder();
            vh.imageView=convertView.findViewById(R.id.image_zhan);
            vh.textView=convertView.findViewById(R.id.text_title);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolder) convertView.getTag();
        }
        vh.textView.setText(getItem(position).getTitle());
        ImageLoader.getInstance().displayImage(getItem(position).getImg(),vh.imageView);
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}

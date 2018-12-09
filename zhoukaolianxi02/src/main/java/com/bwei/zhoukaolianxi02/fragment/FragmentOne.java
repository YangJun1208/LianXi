package com.bwei.zhoukaolianxi02.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bwei.zhoukaolianxi02.R;
import com.bwei.zhoukaolianxi02.adpter.MyAdapter;
import com.bwei.zhoukaolianxi02.bean.TuPianBean;
import com.bwei.zhoukaolianxi02.persenter.IPersenterImpl;
import com.bwei.zhoukaolianxi02.view.IView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

public class FragmentOne extends Fragment implements IView {


    private Banner banner;
    private IPersenterImpl iPersenter;
    private int index;
    private ListView listView;
    private String dataUrl="http://www.xieast.com/api/banner.php";
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentone,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iPersenter=new IPersenterImpl(this);
        listView=view.findViewById(R.id.listView);

        banner=view.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                TuPianBean.DataBean tuPianBean = (TuPianBean.DataBean) path;
                com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                        .displayImage(tuPianBean.getImg(),imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        loadData();
    }

    public void loadData(){
        iPersenter.getRequest(dataUrl,TuPianBean.class);
    }

    @Override
    public void onSuccess(Object data) {
        TuPianBean tuPianBean= (TuPianBean) data;
        banner.setImages(tuPianBean.getData());
        banner.start();

        adapter = new MyAdapter(getActivity());
        listView.setAdapter(adapter);
        adapter.setDatas(tuPianBean.getData());


    }
}

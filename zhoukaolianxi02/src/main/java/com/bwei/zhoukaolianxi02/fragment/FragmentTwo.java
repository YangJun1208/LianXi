package com.bwei.zhoukaolianxi02.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.zhoukaolianxi02.R;
import com.bwei.zhoukaolianxi02.activity.FourActivity;
import com.bwei.zhoukaolianxi02.activity.ThreeActivity;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class FragmentTwo extends Fragment {

    private ImageView image_erweima;
    private Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentthree,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        image_erweima=view.findViewById(R.id.erweima);
        button=view.findViewById(R.id.create);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FourActivity.class);
                startActivity(intent);
            }
        });

        String result = ((ThreeActivity) getActivity()).getResult();
        create create = new create(getActivity(), image_erweima, result);
        create.execute(result);

    }

    static class create extends AsyncTask<String,Void,Bitmap> {
        private WeakReference<Context> mContext;
        private WeakReference<ImageView> mImageView;

        public create(Context context, ImageView imageView, String name) {
            mContext=new WeakReference<>(context);
            mImageView=new WeakReference<>(imageView);
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            String str=strings[0];
            if(TextUtils.isEmpty(str)){
                return null;
            }
            int i = mContext.get().getResources().getDimensionPixelOffset(R.dimen.size);

            return QRCodeEncoder.syncEncodeQRCode(str,i);
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {

            if(bitmap!=null){
                 mImageView.get().setImageBitmap(bitmap);
            }else{
                Toast.makeText(mContext.get(), "生成失败", Toast.LENGTH_SHORT).show();
            }
        }


    }

}

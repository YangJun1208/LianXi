package com.bwei.recycleview.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {

    private static volatile OkHttpUtils instance;
    private OkHttpClient client;

    private Handler mHandler =new Handler(Looper.getMainLooper());
    public static OkHttpUtils getInstance() {
        if(instance==null){
            synchronized (OkHttpUtils.class){
                if(null==instance){
                    instance=new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    private OkHttpUtils(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public void postEnqueue(String dataUrl, Map<String,String> params, final Class clazz, final ICallBack callBack){
        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String,String> entry:params.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }

        RequestBody build = builder.build();
        Request builder1 = new Request.Builder()
                .post(build)
                .url(dataUrl)
                .build();

        Call call = client.newCall(builder1);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFaile(e);
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                String result = response.body().string();
                final Object o = new Gson().fromJson(result, clazz);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(o);
                    }
                });
            }
        });

    }


}

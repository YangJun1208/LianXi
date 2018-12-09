package com.bwei.zhoukaolianxi02.model;

import android.os.AsyncTask;

import com.bwei.zhoukaolianxi02.NetUtils;
import com.bwei.zhoukaolianxi02.callback.MyCallBank;
import com.google.gson.Gson;

public class IModelImpl implements IModel {

    public  <T> T getRequest(String dataUrl,Class clazz){
        return (T) new Gson().fromJson(NetUtils.getRequest(dataUrl),clazz);
    }

    @Override
    public void getRequest(String dataUrl, final Class clazz, final MyCallBank callBank) {
        new AsyncTask<String,Void,Object>(){
            @Override
            protected Object doInBackground(String... strings) {
                return getRequest(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                callBank.onSuccess(o);
            }
        }.execute(dataUrl);
    }
}

package com.bwei.recycleview.model;

import com.bwei.recycleview.callback.MyCallBack;
import com.bwei.recycleview.okhttp.ICallBack;
import com.bwei.recycleview.okhttp.OkHttpUtils;

import java.util.Map;

public class IModelImpl implements IModel {

    @Override
    public void getRequest(String dataUrl, Map<String, String> params, Class clazz, final MyCallBack callBack) {

        OkHttpUtils.getInstance().postEnqueue(dataUrl, params, clazz, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                callBack.onSuccess(obj);
            }

            @Override
            public void onFaile(Exception e) {
                callBack.onSuccess(e);
            }
        });
    }
}

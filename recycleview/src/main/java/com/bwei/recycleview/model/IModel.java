package com.bwei.recycleview.model;

import com.bwei.recycleview.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void getRequest(String dataUrl, Map<String,String> params, Class clazz, MyCallBack callBack);
}

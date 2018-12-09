package com.bwei.zhoukaolianxi02.model;

import com.bwei.zhoukaolianxi02.callback.MyCallBank;

public interface IModel {
    void getRequest(String dataUrl,Class clazz, MyCallBank callBank);
}

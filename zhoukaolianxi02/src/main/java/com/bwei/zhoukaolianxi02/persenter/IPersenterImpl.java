package com.bwei.zhoukaolianxi02.persenter;

import android.content.Context;

import com.bwei.zhoukaolianxi02.callback.MyCallBank;
import com.bwei.zhoukaolianxi02.model.IModelImpl;
import com.bwei.zhoukaolianxi02.view.IView;

public class IPersenterImpl implements IPersenter {

    private IView mIView;
    private IModelImpl iModel;

    public IPersenterImpl(IView iView){
        this.mIView=iView;
        iModel=new IModelImpl();
    }



    @Override
    public void getRequest(String dataUrl, Class clazz) {
        iModel.getRequest(dataUrl, clazz, new MyCallBank() {
            @Override
            public void onSuccess(Object data) {
                mIView.onSuccess(data);
            }
        });
    }
    public void deCatch(){
        iModel=null;
        mIView=null;
    }
}

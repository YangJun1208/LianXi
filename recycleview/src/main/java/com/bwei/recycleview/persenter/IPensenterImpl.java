package com.bwei.recycleview.persenter;

import com.bwei.recycleview.callback.MyCallBack;
import com.bwei.recycleview.model.IModelImpl;
import com.bwei.recycleview.view.IView;

import java.util.Map;

public class IPensenterImpl implements IPersenter {

    private IView mIView;
    private IModelImpl iModel;

    public IPensenterImpl(IView iView){
        mIView=iView;
        iModel=new IModelImpl();
    }

    public void deteach(){
        iModel=null;
        mIView=null;
    }

    @Override
    public void getRequest(String dataUrl, Map<String, String> params, Class clazz) {
        iModel.getRequest(dataUrl, params, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                mIView.onSuccess(data);
            }
        });
    }
}

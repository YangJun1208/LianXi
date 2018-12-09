package com.bwei.zhoukaolianxi02;

import android.content.Context;
import android.util.Log;

public class UnTacherHandler implements Thread.UncaughtExceptionHandler {

    private Context mContext;
    private static UnTacherHandler unTacherHandler;

    private UnTacherHandler(Context context){
        init(context);
    }

    public static UnTacherHandler getInstance(Context context){
        if(unTacherHandler==null){
            synchronized (UnTacherHandler.class){
                unTacherHandler=new UnTacherHandler(context);
            }
        }
        return unTacherHandler;
    }

    public void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        try{
            Log.i("TAG",e.getLocalizedMessage());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

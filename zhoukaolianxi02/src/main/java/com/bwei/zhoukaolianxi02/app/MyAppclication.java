package com.bwei.zhoukaolianxi02.app;

import android.app.Application;
import android.media.MediaCas;

import com.bwei.zhoukaolianxi02.UnTacherHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class MyAppclication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

        UnTacherHandler.getInstance(getApplicationContext()).init(getApplicationContext());

        ImageLoader.getInstance().init(
                new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(
                        new DisplayImageOptions.Builder()
                                .cacheOnDisk(true)
                                .cacheInMemory(true)
                                .build()
                ).build()
        );

    }
}

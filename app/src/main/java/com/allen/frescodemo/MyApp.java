package com.allen.frescodemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 全局Application
 * Created by allen on 2017/12/11.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Fresco
        Fresco.initialize(this);
    }
}

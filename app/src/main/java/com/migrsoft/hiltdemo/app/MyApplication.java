package com.migrsoft.hiltdemo.app;

import androidx.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}

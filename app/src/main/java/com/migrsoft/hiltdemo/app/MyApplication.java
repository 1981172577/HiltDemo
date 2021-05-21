package com.migrsoft.hiltdemo.app;

import androidx.multidex.MultiDexApplication;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
    }
}

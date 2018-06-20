package com.example.huixuegong.androidadv.architecture.util;

import android.app.Application;
import android.content.Context;

public class ArchApplication extends Application{
    private static Context mGlobalContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mGlobalContext = getApplicationContext();
    }

    public static Context getGlobalContext() {
        return mGlobalContext;
    }
}

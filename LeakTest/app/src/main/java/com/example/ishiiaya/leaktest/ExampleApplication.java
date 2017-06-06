package com.example.ishiiaya.leaktest;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by ishiiaya on 2017/06/07.
 */

public class ExampleApplication extends Application {
private RefWatcher refWatcher;

    public static RefWatcher getRefwatcher(Context context){
ExampleApplication exampleApplication = (ExampleApplication)context.getApplicationContext();
        return exampleApplication.refWatcher;
    }
    @Override public void onCreate() {
        super.onCreate();
refWatcher=LeakCanary.install(this);
    }
}
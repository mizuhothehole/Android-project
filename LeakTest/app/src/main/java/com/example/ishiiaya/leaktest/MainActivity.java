package com.example.ishiiaya.leaktest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

public class MainActivity extends Service {

    private Testclass mTestclass;
    private Testclass.TestIf myIf = new Testclass.TestIf(){
        @Override
        public void onRemove() {
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("testInt","started");
        super.onCreate();

        initIf();
    }

    @Override
    public void onDestroy() {
        Log.d("testInt","destroyed");
        super.onDestroy();
        ExampleApplication.getRefwatcher(getApplicationContext()).watch(this);
    }

    private void initIf(){
        mTestclass = Testclass.getInstance(getApplicationContext());
        mTestclass.setIf(myIf);
    }
}

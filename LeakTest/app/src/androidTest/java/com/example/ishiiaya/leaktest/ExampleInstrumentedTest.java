package com.example.ishiiaya.leaktest;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private int testInt = 1;

    // Context of the app under test.
    Context appContext1 = InstrumentationRegistry.getTargetContext();
    Context appContext2 = InstrumentationRegistry.getTargetContext().getApplicationContext();

    @Before
    public void setUp(){
        Log.d("testInt",""+testInt);
    }

    @After
    public void tearDown(){

    }

    @Test
    public void useAppContext() throws Exception {
        testInt++;
        // Context of the app under test.

        Log.d("testInt","" + appContext1);
        Log.d("testInt","" + appContext2);
        Log.d("testInt",""+testInt);
        Intent intent = new Intent(appContext2,MainActivity.class);
        appContext2.startService(intent);
        Intent intent2 = new Intent(appContext2,MainActivity.class);
        appContext2.stopService(intent2);
    }

    @Test
    public void useAppContext_2() throws Exception {
        testInt++;
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext().getApplicationContext();
        Log.d("testInt",""+testInt);
        Intent intent = new Intent(appContext2,MainActivity.class);
        appContext2.startService(intent);
        Intent intent2 = new Intent(appContext2,MainActivity.class);
        appContext2.stopService(intent2);
    }

    @Test
    public void useAppContext_3() throws Exception {
        testInt++;
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext().getApplicationContext();
        Log.d("testInt",""+testInt);
        Intent intent = new Intent(appContext2,MainActivity.class);
        appContext2.startService(intent);
        Intent intent2 = new Intent(appContext2,MainActivity.class);
        appContext2.stopService(intent2);
    }
    @Test
    public void useAppContext_34() throws Exception {
        testInt++;
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext().getApplicationContext();
        Log.d("testInt",""+testInt);
        Intent intent = new Intent(appContext2,MainActivity.class);
        appContext2.startService(intent);
        Intent intent2 = new Intent(appContext2,MainActivity.class);
        appContext2.stopService(intent2);
    }
    @Test
    public void useAppContext_13() throws Exception {
        testInt++;
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext().getApplicationContext();
        Log.d("testInt",""+testInt);
        Intent intent = new Intent(appContext2,MainActivity.class);
        appContext2.startService(intent);
        Intent intent2 = new Intent(appContext2,MainActivity.class);
        appContext2.stopService(intent2);
    }
    @Test
    public void useAppContext_32() throws Exception {
        testInt++;
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext().getApplicationContext();
        Log.d("testInt",""+testInt);
        Intent intent = new Intent(appContext2,MainActivity.class);
        appContext2.startService(intent);
        Intent intent2 = new Intent(appContext2,MainActivity.class);
        appContext2.stopService(intent2);
    }
}
package com.example.ishiiaya.homeaccount;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

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
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();


        Log.d("makethecontext-instrum",InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext().toString());
        assertEquals("com.example.ishiiaya.homeaccount", appContext.getPackageName());
        Log.d("makethecontext-instrum",InstrumentationRegistry.getArguments().toString());
        Log.d("makethecontext-instrum",appContext.getPackageName());
        Log.d("makethecontext-insApp",appContext.getApplicationContext().toString());
        Log.d("makethecontext-insApp",appContext.getApplicationContext().getPackageName());
        Log.d("makethecontext-insApp",InstrumentationRegistry.getContext().getPackageName());

        Intent intent = new Intent(appContext,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent);
    }
}

package com.example.ishiiaya.leaktest;

import android.content.Context;

/**
 * Created by ishiiaya on 2017/06/07.
 */

public class Testclass {

    private static Testclass mTestclass;
    private Context mContext;

    private Testclass(Context context){
        mContext = context;
    }

    public static Testclass getInstance(Context context){
        if(null == mTestclass){
            mTestclass=new Testclass(context);
            return mTestclass;
        }
        return mTestclass;
    }

    interface TestIf{
        void onRemove();
    }

    private TestIf mIf;

    public void setIf(TestIf testIf){
        this.mIf = testIf;
    }

}

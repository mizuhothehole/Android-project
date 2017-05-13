package com.example.ishiiaya.flyingbottle;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ishiiaya on 2017/04/29.
 */

public class BottleDbHelper extends SQLiteOpenHelper {

    private final static String TAG = "SQLiteHelper";
    private static BottleDbHelper mBottleDbHelper;

    public static BottleDbHelper getDbInstance() {
        if (null == mBottleDbHelper) {
            return new BottleDbHelper(null, null, null, 1);
        }
        return mBottleDbHelper;
    }

    public void initialize(Context context) {
        mBottleDbHelper = new BottleDbHelper(context, "test", null, 1);
    }

    private BottleDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("Create TABLE  " + SchemaProvider.TABLE_NAME_MESSAGE_DATA + "( " +
                    SchemaProvider.CLOMUN_NAME_BOTTLE_PK + " integer Primary Key AUTOINCREMENT, " +
                    SchemaProvider.CLOMUN_NAME_BOTTLE_MESSAGE + " varchar(1000) " +
                    ")");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

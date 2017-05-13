package com.example.ishiiaya.flyingbottle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ishiiaya on 2017/05/01.
 */

public class BottleDbManager {
    private static BottleDbManager mBottleDbManager;
    private BottleDbHelper mSqlHelper;
    private SQLiteDatabase mSQLiteDatabase;

    private BottleDbManager() {
    }

    public static BottleDbManager getInstance() {
        if (null == mBottleDbManager) {
            return new BottleDbManager();
        }
        return mBottleDbManager;
    }

    public void initialize(Context context) {
        mSqlHelper = BottleDbHelper.getDbInstance();
        mSqlHelper.initialize(context);
        mSQLiteDatabase = mSqlHelper.getWritableDatabase();

    }

    public String getBottleData() {
        String bottleMsg = StringUtil.EMPTY;
        Cursor cursor = mSQLiteDatabase.rawQuery(SchemaProvider.SQL_SELECT_ALL_MESSAGE_DATA, null);
        if (null != cursor && cursor.moveToFirst()) {
            Integer allCount = cursor.getCount();
            Integer randonCount = (int) (Math.random() * allCount);
            cursor.move(randonCount);
            bottleMsg = cursor.getString(
                    cursor.getColumnIndex(SchemaProvider.CLOMUN_NAME_BOTTLE_MESSAGE));
            Integer columnId = cursor.getInt(
                    cursor.getColumnIndex(SchemaProvider.CLOMUN_NAME_BOTTLE_PK));
            String[] whereArgs = {String.valueOf(columnId)};
            mSQLiteDatabase.delete(
                    SchemaProvider.TABLE_NAME_MESSAGE_DATA,
                    SchemaProvider.CLOMUN_NAME_BOTTLE_PK + " = ?", whereArgs);
        }
        return bottleMsg;
    }

    public void throwBottleData(String bottleMsg) {
        ContentValues cv = new ContentValues();
        cv.put(SchemaProvider.CLOMUN_NAME_BOTTLE_MESSAGE,
                StringUtil.nullToEmpty(bottleMsg));
        mSQLiteDatabase.insert(SchemaProvider.TABLE_NAME_MESSAGE_DATA, null, cv);
    }

    public void throwInitialData() {
        for (String str : Constants.INITIAL_DATA) {
            ContentValues cv = new ContentValues();
            cv.put(SchemaProvider.CLOMUN_NAME_BOTTLE_MESSAGE, str);
            mSQLiteDatabase.insert(SchemaProvider.TABLE_NAME_MESSAGE_DATA, null, cv);
        }
    }

    public int getBottleCount() {
        int count = 0;
        Cursor cursor = mSQLiteDatabase.rawQuery(SchemaProvider.SQL_SELECT_ALL_MESSAGE_DATA, null);
        if (null != cursor && cursor.moveToFirst()) {
            count = cursor.getCount();
        }
        return count;
    }

}

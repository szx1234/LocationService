package com.example.songzhixin.locationservice.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by songzhixin on 2017/7/19.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static final String CREATE_ACTIVITY =
            "create table activity ("
                    + "id integer,"
                    + "desc text,"
                    + "destination text"
                    + "primary key(id))";
    public static final String CREATE_LOCATION =
            "create table location ("
                    + "latitude real,"
                    + "longitude real,"
                    + "heading real,"
                    + "speed real)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACTIVITY);
        db.execSQL(CREATE_LOCATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

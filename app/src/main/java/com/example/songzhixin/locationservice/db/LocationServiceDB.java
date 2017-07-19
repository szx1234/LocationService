package com.example.songzhixin.locationservice.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.songzhixin.locationservice.model.ActivityDetail;
import com.example.songzhixin.locationservice.model.LocationDetail;

/**
 * Created by songzhixin on 2017/7/19.
 */

public class LocationServiceDB {
    public static final String DB_NAME = "LocationService";
    public static final int VERSION = 1;
    private static LocationServiceDB locationServiceDB;
    private SQLiteDatabase db;

    private LocationServiceDB(Context context) {
        MyDatabaseHelper helper = new MyDatabaseHelper(context, DB_NAME, null, VERSION);
        db = helper.getWritableDatabase();
    }

    public synchronized static LocationServiceDB getInstance(Context context) {
        if(locationServiceDB == null)
            locationServiceDB = new LocationServiceDB(context);
        return locationServiceDB;
    }

    public void saveActivityDetail(ActivityDetail activityContent) {

    }

    public void saveLocationDetail(LocationDetail locationDetail) {

    }

    public ActivityDetail queryActivityDetailById (int activityId) {
        return null;
    }

    public ArrayAdapter<LocationDetail> queryUersLocaitonDetail (int activityId) {
        return null;
    }

}

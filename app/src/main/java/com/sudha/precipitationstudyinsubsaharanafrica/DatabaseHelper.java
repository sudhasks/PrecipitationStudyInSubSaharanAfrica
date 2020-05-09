package com.sudha.precipitationstudyinsubsaharanafrica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import org.sqlite.database.sqlite.SQLiteDatabase;
import org.sqlite.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PrecipitationData.db";
    public static final String TABLE_NAME = "weather_data";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_PREC = "prec";

    Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        System.loadLibrary("sqliteX");
        this.context = context;
        getDatabase(context);
    }

    private SQLiteDatabase getDatabase(Context context) {

        SQLiteDatabase db = null;
        File databaseFile = context.getDatabasePath(DATABASE_NAME);
        db = SQLiteDatabase.openOrCreateDatabase(databaseFile,null);
        return db;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        getDatabase(context).execSQL("create table " + TABLE_NAME + " (" +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_DAY + " text, " +
                COLUMN_LATITUDE + " real, " +
                COLUMN_LONGITUDE + " real, " +
                COLUMN_PREC + "real"+
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        getDatabase(context).execSQL("drop  table if exists " + TABLE_NAME);
        onCreate(db);

    }
public boolean insertData(DataRecord dataRecord){
SQLiteDatabase db = getDatabase(context);
    ContentValues contentValues = new ContentValues();
    contentValues.put(COLUMN_DAY, dataRecord.getDayString("MM/DD/yyyy"));
    contentValues.put(COLUMN_LATITUDE,dataRecord.getLat());
    contentValues.put(COLUMN_LONGITUDE,dataRecord.getLon());
    contentValues.put(COLUMN_PREC,dataRecord.getPrec());

    long result = db.insert(TABLE_NAME, null, contentValues);
    return (result != -1);
}

    public Cursor getAllData() {
        SQLiteDatabase db = getDatabase(context);
        Cursor result = db.rawQuery("select * from " + TABLE_NAME, new String[] {});
        return result;
    }

    public double getData(String latitude, String longitude, Date date) {
        String day = new SimpleDateFormat("M/dd/yy").format(date);
        SQLiteDatabase db = getDatabase(context);
        Cursor result = db.rawQuery("select * from project_data_input " + " where lat = " + latitude
                + " and lon = " + longitude + " and time = '" + day + "'", null);
        result.moveToNext();
        return result.getDouble(4);
    }
}

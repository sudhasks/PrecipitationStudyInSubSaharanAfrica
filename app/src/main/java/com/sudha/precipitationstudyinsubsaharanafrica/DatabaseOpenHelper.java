package com.sudha.precipitationstudyinsubsaharanafrica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {

    public static final String DATABASE_NAME="PrecipitationDatanew.db";
   // public static final int DATABASE_VERSION=1;

    //constructor
    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
}

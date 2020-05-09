package com.sudha.precipitationstudyinsubsaharanafrica;

//we will use instance of this class to get the access to database

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    //private constructor so that object creation from outside the class is avoided
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);

    }

    //to return the single instance of database
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    // to open database
    public void open() {
        this.db = openHelper.getReadableDatabase();
    }

    // closing the database connection
    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

/*public String getPrecipitation(String latitude, String longitude){
Cursor c=db.rawQuery("select precipitation from prec_rec where lat='"+latitude+"' and lon='"+longitude+"'",new String[]{});
    StringBuffer buffer= new StringBuffer();
    while(c.moveToNext()){
        String prec=c.getString(0);
        buffer.append(""+prec);
    }
    return buffer.toString();

}

}*/

    private Cursor getDataCursor(String tableName, String latitude, String longitude, String day) {
        String query = "select * from " + tableName + " where lat = " + latitude
                + " and lon = " + longitude + " and time = '" + day + "'";
        Log.d("Specific query", query);
        return db.rawQuery(query, null);
    }

    private Cursor getApproxDataCursor(String tableName, String latitude, String longitude, String day) {
        Log.d("Fetch approx lat & lon", "original latitude : [ " + latitude + " ], longitude : [ " + longitude + " ]");
        Cursor result;
        double lat = Double.parseDouble(latitude);
        double lon = Double.parseDouble(longitude);
        String query = "select * from " + tableName + " where " +
                "lat > " + (lat - 0.001) + " and lat < " + (lat + 0.001) + " and lon > " + (lon - 0.001) + " and lon<" + (lon + 0.001) + " and time = '" + day + "'";
        Log.d("Approx query", query);
        result = db.rawQuery(query, null);
        return result;
    }

    public String getTableData(String tableName, String latitude, String longitude, Date date, String datePattern) {
        String day = new SimpleDateFormat(datePattern).format(date);
        Cursor result = getDataCursor(tableName, latitude, longitude, day);
        if (result.getCount() == 0) {
            result = getApproxDataCursor(tableName, latitude, longitude, day);
        }
        String tableData = "no Data";
        if (result.getCount() > 0) {
            result.moveToNext();
            //  tableData = result.getString(3);
            tableData = result.getString(3);
        } else {
            Log.d("No Data found", "Latitude : [ " + latitude + " ], Longitude : [ " + longitude + " ], Day : [ " + day + " ], Table : [ " + tableName + " ]");
        }
        return tableData;
    }
    public String getMinTableData(String tableName, String latitude, String longitude, Date date, String datePattern) {
        String day = new SimpleDateFormat(datePattern).format(date);
        Cursor result = getDataCursor(tableName, latitude, longitude, day);
        if (result.getCount() == 0) {
            result = getApproxDataCursor(tableName, latitude, longitude, day);
        }
        String tableData = "no Data";
        if (result.getCount() > 0) {
            result.moveToNext();
            //  tableData = result.getString(3);
            tableData = result.getString(5);
        } else {
            Log.d("No Data found", "Latitude : [ " + latitude + " ], Longitude : [ " + longitude + " ], Day : [ " + day + " ], Table : [ " + tableName + " ]");
        }
        return tableData;
    }

    public String getMaxTableData(String tableName, String latitude, String longitude, Date date, String datePattern) {
        String day = new SimpleDateFormat(datePattern).format(date);
        Cursor result = getDataCursor(tableName, latitude, longitude, day);
        if (result.getCount() == 0) {
            result = getApproxDataCursor(tableName, latitude, longitude, day);
        }
        String tableData = "no Data";
        if (result.getCount() > 0) {
            result.moveToNext();
            //  tableData = result.getString(3);
            tableData = result.getString(6);
        } else {
            Log.d("No Data found", "Latitude : [ " + latitude + " ], Longitude : [ " + longitude + " ], Day : [ " + day + " ], Table : [ " + tableName + " ]");
        }
        return tableData;
    }

    public String getAvgTableData(String tableName, String latitude, String longitude, Date date, String datePattern) {
        String day = new SimpleDateFormat(datePattern).format(date);
        Cursor result = getDataCursor(tableName, latitude, longitude, day);
        if (result.getCount() == 0) {
            result = getApproxDataCursor(tableName, latitude, longitude, day);
        }
        String tableData = "no Data";
        if (result.getCount() > 0) {
            result.moveToNext();
            //  tableData = result.getString(3);
            tableData = result.getString(4);
        } else {
            Log.d("No Data found", "Latitude : [ " + latitude + " ], Longitude : [ " + longitude + " ], Day : [ " + day + " ], Table : [ " + tableName + " ]");
        }
        return tableData;
    }

    public String getTotalTableData(String tableName, String latitude, String longitude, Date date, String datePattern) {
        String day = new SimpleDateFormat(datePattern).format(date);
        Cursor result = getDataCursor(tableName, latitude, longitude, day);
        if (result.getCount() == 0) {
            result = getApproxDataCursor(tableName, latitude, longitude, day);
        }
        String tableData = "no Data";
        if (result.getCount() > 0) {
            result.moveToNext();
            //  tableData = result.getString(3);
            tableData = result.getString(7);
        } else {
            Log.d("No Data found", "Latitude : [ " + latitude + " ], Longitude : [ " + longitude + " ], Day : [ " + day + " ], Table : [ " + tableName + " ]");
        }
        return tableData;
    }

    public String getprecipitationdata(String latitude, String longitude, Date date) {
        String tableData = getTableData("project_data_input", latitude, longitude, date, "M/d/yyyy");
        return tableData;
    }
    public String getMinPrecipitationdata(String latitude, String longitude, Date date) {
        String tableData = getMinTableData("project_data_input", latitude, longitude, date, "M/d/yyyy");
        return tableData;
    }
    public String getMaxPrecipitationdata(String latitude, String longitude, Date date) {
        String tableData = getMaxTableData("project_data_input", latitude, longitude, date, "M/d/yyyy");
        return tableData;
    }
    public String getAvgPrecipitationdata(String latitude, String longitude, Date date) {
        String tableData = getAvgTableData("project_data_input", latitude, longitude, date, "M/d/yyyy");
        return tableData;
    }
    public String getTotalPrecipitationdata(String latitude, String longitude, Date date) {
        String tableData = getTotalTableData("project_data_input", latitude, longitude, date, "M/d/yyyy");
        return tableData;
    }
}




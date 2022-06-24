package com.example.driverhiringapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper2  extends SQLiteOpenHelper {


    public static final String DBNAME = "Driver.db";

    public DBHelper2(Context context) {

        super(context, DBNAME, null, 2);

    }

    public void onCreate(SQLiteDatabase MyDB) {

        String SQL_CREATE_DRIVER_TABLE =  "CREATE TABLE " + UserContract.DriverEntry.TABLE_NAME + " ("
                + UserContract.DriverEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserContract.DriverEntry.COLUMN_DRIVER_NAME + " TEXT , "
                + UserContract.DriverEntry.COLUMN_DRIVER_MOBILE_NUMBER + " INTEGER , "
                + UserContract.DriverEntry.COLUMN_DRIVER_EMAIL_ID + " TEXT , "
                + UserContract.DriverEntry.COLUMN_DRIVER_PRICE_PER_HOUR + " INTEGER , "
                + UserContract.DriverEntry.COLUMN_DRIVER_EXPERIENCE_IN_MONTHS + " INTEGER , "
                + UserContract.DriverEntry.COLUMN_DRIVER_PINCODE+ " INTEGER , "
                + UserContract.DriverEntry.COLUMN_DRIVER_PASSWORD+ " TEXT , "
                + UserContract.DriverEntry.COLUMN_DRIVER_CONFIRM_PASSWORD+ " TEXT );";


        MyDB.execSQL(SQL_CREATE_DRIVER_TABLE);

    }
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists "+ UserContract.DriverEntry.TABLE_NAME);
        onCreate(MyDB);

    }

    public Boolean insertData(String drivername, String password, String confirmPassword, Long mobile_number, String emailid, int pricePerHour, int totalDrivingExperience, int pincode){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserContract.DriverEntry.COLUMN_DRIVER_NAME, drivername);
        values.put(UserContract.DriverEntry.COLUMN_DRIVER_PASSWORD, password);
        values.put(UserContract.DriverEntry.COLUMN_DRIVER_CONFIRM_PASSWORD, confirmPassword);
        values.put(UserContract.DriverEntry.COLUMN_DRIVER_MOBILE_NUMBER, mobile_number);
        values.put(UserContract.DriverEntry.COLUMN_DRIVER_EMAIL_ID, emailid);
        values.put(UserContract.DriverEntry.COLUMN_DRIVER_PRICE_PER_HOUR, pricePerHour);
        values.put(UserContract.DriverEntry.COLUMN_DRIVER_EXPERIENCE_IN_MONTHS, totalDrivingExperience);
        values.put(UserContract.DriverEntry.COLUMN_DRIVER_PINCODE,pincode);
        long result = MyDB.insert(UserContract.DriverEntry.TABLE_NAME, null, values);

        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String drivername) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+UserContract.DriverEntry.TABLE_NAME + " where "+UserContract.DriverEntry.COLUMN_DRIVER_NAME+" = ?", new String[]{drivername});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public Boolean checkusernamepassword(String drivername, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+UserContract.DriverEntry.TABLE_NAME + " where "+UserContract.DriverEntry.COLUMN_DRIVER_NAME+" = ? and "+ UserContract.DriverEntry.COLUMN_DRIVER_PASSWORD+" = ?", new String[] {drivername,password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }



}


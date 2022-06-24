package com.example.driverhiringapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "User.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        String SQL_CREATE_USER_TABLE =  "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " ("
                + UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserContract.UserEntry.COLUMN_USER_NAME + " TEXT , "
                + UserContract.UserEntry.COLUMN_USER_PASSWORD + " TEXT , "
                + UserContract.UserEntry.COLUMN_USER_CONFIRM_PASSWORD + " TEXT , "
                + UserContract.UserEntry.COLUMN_USER_MOBILE_NUMBER + " INTEGER , "
                + UserContract.UserEntry.COLUMN_USER_EMAIL_ID + " TEXT , "
                + UserContract.UserEntry.COLUMN_USER_CITY+ " TEXT , "
                + UserContract.UserEntry.COLUMN_USER_STATE+ " TEXT , "
                + UserContract.UserEntry.COLUMN_USER_PINCODE+ " INTEGER );";



        String SQL_CREATE_CARS_TABLE =  "CREATE TABLE " + UserContract.CarEntry.TABLE_NAME + " ("
                + UserContract.CarEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserContract.CarEntry.COLUMN_CAR_BRAND + " TEXT , "
                + UserContract.CarEntry.COLUMN_CAR_MODEL + " TEXT , "
                + UserContract.CarEntry.COLUMN_CAR_REGISTRATION_NUMBER + " INTEGER , "
                + UserContract.CarEntry.COLUMN_CAR_YEAR_OF_PURCHASING + " INTEGER , "
                + UserContract.CarEntry.COLUMN_CAR_CHASIS_NUMBER + " TEXT , "
                + UserContract.CarEntry.COLUMN_CAR_TYPE_OF_CAR + " TEXT , "
                + UserContract.CarEntry.COLUMN_CAR_TRANSMISSION + " TEXT , "
                + UserContract.CarEntry.COLUMN_CAR_FUEL_TYPE  + " TEXT );";

        // Execute the SQL statement
        MyDB.execSQL(SQL_CREATE_USER_TABLE);
        MyDB.execSQL(SQL_CREATE_CARS_TABLE);



//        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists "+ UserContract.UserEntry.TABLE_NAME);
        MyDB.execSQL("drop Table if exists "+ UserContract.CarEntry.TABLE_NAME);
        onCreate(MyDB);

    }

    public Boolean insertData(String username, String password, String confirmPassword, Long mobile_number, String emailid, String city, String state, int pincode){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COLUMN_USER_NAME, username);
        values.put(UserContract.UserEntry.COLUMN_USER_PASSWORD, password);
        values.put(UserContract.UserEntry.COLUMN_USER_CONFIRM_PASSWORD, confirmPassword);
        values.put(UserContract.UserEntry.COLUMN_USER_MOBILE_NUMBER, mobile_number);
        values.put(UserContract.UserEntry.COLUMN_USER_EMAIL_ID, emailid);
        values.put(UserContract.UserEntry.COLUMN_USER_CITY, city);
        values.put(UserContract.UserEntry.COLUMN_USER_STATE, state);
        values.put(UserContract.UserEntry.COLUMN_USER_PINCODE, pincode);
        long result = MyDB.insert(UserContract.UserEntry.TABLE_NAME, null, values);

        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insertCarData(String carbrand, String carmodel, int registration_no, int yearOfPurchasing, String chasisNumber, String selectedType, String selectedTransmission, String selectedfuelType){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserContract.CarEntry.COLUMN_CAR_BRAND, carbrand);
        values.put(UserContract.CarEntry.COLUMN_CAR_MODEL, carmodel);
        values.put(UserContract.CarEntry.COLUMN_CAR_REGISTRATION_NUMBER, registration_no);
        values.put(UserContract.CarEntry.COLUMN_CAR_YEAR_OF_PURCHASING, yearOfPurchasing);
        values.put(UserContract.CarEntry.COLUMN_CAR_CHASIS_NUMBER, chasisNumber);
        values.put(UserContract.CarEntry.COLUMN_CAR_TYPE_OF_CAR, selectedType);
        values.put(UserContract.CarEntry.COLUMN_CAR_TRANSMISSION, selectedTransmission);
        values.put(UserContract.CarEntry.COLUMN_CAR_FUEL_TYPE, selectedfuelType);
        long result = MyDB.insert(UserContract.CarEntry.TABLE_NAME, null, values);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+UserContract.UserEntry.TABLE_NAME + " where "+UserContract.UserEntry.COLUMN_USER_NAME+" = ?", new String[]{username});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+UserContract.UserEntry.TABLE_NAME + " where "+UserContract.UserEntry.COLUMN_USER_NAME+" = ? and "+ UserContract.UserEntry.COLUMN_USER_PASSWORD+" = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

package com.example.driverhiringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.driverhiringapp.data.DBHelper;
import com.example.driverhiringapp.data.DBHelper2;
import com.example.driverhiringapp.data.UserContract;

public class ShowTableActivity3 extends AppCompatActivity {

    private DBHelper2 DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_table3);

        DB = new DBHelper2(this);


        show_table();
    }
    private void show_table() {

        TextView displayView = (TextView) findViewById(R.id.show_table_text_view3);
        SQLiteDatabase db =  DB.getReadableDatabase();
        String projection[] = {
                UserContract.DriverEntry._ID,
                UserContract.DriverEntry.COLUMN_DRIVER_NAME,
                UserContract.DriverEntry.COLUMN_DRIVER_PASSWORD,
                UserContract.DriverEntry.COLUMN_DRIVER_PRICE_PER_HOUR
        };
        Cursor cursor = db.query(UserContract.DriverEntry.TABLE_NAME,projection,null,null,
                null,null,null);
        try {
            displayView.setText("The driver table contains " + cursor.getCount() + " drivers.\n\n");
            displayView.append(UserContract.DriverEntry._ID + " - " +
                    UserContract.DriverEntry.COLUMN_DRIVER_NAME + " - " +
                    UserContract.DriverEntry.COLUMN_DRIVER_PASSWORD+ " - " +
                    UserContract.DriverEntry.COLUMN_DRIVER_PRICE_PER_HOUR+ " - "
            );

            int idColumnIndex = cursor.getColumnIndex(UserContract.DriverEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(UserContract.DriverEntry.COLUMN_DRIVER_NAME);
            int passwordColumnIndex = cursor.getColumnIndex(UserContract.DriverEntry.COLUMN_DRIVER_PASSWORD);
            int priceperhourColumnIndex = cursor.getColumnIndex(UserContract.DriverEntry.COLUMN_DRIVER_PRICE_PER_HOUR);

            while (cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);// These functions  will give you the values remember
                String currentname = cursor.getString(nameColumnIndex);
                String currentpassword = cursor.getString(passwordColumnIndex);
                int currentpriceperhour = cursor.getInt(priceperhourColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentname + " - " +
                        currentpassword + " - " +
                        currentpriceperhour + " - "));
            }
            int currentRowPosition = cursor.getPosition();
            String currentPosition = String.valueOf(currentRowPosition);
            Log.i("tag","current position of cursor is "+currentPosition);
        }
        finally {
            cursor.close();
        }


    }
}
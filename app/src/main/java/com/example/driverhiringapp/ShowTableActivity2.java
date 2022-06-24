package com.example.driverhiringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.driverhiringapp.data.DBHelper;
import com.example.driverhiringapp.data.UserContract;

public class ShowTableActivity2 extends AppCompatActivity {


    private DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_table2);

        DB = new DBHelper(this);


        show_table();
    }

    private void show_table() {

        TextView displayView = (TextView) findViewById(R.id.show_table_text_view2);
        SQLiteDatabase db =  DB.getReadableDatabase();
        String projection[] = {
                UserContract.CarEntry._ID,
                UserContract.CarEntry.COLUMN_CAR_BRAND,
                UserContract.CarEntry.COLUMN_CAR_MODEL,
                UserContract.CarEntry.COLUMN_CAR_REGISTRATION_NUMBER
        };
        Cursor cursor = db.query(UserContract.CarEntry.TABLE_NAME,projection,null,null,
                null,null,null);
        try {
            displayView.setText("The car table contains " + cursor.getCount() + " cars.\n\n");
            displayView.append(UserContract.CarEntry._ID + " - " +
                    UserContract.CarEntry.COLUMN_CAR_BRAND + " - " +
                    UserContract.CarEntry.COLUMN_CAR_MODEL+ " - " +
                    UserContract.CarEntry.COLUMN_CAR_REGISTRATION_NUMBER+ " - "
            );

            int idColumnIndex = cursor.getColumnIndex(UserContract.CarEntry._ID);
            int brandColumnIndex = cursor.getColumnIndex(UserContract.CarEntry.COLUMN_CAR_BRAND);
            int modelColumnIndex = cursor.getColumnIndex(UserContract.CarEntry.COLUMN_CAR_MODEL);
            int registrationNumberColumnIndex = cursor.getColumnIndex(UserContract.CarEntry.COLUMN_CAR_REGISTRATION_NUMBER);

            while (cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);// These functions  will give you the values remember
                String currentbrand = cursor.getString(brandColumnIndex);
                String currentmodel = cursor.getString(modelColumnIndex);
                int currentCarRegistrationNumber = cursor.getInt(registrationNumberColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentbrand + " - " +
                        currentmodel + " - " +
                        currentCarRegistrationNumber + " - "));
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
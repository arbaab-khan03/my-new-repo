package com.example.driverhiringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.driverhiringapp.data.DBHelper;
import com.example.driverhiringapp.data.UserContract;

public class ShowTableActivity extends AppCompatActivity {


    private DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_table);

        DB = new DBHelper(this);


        show_table();
    }

    private void show_table() {



        TextView displayView = (TextView) findViewById(R.id.show_table_text_view);
        SQLiteDatabase db =  DB.getReadableDatabase();
        String projection[] = {
                UserContract.UserEntry._ID,
                UserContract.UserEntry.COLUMN_USER_NAME,
                UserContract.UserEntry.COLUMN_USER_PASSWORD
        };
        Cursor cursor = db.query(UserContract.UserEntry.TABLE_NAME,projection,null,null,
                null,null,null);
        try {
            displayView.setText("The user table contains " + cursor.getCount() + " users.\n\n");
            displayView.append(UserContract.UserEntry._ID + " - " +
                    UserContract.UserEntry.COLUMN_USER_NAME + " - " +
                    UserContract.UserEntry.COLUMN_USER_PASSWORD + " - "
            );

            int idColumnIndex = cursor.getColumnIndex(UserContract.UserEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_NAME);
            int passwordColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_PASSWORD);

            while (cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);// These functions  will give you the values remember
                String currentName = cursor.getString(nameColumnIndex);
                String currentpassword = cursor.getString(passwordColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentpassword + " - "));
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

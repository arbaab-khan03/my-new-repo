package com.example.driverhiringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DriverProfile extends AppCompatActivity {


    // This is a page (Activity) which opens when driver clicks on the profile tab in the left navigation
    // drawer of their home screen, It contains information like
    // driver's personal information
    // Driver's Address section
    // other deatls section
    // this section named other details contains information like
    // Total Experience, Hourly charges
    // below there are three buttons called document, Experience, Trips
    // on clicking on document tab
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);
    }
}
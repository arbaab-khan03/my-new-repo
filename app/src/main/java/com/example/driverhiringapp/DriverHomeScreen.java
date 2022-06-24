package com.example.driverhiringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DriverHomeScreen extends AppCompatActivity {



    // This is the home screen of driver
    // At the top it displays text like Incomplete profile, Please update your profile
    // to start getting rides

    // At the centre of this page there is a card showing current trips for the driver if there are
    // else if there are no current trips it shows text something like
    // "Relax! there are no trips for today"
    // and there is a background color as well on this page similar to blue with designing.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home_screen);
    }
}
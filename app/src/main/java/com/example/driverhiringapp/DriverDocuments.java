package com.example.driverhiringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DriverDocuments extends AppCompatActivity {

    // If there are no documents found, there is a button called add document
    // at the bottom of this page
    // by clicking on this button the driver can upload their photo of driving license
    // from the Gallery
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_documents);
    }
}
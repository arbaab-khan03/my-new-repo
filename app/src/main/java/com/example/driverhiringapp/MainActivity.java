package com.example.driverhiringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void hireDriverButtonClicked(View view){
        Button hireButton = (Button) findViewById(R.id.hire_driver_button);
        startActivity(new Intent(this,OwnerLogin.class));
    }

    public void workAsADriverButtonClicked(View view){
        Button workasdriver = (Button) findViewById(R.id.work_as_a_driver_button);
        startActivity(new Intent(this,DriverLogin.class));
    }

}





















































































package com.example.driverhiringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.navigation.NavigationView;

public class OwnerHomeScreen extends AppCompatActivity {


    // This is the page where the user lands when login in to their account
    // here you have a left navigation drawer where all the activities are showing
    // and at the bottom of the page you have a button named as book a ride on clicking
    // this button user lands to the activity called Book Ride


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_home);
        NavigationView navigationview = findViewById(R.id.nav_view);

        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_home){
                    startActivity(new Intent(OwnerHomeScreen.this, OwnerHomeScreen.class));
                    Toast.makeText(OwnerHomeScreen.this, "home", Toast.LENGTH_SHORT).show();

                }
                else if(item.getItemId() == R.id.nav_logout){
                    Intent intent = new Intent(OwnerHomeScreen.this,OwnerLogin.class);
                    startActivity(intent);

//                    // the above line can also be written as
//                    startActivity(new Intent(OwnerHomeScreen.this, MainActivity.class));
                }
                else if(item.getItemId() == R.id.nav_cars){
                    Intent intent = new Intent(OwnerHomeScreen.this,UserCars.class);
                    startActivity(intent);
                }
                else if(item.getItemId() == R.id.nav_myprofile){
                    Intent intent = new Intent(OwnerHomeScreen.this,UserMyProfile.class);
                    startActivity(intent);
                }
                else if(item.getItemId() == R.id.nav_ride_history){
                    Intent intent = new Intent(OwnerHomeScreen.this,UserRideHistory.class);
                    startActivity(intent);
                }
                else if(item.getItemId() == R.id.nav_Transactions){
                    Intent intent = new Intent(OwnerHomeScreen.this,UserTransaction.class);
                    startActivity(intent);
                }

                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



    }

    public void BookRide(View view){

        Button book_ride = (Button) findViewById(R.id.book_ride);

        startActivity(new Intent(OwnerHomeScreen.this, UserBookRide.class));
        Toast.makeText(getApplicationContext(),"Welcome to Book ride activity",Toast.LENGTH_SHORT).show();

    }


}
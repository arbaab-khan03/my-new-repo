package com.example.driverhiringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class UserCars extends AppCompatActivity {



    // here you will show all the cars added into your database if no car is added
    // give an option to user to add a car using add a car button or something like (+) button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_car);


        NavigationView navigationview = findViewById(R.id.nav_view);


        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_home){
                    startActivity(new Intent(UserCars.this, OwnerHomeScreen.class));
                    Toast.makeText(UserCars.this, "home", Toast.LENGTH_SHORT).show();

                }
                else if(item.getItemId() == R.id.nav_logout){
                    Intent intent = new Intent(UserCars.this,OwnerLogin.class);
                    startActivity(intent);

//                    // the above line can also be written as
//                    startActivity(new Intent(OwnerHomeScreen.this, MainActivity.class));
                }
                else if(item.getItemId() == R.id.nav_cars){
                    Intent intent = new Intent(UserCars.this,UserCars.class);
                    startActivity(intent);

//
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



    }

    public void addCarButtonClick (View view){

        Button add_car_button = (Button) findViewById(R.id.add_car_button);
        Intent intent = new Intent(UserCars.this, AddCarPage.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "You can now add cars",Toast.LENGTH_LONG).show();
    }
}
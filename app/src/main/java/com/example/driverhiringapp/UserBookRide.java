package com.example.driverhiringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;


public class UserBookRide extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {


    // this page has three fields like
    //  1.Select car (from the cars you have added into your database)

    // the next field is Places Selected
    // this field places selected opens a list of checkboxes like
    // high pass roads, Mountain ranges, Hill Station, Night driving, Long Highways, off roading, snow roads
    // below you have two buttons called cancel and okay
    // the third and last field is Number of days
    // on clicking number of days it will accept number input only from user
    // below this you have a button called search driver
    // on clicking this button there is a loading animation text appears on it as "Finding drivers, Please wait"
    // and then application will then present a list of drivers like a card along with details
    // driver name, contact number, price per hour, rating and a button in the bottom of the car
    // called Book Ride on clicking this button a google Map activity will open
    // which allow owner to select their destination
    // on the bottom of this activity there is a button called CONFIRM RIDE
    // on clicking this button a payment card is open
    // containing details like
    // name on card, card number, Expiry year/ month and CVV number
    // and on the top of this card the price is there called as Total Amount = something
    // at the bottom of the card there is a button called Pay now

    TextView select_place;
    boolean[] selectedPlace;
    ArrayList<Integer> placeList = new ArrayList<>();
    String[] placeArray = {"High Pass Roads","Mountain Ranges","Hill Station","Night Driving","Long highways","Off-Roading","Snow-Road"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_book_ride_navigation_drawer);


        Spinner spinner = (Spinner) findViewById(R.id.cars_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cars_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        NavigationView navigationview = findViewById(R.id.nav_view);


        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_home){
                    startActivity(new Intent(UserBookRide.this, OwnerHomeScreen.class));
                    Toast.makeText(UserBookRide.this, "home", Toast.LENGTH_SHORT).show();

                }
                else if(item.getItemId() == R.id.nav_logout){
                    Intent intent = new Intent(UserBookRide.this,OwnerLogin.class);
                    startActivity(intent);

//                    // the above line can also be written as
//                    startActivity(new Intent(OwnerHomeScreen.this, MainActivity.class));
                }
                else if(item.getItemId() == R.id.nav_cars){
                    Intent intent = new Intent(UserBookRide.this,UserCars.class);
                    startActivity(intent);

//
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



        // inside onCreate method
        TextView select_place = findViewById(R.id.places_selected);
        selectedPlace = new boolean[placeArray.length];
        select_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize alert dialog box
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        UserBookRide.this
                );
                //set title
                builder.setTitle("Select Place");
                //set dialog non cancelable
                builder.setCancelable(false);
                builder.setMultiChoiceItems(placeArray, selectedPlace, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            // when checkbox selected
                            // add position in placeList
                            placeList.add(i);
                            // placelist
                            Collections.sort(placeList);

                        }
                        else{
                            // when checkbox unselected
                            // remove position from place list
                            placeList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        //use for loop
                        for(int j=0;j<placeList.size();j++){
                            //concat array value
                            stringBuilder.append(placeArray[placeList.get(j)]);
                            //check condition
                            if(j!=placeList.size()-1){
                                // when j value not equal to place list size -1
                                // Add comma
                                stringBuilder.append(", ");
                            }
                        }
                        //set text on text view
                        select_place.setText(stringBuilder.toString());

                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for(int j=0;j<selectedPlace.length;j++){
                            // Remove all selection
                            selectedPlace[j] = false;
                            // clear place list
                            placeList.clear();
                            //clear text view
                            select_place.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           //  parent.getItemAtPosition(0);
        Toast.makeText(this,"You have selected item at position "+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        //parent.getItemAtPosition(0);
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
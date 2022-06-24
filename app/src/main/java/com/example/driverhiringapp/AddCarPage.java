package com.example.driverhiringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.driverhiringapp.data.DBHelper;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class AddCarPage extends AppCompatActivity {


    // here you  have to add car by entering it's details like make and model of the car
    // then click on add car button user will transfer to back UserCars page showing the
    // something like a card of added car
    private DBHelper db;



    String selected_type="";
    String selected_Transmission="";
    String selected_fuelType="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car_page_navigation_drawer);



        Button add_car = (Button) findViewById(R.id.add_car_details_button);




        NavigationView navigationview = findViewById(R.id.nav_view);

        db = new DBHelper(this);

        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_home){
                    startActivity(new Intent(AddCarPage.this, OwnerHomeScreen.class));
                    Toast.makeText(AddCarPage.this, "home", Toast.LENGTH_SHORT).show();

                }
                else if(item.getItemId() == R.id.nav_logout){
                    Intent intent = new Intent(AddCarPage.this,OwnerLogin.class);
                    startActivity(intent);

//                    // the above line can also be written as
//                    startActivity(new Intent(OwnerHomeScreen.this, MainActivity.class));
                }
                else if(item.getItemId() == R.id.nav_cars){
                    Intent intent = new Intent(AddCarPage.this,UserCars.class);
                    startActivity(intent);

//
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // inside onCreate method

        Spinner choose_car = (Spinner) findViewById(R.id.choose_car_type);
        Spinner choose_transmission = (Spinner) findViewById(R.id.choose_transmission);
        Spinner choose_fuel_type = (Spinner) findViewById(R.id.choose_fuel_type);

//        <item>HatchBack</item>
//        <item>Sedan</item>
//        <item>Luxury Sedan</item>
//        <item>Coupe</item>
//        <item>Sports Car</item>
//        <item>MUV</item>
        ArrayList<String> carTypes = new ArrayList<>();
        carTypes.add("HatchBack");
        carTypes.add("Sedan");
        carTypes.add("Luxury Sedan");
        carTypes.add("Coupe");
        carTypes.add("Sports Car");
        carTypes.add("MUV");

        // Array list for car Transmission
        ArrayList<String> carTransmission = new ArrayList<>();
        carTransmission.add("Automatic");
        carTransmission.add("Manual");

        // Array list for fuel Types
        ArrayList<String> fuelTypes = new ArrayList<>();
        fuelTypes.add("Petrol");
        fuelTypes.add("Diesel");
        fuelTypes.add("CNG");

//        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddCarPage.this,
//                android.R.layout.simple_expandable_list_item_1,
//                getResources().getStringArray(R.array.Type_of_car));
//        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choose_car.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,
               carTypes));
        choose_car.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_type = carTypes.get(position);
                Toast.makeText(AddCarPage.this,"You have selected "+selected_type,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddCarPage.this,"please select one item",Toast.LENGTH_SHORT).show();
            }
        });



        choose_transmission.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,
                carTransmission));
        choose_transmission.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_Transmission = carTransmission.get(position);
                Toast.makeText(AddCarPage.this,"You have selected transmission: "+selected_Transmission,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddCarPage.this,"please select one item",Toast.LENGTH_SHORT).show();
            }
        });


        choose_fuel_type.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,
                fuelTypes));
        choose_fuel_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_fuelType = fuelTypes.get(position);
                Toast.makeText(AddCarPage.this,"You have selected fuel type: "+selected_fuelType,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddCarPage.this,"please select one item",Toast.LENGTH_SHORT).show();
            }
        });

    }
    // outside onCreate method but inside Main class



    public void addCarDetails(View view){
        // assigning variables
        EditText carbrand = (EditText) findViewById(R.id.car_brand);
        EditText carmodel = (EditText) findViewById(R.id.car_model);
        EditText carRegistrationNumber = (EditText) findViewById(R.id.car_registration_number);
        EditText carChasisNumber = (EditText) findViewById(R.id.car_chasis_number);
        EditText carYearOfPurchasing = (EditText) findViewById(R.id.car_year_of_purchasing);
//        Spinner choose_car = (Spinner) findViewById(R.id.choose_car_type);
//        Spinner choose_transmission = (Spinner) findViewById(R.id.choose_transmission);
//        Spinner choose_fuel_type = (Spinner) findViewById(R.id.choose_fuel_type);




        String car_brand = carbrand.getText().toString().trim();
        String car_model = carmodel.getText().toString().trim();
        String car_registration_number = carRegistrationNumber.getText().toString().trim();

        String car_chasis_number = carChasisNumber.getText().toString().trim();
        String car_year_of_purchasing = carYearOfPurchasing.getText().toString().trim();




        SQLiteDatabase s = db.getWritableDatabase();
        if(car_brand.isEmpty()==false && car_model.isEmpty()==false && car_registration_number.isEmpty()==false && car_chasis_number.isEmpty()==false && car_year_of_purchasing.isEmpty()==false && selected_type.isEmpty()==false && selected_Transmission.isEmpty()==false && selected_fuelType.isEmpty()==false)
        {
            int registration_number_integer = Integer.parseInt(car_registration_number);
            int year_of_purchasing_integer = Integer.parseInt(car_year_of_purchasing);
            Boolean insertedCar = db.insertCarData(car_brand,car_model,registration_number_integer,year_of_purchasing_integer,car_chasis_number,selected_type,selected_Transmission,selected_fuelType);
            if(insertedCar==true){
                startActivity(new Intent(AddCarPage.this, UserCars.class));
                Toast.makeText(AddCarPage.this,"car added successfully",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(AddCarPage.this,"car not added",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Please enter all the fields",Toast.LENGTH_SHORT).show();
        }





    }
}
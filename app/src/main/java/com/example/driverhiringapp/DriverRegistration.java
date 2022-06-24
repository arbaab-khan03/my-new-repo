package com.example.driverhiringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.driverhiringapp.data.DBHelper;
import com.example.driverhiringapp.data.DBHelper2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DriverRegistration extends AppCompatActivity {

    // It will take the details of the driver to create his account
    // containing information like  name, DOB, Gender, contact number, email, Price per hour
    // Total driving experience , Password
    // at the bottom there is a button called submit
    // on clicking this button the driver will land to the another activity
    // which is basically a driver home screen
    // which contains text at the top displaying
    // Incomplete profile, Please update your profile to start getting rides
    // and at the centre there is image like card showing an image and text
    //"Relax! there are no trips for today"
    // Similar to owner home screen there is a left navigation drawer
    // list text which is itself a activity like
    // Homes, Rides, MyProfile, Transactions, Logout
    // after clicking on myProfile

    EditText drivername;
    EditText drivermobilenumber;
    EditText driveremail;
    EditText drivergender;
    EditText driverpriceperhour;
    EditText drivertotaldrivingexperience;
    EditText driverpincode;
    EditText driverpassword;
    EditText driverconfirmpassword;
    Button userSignUp;


    final Calendar myCalendar = Calendar.getInstance();
    EditText driverdateofbirth;
    private RadioGroup radioGroup;
    boolean isAllFieldsChecked = false;
    private DBHelper2 DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registration);

        DB = new DBHelper2(this);

        drivername = (EditText) findViewById(R.id.driver_name);
        drivermobilenumber = (EditText) findViewById(R.id.driver_mobile_number);
        driveremail = (EditText) findViewById(R.id.driver_email_id);
        driverdateofbirth = (EditText) findViewById(R.id.driver_dateOfBirth);
//        drivergender = (EditText) findViewById(R.id.driver_gender);
        driverpriceperhour = (EditText) findViewById(R.id.driver_price_per_hour);
        drivertotaldrivingexperience = (EditText) findViewById(R.id.driver_total_driving_experience);
        driverpincode = (EditText) findViewById(R.id.drivers_pincode);
        driverpassword = (EditText) findViewById(R.id.driver_password);
        driverconfirmpassword = (EditText) findViewById(R.id.driver_confirm_password);
        userSignUp = (Button) findViewById(R.id.driver_signup_button);

        driverdateofbirth = (EditText) findViewById(R.id.driver_dateOfBirth);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }

        };
        driverdateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DriverRegistration.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        userSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //Button SignUPButtonForDriver = (Button) findViewById(R.id.driver_signup_button);

            isAllFieldsChecked = checkAllFields();

            if (isAllFieldsChecked) {

                // Dummy data to check
                // below is the main code to insert data remember


                String user = drivername.getText().toString();
                String pass = driverpassword.getText().toString();
                String confirm_password = driverconfirmpassword.getText().toString();
                String mobile_number_string = drivermobilenumber.getText().toString();
                long mobile_number = Long.parseLong(mobile_number_string);
                String emailid = driveremail.getText().toString();
                String driver_price_per_hour = driverpriceperhour.getText().toString();
                int price_per_hour_integer = Integer.parseInt(driver_price_per_hour);
                String total_driving_experience = drivertotaldrivingexperience.getText().toString();
                int total_driving_experience_integer = Integer.parseInt(total_driving_experience);
                // it should be in months only
                String pincode_string = driverpincode.getText().toString();
                int pincode = Integer.parseInt(pincode_string);

//        startActivity(new Intent(this,DriverLogin.class));
//        Toast.makeText(this,"Now login with your name and password", Toast.LENGTH_SHORT).show();

                if (user.equals("") || pass.equals("") || confirm_password.equals(""))
                    Toast.makeText(DriverRegistration.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(confirm_password)) {
                        Boolean checkUser = DB.checkusername(user);
                        if (checkUser == false) {
                            Boolean insert = DB.insertData(user, pass, confirm_password, mobile_number, emailid, price_per_hour_integer, total_driving_experience_integer, pincode);
                            if (insert == true) {
                                Toast.makeText(DriverRegistration.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), DriverHomeScreen.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(DriverRegistration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(DriverRegistration.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DriverRegistration.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    });
}

    private boolean checkAllFields() {
        if (drivername.length() == 0) {
            drivername.setError("This field is required");
            return false;
        }
        if (driveremail.length() == 0) {
            driveremail.setError("Email is required");
            return false;
        }
        if (!(driveremail.getText().toString().contains("@")) || !(driveremail.getText().toString().contains(".com"))) {
            driveremail.setError("please enter valid email id");
            return false;
        }
        if (driverpincode.length() > 6) {
            driverpincode.setError("pincode should not be greater than 6 digits");
            return false;
        }

        if (driverpassword.length() == 0) {
            driverpassword.setError("Password is required");
            return false;
        }
        if (!(driverpassword.getText().toString().equals(driverconfirmpassword.getText().toString()))) {
            driverconfirmpassword.setError("Password does not match");
            return false;
        }
        if (drivermobilenumber.length() < 10 || drivermobilenumber.length() > 10) {
            drivermobilenumber.setError("length should be exactly 10");
            return false;
        } else if (driverpassword.length() < 8) {
            driverpassword.setError("Password must be minimum 8 characters");
            return false;
        }
        // after all validation return true.
        return true;
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        driverdateofbirth.setText(dateFormat.format(myCalendar.getTime()));
    }

    public void showTable3(View view){
//        int count=3;
//        Button showTable3 = (Button) findViewById(R.id.driver_show_table_button);
//        startActivity(new Intent(this,CheckPasswordActivity.class));
//        CheckPasswordActivity c3 = new CheckPasswordActivity(count);
//        //c3.sendToPage(count);
        startActivity(new Intent(DriverRegistration.this,ShowTableActivity3.class));
    }
}
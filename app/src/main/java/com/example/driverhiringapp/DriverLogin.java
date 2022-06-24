package com.example.driverhiringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.driverhiringapp.data.DBHelper;
import com.example.driverhiringapp.data.DBHelper2;

public class DriverLogin extends AppCompatActivity {

    EditText driverName;
    EditText driverPassword;
//    Button DriverSignIn;

    DBHelper2 DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        driverName = (EditText) findViewById(R.id.driver_inputname);
        driverPassword = (EditText) findViewById(R.id.driver_inputPassword);
//        DriverSignIn = (Button) findViewById(R.id.driver_login);
        DB = new DBHelper2(this);

    }
    public void createAccountDrivers(View view){

        Intent i = new Intent(this,DriverRegistration.class);
        startActivity(i);
    }
    public void driverLogin(View view){

        String user = driverName.getText().toString();
        String pass =  driverPassword.getText().toString();

        if(user.equals("")||pass.equals(""))
            Toast.makeText(DriverLogin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        else{
            Boolean checkuserpass = DB.checkusernamepassword(user, pass);
            if(checkuserpass==true){
                Toast.makeText(DriverLogin.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(getApplicationContext(), DriverHomeScreen.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(DriverLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }

      //  Toast.makeText(this,"Login button is clicked",Toast.LENGTH_SHORT).show();

    }

}

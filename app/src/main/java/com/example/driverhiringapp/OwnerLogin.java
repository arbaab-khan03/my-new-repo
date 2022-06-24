package com.example.driverhiringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.driverhiringapp.data.DBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.acl.Owner;

public class OwnerLogin extends AppCompatActivity {



    // this is basically the first screen of this app



    EditText userName;
    EditText userPassword;
    Button userSignIn;
    DBHelper DB1;
//    private static OwnerLogin sInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        sInstance = this;
        setContentView(R.layout.activity_owner_login);

        userName = (EditText) findViewById(R.id.inputUsername);
        userPassword = (EditText) findViewById(R.id.inputUserPassword);
        userSignIn = (Button) findViewById(R.id.user_sign_in);

        DB1 = new DBHelper(this);

    }

    public void ownerLogin(View view) {

        String myuser = userName.getText().toString();
        String mypass = userPassword.getText().toString();

        if (myuser.equals("") || mypass.equals(""))
            Toast.makeText(OwnerLogin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        else {
            Boolean checkuserpass = DB1.checkusernamepassword(myuser, mypass);

            if (checkuserpass == true) {
                Toast.makeText(OwnerLogin.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), OwnerHomeScreen.class);
                startActivity(intent);
            } else {
                Toast.makeText(OwnerLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }

        }

         //Toast.makeText(this,"button is clicked",Toast.LENGTH_SHORT).show();
    }

    public void createAccountMain(View view){

        Intent i = new Intent(this,OwnerRegistration.class);
        startActivity(i);
    }

}

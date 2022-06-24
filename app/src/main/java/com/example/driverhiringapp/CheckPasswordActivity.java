package com.example.driverhiringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckPasswordActivity extends Activity {

    Context context;
    EditText checkPassword;
    Button submitPassword;
    String password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_password);
        context = this;
         checkPassword = (EditText) findViewById(R.id.developer_password_edittext);
         submitPassword = (Button) findViewById(R.id.submit_password_button);
         password = checkPassword.getText().toString().trim();


    }
    public CheckPasswordActivity(){

    }
    public CheckPasswordActivity(int count){
        // it is a constructor
        sendToPage(count);
    }

    public void sendToPage(int count){

//        EditText checkPassword = (EditText) findViewById(R.id.developer_password_edittext);
//        Button submitPassword = (Button) findViewById(R.id.submit_password_button);



        submitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.equals("maideveloperhoon")){
                    if(count==1){
                        startActivity(new Intent(CheckPasswordActivity.this,ShowTableActivity.class));
                    }
                    else if(count==2){
                        startActivity(new Intent(CheckPasswordActivity.this,ShowTableActivity2.class));
                    }
                    else if(count==3){
                        startActivity(new Intent(CheckPasswordActivity.this,ShowTableActivity3.class));
                    }
                }
                else{
                    Toast.makeText(CheckPasswordActivity.this,"warning! don't try to enter here go back",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
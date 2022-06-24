package com.example.driverhiringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.driverhiringapp.data.DBHelper;
import com.example.driverhiringapp.data.UserContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OwnerRegistration extends AppCompatActivity {

    EditText username;
    EditText useremail;
    EditText userpassword;
    EditText userconfirmpassword;
    EditText userMobileNumber;
    EditText userCity;
    EditText userState;
    Button userSignUp;
    EditText userPinCode;
    public int count=0;
//    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    final Calendar myCalendar = Calendar.getInstance();
    EditText userDateOfBirth;
    private RadioGroup radioGroup;
    boolean isAllFieldsChecked = false;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_registration);

        DB = new DBHelper(this);

        username = (EditText) findViewById(R.id.user_name);
        useremail = (EditText) findViewById(R.id.user_email_id);
        userpassword = (EditText) findViewById(R.id.user_password);
        userconfirmpassword = (EditText) findViewById(R.id.user_confirm_password);
        userMobileNumber = (EditText) findViewById(R.id.user_mobile_number);
        userCity = (EditText) findViewById(R.id.user_city);
        userState = (EditText) findViewById(R.id.user_state);
        userPinCode = (EditText) findViewById(R.id.user_pincode);
        userSignUp = (Button) findViewById(R.id.user_sign_up);
//        btnsignup = (Button) findViewById(R.id.sign_up_button);


        // to input date_of_birth from user
        userDateOfBirth = (EditText) findViewById(R.id.user_dateOfBirth);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }


        };
        userDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OwnerRegistration.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        radioGroup = (RadioGroup) findViewById(R.id.user_radio_group);
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup
                        .OnCheckedChangeListener() {
                    @Override

                    // The flow will come here when
                    // any of the radio buttons in the radioGroup
                    // has been clicked

                    // Check which radio button has been clicked
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId) {

                        // Get the selected Radio Button
                        RadioButton
                                radioButton
                                = (RadioButton) group
                                .findViewById(checkedId);
                    }
                });


        // here all your code goes

        userSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = checkAllFields();
//
//                // the boolean variable turns to be true then
//                // only the user must be proceed to the activity2
                if (isAllFieldsChecked) {

                    // Dummy data to check
                    // below is the main code to insert data remember


                    String user = username.getText().toString();
                    String pass = userpassword.getText().toString();
                    String confirm_password = userconfirmpassword.getText().toString();
                    String mobile_number_string = userMobileNumber.getText().toString();
                    long mobile_number = Long.parseLong(mobile_number_string);
                    String emailid = useremail.getText().toString();
                    String city = userCity.getText().toString();
                    String state = userState.getText().toString();
                    String pincode_string = userPinCode.getText().toString();
                    int pincode = Integer.parseInt(pincode_string);

                    if (user.equals("") || pass.equals("") || confirm_password.equals(""))
                        Toast.makeText(OwnerRegistration.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else {
                        if (pass.equals(confirm_password)) {
                            Boolean checkUser = DB.checkusername(user);
                            if (checkUser == false) {
                                Boolean insert = DB.insertData(user, pass, confirm_password, mobile_number, emailid, city, state, pincode);
                                if (insert == true) {
                                    Toast.makeText(OwnerRegistration.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), OwnerHomeScreen.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(OwnerRegistration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(OwnerRegistration.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(OwnerRegistration.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                        }
                    }
                    //}
                    //}
                }
            }
        });
    }


    public void showTable1(View view){
//        int count=1;
//        Button showTable1 = (Button) findViewById(R.id.show_table1);
//        startActivity(new Intent(this,CheckPasswordActivity.class));
//        CheckPasswordActivity c1 = new CheckPasswordActivity(count);
        //c1.sendToPage(count);
        startActivity(new Intent(OwnerRegistration.this,ShowTableActivity.class));

    }
    public void showTable2(View view){

//        int count=2;
//        Button showTable2 = (Button) findViewById(R.id.show_table2);
//        startActivity(new Intent(this,CheckPasswordActivity.class));
//        CheckPasswordActivity c2 = new CheckPasswordActivity(count);
//        //c2.sendToPage(count);
        startActivity(new Intent(OwnerRegistration.this,ShowTableActivity2.class));

    }
    private boolean checkAllFields() {
        if (username.length() == 0) {
            username.setError("This field is required");
            return false;
        }
        if (useremail.length() == 0) {
            useremail.setError("Email is required");
            return false;
        }
        if (!(useremail.getText().toString().contains("@")) || !(useremail.getText().toString().contains(".com"))) {
            useremail.setError("please enter valid email id");
            return false;
        }
        if (userCity.length() > 30) {
            userCity.setError("city length should not be greater than 30 characters");
            return false;
        }
        if (userState.length() > 30) {
            userState.setError("state length should not be greater than 30 characters");
            return false;
        }
        if (userPinCode.length() > 6) {
            userPinCode.setError("pincode should not be greater than 6 digits");
            return false;
        }

        if (userpassword.length() == 0) {
            userpassword.setError("Password is required");
            return false;
        }
        if (!(userpassword.getText().toString().equals(userconfirmpassword.getText().toString()))) {
            userconfirmpassword.setError("Password does not match");
            return false;
        }
        if (userMobileNumber.length() < 10 || userMobileNumber.length() > 10) {
            userMobileNumber.setError("length should be exactly 10");
            return false;
        } else if (userpassword.length() < 8) {
            userpassword.setError("Password must be minimum 8 characters");
            return false;
        }
        // after all validation return true.
        return true;
    }

    // updatelabel function is used do not ever try to remove it.
    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        userDateOfBirth.setText(dateFormat.format(myCalendar.getTime()));
    }

}
//    public void createAccountOwner(View view){
//
////        inputemail = (EditText) findViewById(R.id.inputemail_id);
////        inputpassword = (EditText) findViewById(R.id.inputPassword);
//
//        startActivity(new Intent(this,OwnerLogin.class));
//        Toast.makeText(this,"account has been created! now you can login with the same account", Toast.LENGTH_SHORT).show();
//
//        //perForAuth();
//    }


//    private void sendUsertonextActivity() {
//        Intent i = new Intent(OwnerRegistration.this,OwnerHomeScreen.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i);
//
//    }

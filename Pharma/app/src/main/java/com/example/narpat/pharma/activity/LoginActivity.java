package com.example.narpat.pharma.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.narpat.pharma.R;
import com.example.narpat.pharma.dbfile.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper databaseHelper;

    EditText et_mobile,et_password;
    Button button_login,button_sigup;

    private String mobile,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
        initObjects();




    }

    private void initViews() {

        et_mobile=(EditText) findViewById(R.id.editTextMobileNumber);
        et_password=(EditText) findViewById(R.id.editTextPassword);

        button_login=(Button) findViewById(R.id.ButtonLogin);
        button_sigup=(Button) findViewById(R.id.ButtonSignup);
    }


    private void initListeners(){

        button_login.setOnClickListener(this);
        button_sigup.setOnClickListener(this);


    }

    private void initObjects(){

        databaseHelper = new DatabaseHelper(this);
    }





    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.ButtonLogin:
                verifyFromSQLite();

                break;
            case R.id.ButtonSignup:
                Intent i=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
                break;
        }

    }


    private void verifyFromSQLite(){

        if(!validate()){

            Toast.makeText(this, "Enter the valid Data", Toast.LENGTH_SHORT).show();

        }

        else if (databaseHelper.checkUser(et_mobile.getText().toString().trim()
                                    ,et_password.getText().toString().trim())){

            Intent i=new Intent(LoginActivity.this,HomeActivity.class);
            emptyInputEditText();
            startActivity(i);
            // Tanzeel - Finish Login as we don't want it to show up with user clicks on back button from home screen
            finish();
        }
        else{
            Toast.makeText(this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
            emptyInputEditText();

        }
    }
    public boolean validate(){
        intialize();

        boolean valid=true;

        if(mobile.isEmpty()||mobile.length()<10){
            et_mobile.setError("Please Enter Vallid Mobile Number");
            valid=false;
        }
        else if (password.isEmpty()||password.length()<2){
            et_password.setError("Please Enter Password");
            valid=false;
        }

        return valid;


    }
    public void intialize(){

        mobile=et_mobile.getText().toString().trim();
        password=et_password.getText().toString().trim();
    }

    private void emptyInputEditText(){
        et_mobile.setText(null);
        et_password.setText(null);
    }
}

package com.example.narpat.pharma.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.narpat.pharma.R;
import com.example.narpat.pharma.dbfile.DatabaseHelper;
import com.example.narpat.pharma.model.UserVo;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

   private EditText et_name,et_mobile,et_pass,et_cpass;

    private String name,mobile,password,cpassword;
    Button btn_register,btn_siginin;
    private DatabaseHelper databaseHelper;
    private UserVo user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        initViews();
        initListeners();
        initObjects();

    }
    private void initViews(){

        et_name = (EditText) findViewById(R.id.editTextUserName);
        et_mobile = (EditText) findViewById(R.id.editTextMobileNumber);
        et_pass = (EditText) findViewById(R.id.editTextPassword);
        et_cpass = (EditText) findViewById(R.id.editTextConformPass);

        btn_register = (Button) findViewById(R.id.buttonRegister);
        btn_siginin = (Button) findViewById(R.id.buttonSignin);

    }
    private void initListeners(){

        btn_register.setOnClickListener(this);
        btn_siginin.setOnClickListener(this);

    }

    private void initObjects(){

        databaseHelper = new DatabaseHelper(this);
        user = new UserVo();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.buttonRegister:
                postDataToSQLite();
                break;
            case R.id.buttonSignin:
                finish();
                break;
        }

    }
    private void postDataToSQLite(){


        if(!validate()){

            Toast.makeText(this, "Registration Failed..!!!!", Toast.LENGTH_SHORT).show();

        }
        else if (!databaseHelper.checkUser(et_mobile.getText().toString().trim())){

            user.setName(et_name.getText().toString().trim());
            user.setMobile(et_mobile.getText().toString().trim());
            user.setPassword(et_pass.getText().toString().trim());

            databaseHelper.addUser(user);

            Toast.makeText(this, "Registration Successfully", Toast.LENGTH_SHORT).show();
            emptyInputEditText();
            finish();


        }
        else {

            Toast.makeText(this, "Mobile Number Already Exists", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean validate(){
        intialize();

        boolean valid=true;

        if(name.isEmpty()|| name.length()>32){
            et_name.setError("Please Enter Valid name");
            valid=false;
        }
        else if(mobile.isEmpty()||mobile.length()<10){
            et_mobile.setError("Please Enter Vallid Mobile Number");
            valid=false;
        }
        else if (password.isEmpty()||password.length()<2){
            et_pass.setError("Please Enter Password");
            valid=false;
        }
        else if (cpassword.isEmpty()){
            et_cpass.setError("Please enter same password");
            valid=false;
        }
        else if(!password.equals(cpassword)){
            et_cpass.setError("Please enter same password");
            valid=false;
        }
        return valid;


    }
    public void intialize(){
        name=et_name.getText().toString().trim();
        mobile=et_mobile.getText().toString().trim();
        password=et_pass.getText().toString().trim();
        cpassword=et_cpass.getText().toString().trim();
    }

    private void emptyInputEditText(){

        et_name.setText(null);
        et_mobile.setText(null);
        et_pass.setText(null);
        et_cpass.setText(null);

    }

}

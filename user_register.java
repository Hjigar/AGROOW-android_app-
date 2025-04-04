package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.ResponceModel;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_register extends AppCompatActivity {
    EditText FNAME,LNAME,PHONE,DOB,ADDRESS,EMAIL,PASSWORD;
    Button REGISTER;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);
        FNAME=(EditText)findViewById(R.id.fn);
        LNAME=(EditText)findViewById(R.id.ln);
        PHONE=(EditText)findViewById(R.id.phone);
        DOB=(EditText)findViewById(R.id.dob);
        ADDRESS=(EditText)findViewById(R.id.address);
        EMAIL=(EditText)findViewById(R.id.email);
        PASSWORD=(EditText)findViewById(R.id.password);

        REGISTER=(Button) findViewById(R.id.U_register);


        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int myear=c.get(Calendar.YEAR);
                int mMonth=c.get(Calendar.MONTH);
                int mday=c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dp=new DatePickerDialog(user_register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        DOB.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },myear,mMonth,mday);
                dp.show();

            }
        });
        REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    check_credentials();
                    if(check_credentials() == true) {
                        Insert_User();
                        Intent i = new Intent(user_register.this, main_agroww_login1.class);
                        startActivity(i);
                    }
                }catch (Exception e){
                    Toast.makeText(user_register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private static boolean isValidemail(String EMAIL)
    {
        return !TextUtils.isEmpty(EMAIL)&& Patterns.EMAIL_ADDRESS.matcher(EMAIL).matches();
    }
    public boolean check_credentials()
   {
        if(TextUtils.isEmpty(FNAME.getText().toString()))
        {
            FNAME.setError("First Name Required");
            return false;
        }
        else if(TextUtils.isDigitsOnly(FNAME.getText().toString()))
        {
            FNAME.setError("Digit is not inputable");
            return false;
        }
        else if(TextUtils.isEmpty(LNAME.getText().toString()))
        {
            LNAME.setError("Last Name Required");
            return false;
        }
        else if(TextUtils.isDigitsOnly(LNAME.getText().toString()))
        {
            LNAME.setError("Digit is not inputable");
            return false;
        }
        else if(TextUtils.isEmpty(PHONE.getText().toString()))
        {
            PHONE.setError("Phone Number is Required");
            return false;
        }
        else if(PHONE.length()>10)
        {
            PHONE.setError("Phone Number Compelsory 10 Digits");
            return false;
        }
        else if(PHONE.length()<10)
        {
            PHONE.setError("Phone Number Compelsory 10 Digits");
            return false;
        }
        else if(TextUtils.isEmpty(DOB.getText().toString()))
        {
            DOB.setError("Date Of Birth is Required");
            return false;
        }
        else if(TextUtils.isEmpty(ADDRESS.getText().toString()))
        {
            ADDRESS.setError("Address is Required");
            return false;
        }
        else if(TextUtils.isEmpty(EMAIL.getText().toString()))
        {
            EMAIL.setError("Email is Required");
            return false;
        }
        else if(isValidemail(EMAIL.getText().toString())==false)
        {
            EMAIL.setError("Please input email formate only");
            return false;
        }
        else if(TextUtils.isEmpty(PASSWORD.getText().toString()))
        {
            PASSWORD.setError("Password is Required");
            return false;
        }
        else if(PASSWORD.length()<5)
        {
            PASSWORD.setError("Password Contain Minimum 5 Character");
            return false;
        }
        return true;
    }

    public void Insert_User() {
        Call<ResponceModel> call;
        call = RetrofitClient_agroww
                .getInstance()
                .getApi()
                .user_insert(FNAME.getText().toString(),LNAME.getText().toString(), PHONE.getText().toString(), DOB.getText().toString(), ADDRESS.getText().toString(), EMAIL.getText().toString(), PASSWORD.getText().toString());
        call.enqueue(new Callback<ResponceModel>() {
            @Override
            public void onResponse(Call<ResponceModel> call, Response<ResponceModel> response) {
                try {
                    ResponceModel responceModel = response.body();
                    if (response.isSuccessful()) {
                        Toast.makeText(user_register.this, responceModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    //whrne call data not insereted
                    else {
                        Toast.makeText(user_register.this, responceModel.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<ResponceModel> call, Throwable t) {
                Toast.makeText(user_register.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("RES123", t.getMessage());
            }
        });
    }
}


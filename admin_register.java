package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.Validator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.ResponceModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class admin_register extends AppCompatActivity{
    EditText NAME,PHONE,EMAIL,PASSWORD;
    Button rg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_register);
        NAME=(EditText)findViewById(R.id.name);
        PHONE=(EditText)findViewById(R.id.phno);
        EMAIL=(EditText)findViewById(R.id.email);
        PASSWORD=(EditText)findViewById(R.id.pass);
        rg=(Button) findViewById(R.id.reg);

        rg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(check_credentials()==true)
                    {
                        Insert_Admin();
                        Intent i = new Intent(admin_register.this, main_agroww_login1.class);
                        startActivity(i);
                    }
                }catch (Exception e){
                    Toast.makeText(admin_register.this, "put all data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean check_credentials()
    {
        if(TextUtils.isEmpty(NAME.getText().toString()))
        {
            NAME.setError("Name is Required");
            return false;
        }
        else if(TextUtils.isEmpty(PHONE.getText().toString()))
        {
            PHONE.setError("Phone Number is Required");
            return false;
        }
        else if(PHONE.length()<10)
        {
            PHONE.setError("Phone Number Compelsory 10 Digits");
            return false;
        }
        else if(TextUtils.isEmpty(EMAIL.getText().toString()))
        {
            EMAIL.setError("Email is Required");
            return false;
        }
        else if(TextUtils.isEmpty(PASSWORD.getText().toString()))
        {
            PASSWORD.setError("Password is Required");
            return false;
        }
        else if(PASSWORD.length()<=8)
        {
            PASSWORD.setError("Password minimum 8 Digits");
            return false;
        }
        else if(EMAIL.getText().toString().matches("email"))
        {
            EMAIL.setError("Please input email formate only ");
            return false;
        }
        return true;
    }
    private void Insert_Admin() {

        Call<ResponceModel> call = RetrofitClient_agroww
                .getInstance()
                .getApi()
                .admin_insert(NAME.getText().toString(),PHONE.getText().toString(),EMAIL.getText().toString(),PASSWORD.getText().toString());
        call.enqueue(new Callback<ResponceModel>() {
            @Override
            public void onResponse(Call<ResponceModel> call, Response<ResponceModel> response) {
                ResponceModel responceModel=response.body();

               // Log.d("heloo",response.body()+"");
                if(response.isSuccessful())
                {
                    Toast.makeText(admin_register.this,responceModel.getMessage(), Toast.LENGTH_LONG).show();
                }
                //when call data not insereted
                else
                {
                    Toast.makeText(admin_register.this,responceModel.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponceModel> call, Throwable t)
            {
                Toast.makeText(admin_register.this,t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("RES123",t.getMessage());
            }
        });
    }

}

package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.ResponceModel;
import com.example.myapplication.models_agroww.UsersData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admin_login extends AppCompatActivity {/*
     Button login;
     EditText EMAIL,PASS;
     TextView treg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        login=(Button)findViewById(R.id.login);
        treg=(TextView)findViewById(R.id.reg);
        EMAIL=(EditText)findViewById(R.id.email);
        PASS=(EditText)findViewById(R.id.pass);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(EMAIL.getText().toString()))
                {
                    EMAIL.setError("EMAIL INPUT COMPELSORY");
                    return;
                }
                else if(TextUtils.isEmpty(PASS.getText().toString()) || PASS.length()>8) {
                    PASS.setError("PASSWORD INPUT COMPELSORY");
                    return;
                }
                else if(PASS.length()>8) {
                    PASS.setError("PASSWORD MUST BE 8 CHARACTER");
                    return;
                }
                Admin_fetch();


            }
        });

        treg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_login.this,admin_register.class);
                startActivity(i);
            }
        });

    }
    public void Admin_fetch()
    {
        Call<UsersData> call = RetrofitClient_agroww
                .getInstance()
                .getApi()
                .Admin_login();
        call.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                UsersData userdata = response.body();
                if (response.isSuccessful()) {
                    Toast.makeText(admin_login.this, userdata.getMessage(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(admin_login.this, admin_dashbord.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(admin_login.this, userdata.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            //failur call when app is fail and give error
            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                Toast.makeText(admin_login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RES123", t.getMessage());
            }
        });
    }*/
}

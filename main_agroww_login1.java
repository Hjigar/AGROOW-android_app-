package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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

public class main_agroww_login1 extends AppCompatActivity {
    Button login;
    EditText EMAIL,PASS;
    TextView treg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_agroww_login1);
        login=(Button)findViewById(R.id.login);
        treg=(TextView)findViewById(R.id.treg);
        EMAIL=(EditText)findViewById(R.id.email);
        PASS=(EditText)findViewById(R.id.pass);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fetch();
                if(TextUtils.isEmpty(EMAIL.getText().toString()))
                {
                    EMAIL.setError("EMAIL INPUT COMPELSORY");
                    return;
                }
                else if(TextUtils.isEmpty(PASS.getText().toString()))
                {
                    PASS.setError("PASSWORD INPUT COMPELSORY");
                    return;
                }
                else if(PASS.length()>8)
                {
                    PASS.setError("PASSWORD MUST BE 8 CHARACTER");
                    return;
                }
                else if(EMAIL.getText().toString().matches("email"))
                {
                    EMAIL.setError("Email Formate plz");
                    return;
                }
            }
        });

        treg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(main_agroww_login1.this,main_register_agroww.class);
                startActivity(i);
            }
        });

    }
    public void Fetch()
    {
        Call<ResponceModel> call = RetrofitClient_agroww
                .getInstance()
                .getApi()
                .Login(EMAIL.getText().toString(),PASS.getText().toString());
        call.enqueue(new Callback<ResponceModel>() {
            @Override
            public void onResponse(Call<ResponceModel> call, Response<ResponceModel> response) {
                ResponceModel userdata = response.body();

                try
                {
                if (response.isSuccessful()) {
                    Toast.makeText(main_agroww_login1.this, userdata.getMessage() , Toast.LENGTH_LONG).show();
                    if (userdata.getType().equals("admin")) {

                        SharedPreferences sp = getSharedPreferences("adminSP", MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("adminSP",userdata.getUid());
                        ed.commit();

                        SharedPreferences sp1 = getSharedPreferences("fname", MODE_PRIVATE);
                        SharedPreferences.Editor ed1 = sp1.edit();
                        ed1.putString("fname",userdata.getFname());
                        ed1.commit();


                        Intent i = new Intent(main_agroww_login1.this, admin_dashbord.class);
                        startActivity(i);
                    }
                    else if (userdata.getType().equals("user")) {

                        SharedPreferences sp = getSharedPreferences("userSP", MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("userSP",userdata.getUid());
                        ed.commit();

                        SharedPreferences sp1 = getSharedPreferences("fname", MODE_PRIVATE);
                        SharedPreferences.Editor ed1 = sp1.edit();
                        ed1.putString("fname",userdata.getFname());
                        ed1.commit();

                        Intent i = new Intent(main_agroww_login1.this, user_dashboard.class);
                        startActivity(i);
                    } else if (userdata.getMessage().equals("INVALID EMAIL OR PASSWORD")) {
                        Toast.makeText(main_agroww_login1.this, "INVALID EMAIL OR PASSWORD", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(main_agroww_login1.this, userdata.getMessage(), Toast.LENGTH_LONG).show();

                }
            }catch (Exception e){
                    Toast.makeText(main_agroww_login1.this, "Invalid Email Or Password", Toast.LENGTH_SHORT).show();
                }

            }

            //failur call when app is fail and give error
            @Override
            public void onFailure(Call<ResponceModel> call, Throwable t) {
                Toast.makeText(main_agroww_login1.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RES123", t.getMessage()+"faill");
            }
        });
    }
}

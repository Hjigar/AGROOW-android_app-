package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.models.ResponceModel;
import com.example.myapplication.models.UsersData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class demo_database_php extends AppCompatActivity {
    EditText NAME,PH;
    Button add;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdata);
        NAME=(EditText) findViewById(R.id.name);
        PH=(EditText) findViewById(R.id.ph);
        add=(Button) findViewById(R.id.ok);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //buton ni clkeck evebt par insert userb ni methid ne clal karai data add karays
                insertuser();
            }
        });
    }
    //insert user ni method thi all data insert thase demo name na database ma
    public void insertuser() {
        Call<ResponceModel> call=RetrofitClient
                //all methid ne . this user kari ne tabkle na field  lakhi ne ene accsess karya
                .getInstance()
                .getApi()
                //fiel na  name and ena data
                .registeration(NAME.getText().toString(),PH.getText().toString());
        //call ni enque method banai tena thi know abouet data add or not add
        call.enqueue(new Callback<ResponceModel>() {
            @Override
            public void onResponse(Call<ResponceModel> call, Response<ResponceModel> response) {
                ResponceModel responceModel=response.body();
                //Log.d("jigar",response.body()+"");
                //jyare complte run ths eto aa methos call tha thase
                if(response.isSuccessful())
                {
                    Toast.makeText(demo_database_php.this,responceModel.getMessage(), Toast.LENGTH_LONG).show();
                }
                //whrne call data not insereted
                else
                {
                    Toast.makeText(demo_database_php.this,responceModel.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            //failur call when app is fail and give error
            @Override
            public void onFailure(Call<ResponceModel> call, Throwable t) {
                Toast.makeText(demo_database_php.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RES123",t.getMessage());
            }
        });
    }
    public void fetchdata() {
        Call<UsersData> call=RetrofitClient
                //all methid ne . this user kari ne tabkle na field  lakhi ne ene accsess karya
                .getInstance()
                .getApi()
                //fiel na  name and ena data
                .fetchdata();
        //call ni enque method banai tena thi know abouet data add or not add
        call.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                UsersData userdata=response.body();
                //Log.d("jigar",response.body()+"");
                //jyare complte run ths eto aa methos call tha thase
                if(response.isSuccessful())
                {
                    Toast.makeText(demo_database_php.this,userdata.getMessage(), Toast.LENGTH_LONG).show();
                }

                else
                {
                    Toast.makeText(demo_database_php.this,userdata.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            //failur call when app is fail and give error
            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                Toast.makeText(demo_database_php.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RES123",t.getMessage());
            }
        });
    }




}

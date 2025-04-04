package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class user_dashboard extends AppCompatActivity {
    ImageButton PROFILE,LICENSE,SUBSIDY,PRODUCTS,REPORTS,ORDERS,ABOUTE;
    String uname;
    TextView UNAME;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_dashboard);
        PROFILE=(ImageButton) findViewById(R.id.profile);
        LICENSE=(ImageButton) findViewById(R.id.license);
        SUBSIDY=(ImageButton) findViewById(R.id.subsidy);
        PRODUCTS=(ImageButton) findViewById(R.id.products);
        REPORTS=(ImageButton) findViewById(R.id.reports);
        ORDERS=(ImageButton) findViewById(R.id.order);
        ABOUTE=(ImageButton) findViewById(R.id.aboute);

        UNAME=(TextView)findViewById(R.id.tv2);
        SharedPreferences sp=getSharedPreferences("fname",MODE_PRIVATE);
        uname=sp.getString("fname","");
        UNAME.setText(uname);

        PROFILE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_dashboard.this,user_profile.class);
                startActivity(i);
            }
        });
        LICENSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_dashboard.this,license_main_view.class);
                startActivity(i);
            }
        });
        SUBSIDY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_dashboard.this,subsidy_main_view.class);
                startActivity(i);
            }
        });
        PRODUCTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_dashboard.this,user_products.class);
                startActivity(i);
            }
        });
        REPORTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_dashboard.this,user_reports.class);
                startActivity(i);
            }
        });
        ORDERS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_dashboard.this,user_orders.class);
                startActivity(i);
            }
        });
        ABOUTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_dashboard.this,user_aboute.class);
                startActivity(i);
            }
        });


    }
}

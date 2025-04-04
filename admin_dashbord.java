package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.ResponceModel;
import com.example.myapplication.models_agroww.UsersData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admin_dashbord extends AppCompatActivity {
    ImageButton LICENSE,SUBSIDY,PRODUCTS,ORDERS,USER,SALES;
    Button LOGOUT;
    String uname;
    TextView UNAME;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashbord);

        LICENSE=(ImageButton) findViewById(R.id.license);
        SUBSIDY=(ImageButton) findViewById(R.id.subsidy);
        PRODUCTS=(ImageButton) findViewById(R.id.products);
        ORDERS=(ImageButton) findViewById(R.id.order);
        USER=(ImageButton) findViewById(R.id.user);
        SALES=(ImageButton) findViewById(R.id.sales);
        LOGOUT=(Button) findViewById(R.id.logout);
        UNAME=(TextView)findViewById(R.id.tv2);
        SharedPreferences sp=getSharedPreferences("fname",MODE_PRIVATE);
        uname=sp.getString("fname","");
        UNAME.setText(uname);


        SALES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_dashbord.this,admin_tot_sales.class);
                startActivity(i);
            }
        });
        LICENSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_dashbord.this,admin_m_license.class);
                startActivity(i);
            }
        });
        SUBSIDY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_dashbord.this,admin_m_subsidy.class);
                startActivity(i);
            }
        });
        PRODUCTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_dashbord.this,admin_m_products.class);
                startActivity(i);
            }
        });
        ORDERS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_dashbord.this,admin_m_orders.class);
                startActivity(i);
            }
        });
        USER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_dashbord.this,admin_manage_user.class);
                startActivity(i);
            }
        });

        LOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("adminSP",MODE_PRIVATE);
                sp.edit().clear().commit();
                Intent i=new Intent(admin_dashbord.this,main_agroww_login1.class);
                startActivity(i);
            }
        });
    }
}

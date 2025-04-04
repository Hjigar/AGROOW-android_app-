package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class user_login extends AppCompatActivity {
    Button login;
    TextView treg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_agroww_login1);
        login=(Button)findViewById(R.id.login);
        treg=(TextView)findViewById(R.id.reg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_login.this,user_dashboard.class);
                startActivity(i);
            }
        });
        treg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_login.this,user_register.class);
                startActivity(i);
            }
        });

    }
}

package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class main_register_agroww extends AppCompatActivity {
    ImageView USER,ADMIN;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_register_agroww);

        USER=(ImageView) findViewById(R.id.user_login);
       // ADMIN=(ImageView) findViewById(R.id.admin_login);

        USER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(main_register_agroww.this,user_register.class);
                startActivity(i);
            }
        });
       /* ADMIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(main_register_agroww.this,admin_register.class);
                startActivity(i);
            }
        });*/
    }
}

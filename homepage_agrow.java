package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class homepage_agrow extends AppCompatActivity {
    Handler h;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("userSP", MODE_PRIVATE);
                if (sp.getString("userSP", "").isEmpty()) {
                    Intent i = new Intent(homepage_agrow.this, main_agroww_login1.class);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(homepage_agrow.this, user_dashboard.class);
                    startActivity(i);
                }
            }
        },3000);
    }
}


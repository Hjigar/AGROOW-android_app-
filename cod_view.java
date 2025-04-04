package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.ProductData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cod_view extends AppCompatActivity {
    Button confirm;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cod_view);
        confirm=(Button) findViewById(R.id.confirm);

        SharedPreferences sp=getSharedPreferences("userSP",MODE_PRIVATE);
        uid=sp.getString("userSP","");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PAYMENT_MODE(uid);
                Intent i=new Intent(cod_view.this,user_orders.class);
                startActivity(i);
            }
        });

    }

    private void PAYMENT_MODE(String uid) {
        Call<ProductData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .paymentcod(uid);
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                Toast.makeText(cod_view.this, "Added in Payment Gateway", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(cod_view.this,user_orders.class);
                startActivity(i);

            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Toast.makeText(cod_view.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

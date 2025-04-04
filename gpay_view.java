package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.ProductData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class gpay_view extends AppCompatActivity {
    TextView price,PNAME,PAY_NAME;
    EditText pin;
    String uname;
    Button PAY;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpay_view);

        price=(TextView) findViewById(R.id.price);
        PNAME=(TextView) findViewById(R.id.pname);
        PAY_NAME=(TextView) findViewById(R.id.pay_name);
        pin=(EditText) findViewById(R.id.pin);
        PAY=(Button) findViewById(R.id.pay);

        SharedPreferences sp=getSharedPreferences("userSP",MODE_PRIVATE);
        uid=sp.getString("userSP","");
        //PAY_NAME.setText(uname);
        price.setText(getIntent().getStringExtra("price"));
        PNAME.setText(getIntent().getStringExtra("pname"));

        PAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(gpay_view.this, "Payment complete", Toast.LENGTH_SHORT).show();
                PAYMENT_MODE(uid);
                Intent i=new Intent(gpay_view.this,user_orders.class);
                startActivity(i);
            }
        });

    }

    private void PAYMENT_MODE(String uid) {
        Call<ProductData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .paymentmode(uid);
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                Toast.makeText(gpay_view.this, "Added in Payment Gateway", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(gpay_view.this,user_orders.class);
                startActivity(i);

            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Toast.makeText(gpay_view.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}

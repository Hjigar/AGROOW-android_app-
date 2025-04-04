package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class u_payment_gateway extends AppCompatActivity {
    TextView PNAME, PRICE,Quan,TOTAL,UNAME;
    ImageView PIMG,GPAY,COD;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_payment_gateway);
        Quan = (TextView) findViewById(R.id.quantity);
        PNAME = (TextView) findViewById(R.id.pname);
        UNAME = (TextView) findViewById(R.id.uname);
        PRICE = (TextView) findViewById(R.id.price);
        PIMG = (ImageView) findViewById(R.id.pimg);
        GPAY = (ImageView) findViewById(R.id.gpay);
        COD = (ImageView) findViewById(R.id.cod);
        TOTAL = (TextView) findViewById(R.id.total);

        SharedPreferences sp=getSharedPreferences("fname",MODE_PRIVATE);
        uname=sp.getString("fname","");
        UNAME.setText(uname);

        PNAME.setText(getIntent().getStringExtra("pname"));
        PRICE.setText(getIntent().getStringExtra("price"));
        Quan.setText(getIntent().getStringExtra("quantity"));
        Picasso.get().load(RetrofitClient_agroww.IMG_URL + getIntent().getStringExtra("pimg")).into(PIMG);
        TOTAL.setText(getIntent().getStringExtra("total"));

        GPAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(u_payment_gateway.this, gpay_view.class);
                i.putExtra("pid",getIntent().getIntExtra("pid",0));
                i.putExtra("price",TOTAL.getText().toString());
                i.putExtra("pname",PNAME.getText().toString());
                startActivity(i);
            }
        });
        COD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(u_payment_gateway.this, cod_view.class);
                startActivity(i);
            }
        });
    }
}

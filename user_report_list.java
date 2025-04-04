package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class user_report_list extends AppCompatActivity {
    TextView PNAME,PRICE,QUAN,TOTAL,PHONE,ADDRESS,UID,PDATE;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_report_list);
        PNAME=(TextView)findViewById(R.id.pname);
        PRICE=(TextView)findViewById(R.id.price);
        QUAN=(TextView)findViewById(R.id.quantity);
        TOTAL=(TextView)findViewById(R.id.total);
        PHONE=(TextView)findViewById(R.id.phone);
        ADDRESS=(TextView)findViewById(R.id.address);
        PDATE=(TextView)findViewById(R.id.buy_date);
        UID=(TextView)findViewById(R.id.uid);

        PNAME.setText(getIntent().getStringExtra("pname"));
        PRICE.setText(getIntent().getStringExtra("price"));
        QUAN.setText(getIntent().getStringExtra("quantity"));
        PDATE.setText(getIntent().getStringExtra("pdate"));
        TOTAL.setText(getIntent().getStringExtra("Total"));
        PHONE.setText(getIntent().getStringExtra("phone"));
        ADDRESS.setText(getIntent().getStringExtra("address"));
       // UID.setText(getIntent().getStringExtra("uid"));


    }
}

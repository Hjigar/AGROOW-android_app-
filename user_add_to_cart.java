package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.ProductData;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_add_to_cart extends AppCompatActivity {
    EditText PNAME, PRICE, DESCRIPTION, QUAN;
    ImageView PIMG;
    Button BUY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_add_to_cart);

        PIMG = (ImageView) findViewById(R.id.pimg);
        PNAME = (EditText) findViewById(R.id.pname);
        PRICE = (EditText) findViewById(R.id.price);
        QUAN = (EditText) findViewById(R.id.qun);



        BUY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}


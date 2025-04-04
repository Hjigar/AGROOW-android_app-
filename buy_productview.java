package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.ProductData;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class buy_productview extends AppCompatActivity {
        EditText PHONE,ADD,UID;
        TextView PNAME,QUAN,PRICE,TOTAL;
        ImageView PIMG;
        Button PAY;
        String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_productview);
        QUAN = (TextView) findViewById(R.id.quantity);
        PIMG=(ImageView)findViewById(R.id.pimg);
        PNAME=(TextView)findViewById(R.id.pname);
        ADD=(EditText)findViewById(R.id.address);
        PHONE=(EditText)findViewById(R.id.phone);
        PRICE = (TextView) findViewById(R.id.price);
        TOTAL= (TextView) findViewById(R.id.total);

        PAY = (Button) findViewById(R.id.pay);


        UID=(EditText)findViewById(R.id.uid);
        SharedPreferences sp=getSharedPreferences("userSP",MODE_PRIVATE);
        uid=sp.getString("userSP","");
      // Toast.makeText(buy_productview.this, getIntent().getStringExtra("pid"), Toast.LENGTH_SHORT).show();

        PNAME.setText(getIntent().getStringExtra("pname"));
        PRICE.setText(getIntent().getStringExtra("price"));
        QUAN.setText(getIntent().getStringExtra("quantity"));
        TOTAL.setText(String.valueOf(Integer.parseInt(PRICE.getText().toString())*Integer.parseInt(QUAN.getText().toString())));

        Picasso.get().load(RetrofitClient_agroww.IMG_URL + getIntent().getStringExtra("pimg")).into(PIMG);


            PAY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        if(check_credentials()==true) {
                            BUY_PRODUCT();
                            Intent i = new Intent(buy_productview.this, u_payment_gateway.class);
                            i.putExtra("pid",getIntent().getIntExtra("pid",0));
                            i.putExtra("pname", PNAME.getText().toString());
                            i.putExtra("price",PRICE.getText().toString());
                            i.putExtra("quantity",QUAN.getText().toString());
                            i.putExtra("pimg",getIntent().getStringExtra("pimg"));
                            i.putExtra("total",TOTAL.getText().toString());
                            startActivity(i);
                        }
                    } catch (Exception E) {}
                }
            });

    }
    public boolean check_credentials()
    {
        if(TextUtils.isEmpty(PHONE.getText().toString()))
        {
            PHONE.setError("Phone Number is Required");
            return false;
        }
        else if(PHONE.length()<10)
        {
            PHONE.setError("Phone Number Compelsory 10 Digits");
            return false;
        }
        else if(TextUtils.isEmpty(ADD.getText().toString()))
        {
            ADD.setError("Address is required is Required");
            return false;
        }
        return true;
    }
    private void BUY_PRODUCT() {
        Call<ProductData>call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .product_buy(String.valueOf(getIntent().getIntExtra("pid",0)),uid,PHONE.getText().toString(),ADD.getText().toString(),PNAME.getText().toString(),PRICE.getText().toString(),QUAN.getText().toString(),TOTAL.getText().toString());
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                Toast.makeText(buy_productview.this, "Added in Payment Gateway", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Toast.makeText(buy_productview.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

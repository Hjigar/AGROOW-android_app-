package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.ProductData;
import com.example.myapplication.models_agroww.ResponceModel;
import com.example.myapplication.models_agroww.UsersData;
import com.example.myapplication.models_agroww.admn_m_user;
import com.example.myapplication.models_agroww.product_GT;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admin_m_products extends AppCompatActivity {
    EditText PNAME,PRICE,PDECS,PQUN,PIMG;

    Button INSERT,Mange;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_m_products);
        PNAME=(EditText) findViewById(R.id.pname);
        PRICE=(EditText) findViewById(R.id.price);
        PDECS=(EditText) findViewById(R.id.pdesc);
        PQUN=(EditText) findViewById(R.id.pquan);
        PIMG=(EditText) findViewById(R.id.pimg);

        INSERT=(Button) findViewById(R.id.insert);
        Mange=(Button) findViewById(R.id.manage);

        INSERT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    if (check_credentials() == true)
                    {
                        product_insert();
                        Intent i=new Intent(admin_m_products.this,admin_m_products_list.class);
                        startActivity(i);

                    }
                }catch (Exception e){
                    Toast.makeText(admin_m_products.this, "put all data", Toast.LENGTH_SHORT).show();

                }
            }
        });
        Mange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_m_products.this,admin_m_products_list.class);
                startActivity(i);
            }
        });
    }
    public boolean check_credentials()
    {
        if(TextUtils.isEmpty(PNAME.getText().toString()))
        {
            PNAME.setError("Product Name is Required");
            return false;
        }
        else if(TextUtils.isEmpty(PQUN.getText().toString()))
        {
            PQUN.setError("Product Quantity is Required");
            return false;
        }
        else if(TextUtils.isEmpty(PDECS.getText().toString()))
        {
            PDECS.setError("Product Description is mandatory");
            return false;
        }
        else if(TextUtils.isEmpty(PRICE.getText().toString()))
        {
            PRICE.setError("Price is Required");
            return false;
        }
        else if(TextUtils.isEmpty(PIMG.getText().toString()))
        {
            PIMG.setError("Product Image is Required");
            return false;
        }
        else if(PRICE.length()<3)
        {
            PRICE.setError("PRICE minimum 3 Digits");
            return false;
        }
        return true;
    }
       public void product_insert() {
        Call<ResponceModel> call = RetrofitClient_agroww
                .getInstance()
                .getApi()
                .product_insert(PNAME.getText().toString(),PRICE.getText().toString(),PDECS.getText().toString(),PQUN.getText().toString(),PIMG.getText().toString());
        call.enqueue(new Callback<ResponceModel>() {
            @Override
            public void onResponse(Call<ResponceModel> call, Response<ResponceModel> response) {
                ResponceModel responceModel=response.body();
                if(response.isSuccessful())
                {
                    Toast.makeText(admin_m_products.this,responceModel.getMessage()+"Product Added", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(admin_m_products.this,responceModel.getMessage()+"error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponceModel> call, Throwable t)
            {
                Toast.makeText(admin_m_products.this,t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("RES123",t.getMessage());
            }
        });
    }
}

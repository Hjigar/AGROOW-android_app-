package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.ProductData;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class a_update_product extends AppCompatActivity {
    EditText PID,PNAME,PIMG,PRICE,DESC,QUANTITY;
    Button UPADTE_PRODUCT;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_update_product);
        PID=(EditText) findViewById(R.id.u_pid);
        PNAME= (EditText)findViewById(R.id.u_pname);
        QUANTITY= (EditText)findViewById(R.id.u_quantity);
        PIMG=(EditText) findViewById(R.id.u_pimg);
        PRICE=(EditText) findViewById(R.id.u_price);
        DESC=(EditText) findViewById(R.id.u_desc);

        UPADTE_PRODUCT=(Button) findViewById(R.id.update_product);
        String pid= String.valueOf(getIntent().getIntExtra("pid",0));
        PID.setText(pid);
        PNAME.setText(getIntent().getStringExtra("pname"));
        QUANTITY.setText(getIntent().getStringExtra("quantity"));
        PIMG.setText(getIntent().getStringExtra("pimg"));
        PRICE.setText(getIntent().getStringExtra("price"));
        DESC.setText(getIntent().getStringExtra("desc"));

        UPADTE_PRODUCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_product();
                Snackbar sb=Snackbar.make(UPADTE_PRODUCT,"UPDATE COMPLETE", Snackbar.LENGTH_LONG);
                Intent i=new Intent(a_update_product.this,admin_m_products_list.class);
                startActivity(i);
                sb.show();

            }
        });

    }

    private void update_product() {
        Call<ProductData>call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .update_product(PID.getText().toString(),PNAME.getText().toString(),PRICE.getText().toString(),QUANTITY.getText().toString(),DESC.getText().toString(),PIMG.getText().toString());
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                Toast.makeText(a_update_product.this, "PRODUCT UPDATE COMPLETE", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Toast.makeText(a_update_product.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.ProductData;
import com.example.myapplication.models_agroww.product_GT;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_products extends AppCompatActivity {
    ListView PRODUCT;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_product_view);
        PRODUCT = (ListView) findViewById(R.id.products);

        product_m_fetch();
    }
        private void product_m_fetch() {
            Call<ProductData> call= RetrofitClient_agroww
                    .getInstance()
                    .getApi()
                    .product_m_fetch();
            call.enqueue(new Callback<ProductData>() {
                @Override
                public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                    ProductData pdata=response.body();
                    if(response.isSuccessful())
                    {
                        Toast.makeText(user_products.this,pdata.getMessage(), Toast.LENGTH_LONG).show();
                        cust_Adapter ca=new cust_Adapter(user_products.this,pdata.getData());
                        PRODUCT.setAdapter(ca);

                    }
                    Toast.makeText(user_products.this,response.message()+"succses", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<ProductData> call, Throwable t) {
                    Toast.makeText(user_products.this,t.getMessage()+"fail", Toast.LENGTH_LONG).show();
                }
            });
        }
        public class cust_Adapter extends BaseAdapter {
            Context context;
            ArrayList<product_GT> ar;
            LayoutInflater inflater;

            public cust_Adapter(user_products context, ArrayList<product_GT> ar) {
                this.context = context;
                this.ar = ar;
                inflater = (LayoutInflater.from(context)) ;
            }


            @Override
            public int getCount() {
                return ar.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                convertView = inflater.inflate(R.layout.u_product_cust, null);

                ImageView pimg = (ImageView) convertView.findViewById(R.id.pimg);
                TextView pname = (TextView) convertView.findViewById(R.id.pname);
                TextView price = (TextView) convertView.findViewById(R.id.price);
                Button Buy=(Button)convertView.findViewById(R.id.buy);

                //pimg.setText(ar.get(position).getP_IMAGE());
                Picasso.get().load(RetrofitClient_agroww.IMG_URL+ar.get(position).getP_IMAGE()).into(pimg);
                pname.setText(ar.get(position).getP_NAME());
                price.setText(ar.get(position).getP_PRICE());
                try {
                pimg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(user_products.this, user_product_mainview.class);
                        i.putExtra("pid",ar.get(position).getP_ID());
                        i.putExtra("pimg",ar.get(position).getP_IMAGE());
                        i.putExtra("pname", ar.get(position).getP_NAME());
                        i.putExtra("price", ar.get(position).getP_PRICE());
                        i.putExtra("des", ar.get(position).getDESCRIPTION());
                        startActivity(i);
                    }
                });
            }catch (Exception e){
                Toast.makeText(user_products.this, e.getMessage()+"jhbv", Toast.LENGTH_SHORT).show();
            }
                try {
                Buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Intent i = new Intent(user_products.this, user_product_mainview.class);
                            i.putExtra("pimg",ar.get(position).getP_IMAGE());
                            i.putExtra("pid",ar.get(position).getP_ID());
                            i.putExtra("pname", ar.get(position).getP_NAME());
                            i.putExtra("price", ar.get(position).getP_PRICE());
                            i.putExtra("des", ar.get(position).getDESCRIPTION());
                            startActivity(i);
                    }
                }); }catch (Exception e){
                    Toast.makeText(user_products.this, e.getMessage()+"jhbv", Toast.LENGTH_SHORT).show();
                }
                return convertView;
            }
        }

}

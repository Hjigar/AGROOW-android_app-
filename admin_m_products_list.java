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

public class admin_m_products_list extends AppCompatActivity {
    ListView P_DATA;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_product_list);
        P_DATA=(ListView)findViewById(R.id.items);
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
                    Toast.makeText(admin_m_products_list.this,pdata.getMessage(), Toast.LENGTH_LONG).show();
                    cust_Adapter ca=new cust_Adapter(admin_m_products_list.this,pdata.getData());
                    P_DATA.setAdapter(ca);

                }
                Toast.makeText(admin_m_products_list.this,response.message()+"succses", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Toast.makeText(admin_m_products_list.this,t.getMessage()+"fail", Toast.LENGTH_LONG).show();
            }
        });
    }
    public class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<product_GT> ar;
        LayoutInflater inflater;

        public cust_Adapter(admin_m_products_list context, ArrayList<product_GT> ar) {
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

            convertView = inflater.inflate(R.layout.user_product_list, null);

            ImageView pimg = (ImageView) convertView.findViewById(R.id.pimg);
            TextView pname = (TextView) convertView.findViewById(R.id.pname);
            TextView quan = (TextView) convertView.findViewById(R.id.quantity);
            TextView price = (TextView) convertView.findViewById(R.id.price);

            Button DELTE_PRODUCT=(Button)convertView.findViewById(R.id.del_product);
            Button UPDATE_PRODUCT=(Button)convertView.findViewById(R.id.update_product);

            //pimg.setImageResource("http://172.16.16.61/AGROWW_30/Images/"+ar.get(position).getP_IMAGE());
            Picasso.get().load(RetrofitClient_agroww.IMG_URL+ar.get(position).getP_IMAGE()).into(pimg);
            pname.setText(ar.get(position).getP_NAME());
            //quan.setText(ar.get(position).getP_QUANTITY());
            price.setText(ar.get(position).getP_PRICE());

            DELTE_PRODUCT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    DEL_PRODUCT(ar.get(position).getP_ID());
                }
            });
            UPDATE_PRODUCT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(admin_m_products_list.this,a_update_product.class);
                    i.putExtra("pid",ar.get(position).getP_ID());
                    i.putExtra("pname",ar.get(position).getP_NAME());
                    i.putExtra("pimg",ar.get(position).getP_IMAGE());
                    i.putExtra("quantity",ar.get(position).getP_QUANTITY());
                    i.putExtra("price",ar.get(position).getP_PRICE());
                    i.putExtra("desc",ar.get(position).getDESCRIPTION());
                    startActivity(i);
                    product_m_fetch();
                }
            });

            return convertView;
        }
    }

    private void DEL_PRODUCT(int pid) {
        Call<ProductData>call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .del_product(pid);
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                Toast.makeText(admin_m_products_list.this, "DELETE PRODUCT", Toast.LENGTH_SHORT).show();
                product_m_fetch();
            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Toast.makeText(admin_m_products_list.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

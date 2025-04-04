package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class u_product_mainview_list extends AppCompatActivity {
    ListView PRODUCT_MAIN_DATA;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_product_mainview_list);
        PRODUCT_MAIN_DATA=(ListView)findViewById(R.id.product_main_data);
        FETCH_PRODUCT();
    }
    private void FETCH_PRODUCT() {
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
                    Toast.makeText(u_product_mainview_list.this,pdata.getMessage(), Toast.LENGTH_LONG).show();
                    cust_Adapter ca=new cust_Adapter(u_product_mainview_list.this,pdata.getData());
                    PRODUCT_MAIN_DATA.setAdapter(ca);

                }
                Toast.makeText(u_product_mainview_list.this,response.message()+"succses", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Toast.makeText(u_product_mainview_list.this,t.getMessage()+"fail", Toast.LENGTH_LONG).show();
            }
        });
    }
    public class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<product_GT> ar;
        LayoutInflater inflater;

        public cust_Adapter(u_product_mainview_list context, ArrayList<product_GT> ar) {
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

            convertView = inflater.inflate(R.layout.user_product_mainview, null);

            ImageView pimg = (ImageView) convertView.findViewById(R.id.pimg);
            TextView pname = (TextView) convertView.findViewById(R.id.pname);
            TextView price = (TextView) convertView.findViewById(R.id.price);
            TextView quan = (TextView) convertView.findViewById(R.id.quantity);
            TextView desc = (TextView) convertView.findViewById(R.id.description);


            //pimg.setImageResource("http://172.16.16.61/AGROWW_30/Images/"+ar.get(position).getP_IMAGE());
            Picasso.get().load("http://172.16.16.61/AGROWW_30/Images/"+ar.get(position).getP_IMAGE()).into(pimg);
            pname.setText(getIntent().getStringExtra("pname"));
            price.setText(getIntent().getStringExtra("price"));
            quan.setText(ar.get(position).getP_QUANTITY());
            desc.setText(ar.get(position).getDESCRIPTION());
            return convertView;
        }
    }
}

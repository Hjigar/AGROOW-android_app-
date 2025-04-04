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
import com.example.myapplication.models_agroww.agroww_tot_sales_GT;
import com.example.myapplication.models_agroww.agroww_total_sales;
import com.example.myapplication.models_agroww.product_GT;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admin_tot_sales extends AppCompatActivity {
    TextView day1,day7,day31,day365,stock,user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_tot_sales);
        day1=(TextView)findViewById(R.id.day1);
        day7=(TextView)findViewById(R.id.day7);
        day31=(TextView)findViewById(R.id.day31);
        day365=(TextView)findViewById(R.id.day365);
        stock=(TextView)findViewById(R.id.stock);
        user=(TextView)findViewById(R.id.users);
        //TOTAL=(ListView)findViewById(R.id.tot);

        DASHBOARD();

    }

    private void DASHBOARD() {
        Call<agroww_total_sales> call= RetrofitClient_agroww
                .getInstance()
                .getApi()
                .tot_agroww();
        call.enqueue(new Callback<agroww_total_sales>() {
            @Override
            public void onResponse(Call<agroww_total_sales> call, Response<agroww_total_sales> response) {
                agroww_total_sales totsal=response.body();
                if(response.isSuccessful())
                {
                    Toast.makeText(admin_tot_sales.this,totsal.getMessage(), Toast.LENGTH_LONG).show();
                   /* cust_Adapter ca=new cust_Adapter(admin_tot_sales.this,totsal.getData());
                    TOTAL.setAdapter(ca);*/
                    ArrayList<agroww_tot_sales_GT> gt = totsal.getData();
                    day1.setText(gt.get(0).getDay1());
                    day7.setText(gt.get(1).getDay7());
                    day31.setText(gt.get(2).getDay31());
                    day365.setText(gt.get(3).getDay365());
                    stock.setText(gt.get(4).getStock());
                    user.setText(gt.get(5).getUser());
                }
                Toast.makeText(admin_tot_sales.this,response.message()+"succses", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<agroww_total_sales> call, Throwable t) {
                Toast.makeText(admin_tot_sales.this,t.getMessage()+"fail", Toast.LENGTH_LONG).show();
            }
        });
    }
   /* public class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<agroww_tot_sales_GT> ar;
        LayoutInflater inflater;

        public cust_Adapter(admin_tot_sales context, ArrayList<agroww_tot_sales_GT> ar) {
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

            convertView = inflater.inflate(R.layout.a_tot_sales, null);

            TextView DAY1=(TextView) convertView.findViewById(R.id.day1);
            TextView DAY7=(TextView) convertView.findViewById(R.id.day7);
            TextView  MONTH1=(TextView) convertView.findViewById(R.id.mo1);
            TextView YEAR1=(TextView) convertView.findViewById(R.id.year1);
            TextView STOCK=(TextView) convertView.findViewById(R.id.stock);
            TextView USERS=(TextView) convertView.findViewById(R.id.users);


            DAY1.setText(ar.get(position).getDay1());
            DAY7.setText(ar.get(position).getDay7());
            MONTH1.setText(ar.get(position).getDay31());
            YEAR1.setText(ar.get(position).getDay365());
            STOCK.setText(ar.get(position).getStock());
            USERS.setText(ar.get(position).getUser());
            return convertView;
        }
    }*/
}

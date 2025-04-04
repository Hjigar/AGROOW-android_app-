package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.OrderData;
import com.example.myapplication.models_agroww.order_GT;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_orders extends AppCompatActivity {
    ListView Order_data;
    EditText SEARCH_ORDER;
    Button SEARCH;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_orders);
        Order_data = (ListView) findViewById(R.id.order_data);
        SEARCH_ORDER = (EditText) findViewById(R.id.search_date);
        SEARCH = (Button) findViewById(R.id.serach);
        SharedPreferences sp=getSharedPreferences("userSP",MODE_PRIVATE);
        uid=sp.getString("userSP","");
        //Toast.makeText(user_orders.this, uid, Toast.LENGTH_SHORT).show();
        try {
            ORDER_VIEW(uid);
        }catch (Exception e){
            Toast.makeText(user_orders.this, "order is empty", Toast.LENGTH_SHORT).show();
        }
        SEARCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SEARCH_ORDER_M();
            }
        });

    }

    private void SEARCH_ORDER_M() {
        Call<OrderData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .search_order(SEARCH_ORDER.getText().toString());
        call.enqueue(new Callback<OrderData>() {
            @Override
            public void onResponse(Call<OrderData> call, Response<OrderData> response) {
                OrderData orderData=response.body();
                Toast.makeText(user_orders.this, "Order Data", Toast.LENGTH_SHORT).show();
                cust_Adapter ca=new cust_Adapter(user_orders.this,orderData.getData());
                Order_data.setAdapter(ca);
            }

            @Override
            public void onFailure(Call<OrderData> call, Throwable t) {
                Toast.makeText(user_orders.this, "fail to fetch", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void ORDER_VIEW(String uid) {
            Call<OrderData> call=RetrofitClient_agroww
                    .getInstance()
                    .getApi()
                    .order_data(uid);
            call.enqueue(new Callback<OrderData>() {
                @Override
                public void onResponse(Call<OrderData> call, Response<OrderData> response) {
                    OrderData orderData=response.body();
                    Toast.makeText(user_orders.this, "Order Data", Toast.LENGTH_SHORT).show();
                    cust_Adapter ca=new cust_Adapter(user_orders.this,orderData.getData());
                    Order_data.setAdapter(ca);
                }

                @Override
                public void onFailure(Call<OrderData> call, Throwable t) {
                    Toast.makeText(user_orders.this, "fail to fetch", Toast.LENGTH_SHORT).show();

                }
            });
        }
        public class cust_Adapter extends BaseAdapter {
            Context context;
            ArrayList<order_GT> ar;
            LayoutInflater inflater;

            public cust_Adapter(user_orders context, ArrayList<order_GT> ar) {
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

                convertView = inflater.inflate(R.layout.user_order_list_view, null);


                TextView UID= (TextView) convertView.findViewById(R.id.uid);
                TextView PHONE = (TextView) convertView.findViewById(R.id.phone);
                TextView ADDRESS = (TextView) convertView.findViewById(R.id.address);
                TextView pname = (TextView) convertView.findViewById(R.id.pname);
                TextView price = (TextView) convertView.findViewById(R.id.price);
                TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
                TextView total = (TextView) convertView.findViewById(R.id.total);
                TextView buy_date = (TextView) convertView.findViewById(R.id.buy_date);
                ImageView REPORT=(ImageView)convertView.findViewById(R.id.report);
                String uid= String.valueOf(getIntent().getIntExtra("uid",0));

                //UID.setText(uid);
                //UID.setText(ar.get(position).getUSER_ID());
                PHONE.setText(ar.get(position).getPHONE());
                ADDRESS.setText(ar.get(position).getADDRESS());
                pname.setText(ar.get(position).getPname());
                price.setText(ar.get(position).getPrice());
                quantity.setText(ar.get(position).getQuantity());
                total.setText(ar.get(position).getTotal());
                buy_date.setText(ar.get(position).getPdate());

                REPORT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(user_orders.this,user_report_list.class);
                        //i.putExtra("uid", UID.getText().toString());
                        i.putExtra("pdate", buy_date.getText().toString());
                        i.putExtra("address",ADDRESS.getText().toString());
                        i.putExtra("phone", PHONE.getText().toString());
                        i.putExtra("pname",pname.getText().toString());
                        i.putExtra("price",price.getText().toString());
                        i.putExtra("quantity",quantity.getText().toString());
                        i.putExtra("Total",total.getText().toString());
                        startActivity(i);
                    }
                });

                return convertView;
            }
        }
}

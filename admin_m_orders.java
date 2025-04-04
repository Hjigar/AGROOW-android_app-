package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.OrderData;
import com.example.myapplication.models_agroww.ProductData;
import com.example.myapplication.models_agroww.UsersData;
import com.example.myapplication.models_agroww.order_GT;
import com.example.myapplication.models_agroww.product_GT;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admin_m_orders extends AppCompatActivity {
    ListView Order_data;
    EditText searchdate;
    TextView error;
    Button search;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_m_orders);
        Order_data=(ListView) findViewById(R.id.order_data);
        searchdate=(EditText)findViewById(R.id.search_date);
        error=(TextView) findViewById(R.id.error);
        search=(Button)findViewById(R.id.serach);
        ORDER_VIEW();

        searchdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int myear=c.get(Calendar.YEAR);
                int mMonth=c.get(Calendar.MONTH);
                int mday=c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dp=new DatePickerDialog(admin_m_orders.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        searchdate.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },myear,mMonth,mday);
                dp.show();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (searchdate.getText().toString().isEmpty())
                    {
                        error.setText("NO DATA FOUND");
                    }
                    else
                        {
                        SERACH_ORDERS();
                    }
                }catch (Exception e){
                    error.setText("date is too Long");
                }
            }
        });


    }

    private void SERACH_ORDERS() {
        Call<OrderData> call = RetrofitClient_agroww
                //all methid ne . this user kari ne tabkle na field  lakhi ne ene accsess karya
                .getInstance()
                .getApi()
                .serach_order(searchdate.getText().toString());
        //call ni enque method banai tena thi know abouet data add or not add
        call.enqueue(new Callback<OrderData>() {
            @Override
            public void onResponse(Call<OrderData> call, Response<OrderData> response) {
                OrderData udata = response.body();
                if (response.isSuccessful()) {
                    //Toast.makeText(admin_manage_user.this, udata.getMessage(), Toast.LENGTH_LONG).show();
                    cust_Adapter ca = new cust_Adapter(admin_m_orders.this, udata.getData());
                    Order_data.setAdapter(ca);
                }
                //whrne call data not insereted
                else {
                    Toast.makeText(admin_m_orders.this, udata.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            //failur call when app is fail and give error
            @Override
            public void onFailure(Call<OrderData> call, Throwable t) {
                Toast.makeText(admin_m_orders.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RES123", t.getMessage());
            }
        });
    }

    private void ORDER_VIEW() {
        Call<OrderData>call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .order_data_admin();
        call.enqueue(new Callback<OrderData>() {
            @Override
            public void onResponse(Call<OrderData> call, Response<OrderData> response) {
                OrderData orderData=response.body();
                Toast.makeText(admin_m_orders.this, "Order Data", Toast.LENGTH_SHORT).show();
                cust_Adapter ca=new cust_Adapter(admin_m_orders.this,orderData.getData());
                Order_data.setAdapter(ca);
            }

            @Override
            public void onFailure(Call<OrderData> call, Throwable t) {
                Toast.makeText(admin_m_orders.this, "fail to fetch", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<order_GT> ar;
        LayoutInflater inflater;

        public cust_Adapter(admin_m_orders context, ArrayList<order_GT> ar) {
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

            convertView = inflater.inflate(R.layout.admin_order_view_list, null);


            TextView  UID= (TextView) convertView.findViewById(R.id.uid);
            TextView PHONE = (TextView) convertView.findViewById(R.id.phone);
            TextView ADDRESS = (TextView) convertView.findViewById(R.id.address);
            TextView pname = (TextView) convertView.findViewById(R.id.pname);
            TextView price = (TextView) convertView.findViewById(R.id.price);
            TextView date = (TextView) convertView.findViewById(R.id.buy_date);
            TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
            Button delete=(Button)convertView.findViewById(R.id.delete);
            TextView total = (TextView) convertView.findViewById(R.id.total);


            //UID.setText(ar.get(position).getUSER_ID());
            PHONE.setText(ar.get(position).getPHONE());
            ADDRESS.setText(ar.get(position).getADDRESS());
            pname.setText(ar.get(position).getPname());
            price.setText(ar.get(position).getPrice());
            date.setText(ar.get(position).getPdate());
            quantity.setText(ar.get(position).getQuantity());
            total.setText(ar.get(position).getTotal());

            return convertView;
        }
    }
}

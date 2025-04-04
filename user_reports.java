package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.OrderData;
import com.example.myapplication.models_agroww.ReportData;
import com.example.myapplication.models_agroww.order_GT;
import com.example.myapplication.models_agroww.report_GT;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_reports extends AppCompatActivity {
    ListView Report_data;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_reports);
        Report_data=(ListView) findViewById(R.id.report_data);
        SharedPreferences sp=getSharedPreferences("userSP",MODE_PRIVATE);
        uid=sp.getString("userSP","");
        try{
        REPORT_VIEW(uid);
        }catch (Exception e){
            Toast.makeText(user_reports.this, "report is empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void REPORT_VIEW(String uid) {
        Call<ReportData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .report_data(uid);
        call.enqueue(new Callback<ReportData>() {
            @Override
            public void onResponse(Call<ReportData> call, Response<ReportData> response) {
                ReportData reportData=response.body();
                Toast.makeText(user_reports.this, "Report Data", Toast.LENGTH_SHORT).show();
                cust_Adapter ca=new cust_Adapter(user_reports.this,reportData.getData());
                Report_data.setAdapter(ca);
            }

            @Override
            public void onFailure(Call<ReportData> call, Throwable t) {
                Toast.makeText(user_reports.this, "fail to fetch", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<report_GT> ar;
        LayoutInflater inflater;

        public cust_Adapter(user_reports context, ArrayList<report_GT> ar) {
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

            convertView = inflater.inflate(R.layout.user_report_list, null);


            TextView UID= (TextView) convertView.findViewById(R.id.uid);
            TextView PHONE = (TextView) convertView.findViewById(R.id.phone);
            TextView ADDRESS = (TextView) convertView.findViewById(R.id.address);
            TextView pname = (TextView) convertView.findViewById(R.id.pname);
            TextView price = (TextView) convertView.findViewById(R.id.price);
            TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
            TextView total = (TextView) convertView.findViewById(R.id.total);
            TextView buy_date = (TextView) convertView.findViewById(R.id.buy_date);

           // UID.setText(ar.get(position).getUSER_ID());
            PHONE.setText(ar.get(position).getPHONE());
            ADDRESS.setText(ar.get(position).getADDRESS());
            pname.setText(ar.get(position).getPname());
            price.setText(ar.get(position).getPrice());
            quantity.setText(ar.get(position).getQuantity());
            total.setText(ar.get(position).getTotal());
            buy_date.setText(ar.get(position).getPdate());

            return convertView;
        }
    }
}

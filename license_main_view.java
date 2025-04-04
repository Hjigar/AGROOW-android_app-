package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
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

import com.example.myapplication.models_agroww.LicenseData;
import com.example.myapplication.models_agroww.license_GT;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class license_main_view extends AppCompatActivity {
    TextView Apply_lic;
    ListView LICENSE;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.licence);
        Apply_lic = (TextView) findViewById(R.id.apply_lic);
        LICENSE = (ListView) findViewById(R.id.license_data);
        License_view();


        Apply_lic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(license_main_view.this, user_license.class);
                startActivity(i);
            }
        });

    }

    public void License_view() {
        Call<LicenseData> call = RetrofitClient_agroww
                .getInstance()
                .getApi()
                .View_license();
        call.enqueue(new Callback<LicenseData>() {
            @Override
            public void onResponse(Call<LicenseData> call, Response<LicenseData> response) {
                LicenseData licenseData = response.body();
                if (response.isSuccessful()) {
                    try
                    {
                    Toast.makeText(license_main_view.this, "Success", Toast.LENGTH_SHORT).show();
                    cust_Adapter ca = new cust_Adapter(license_main_view.this, licenseData.getData());
                    LICENSE.setAdapter(ca);
                }catch(Exception e){}
                }
                else
                    {
                    Toast.makeText(license_main_view.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LicenseData> call, Throwable t) {

                Toast.makeText(license_main_view.this, "Fail to fetch data", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<license_GT> ar;
        LayoutInflater inflater;

        public cust_Adapter(license_main_view context, ArrayList<license_GT> ar) {
            this.context = context;
            this.ar = ar;
            inflater = (LayoutInflater.from(context));
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

            convertView = inflater.inflate(R.layout.user_license_list, null);


            TextView UID = (TextView) convertView.findViewById(R.id.uid);
            TextView License_ID= (TextView) convertView.findViewById(R.id.license_id);
            TextView phone = (TextView) convertView.findViewById(R.id.phone);
            ImageView farm = (ImageView) convertView.findViewById(R.id.farm);
            ImageView crop = (ImageView) convertView.findViewById(R.id.crop);
            TextView farm_add = (TextView) convertView.findViewById(R.id.farm_add);
            TextView desc = (TextView) convertView.findViewById(R.id.description);
            TextView status_lic = (TextView) convertView.findViewById(R.id.status);

           // UID.setText(ar.get(position).getUSER_ID());
            Picasso.get().load(RetrofitClient_agroww.IMG_URL+ar.get(position).getFARM_IMG()).into(farm);
            Picasso.get().load(RetrofitClient_agroww.IMG_URL+ar.get(position).getCROP_IMG()).into(crop);
            // farm.setText(ar.get(position).getFARM_IMG());
            //crop.setText(ar.get(position).getCROP_IMG());
            License_ID.setText(ar.get(position).getLicense_id());
            phone.setText(ar.get(position).getPHONE());
            farm_add.setText(ar.get(position).getFARM_ADD());
            desc.setText(ar.get(position).getDESCRIPTION());
            status_lic.setText(ar.get(position).getStatus());

            return convertView;
        }
    }
}

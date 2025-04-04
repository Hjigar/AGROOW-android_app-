package com.example.myapplication;

import android.content.Context;
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

import com.example.myapplication.models_agroww.LicenseData;
import com.example.myapplication.models_agroww.license_GT;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Approve_license_view extends AppCompatActivity {
    ListView APPROVED_DATA;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approve_license_view);
        APPROVED_DATA=(ListView)findViewById(R.id.license_data);
        manage_license_m();
    }


        public  class cust_Adapter extends BaseAdapter {
            Context context;
            ArrayList<license_GT> ar;
            LayoutInflater inflater;

            public cust_Adapter(Approve_license_view context, ArrayList<license_GT> ar) {
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

                convertView = inflater.inflate(R.layout.approve_license_list, null);

                //TextView UID= (TextView) convertView.findViewById(R.id.uid);
                TextView License_ID= (TextView) convertView.findViewById(R.id.license_id);
                TextView PHONE= (TextView) convertView.findViewById(R.id.phone);
                TextView LID= (TextView) convertView.findViewById(R.id.lid);
                ImageView farm= (ImageView) convertView.findViewById(R.id.farm);
                ImageView crop= (ImageView) convertView.findViewById(R.id.crop);
                TextView farm_add= (TextView) convertView.findViewById(R.id.farm_add);
                TextView desc= (TextView) convertView.findViewById(R.id.description);
                TextView status_lic= (TextView) convertView.findViewById(R.id.status);

                //String lid= String.valueOf(getIntent().getIntExtra("lid",0));

                //UID.setText(ar.get(position).getUSER_ID());
                License_ID.setText(ar.get(position).getLicense_id());
                PHONE.setText(ar.get(position).getPHONE());
                //LID.setText(lid);
                //farm.setText(ar.get(position).getFARM_IMG());
                //crop.setText(ar.get(position).getCROP_IMG());
                Picasso.get().load("http://172.16.16.61/AGROWW_30/Images/"+ar.get(position).getFARM_IMG()).into(farm);
                Picasso.get().load("http://172.16.16.61/AGROWW_30/Images/"+ar.get(position).getCROP_IMG()).into(crop);
                farm_add.setText(ar.get(position).getFARM_ADD());
                desc.setText(ar.get(position).getDESCRIPTION());
                status_lic.setText(ar.get(position).getStatus());
                return convertView;
            }
        }
        public void manage_license_m() {
            Call<LicenseData> call=RetrofitClient_agroww
                    .getInstance()
                    .getApi()
                    .Approved_license();
            call.enqueue(new Callback<LicenseData>() {
                @Override
                public void onResponse(Call<LicenseData> call, Response<LicenseData> response) {
                    LicenseData licenseData = response.body();
                    if(response.isSuccessful())
                    {
                        Toast.makeText(Approve_license_view.this, "Success", Toast.LENGTH_SHORT).show();
                        cust_Adapter ca = new cust_Adapter(Approve_license_view.this, licenseData.getData());
                        APPROVED_DATA.setAdapter(ca);
                    }
                    else
                    {
                        Toast.makeText(Approve_license_view.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LicenseData> call, Throwable t) {

                    Toast.makeText(Approve_license_view.this, "Fail to fetch data", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }


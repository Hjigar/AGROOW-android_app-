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

import com.example.myapplication.models_agroww.LicenseData;
import com.example.myapplication.models_agroww.license_GT;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admin_m_license extends AppCompatActivity {
    ListView l_data;
    Button Aproove,Decline;
    ImageView APPROVED,DECLINE;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_m_license);
        l_data=(ListView) findViewById(R.id.license_data);
        Aproove=(Button) findViewById(R.id.approve);
        Decline=(Button) findViewById(R.id.decline);
        APPROVED=(ImageView) findViewById(R.id.approved_license);
        DECLINE=(ImageView) findViewById(R.id.decline_license);



        APPROVED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_m_license.this,Approve_license_view.class);
                startActivity(i);
            }
        });
        DECLINE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_m_license.this,decline_license_view.class);
                startActivity(i);
            }
        });



        SharedPreferences sp=getSharedPreferences("LoginSP",MODE_PRIVATE);
        uid=sp.getString("userSP","");
        try {
            manage_license_m();
        }catch (Exception e){}
        Aproove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_m_license.this,Approve_license_view.class);
                startActivity(i);
            }
        });
        Decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_m_license.this,decline_license_view.class);
                startActivity(i);
            }
        });
    }

    public  class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<license_GT> ar;
        LayoutInflater inflater;

        public cust_Adapter(admin_m_license context, ArrayList<license_GT> ar) {
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

            convertView = inflater.inflate(R.layout.m_license_cust, null);

            //TextView UID= (TextView) convertView.findViewById(R.id.uid);
            TextView License_ID= (TextView) convertView.findViewById(R.id.license_id);
            TextView PHONE= (TextView) convertView.findViewById(R.id.phone);
            TextView LID= (TextView) convertView.findViewById(R.id.lid);
            ImageView farm= (ImageView) convertView.findViewById(R.id.farm);
            ImageView crop= (ImageView) convertView.findViewById(R.id.crop);
            TextView farm_add= (TextView) convertView.findViewById(R.id.farm_add);
            TextView desc= (TextView) convertView.findViewById(R.id.description);
            TextView status_lic= (TextView) convertView.findViewById(R.id.status);

            Button DELETE_LICENCE=(Button)convertView.findViewById(R.id.Dicline);
            Button APPROVE_LICENCE=(Button)convertView.findViewById(R.id.Approve);
            //String uid= String.valueOf(getIntent().getIntExtra("uid",0));

            //UID.setText(ar.get(position).getUSER_ID());
            License_ID.setText(ar.get(position).getLicense_id());
            PHONE.setText(ar.get(position).getPHONE());
            //UID.setText(uid);
            //farm.setText(ar.get(position).getFARM_IMG());
            //crop.setText(ar.get(position).getCROP_IMG());
            Picasso.get().load(RetrofitClient_agroww.IMG_URL+ar.get(position).getFARM_IMG()).into(farm);
            Picasso.get().load(RetrofitClient_agroww.IMG_URL+ar.get(position).getCROP_IMG()).into(crop);
            farm_add.setText(ar.get(position).getFARM_ADD());
            desc.setText(ar.get(position).getDESCRIPTION());
            status_lic.setText(ar.get(position).getStatus());

            DELETE_LICENCE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        DECLINE_LICENCE(uid);
                        manage_license_m();
                    }catch (Exception e){
                        Toast.makeText(admin_m_license.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            APPROVE_LICENCE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    APPROVE_LICENCE(uid);
                    manage_license_m();
                }
            });
            return convertView;
        }
    }

    private void APPROVE_LICENCE(String uid) {
        Call<LicenseData>call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .Approve_lic(uid);
        call.enqueue(new Callback<LicenseData>() {
            @Override
            public void onResponse(Call<LicenseData> call, Response<LicenseData> response) {
                Toast.makeText(admin_m_license.this, "Approved License", Toast.LENGTH_SHORT).show();
                manage_license_m();
            }

            @Override
            public void onFailure(Call<LicenseData> call, Throwable t) {
                Toast.makeText(admin_m_license.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void DECLINE_LICENCE(String uid) {
        Call<LicenseData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .dicline_license(uid);
        call.enqueue(new Callback<LicenseData>() {
            @Override
            public void onResponse(Call<LicenseData> call, Response<LicenseData> response) {
                Toast.makeText(admin_m_license.this, "Delete Success", Toast.LENGTH_SHORT).show();
                manage_license_m();
            }

            @Override
            public void onFailure(Call<LicenseData> call, Throwable t) {
                Toast.makeText(admin_m_license.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void manage_license_m() {
        Call<LicenseData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .manage_license();
        call.enqueue(new Callback<LicenseData>() {
            @Override
            public void onResponse(Call<LicenseData> call, Response<LicenseData> response) {
                LicenseData licenseData = response.body();
                if(response.isSuccessful())
                {
                    Toast.makeText(admin_m_license.this, "Success", Toast.LENGTH_SHORT).show();
                    cust_Adapter ca = new cust_Adapter(admin_m_license.this, licenseData.getData());
                    l_data.setAdapter(ca);
                }
                else
                {
                    Toast.makeText(admin_m_license.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LicenseData> call, Throwable t) {

                Toast.makeText(admin_m_license.this, "Fail to fetch data", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

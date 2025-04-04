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
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.LicenseData;
import com.example.myapplication.models_agroww.SubsidyData;
import com.example.myapplication.models_agroww.license_GT;
import com.example.myapplication.models_agroww.subsidy_GT;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admin_m_subsidy extends AppCompatActivity {
    ListView m_subsidy;
    ImageView APPROVED,DECLINE;
    TextView de;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_m_subsidy);
        m_subsidy=(ListView) findViewById(R.id.subsidy_data);
        de=(TextView)findViewById(R.id.t1);
        SharedPreferences sp=getSharedPreferences("LoginSP",MODE_PRIVATE);
        uid=sp.getString("userSP","");
        try {
            Manage_subsidy();
        }catch (Exception e){
            de.setText("NO DATA FOUND");
        }
        APPROVED=(ImageView) findViewById(R.id.approved_subsidy);
        DECLINE=(ImageView) findViewById(R.id.decline_subsidy);



        APPROVED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_m_subsidy.this,Approve_subsidy_view.class);
                startActivity(i);
            }
        });
        DECLINE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_m_subsidy.this,decline_subsidy_view.class);
                startActivity(i);
            }
        });
    }
    public class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<subsidy_GT> ar;
        LayoutInflater inflater;


        public cust_Adapter(admin_m_subsidy context, ArrayList<subsidy_GT> ar) {
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

            convertView = inflater.inflate(R.layout.m_subsidy_cust, null);

            TextView Subsidy_ID= (TextView) convertView.findViewById(R.id.subsidy_id);
            TextView PHONE= (TextView) convertView.findViewById(R.id.phone);
            TextView SID= (TextView) convertView.findViewById(R.id.sid);
            ImageView farm= (ImageView) convertView.findViewById(R.id.sfarm);
            ImageView crop= (ImageView) convertView.findViewById(R.id.scrop);
            TextView farm_add= (TextView) convertView.findViewById(R.id.sfarm_add);
            TextView sub_amt= (TextView) convertView.findViewById(R.id.sub_amt);
            TextView desc= (TextView) convertView.findViewById(R.id.sdescription);
            TextView sub_status= (TextView) convertView.findViewById(R.id.sub_status);
            Button DELETE_SUBSIDY=(Button)convertView.findViewById(R.id.Dicline);
            Button APPROVE_SUBSIDY=(Button)convertView.findViewById(R.id.Approve);

            //String sid= String.valueOf(getIntent().getIntExtra("sid",0));
            Subsidy_ID.setText(ar.get(position).getSubsidy_id());
            PHONE.setText(ar.get(position).getPHONE());
            //SID.setText(sid);
            Picasso.get().load(RetrofitClient_agroww.IMG_URL+ar.get(position).getFARM_IMG()).into(farm);
            Picasso.get().load(RetrofitClient_agroww.IMG_URL+ar.get(position).getCROP_IMG()).into(crop);
            farm_add.setText(ar.get(position).getFARM_ADD());
            sub_amt.setText(ar.get(position).getSub_amount());
            desc.setText(ar.get(position).getDesciption());
            sub_status.setText(ar.get(position).getStatus());

            DELETE_SUBSIDY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        DECLINE_SUBSIDY(uid);
                        Manage_subsidy();
                    }catch (Exception e){
                        Toast.makeText(admin_m_subsidy.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            APPROVE_SUBSIDY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Approve_sub(uid);
                    Manage_subsidy();
                }
            });
            return convertView;
        }
    }

    private void DECLINE_SUBSIDY(String uid) {
        Call<LicenseData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .dicline_subsidy(uid);
        call.enqueue(new Callback<LicenseData>() {
            @Override
            public void onResponse(Call<LicenseData> call, Response<LicenseData> response) {
                Toast.makeText(admin_m_subsidy.this, "Delete Success", Toast.LENGTH_SHORT).show();
                Manage_subsidy();
            }

            @Override
            public void onFailure(Call<LicenseData> call, Throwable t) {
                Toast.makeText(admin_m_subsidy.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Approve_sub(String uid) {
        Call<SubsidyData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .Approve_subsidy(uid);
        call.enqueue(new Callback<SubsidyData>() {
            @Override
            public void onResponse(Call<SubsidyData> call, Response<SubsidyData> response) {
                SubsidyData subsidyData = response.body();
                Toast.makeText(admin_m_subsidy.this, "APPROVE SUBSIDY", Toast.LENGTH_SHORT).show();
                Manage_subsidy();

            }

            @Override
            public void onFailure(Call<SubsidyData> call, Throwable t)
            {
                Toast.makeText(admin_m_subsidy.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Manage_subsidy() {
        Call<SubsidyData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .manage_subsidy();
        call.enqueue(new Callback<SubsidyData>() {
            @Override
            public void onResponse(Call<SubsidyData> call, Response<SubsidyData> response) {
                SubsidyData subsidyData = response.body();
                if (response.isSuccessful())
                {
                    cust_Adapter ca = new cust_Adapter(admin_m_subsidy.this, subsidyData.getData());
                    m_subsidy.setAdapter(ca);
                }
            }

            @Override
            public void onFailure(Call<SubsidyData> call, Throwable t)
            {
                Toast.makeText(admin_m_subsidy.this, "Fail to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

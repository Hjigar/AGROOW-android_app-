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

import com.example.myapplication.models_agroww.LicenseData;
import com.example.myapplication.models_agroww.SubsidyData;
import com.example.myapplication.models_agroww.license_GT;
import com.example.myapplication.models_agroww.subsidy_GT;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Approve_subsidy_view extends AppCompatActivity {
    ListView APPROVED_DATA;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approve_subsidy_view);
        APPROVED_DATA=(ListView)findViewById(R.id.subsidy_data);
        Approve_subsidy_view();
    }


    public  class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<subsidy_GT> ar;
        LayoutInflater inflater;

        public cust_Adapter(Approve_subsidy_view context, ArrayList<subsidy_GT> ar) {
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

            convertView = inflater.inflate(R.layout.approve_subsidy_list, null);

            TextView Subsidy_ID= (TextView) convertView.findViewById(R.id.subsidy_id);
            TextView PHONE= (TextView) convertView.findViewById(R.id.phone);
            TextView SID= (TextView) convertView.findViewById(R.id.sid);
            ImageView farm= (ImageView) convertView.findViewById(R.id.sfarm);
            ImageView crop= (ImageView) convertView.findViewById(R.id.scrop);
            TextView farm_add= (TextView) convertView.findViewById(R.id.sfarm_add);
            TextView sub_amt= (TextView) convertView.findViewById(R.id.sub_amt);
            TextView desc= (TextView) convertView.findViewById(R.id.sdescription);
            TextView sub_status= (TextView) convertView.findViewById(R.id.sub_status);


            //String sid= String.valueOf(getIntent().getIntExtra("sid",0));
            Subsidy_ID.setText(ar.get(position).getSubsidy_id());
            PHONE.setText(ar.get(position).getPHONE());
            //SID.setText(sid);
            Picasso.get().load("http://172.16.16.61/AGROWW_30/Images/"+ar.get(position).getFARM_IMG()).into(farm);
            Picasso.get().load("http://172.16.16.61/AGROWW_30/Images/"+ar.get(position).getCROP_IMG()).into(crop);
            farm_add.setText(ar.get(position).getFARM_ADD());
            sub_amt.setText(ar.get(position).getSub_amount());
            desc.setText(ar.get(position).getDesciption());
            sub_status.setText(ar.get(position).getStatus());
            return convertView;
        }
    }
    public void Approve_subsidy_view() {
        Call<SubsidyData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .Approved_subsidy();
        call.enqueue(new Callback<SubsidyData>() {
            @Override
            public void onResponse(Call<SubsidyData> call, Response<SubsidyData> response) {
                SubsidyData licenseData = response.body();
                if(response.isSuccessful())
                {
                    Toast.makeText(Approve_subsidy_view.this, "Success", Toast.LENGTH_SHORT).show();
                    cust_Adapter ca = new cust_Adapter(Approve_subsidy_view.this, licenseData.getData());
                    APPROVED_DATA.setAdapter(ca);
                }
                else
                {
                    Toast.makeText(Approve_subsidy_view.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubsidyData> call, Throwable t) {

                Toast.makeText(Approve_subsidy_view.this, "Fail to fetch data", Toast.LENGTH_SHORT).show();

            }
        });
    }

}


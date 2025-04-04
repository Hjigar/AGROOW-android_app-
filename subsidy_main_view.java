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

import com.example.myapplication.models_agroww.SubsidyData;
import com.example.myapplication.models_agroww.subsidy_GT;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class subsidy_main_view extends AppCompatActivity {
    TextView Apply_sub;
    ListView Subsidy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subsidy);
        Apply_sub = (TextView) findViewById(R.id.apply_subsidy);
        Subsidy = (ListView) findViewById(R.id.subsidy_data);
        Subsidy_view();

    Apply_sub.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(subsidy_main_view.this,user_subsidy.class);
            startActivity(i);
        }
    });
    }

    private void Subsidy_view() {
        Call<SubsidyData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .View_subsidy();
        call.enqueue(new Callback<SubsidyData>() {
            @Override
            public void onResponse(Call<SubsidyData> call, Response<SubsidyData> response) {
                SubsidyData subsidyData = response.body();
                if (response.isSuccessful())
                {
                    try {
                        cust_Adapter ca = new cust_Adapter(subsidy_main_view.this, subsidyData.getData());
                        Subsidy.setAdapter(ca);
                    }catch (Exception e){}
                }
            }

            @Override
            public void onFailure(Call<SubsidyData> call, Throwable t)
            {
                Toast.makeText(subsidy_main_view.this, "Fail to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<subsidy_GT> ar;
        LayoutInflater inflater;


        public cust_Adapter(subsidy_main_view context, ArrayList<subsidy_GT> ar) {
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

            convertView = inflater.inflate(R.layout.user_subsidy_list, null);

            TextView SID = (TextView) convertView.findViewById(R.id.sid);
            TextView Subsidy_ID= (TextView) convertView.findViewById(R.id.subsidy_id);
            TextView phone = (TextView) convertView.findViewById(R.id.phone);
            ImageView farm = (ImageView) convertView.findViewById(R.id.sfarm);
            ImageView crop = (ImageView) convertView.findViewById(R.id.scrop);
            TextView farm_add = (TextView) convertView.findViewById(R.id.sfarm_add);
            TextView sub_amt = (TextView) convertView.findViewById(R.id.sub_amt);
            TextView desc = (TextView) convertView.findViewById(R.id.sdescription);
            TextView sub_status = (TextView) convertView.findViewById(R.id.sub_status);
            String sid = String.valueOf(getIntent().getIntExtra("sid", 0));

            //SID.setText(sid);
           // farm.setText(ar.get(position).getFARM_IMG());
           // crop.setText(ar.get(position).getCROP_IMG());
            Subsidy_ID.setText(ar.get(position).getSubsidy_id());
            phone.setText(ar.get(position).getPHONE());
            Picasso.get().load(RetrofitClient_agroww.IMG_URL+ar.get(position).getFARM_IMG()).into(farm);
            Picasso.get().load(RetrofitClient_agroww.IMG_URL+ar.get(position).getCROP_IMG()).into(crop);
            farm_add.setText(ar.get(position).getFARM_ADD());
            sub_amt.setText(ar.get(position).getSub_amount());
            desc.setText(ar.get(position).getDesciption());
            //sub_status.setText(ar.get(position).getStatus());

            return convertView;
        }
    }
}

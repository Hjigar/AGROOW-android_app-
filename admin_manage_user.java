package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.UserData;
import com.example.myapplication.models_agroww.UsersData;
import com.example.myapplication.models_agroww.admn_m_user;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admin_manage_user extends AppCompatActivity {
    ListView lv;
    EditText Search;
    Button SEARCH;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_manage_user);
        lv=(ListView) findViewById(R.id.user_data);
        Search=(EditText)findViewById(R.id.search);
        SEARCH=(Button) findViewById(R.id.sb1);

        fetch_data();
        SEARCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Search.getText().toString().isEmpty())
                {
                    fetch_data();
                }
                else
                {
                    Search_user();
                }
            }
        });
    }

    public void Search_user()
    {
        Call<UsersData> call = RetrofitClient_agroww
                //all methid ne . this user kari ne tabkle na field  lakhi ne ene accsess karya
                .getInstance()
                .getApi()
                .serach_user(Search.getText().toString());
        //call ni enque method banai tena thi know abouet data add or not add
        call.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                UsersData udata = response.body();
                if (response.isSuccessful()) {
                    //Toast.makeText(admin_manage_user.this, udata.getMessage(), Toast.LENGTH_LONG).show();
                    cust_Adapter ca = new cust_Adapter(admin_manage_user.this, udata.getData());
                    lv.setAdapter(ca);
                }
                //whrne call data not insereted
                else {
                    Toast.makeText(admin_manage_user.this, udata.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            //failur call when app is fail and give error
            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                Toast.makeText(admin_manage_user.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RES123", t.getMessage());
            }
        });
    }
        public void fetch_data()
        {
            Call<UsersData> call = RetrofitClient_agroww
                    //all methid ne . this user kari ne tabkle na field  lakhi ne ene accsess karya
                    .getInstance()
                    .getApi()
                    //fiel na  name and ena data
                    .fetch_user();
            //call ni enque method banai tena thi know abouet data add or not add
            call.enqueue(new Callback<UsersData>() {
                @Override
                public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                    UsersData udata = response.body();
                    if (response.isSuccessful()) {
                        //Toast.makeText(admin_manage_user.this, udata.getMessage(), Toast.LENGTH_LONG).show();
                        cust_Adapter ca = new cust_Adapter(admin_manage_user.this, udata.getData());
                        lv.setAdapter(ca);

                    }
                    //whrne call data not insereted
                    else {
                        Toast.makeText(admin_manage_user.this, udata.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                //failur call when app is fail and give error
                @Override
                public void onFailure(Call<UsersData> call, Throwable t) {
                    Toast.makeText(admin_manage_user.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("RES123", t.getMessage());
                }
            });
        }

       public class cust_Adapter extends BaseAdapter {
            Context context;
            ArrayList<admn_m_user> ar;
            LayoutInflater inflater;

            public cust_Adapter(admin_manage_user context, ArrayList<admn_m_user> ar) {
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

                convertView = inflater.inflate(R.layout.admin_m_u_s_view, null);

                TextView ID=(TextView) convertView.findViewById(R.id.uid);
                TextView fn= (TextView) convertView.findViewById(R.id.fn);
                TextView ln = (TextView) convertView.findViewById(R.id.ln);
                TextView ph= (TextView) convertView.findViewById(R.id.phno);
                TextView dob = (TextView) convertView.findViewById(R.id.dob);
                TextView add= (TextView) convertView.findViewById(R.id.addres);
                TextView ema = (TextView) convertView.findViewById(R.id.email);
                TextView pass = (TextView) convertView.findViewById(R.id.pass);
                Button DELETE=(Button) convertView.findViewById(R.id.delete);
                Button UPDATE=(Button) convertView.findViewById(R.id.update);
                String id= String.valueOf(getIntent().getIntExtra("id",0));


               //ID.setText(id);
                fn.setText(ar.get(position).getF_NAME());
                ln.setText(ar.get(position).getL_NAME());
                ph.setText(ar.get(position).getPHONE());
                dob.setText(ar.get(position).getDOB());
                add.setText(ar.get(position).getADDRESS());
                ema.setText(ar.get(position).getEMAIL());
                pass.setText(ar.get(position).getPASSWORD());

                DELETE.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DELETE_USER(ar.get(position).getUSER_ID());
                        fetch_data();
                    }
                });
                UPDATE.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent i=new Intent(admin_manage_user.this,update_user.class);
                        i.putExtra("uid",ar.get(position).getUSER_ID());
                        i.putExtra("fname",ar.get(position).getF_NAME());
                        i.putExtra("lname",ar.get(position).getL_NAME());
                        i.putExtra("dob",ar.get(position).getDOB());
                        i.putExtra("phone",ar.get(position).getPHONE());
                        i.putExtra("address",ar.get(position).getADDRESS());
                        i.putExtra("email",ar.get(position).getEMAIL());
                        i.putExtra("password",ar.get(position).getPASSWORD());
                        startActivity(i);
                        fetch_data();
                    }
                });
                return convertView;
            }
        }

    private void UPDATE_USER() {

    }

    private void DELETE_USER(int id) {
        Call<UsersData>call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .Delete_user(id);
        call.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                Toast.makeText(admin_manage_user.this, "DELETE USER", Toast.LENGTH_SHORT).show();
                fetch_data();
            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                Toast.makeText(admin_manage_user.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

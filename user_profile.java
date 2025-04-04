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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.SubsidyData;
import com.example.myapplication.models_agroww.UserData;
import com.example.myapplication.models_agroww.UsersData;
import com.example.myapplication.models_agroww.admn_m_user;
import com.example.myapplication.models_agroww.user_profile_GT;
import com.example.myapplication.models_agroww.user_profile_data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_profile extends AppCompatActivity {
    Button LOGOUT;
    TextView UNAME;
    ListView USERDATA;
    String uname;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        LOGOUT=(Button) findViewById(R.id.logout);
        UNAME=(TextView)findViewById(R.id.uname);
       // SUB_AMT=(TextView)findViewById(R.id.sub_amt);
        USERDATA=(ListView)findViewById(R.id.user_data);
        USERSDATA();

        LOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("userSP",MODE_PRIVATE);
                sp.edit().clear().commit();
                //logout(uid) ;
                Intent i=new Intent(user_profile.this,main_agroww_login1.class);
                startActivity(i);
            }
        });

        SharedPreferences sp=getSharedPreferences("fname",MODE_PRIVATE);
        uname=sp.getString("fname","");
        UNAME.setText(uname);



    }



    private void USERSDATA() {
        Call<user_profile_data>call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .user_details();
        call.enqueue(new Callback<user_profile_data>() {
            @Override
            public void onResponse(Call<user_profile_data> call, Response<user_profile_data> response) {
                user_profile_data udata = response.body();
                if(response.isSuccessful())
                {
                    cust_Adapter ca = new cust_Adapter(user_profile.this, udata.getData());
                    USERDATA.setAdapter(ca);
                }
            }

            @Override
            public void onFailure(Call<user_profile_data> call, Throwable t) {

            }
        });
    }
    public class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<user_profile_GT> ar;
        LayoutInflater inflater;

        public cust_Adapter(user_profile context, ArrayList<user_profile_GT> ar) {
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

            convertView = inflater.inflate(R.layout.user_details_view, null);

            TextView fn= (TextView) convertView.findViewById(R.id.fn);
            TextView lid = (TextView) convertView.findViewById(R.id.lid);
            TextView sid = (TextView) convertView.findViewById(R.id.sid);
            TextView samt = (TextView) convertView.findViewById(R.id.samt);


            fn.setText(UNAME.getText().toString());
            lid.setText(ar.get(position).getLicense_id());
            sid.setText(ar.get(position).getSubsidy_id());
            samt.setText(ar.get(position).getSub_amount());

            return convertView;
        }
    }

    /*private void logout() {
        Call<UsersData>call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .logout(uid);
        call.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                Toast.makeText(user_profile.this, "Logout", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                Toast.makeText(user_profile.this, "fail to logout", Toast.LENGTH_SHORT).show();

            }
        });
    }*/
}
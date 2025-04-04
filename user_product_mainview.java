package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.example.myapplication.models_agroww.ProductData;
import com.example.myapplication.models_agroww.product_GT;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_product_mainview extends AppCompatActivity {
    TextView PNAME, PRICE, DESCRIPTION;
    ImageView PIMG;
    EditText QUAN;
    Button BUY;
    ArrayList<product_GT> ar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_product_mainview);
        //FETCH_PRODUCT();
        QUAN = (EditText) findViewById(R.id.quantity);
        PNAME = (TextView) findViewById(R.id.pname);
        PRICE = (TextView) findViewById(R.id.price);
        PIMG = (ImageView) findViewById(R.id.pimg);
        DESCRIPTION = (TextView) findViewById(R.id.description);
        BUY = (Button) findViewById(R.id.buy);
        //Toast.makeText(user_product_mainview.this, getIntent().getStringExtra("pid"), Toast.LENGTH_SHORT).show();

        //user data aii agad getch thay che jyare buy par click kare tyare


        PNAME.setText(getIntent().getStringExtra("pname"));
        PRICE.setText(getIntent().getStringExtra("price"));
        QUAN.setText(getIntent().getStringExtra("quantity"));
        DESCRIPTION.setText(getIntent().getStringExtra("des"));

        Picasso.get().load(RetrofitClient_agroww.IMG_URL + getIntent().getStringExtra("pimg")).into(PIMG);

        BUY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(QUAN.getText().toString()))
                {
                    QUAN.setError("Please input quantity");
                    return;
                }
                Intent i = new Intent(user_product_mainview.this, buy_productview.class);
                i.putExtra("pid",getIntent().getIntExtra("pid",0));
                i.putExtra("pname", PNAME.getText().toString());
                i.putExtra("price",PRICE.getText().toString());
                i.putExtra("quantity",QUAN.getText().toString());
                i.putExtra("pimg",getIntent().getStringExtra("pimg"));
                startActivity(i);
            }
        });

    }
}

    /*private void FETCH_PRODUCT() {
        Call<ProductData> call= RetrofitClient_agroww
                .getInstance()
                .getApi()
                .product_m_fetch();
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                ProductData pdata=response.body();
                if(response.isSuccessful())
                {
                    Toast.makeText(user_product_mainview.this,pdata.getMessage(), Toast.LENGTH_LONG).show();
                    cust_Adapter ca=new cust_Adapter(user_product_mainview.this,pdata.getData());
                    PRODUCT_DATA.setAdapter(ca);

                }
                Toast.makeText(user_product_mainview.this,response.message()+"succses", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Toast.makeText(user_product_mainview.this,t.getMessage()+"fail", Toast.LENGTH_LONG).show();
            }
        });
    }
    public class cust_Adapter extends BaseAdapter {
        Context context;
        ArrayList<product_GT> ar;
        LayoutInflater inflater;

        public cust_Adapter(user_product_mainview context, ArrayList<product_GT> ar) {
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

            convertView = inflater.inflate(R.layout.user_product_mainview, null);

            ImageView pimg = (ImageView) convertView.findViewById(R.id.pimg);
            TextView PNAME = (TextView) convertView.findViewById(R.id.pname);
            TextView PRICE = (TextView) convertView.findViewById(R.id.price);
            TextView DESCRIPTION = (TextView) convertView.findViewById(R.id.description);
            TextView QUAN = (TextView) convertView.findViewById(R.id.quantity);


           // pimg.setImageResource("http://172.16.16.61/AGROWW_30/Images/"+ar.get(position).getP_IMAGE());
            //Picasso.get().load("http://172.16.16.61/AGROWW_30/Images/"+ar.get(position).getP_IMAGE()).into(pimg);
            PNAME.setText(getIntent().getStringExtra("pname"));
            PRICE.setText(getIntent().getStringExtra("price"));
            DESCRIPTION.setText(ar.get(position).getDESCRIPTION());
            QUAN.getText().toString();
            return convertView;
        }
    }*/



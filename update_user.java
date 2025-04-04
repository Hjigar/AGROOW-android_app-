package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.UsersData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class update_user extends AppCompatActivity {
    EditText UID,FNAME,LNAME,PHONE,ADDRESS,DOB,EMAIL,PASSWORD;
    Button UPDATE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_view);
        EditText UID=(EditText) findViewById(R.id.uid);
        EditText FNAME= (EditText) findViewById(R.id.fn);
        EditText LNAME = (EditText) findViewById(R.id.ln);
        EditText PHONE= (EditText) findViewById(R.id.phno);
        EditText DOB = (EditText) findViewById(R.id.dob);
        EditText ADDRESS= (EditText) findViewById(R.id.addres);
        EditText EMAIL = (EditText) findViewById(R.id.email);
        EditText PASSWORD = (EditText) findViewById(R.id.pass);
        UPDATE=(Button) findViewById(R.id.update);
        String uid= String.valueOf(getIntent().getIntExtra("uid",0));
        UID.setText(uid);
        FNAME.setText(getIntent().getStringExtra("fname"));
        LNAME.setText(getIntent().getStringExtra("lname"));
        PHONE.setText(getIntent().getStringExtra("phone"));
        DOB.setText(getIntent().getStringExtra("dob"));
        ADDRESS.setText(getIntent().getStringExtra("address"));
        EMAIL.setText(getIntent().getStringExtra("email"));
        PASSWORD.setText(getIntent().getStringExtra("password"));

       UPDATE.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               UPDATE_USER();
           }
       });
    }

    private void UPDATE_USER() {
        Call<UsersData> call=RetrofitClient_agroww
                .getInstance()
                .getApi()
                .update_user(UID.getText().toString(),FNAME.getText().toString(),LNAME.getText().toString(),PHONE.getText().toString(),DOB.getText().toString(),ADDRESS.getText().toString(),EMAIL.getText().toString(),PASSWORD.getText().toString());
        call.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                Toast.makeText(update_user.this, "UPADTE USER", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(update_user.this,admin_manage_user.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                Toast.makeText(update_user.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

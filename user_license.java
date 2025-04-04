package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models_agroww.LicenseData;
import com.example.myapplication.models_agroww.ResponceModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_license extends AppCompatActivity {
    EditText PHONE,FARM,CROP,F_ADD,DESC,UID;
    Button L_APPLY;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.licence_create);
        UID=(EditText) findViewById(R.id.uid);
        PHONE=(EditText) findViewById(R.id.phone);
        FARM=(EditText) findViewById(R.id.farm_img);
        CROP=(EditText) findViewById(R.id.crop_img);
        F_ADD=(EditText) findViewById(R.id.farm_add);
        DESC=(EditText) findViewById(R.id.despicstion);
        L_APPLY=(Button)findViewById(R.id.apply_license);

        SharedPreferences sp=getSharedPreferences("userSP",MODE_PRIVATE);
        uid=sp.getString("userSP","");
        UID.setText(uid);

        L_APPLY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    if (check_credentials() == true)
                    {
                        APPLY_LICENSE();
                    }
                }catch (Exception e){
                    Toast.makeText(user_license.this, "put all data", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void APPLY_LICENSE() {
        Call<LicenseData>call =RetrofitClient_agroww
                .getInstance()
                .getApi()
                .APPLY_L(uid,PHONE.getText().toString(),FARM.getText().toString(),CROP.getText().toString(),F_ADD.getText().toString(),DESC.getText().toString());
        call.enqueue(new Callback<LicenseData>() {
            @Override
            public void onResponse(Call<LicenseData> call, Response<LicenseData> response) {
                Toast.makeText(user_license.this, response.message()+"Apply Succesfuly", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LicenseData> call, Throwable t) {
                Toast.makeText(user_license.this, t.getMessage()+"fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean check_credentials()
    {

        if(TextUtils.isEmpty(PHONE.getText().toString()))
        {
            PHONE.setError("Phone Number is Required");
            return false;
        }
        else if(PHONE.length()<10)
        {
            PHONE.setError("Phone Number Compelsory 10 Digits");
            return false;
        }else if(PHONE.length()>10)
        {
            PHONE.setError("Phone Number Compelsory 10 Digits");
            return false;
        }

        else if(TextUtils.isEmpty(FARM.getText().toString()))
        {
            FARM.setError("Farm Image  is Required");
            return false;
        }
        else if(TextUtils.isEmpty(CROP.getText().toString()))
        {
            CROP.setError("Crop Image is Required");
            return false;
        }
        else if(TextUtils.isEmpty(F_ADD.getText().toString()))
        {
            F_ADD.setError("Farm Adderss is required");
            return false;
        }
        else if(TextUtils.isEmpty(DESC.getText().toString()))
        {
            DESC.setError("Description is required");
            return false;
        }
        return true;
    }
}

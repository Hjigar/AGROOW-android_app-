package com.example.myapplication;

import com.example.myapplication.models_agroww.ResponceModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface User_Api {

    @FormUrlEncoded
    @POST("User_insert.php")
    Call<ResponceModel> user_insert(
            @Field("fn") String fn,
            @Field("ln") String ln,
            @Field("phno") String phone,
            @Field("dob") String dob,
            @Field("addres") String address,
            @Field("email") String email,
            @Field("pass") String password
    );
}

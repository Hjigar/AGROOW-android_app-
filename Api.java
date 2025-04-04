package com.example.myapplication;

import com.example.myapplication.models.ResponceModel;
import com.example.myapplication.models.UsersData;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    //
    @FormUrlEncoded
    //post method this data put thase
    @POST("inser_demo.php")
    //call na responesemodel this table na data na fiel coonec karva
    Call<ResponceModel> registeration(
        @Field("name") String name,
        @Field("ph") String ph
        );
    @GET("select_demo")
    Call<UsersData> fetchdata(
    );
    @FormUrlEncoded
    @POST("Admin_insert.php")
    Call<com.example.myapplication.models_agroww.ResponceModel> admin(
            @Field("name") String name,
            @Field("ph") String phone,
            @Field("email") String email,
            @Field("pass") String password
    );
}

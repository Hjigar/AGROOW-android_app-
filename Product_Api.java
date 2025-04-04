package com.example.myapplication;

import com.example.myapplication.models_agroww.ResponceModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Product_Api {
    @FormUrlEncoded
    @POST("Product_insert.php")
    Call<ResponceModel> product_insert(
            @Field("pname") String pname,
            @Field("price") String price,
            @Field("description") String description,
            @Field("pquan") String pquan,
            @Field("pimg") String pimg
    );
}

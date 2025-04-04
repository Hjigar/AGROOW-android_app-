package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient_agroww {
    private static String BASE_URL="http://172.16.16.61/AGROWW_30/";
    public static String IMG_URL="http://172.16.16.61/AGROWW_30/Images/";
    private static RetrofitClient_agroww retrofitClient;
    private static Retrofit retrofit;

    public RetrofitClient_agroww() {
        retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient_agroww getInstance()
    {
        //check karse ke eretrofit vche le nai
        // jo hase to return karse nai hoty to new client craete karse
        if(retrofitClient==null)
        {
            retrofitClient=new RetrofitClient_agroww();
        }
        return retrofitClient;
    }
    //retrofit na object thi api na class ne creete kari ne ena object thi  call akrayo
    public Admin_Api getApi()
    {
        return retrofit.create(Admin_Api.class);
    }

}

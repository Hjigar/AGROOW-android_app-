package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//retrofiltclient ee pph no bridge che je data baee sathe
// conenct thase jenathi accseess annd all opertaion thase
public class RetrofitClient {
    //base url for coonect data d=base
    private static String BASE_URL="http://172.16.16.61/tybca30/";
    //retrofitclint no object thi dat bazse ma bridge banse third party data ba de na
    private static RetrofitClient retrofitClient;
    //retrofit na object thi data access thse pura program ma
    private static Retrofit retrofit;

    //public method banai ratrofit client ni ena this bade
    // urlm set ksri ne factrory ma data sert kasri ne ene build karai
    public RetrofitClient() {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //gon conveter factory data ne json formate ma add karse and select karse
                .addConverterFactory(GsonConverterFactory.create())
                //last ma data ne build karse
                .build();
    }
    //syncronizeed me thod bani en athi check karse alredy che ke nai
    public static synchronized RetrofitClient getInstance()
    {
        //check karse ke eretrofit vche le nai
        // jo hase to return karse nai hoty to new client craete karse
        if(retrofitClient==null)
        {
            retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }
    //retrofit na object thi api na class ne creete kari ne ena object thi  call akrayo
    public Api getApi()
    {
        return retrofit.create(Api.class);
    }
}

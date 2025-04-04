package com.example.myapplication;

import com.example.myapplication.models_agroww.LicenseData;
import com.example.myapplication.models_agroww.OrderData;
import com.example.myapplication.models_agroww.ProductData;
import com.example.myapplication.models_agroww.ReportData;
import com.example.myapplication.models_agroww.ResponceModel;
import com.example.myapplication.models_agroww.SubsidyData;
import com.example.myapplication.models_agroww.UsersData;
import com.example.myapplication.models_agroww.agroww_total_sales;
import com.example.myapplication.models_agroww.user_profile_data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
 public interface Admin_Api {
     //ADMIN NSIDE APIS
     //ADMIN LOGIN
     @FormUrlEncoded
     @POST("Admin_login.php")
     Call<ResponceModel> Login(
             @Field("email") String email,
             @Field("pass") String password
     );

     @FormUrlEncoded
     @POST("Admin_insert.php")
     Call<ResponceModel> admin_insert(
             @Field("name") String name,
             @Field("ph") String phone,
             @Field("email") String email,
             @Field("pass") String password
     );

     //Admin MANGE USER

     @GET("Admin_manage_user.php")
     Call<UsersData> fetch_user();

     @FormUrlEncoded
     @POST("delete_user.php")
     Call<UsersData> Delete_user(
             @Field("uid") int id
     );

     @FormUrlEncoded
     @POST("Search_user.php")
     Call<UsersData> serach_user(
             @Field("uname") String uname
     );


     @FormUrlEncoded
     @POST("update_user.php")
     Call<UsersData> update_user(
             @Field("uid") String  uid,
             @Field("fn") String fn,
             @Field("ln") String ln,
             @Field("phone") String phone,
             @Field("dob") String dob,
             @Field("address") String address,
             @Field("email") String email,
             @Field("password") String password
     );
     //Admin MANGE LICENSE
     @GET("manage_license.php")
     Call<LicenseData> manage_license();

     @FormUrlEncoded
     @POST("Approve_license.php")
     Call<LicenseData> Approve_lic(
             @Field("uid") String uid
     );
     @FormUrlEncoded
     @POST("decline_license.php")
     Call<LicenseData> dicline_license(
             @Field("uid") String uid

     );

     //Admin MANGE SUBSIDY
     @FormUrlEncoded
     @POST("decline_subsidy.php")
     Call<LicenseData> dicline_subsidy(
             @Field("uid") String uid
     );

     @GET("Approved_subsidy_view.php")
     Call<SubsidyData> Approved_subsidy();

     @GET("Decline_subsidy_view.php")
     Call<SubsidyData> Decline_subsidy();

     @GET("manage_subsidy.php")
     Call<SubsidyData> manage_subsidy();

     @FormUrlEncoded
     @POST("delete_subsidy.php")
     Call<SubsidyData> delete_subsidy(
             @Field("sid") int sid
     );

     @FormUrlEncoded
     @POST("Approve_subsidy.php")
     Call<SubsidyData> Approve_subsidy(
             @Field("uid") String uid
     );
     //Admin MANGE PRODUCTS
     //PRODUCT API
     @FormUrlEncoded
     @POST("Product_insert.php")
     Call<ResponceModel> product_insert(
             @Field("pname") String pname,
             @Field("price") String price,
             @Field("pdesc") String pdesc,
             @Field("pquan") String pquan,
             @Field("pimg") String pimg
     );
     @GET("product_fetch.php")
     Call<ProductData> product_m_fetch();


     @FormUrlEncoded
     @POST("delete_product.php")
     Call<ProductData> del_product(
             @Field("pid") int pid
     );

     @FormUrlEncoded
     @POST("update_product.php")
     Call<ProductData> update_product(
             String s, @Field("pid") String pid,
             @Field("pname") String pname,
             @Field("price") String price,
             @Field("quantity") String pquan,
             @Field("pimg") String pimg
     );
     //Admin MANGE ORDERS

     @GET("order_view_admin.php")
     Call<OrderData> order_data_admin(
     );

     @FormUrlEncoded
     @POST("serach_order.php")
     Call<OrderData> serach_order(
             @Field("pdate") String pdate
     );

     //Admin MANGE TOTAL_SALES
     @GET("dash1.php")
     Call<agroww_total_sales> tot_agroww(
     );

     //USERS API'S


     //USER LICENSE
     @GET("User_license.php")
     Call<LicenseData> View_license();

     @FormUrlEncoded
     @POST("License_insert.php")
     Call<LicenseData> APPLY_L(
             @Field("uid") String uid,
             @Field("phone") String phone,
             @Field("farm_img") String farm_img,
             @Field("crop_img") String crop_img,
             @Field("fadd") String fadd,
             @Field("description") String description
     );


     @GET("Approved_license_view.php")
     Call<LicenseData> Approved_license();

     @GET("Decline_license_view.php")
     Call<LicenseData> Decline_license();


     // USER SUBSIDY

     @FormUrlEncoded
     @POST("Subsidy_insert.php")
     Call<SubsidyData> APPLY_S(
             @Field("uid") String uid,
             @Field("phone") String phone,
             @Field("farm_img") String farm_img,
             @Field("crop_img") String crop_img,
             @Field("fadd") String fadd,
             @Field("subsidy_amo") String subsidy_amo,
             @Field("description") String description
     );
     @GET("User_subsidy.php")
     Call<SubsidyData> View_subsidy();
     //USER PRODUCTS

     @FormUrlEncoded
     @POST("product_buy.php")
     Call<ProductData> product_buy(
             @Field("pid") String pid,
             @Field("uid") String uid,
             @Field("phone") String phone,
             @Field("add") String address,
             @Field("pname") String pname,
             @Field("price") String price,
             @Field("pquan") String pquan,
             @Field("total") String total

     );
     //USER ORDERS
     @FormUrlEncoded
     @POST("order_view.php")
     Call<OrderData> order_data(
             @Field("uid") String uid
     );

     @FormUrlEncoded
     @POST("order_view.php")
     Call<ReportData> report_data(
             @Field("uid") String uid

     );
     @GET("search_order.php")
     Call<OrderData> search_order(
             @Field("pdate") String pdate
     );

     @FormUrlEncoded
     @POST("gpaymode.php")
     Call<ProductData> paymentmode(
             @Field("uid") String uid
     );


     @FormUrlEncoded
     @POST("cod.php")
     Call<ProductData> paymentcod(
             @Field("uid") String uid
     );
     //USER REPORTS
     //USER REGISTER
     @FormUrlEncoded
     @POST("User_register.php")
     Call<ResponceModel> user_insert(
             @Field("fn") String fn,
             @Field("ln") String ln,
             @Field("phone") String phone,
             @Field("dob") String dob,
             @Field("address") String address,
             @Field("email") String email,
             @Field("password") String password
     );
     //USER PROFILE
     @GET("user_details.php")
     Call<user_profile_data> user_details();
     //USER
     //USER

  //License API

  @FormUrlEncoded
  @POST("delete_license.php")
  Call<LicenseData> delete_license(
          @Field("lid") int lid
  );

       //LOGOUT

     @GET("logout.php")
     Call<SubsidyData> logout();
 }

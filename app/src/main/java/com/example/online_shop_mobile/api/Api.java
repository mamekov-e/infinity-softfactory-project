package com.example.online_shop_mobile.api;

import com.example.online_shop_mobile.models.response.DefaultResponse;
import com.example.online_shop_mobile.models.response.LoginResponse;
import com.example.online_shop_mobile.models.response.ProductResponse;
import com.example.online_shop_mobile.models.User;
import com.example.online_shop_mobile.models.response.UsersResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("signup")
    Call<ResponseBody> createUser(
            @Query("email") String email,
            @Query("password") String password,
            @Query("firstName") String fName,
            @Query("lastName") String lName
    );

    @GET("login")
    Call<LoginResponse> loginUser(
            @Query("email") String email,
            @Query("password") String password
    );

    @GET("get-products")
    Call<ProductResponse> getProducts();

    @GET("allusers")
    Call<UsersResponse> getUsers();

    @FormUrlEncoded
    @POST("update-profile")
    Call<User> updateUser(
            @Field("email") String email,
            @Field("fName") String fName,
            @Field("lName") String lName,
            @Field("city") String city,
            @Field("address") String address

    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );

    @DELETE("deleteuser/{id}")
    Call<DefaultResponse> deleteUser(@Path("id") int id);

}

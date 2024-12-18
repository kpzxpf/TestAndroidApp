package com.example.test.data;

import com.example.test.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SupabaseUserApi {

    @GET("/rest/v1/users")
    Call<List<User>> getUsers(
            @Header("apikey") String apiKey,
            @Header("Authorization") String bearerToken
    );

    @POST("/rest/v1/users")
    Call<User> addUser(
            @Header("apikey") String apiKey,
            @Header("Authorization") String bearerToken,
            @Body User user
    );

    @GET("rest/v1/users")
    Call<List<User>> getUserById(
            @Header("apikey") String apiKey,
            @Header("Authorization") String bearerToken,
            @Query("id") String idFilter
    );

    @GET("rest/v1/users")
    Call<List<User>> existsUserPasswordAndEmail(
            @Header("apikey") String apiKey,
            @Header("Authorization") String bearerToken,
            @Query("email") String emailFilter,
            @Query("password")String passwordFilter
    );
}
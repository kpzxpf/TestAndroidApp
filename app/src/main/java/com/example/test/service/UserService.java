package com.example.test.service;

import com.example.test.data.SupabaseInitializer;
import com.example.test.data.SupabaseUserApi;
import com.example.test.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class UserService {
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVnbGh2YmJyZHN5Zm9kc3hhYnN5Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzMyODM0NTAsImV4cCI6MjA0ODg1OTQ1MH0.K-Gm4dpTWt3W3ayykLumTJT2-qThKxY-IQeRTwqp03A";
    private static final String BEARER_TOKEN = "Bearer " + API_KEY;
    private final SupabaseUserApi apiService = SupabaseInitializer.getClient()
            .create(SupabaseUserApi.class);


    public void getUsers(Callback<List<User>> callback) {
        Call<List<User>> call = apiService.getUsers(API_KEY, BEARER_TOKEN);
        call.enqueue(callback);
    }

    public void getUserById(long userId, Callback<List<User>> callback) {
        String idFilter = "eq." + userId;

        Call<List<User>> call = apiService.getUserById(API_KEY, BEARER_TOKEN, idFilter);
        call.enqueue(callback);
    }

    public void createUser(User user, Callback<User> callback) {
        Call<User> call = apiService.addUser(API_KEY, BEARER_TOKEN, user);
        call.enqueue(callback);
    }

    public void existsUserPassEmail(String email, String password, Callback<List<User>> callback){
        String emailFilter = "eq." + email;
        String passwordFilter = "eq." + password;

        Call<List<User>> call = apiService.existsUserPasswordAndEmail(
                API_KEY, BEARER_TOKEN, emailFilter, passwordFilter);
        call.enqueue(callback);
    }
}


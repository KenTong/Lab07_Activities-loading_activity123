package com.example.android.lab07_activities.service;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface SubmitAnswersService {
    @POST
    Call<String> send(@Url String url);
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://docs.google.com/")
            .addConverterFactory(new ToStringConverterFactory())
            .build();
}

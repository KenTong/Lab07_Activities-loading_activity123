package com.example.android.lab07_activities.service;

import com.example.android.lab07_activities.model.QuestionList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

public interface QuestionListService {

    // 宣告抽象方法 public abstract 可省略
    @GET("/uc?export=download&id=0BwIdzAjvQ8FwWG5HZUxIZHJOd0k")
    Call<QuestionList> getQuestionList();

    // 宣告 final 靜態欄位 (介面的欄位必定是 public static final ，也可省略)
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://drive.google.com/")
            .addConverterFactory(SimpleXmlConverterFactory.create()) // 將讀到的資料交給 SimpleXml 解析
            .build();

}
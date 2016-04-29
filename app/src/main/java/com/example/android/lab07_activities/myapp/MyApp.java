package com.example.android.lab07_activities.myapp;

import android.app.Application;
import android.content.Context;

import com.example.android.lab07_activities.model.UserAnswers;

public class MyApp extends Application {
    private static Context context;
    private static UserAnswers userAnswers;

    // getter
    public static Context getContext() { // 產生一個 Context (有內容的東西)
        return context;
    }

    // getter
    public static UserAnswers getUserAnswers() {
        if(userAnswers == null) {
            userAnswers = new UserAnswers(3); // 可放3題答案
        }
        return userAnswers;
    }
    // 建構子
    public MyApp() {
        context = this; // context 就是 MyApp 物件自己
    }
}

package com.example.android.lab07_activities.adapter;

public interface QuestionAdapter { // 轉接器
    int getQuestionCount();
    CharSequence getQuestion(int index);
    CharSequence getQuestionOptionsA(int index);
    CharSequence getQuestionOptionsB(int index);
    CharSequence getQuestionOptionsC(int index);

}

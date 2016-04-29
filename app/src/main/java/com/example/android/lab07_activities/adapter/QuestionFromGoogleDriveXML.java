package com.example.android.lab07_activities.adapter;

import com.example.android.lab07_activities.model.Question;

import java.util.List;

public class QuestionFromGoogleDriveXML implements QuestionAdapter {

    private List<Question> list;

    // 建構子
    public QuestionFromGoogleDriveXML(List<Question> list) {
        this.list = list;
    }

    @Override
    public int getQuestionCount() {
        return list.size();
    }

    @Override
    public CharSequence getQuestion(int index) {
        return list.get(index).getDescription();
    }

    @Override
    public CharSequence getQuestionOptionsA(int index) {
        return list.get(index).getOptionA();
    }

    @Override
    public CharSequence getQuestionOptionsB(int index) {
        return list.get(index).getOptionB();
    }

    @Override
    public CharSequence getQuestionOptionsC(int index) {
        return list.get(index).getOptionC();
    }
}
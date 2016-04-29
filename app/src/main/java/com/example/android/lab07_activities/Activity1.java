package com.example.android.lab07_activities;

public class Activity1 extends QuestionActivity {

    @Override
    protected Class getBackActivityClass() {
        return null;
    }

    @Override
    protected Class getNextActivityClass() {
        return Activity2.class;
    }

    @Override
    protected int getBackButtonVisibility() {
        return QuestionActivity.GONE;
    }

    @Override
    protected int getNextButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }
}

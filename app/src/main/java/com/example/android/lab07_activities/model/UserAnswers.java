package com.example.android.lab07_activities.model;

public class UserAnswers {
    private char[] answers;                 // 存放 3 個答案 (字元陣列)
    private CharSequence[] descriptions;    // 存放 3 個描述答案的字串

    // 建構子
    public UserAnswers(int questionCount) {
        answers = new char[questionCount];              // 建立陣列
        descriptions = new CharSequence[questionCount]; // 建立陣列
    }

    // setter
    public void setAnswer(int index, char answer, CharSequence description) {
        answers[index] = answer;
        descriptions[index] = description;
    }

    // getter
    public char getAnswer(int index) {
        return answers[index];
    }

    // getter
    public CharSequence getDescription(int index) {
        return descriptions[index];
    }
}



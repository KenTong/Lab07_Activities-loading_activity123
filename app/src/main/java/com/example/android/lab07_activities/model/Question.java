package com.example.android.lab07_activities.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Question {
    @Element
    private String description;
    @Element
    private String optionA;
    @Element
    private String optionB;
    @Element
    private String optionC;

    // 無參數建構子
    public Question() {
    }

    // 建構子
    public Question(String description, String optionA, String optionB, String optionC) {
        this.description = description;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
    }

    // getter
    public String getDescription() {
        return description;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }
}

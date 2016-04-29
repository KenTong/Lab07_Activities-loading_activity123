package com.example.android.lab07_activities.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class QuestionList {
    @ElementList
    private List<Question> list;

    // getter
    public List<Question> getList() {
        return list;
    }

}

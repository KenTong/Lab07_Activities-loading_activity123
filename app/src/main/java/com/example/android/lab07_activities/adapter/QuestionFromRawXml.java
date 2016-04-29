package com.example.android.lab07_activities.adapter;

import android.content.res.Resources;
import android.util.Log;

import com.example.android.lab07_activities.R;
import com.example.android.lab07_activities.model.Question;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuestionFromRawXml implements QuestionAdapter {

    List<Question> list = new ArrayList<>(); // 陣列存放所有的 Question 物件

    // 建構子
    public QuestionFromRawXml(Resources res)  throws IOException, XmlPullParserException {
        // 準備從 raw 資料夾讀取 xml 資料
        InputStream is = res.openRawResource(R.raw.questions); // 取得 xml 檔案資料流
        list = parse(is); // 執行轉換


    }

    private List<Question> parse(InputStream is) throws IOException, XmlPullParserException {

        // 取得 工廠
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

        // 取得 轉 xml 資料的物件 XmlPullParser
        XmlPullParser xpp = factory.newPullParser();

        // 設定 資料來源
        xpp.setInput(is, "UTF-8");

        // 讀取下一個 tag
        xpp.nextTag();

        // 驗證 tag 是 Exams
        xpp.require(XmlPullParser.START_TAG, null, "Exams");

        while(xpp.nextTag() == XmlPullParser.START_TAG) {
            xpp.require(XmlPullParser.START_TAG, null, "Exam");

            xpp.nextTag();
            xpp.require(XmlPullParser.START_TAG, null, "Question");
            String question = xpp.nextText();
            Log.d("question", question);
            xpp.require(XmlPullParser.END_TAG, null, "Question");

            xpp.nextTag();
            xpp.require(XmlPullParser.START_TAG, null, "OptionA");
            String optionA = xpp.nextText();
            xpp.require(XmlPullParser.END_TAG, null, "OptionA");

            xpp.nextTag();
            xpp.require(XmlPullParser.START_TAG, null, "OptionB");
            String optionB = xpp.nextText();
            xpp.require(XmlPullParser.END_TAG, null, "OptionB");

            xpp.nextTag();
            xpp.require(XmlPullParser.START_TAG, null, "OptionC");
            String optionC = xpp.nextText();
            xpp.require(XmlPullParser.END_TAG, null, "OptionC");

            xpp.nextTag();
            xpp.require(XmlPullParser.END_TAG, null, "Exam");

            list.add( new Question(question,
                            optionA,
                            optionB,
                            optionC)
            );

        }
        xpp.require(XmlPullParser.END_TAG, null, "Exams");

        return list;
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

package com.example.android.lab07_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditTextActivity extends AppCompatActivity {

    // key 值的最佳實踐方式，以 App 的 package 作為前綴詞
    public static final String BUNDLE_KEY_TEXT = "com.teacheryin.android.text"; // 包裹中資料的 key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
    }

    public void ok(View view) {
        // 取得輸入的文字
        EditText editText = (EditText)findViewById(R.id.edit_text);
        CharSequence text =  editText.getText();

        Intent intent = new Intent();           // 建立傳令兵
        intent.putExtra(BUNDLE_KEY_TEXT, text); // 將輸入文字的結果放進傳令兵的包裹裡

        setResult(RESULT_OK, intent);   // 傳送結果， 一切 OK
        finish(); // 結束目前的 Activity
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED); // 傳送結果，取消了，所以包裹沒放資料

        finish(); // 結束目前的 Activity
    }
}

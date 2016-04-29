package com.example.android.lab07_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_COLOR_REQUEST = 0;
    private static final int EDIT_TEXT_REQUEST = 1;

    private int m_color = 0xFFFFFFFF; // 紀錄選了什麼顏色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 按下 選擇顏色
    public void selectColor(View view) {
        // 建立傳令兵
        // new Intent( 發送者 , 接收者 )
        Intent intent = new Intent(this, ColorPickerActivity.class);

//        startActivity(intent); // 單純切畫面，沒有要結果

        // 啟動另一個 Activity 能傳回執行結果， 須設定 傳令兵(intent) 與 請求碼(request_code)
        startActivityForResult(intent, SELECT_COLOR_REQUEST);
    }

    // 按下 輸入文字
    public void editText(View view) {
        // 建立傳令兵
        // new Intent( 發送者 , 接收者 )
        Intent intent = new Intent(this, EditTextActivity.class);
        // 啟動另一個 Activity ， 設定 傳令兵 與 請求碼
        startActivityForResult(intent, EDIT_TEXT_REQUEST);
    }

    // 按下 NEXT (切下一個畫面 Activit1)
    public void next(View view) {
        Intent intent = new Intent(this, Activity1.class);
        // 將所選的顏色放到 intent 裡
        intent.putExtra(ColorPickerActivity.BUNDLE_KEY_COLOR_INT, m_color);
        startActivity(intent);

    }

    // 接收另一個 Activity 執行 setResult() 傳回結果
    // android system 收到結果會呼叫 onActivityResult()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 判斷哪個請求傳來的結果
        if(requestCode == SELECT_COLOR_REQUEST) {
            if(resultCode == RESULT_OK) {
                // 取得 傳令兵 包裹
                Bundle bundle = data.getExtras();

                // 從包裹拿出 Int 資料， 需提供資料的 key
                m_color = bundle.getInt(ColorPickerActivity.BUNDLE_KEY_COLOR_INT);
                CharSequence colorName = bundle.getCharSequence(ColorPickerActivity.BUNDLE_KEY_COLOR_NAME);

                // 從包裹拿出 CharSequence 資料， 需提供資料的 key
                TextView tv_color = (TextView) findViewById(R.id.tv_color);
                tv_color.setGravity(Gravity.CENTER);
                tv_color.setText(colorName);

                // 變更 ScrollView 背景顏色
                ScrollView scrollView = (ScrollView)findViewById(R.id.scroll_view);
                scrollView.setBackgroundColor(m_color);

            }
        } else if(requestCode == EDIT_TEXT_REQUEST) {
            if(resultCode == RESULT_OK) {
                // 取得 傳令兵 包裹
                Bundle bundle = data.getExtras();
                // 從包裹拿出 CharSequence 資料， 需提供資料的 key
                CharSequence text = bundle.getCharSequence(EditTextActivity.BUNDLE_KEY_TEXT);

                // 變更 TextView 中的文字
                TextView tv_color = (TextView) findViewById(R.id.tv_color);
                tv_color.setGravity(Gravity.LEFT); // 文字靠左對齊
                tv_color.setText(text);

            }
        }
    }

}

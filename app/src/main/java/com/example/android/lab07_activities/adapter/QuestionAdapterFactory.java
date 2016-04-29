package com.example.android.lab07_activities.adapter;

import android.content.res.Resources;
import android.util.Log;

import com.example.android.lab07_activities.model.Question;
import com.example.android.lab07_activities.model.QuestionList;
import com.example.android.lab07_activities.myapp.MyApp;
import com.example.android.lab07_activities.service.QuestionListService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionAdapterFactory { // 轉接器工廠

    // 哪個 Activity 要取得下載題目的 adapter，請它實作下面 CallBack 介面
    public interface Receiver {
        // 接收一個 Adapter
        void receiveQuestionAdapter(QuestionAdapter adapter);
    }

    // 靜態成員
    private static QuestionAdapter adapter;

    // 舊版
    public static QuestionAdapter getQuestionAdapter() {
        if(adapter == null) {
            // 透過 MyApp 取得 Context，進一步取得 Resources
            Resources res = MyApp.getContext().getResources();
            //            adapter = new QuestionFromStringResource(res);
            try {
                adapter = new QuestionFromRawXml(res);

            } catch(Exception e) {
                Log.d("factory",e.toString());
            }
        }
        return adapter;
    }

    // 新版
    public static void getQuestionAdapter(Receiver receiver) { // 新版
        loadFromGoogleDrive(receiver);  // 從 Google Drive 雲端
    }

    private static void loadFromGoogleDrive(final Receiver receiver) {
        // 建立 Service
        QuestionListService service = QuestionListService.retrofit.create(QuestionListService.class);

        // 執行 Service，將來透過 Call 來取得結果，結果的型別是 QuestionList
        Call<QuestionList> call =  service.getQuestionList();
        // 透過 Call 物件，建立新任務(新執行緒)，非同步來跑 Service (主執行緒 UI Thread 不用等檔案下載完成)
        // 準備一個實作 Callback<QuestionList>的物件，將來下載任務完成後會呼叫 onResponse() 或 onFailure()
        call.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                if(response.isSuccessful()) { // 若成功從網站獲取資料
                    Log.d("Retrofit", "onResponse() success");
                    QuestionList ql = response.body();
                    List<Question> list = ql.getList();
                    // QuestionFromGoogleDriveXML 類別稍後完成
                    adapter = new QuestionFromGoogleDriveXML(list);
                    receiver.receiveQuestionAdapter(adapter); // 通知 Activity 收 adapter
                } else { // 網站出問題，無法成功獲取資料
                    Log.d("Retrofit", "onResponse() : error response, no access to resource ?");
                }
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {
                // 目前無法連接網路
                Log.d("Retrofit", "onFailure() : " + t.toString());
            }
        });
    }

    // 私有建構子
    private QuestionAdapterFactory() { // 產生轉接器
    }

}


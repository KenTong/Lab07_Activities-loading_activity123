package com.example.android.lab07_activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.example.android.lab07_activities.adapter.QuestionAdapter;
import com.example.android.lab07_activities.adapter.QuestionAdapterFactory;
import com.example.android.lab07_activities.model.UserAnswers;
import com.example.android.lab07_activities.myapp.MyApp;
import com.example.android.lab07_activities.service.SubmitAnswersService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity3 extends QuestionActivity {

    @Override
    protected void onStart() {
        super.onStart();
        setNextButtonText("Finish"); // 呼叫父類別寫好的功能
    }

    @Override
    protected Class getBackActivityClass() {
        return Activity2.class;
    }

    @Override
    protected Class getNextActivityClass() {
        return null;
    }

    @Override
    protected int getBackButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }

    @Override
    protected int getNextButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }

    @Override
    public void next(View view) {

        QuestionAdapter adapter = QuestionAdapterFactory.getQuestionAdapter();
        final UserAnswers userAnswers = MyApp.getUserAnswers();
        StringBuilder message = new StringBuilder();

        message.append("您的作答如下\n\n");
        for(int i = 0 ; i < adapter.getQuestionCount() ; i++) {
            message.append(String.valueOf(i+1))
                    .append(": ")
                    .append(userAnswers.getAnswer(i))
                    .append("\n")
                    .append(userAnswers.getDescription(i))
                    .append("\n\n");

        }
        message.append("\n確定要結束?");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SubmitAnswersService service = SubmitAnswersService.retrofit.create(SubmitAnswersService.class);

                        char Q1Ans = userAnswers.getAnswer(0);
                        char Q2Ans = userAnswers.getAnswer(1);
                        char Q3Ans = userAnswers.getAnswer(2);

                        String url="/forms/d/1byHESFeGHUDSo4-RGvVLqrCefXACT2pH6cdSH_mFR6k/viewform?entry.1916249442=B&entry.643382038=B&entry.86647199=B&submit=Submit\"";
                        Call<String> call = service.send(url);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.isSuccessful()){
                                    Log.d("Retrofit","onResponse() success");
                                    Log.d("Retrofit","Response" + response.body());
                                }else{
                                    Log.d("Retrofit","onRespone() : error resone, noaccess result ?");
                                    Log.d("Retrofit","respone code = " + response.code());

                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("Retrofit","onFailure() :"+ toString());
                            }
                        });

                        Intent intent = new Intent(Activity3.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Removes other Activities from stack
                        startActivity(intent); // 回主畫面
                        QuestionActivity.resetQuestionIndex(); // 索引編號歸零
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}

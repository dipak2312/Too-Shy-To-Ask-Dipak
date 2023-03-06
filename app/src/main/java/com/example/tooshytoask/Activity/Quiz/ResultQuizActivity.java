package com.example.tooshytoask.Activity.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Home.HomeActivity;
import com.example.tooshytoask.AuthModels.QuizAnswerAuthModel;
import com.example.tooshytoask.Fragment.Quiz.QuestionFragment;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.QuizAnswerResponse;
import com.example.tooshytoask.Models.QuizData;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ResultQuizActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    RelativeLayout rel_back;
    Button btn_home,btn_next_quiz;
    TextView txt_percentage,txt_title;
    int anwer,incorrect_answer,total;
    double percentage;
    ArrayList<QuizData> quizdata;
    CustomProgressDialog dialog;
    SPManager spManager;
    String quiz_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quiz);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context=ResultQuizActivity.this;
        dialog=new CustomProgressDialog(context);
        spManager=new SPManager(context);
        quizdata=new ArrayList<>();
        rel_back=(RelativeLayout)findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        btn_home=(Button)findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);
        btn_next_quiz=(Button)findViewById(R.id.btn_next_quiz);
        btn_next_quiz.setOnClickListener(this);

        txt_percentage=(TextView)findViewById(R.id.txt_percentage);
        txt_title=(TextView)findViewById(R.id.txt_title);


        anwer = QuestionFragment.answer;
        incorrect_answer= QuestionFragment.incorect_answer;
        QuestionFragment.answer=0;
        QuestionFragment.incorect_answer=0;
        total=anwer+incorrect_answer;

        percentage=anwer * 100 / total;


        txt_percentage.setText(Double.toString(percentage)+"%"+" "+"Score");
        txt_title.setText("You answerd"+" "+anwer+" "+"out of"+" "+total+" "+"question correctly.\n To continue click on next quiz or click on home to go back");

        quizdata=QuestionFragment.quizdata;


        quiz_id=QuestionFragment.quiz_id;
        QuestionFragment.quiz_id="";

        sendReply();
    }

    public void sendReply()
    {
        dialog.show("");
        //hi,mr,gu,ta

        QuizAnswerAuthModel model=new QuizAnswerAuthModel();
        model.setQuiz_id(quiz_id);
        model.setUser_id(spManager.getUserId());
        model.setQuizData(quizdata);

        WebServiceModel.getRestApi().getQuizAns(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QuizAnswerResponse>() {
                    @Override
                    public void onNext(QuizAnswerResponse quizAnswerResponse) {
                        dialog.dismiss("");
                        String msg = quizAnswerResponse.getMsg();

                        if (msg.equals("success")) {


                            Toast.makeText(context,"Data saved successfully",Toast.LENGTH_SHORT).show();
                            QuestionFragment.quizdata.clear();

                        } else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();
                        QuestionFragment.quizdata.clear();
                        dialog.dismiss("");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        if(id==btn_home.getId())
        {
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if(id==btn_next_quiz.getId())
        {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else if(id==rel_back.getId())
        {
            finish();
        }
    }
}
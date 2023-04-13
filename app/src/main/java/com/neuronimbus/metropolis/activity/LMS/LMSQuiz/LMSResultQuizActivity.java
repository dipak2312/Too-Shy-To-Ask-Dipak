package com.neuronimbus.metropolis.activity.LMS.LMSQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.ClearLMSQuizAuthModel;
import com.neuronimbus.metropolis.AuthModels.LessonUpdateAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.Courses.LMSQuiz.ClearLMSQuizesponse;
import com.neuronimbus.metropolis.Models.Courses.LMSQuiz.LessonUpdateResponse;
import com.neuronimbus.metropolis.Models.QuizData;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.activity.LMS.CoursesDetailActivity;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LMSResultQuizActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    RelativeLayout rel_back;
    Button btn_home,btn_next_quiz;
    TextView txt_percentage,txt_title;
    int anwer,incorrect_answer,total;
    double percentage;
    ArrayList<QuizData> quizdata;
    CustomProgressDialog dialog;
    SPManager spManager;
    String quiz_id,lesson_id ="", courses_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lmsresult_quiz);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context= LMSResultQuizActivity.this;
        dialog=new CustomProgressDialog(context);
        spManager=new SPManager(context);
        quizdata=new ArrayList<>();
        rel_back=findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        btn_home=findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);
        btn_next_quiz=findViewById(R.id.btn_next_quiz);
        btn_next_quiz.setOnClickListener(this);

        txt_percentage=findViewById(R.id.txt_percentage);
        txt_title=findViewById(R.id.txt_title);


        anwer = LMSQuestionFragment.answer;
        incorrect_answer= LMSQuestionFragment.incorect_answer;
        LMSQuestionFragment.answer=0;
        LMSQuestionFragment.incorect_answer=0;
        total=anwer+incorrect_answer;

        percentage=anwer * 100 / total;


        txt_percentage.setText(Double.toString(percentage)+"%"+" "+"Score");
        txt_title.setText("You answerd"+" "+anwer+" "+"out of"+" "+total+" "+"question correctly.\n To continue click on next quiz or click on home to go back");

        //quizdata=LMSQuestionFragment.quizdata;


        quiz_id=LMSQuestionFragment.quiz_id;
        LMSQuestionFragment.quiz_id="";
        lesson_id = LMSQuestionFragment.lesson_id;
        courses_id = LMSQuestionFragment.courses_id;

        if (percentage < 25){
            btn_next_quiz.setVisibility(View.GONE);
            getClearLMSQuiz();
        }

    }

    public void getClearLMSQuiz(){

        ClearLMSQuizAuthModel model = new ClearLMSQuizAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setCourse_id(courses_id);
        model.setLesson_id(lesson_id);

        WebServiceModel.getRestApi().getClearLMSQuiz(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ClearLMSQuizesponse>() {
                    @Override
                    public void onNext(ClearLMSQuizesponse clearLMSQuizesponse) {
                        String msg = clearLMSQuizesponse.getMsg();

                        if (msg.equals("success")){

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getLessonUpdate(){

        LessonUpdateAuthModel model = new LessonUpdateAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setCourse_id(courses_id);
        model.setLesson_id(lesson_id);

        WebServiceModel.getRestApi().getLessonUpdate(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LessonUpdateResponse>() {
                    @Override
                    public void onNext(LessonUpdateResponse lessonUpdateResponse) {
                        String msg = lessonUpdateResponse.getMsg();

                        if (msg.equals("Lesson Enrolled & completed")){

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

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
            Intent intent = new Intent(context, CoursesDetailActivity.class);
            intent.putExtra("lesson_id", lesson_id);
            intent.putExtra("courses_id", courses_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if(id==btn_next_quiz.getId())
        {
            Intent intent = new Intent(this, CoursesDetailActivity.class);
            intent.putExtra("lesson_id", lesson_id);
            intent.putExtra("courses_id", courses_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            getLessonUpdate();
        }
        else if(id==rel_back.getId())
        {
            finish();
        }
    }
}
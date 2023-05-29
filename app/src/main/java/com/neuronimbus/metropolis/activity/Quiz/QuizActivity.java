package com.neuronimbus.metropolis.activity.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.QuizAnswerAuthModel;
import com.neuronimbus.metropolis.Fragment.Quiz.QuestionFragment;
import com.neuronimbus.metropolis.Models.QuizAnswerResponse;
import com.neuronimbus.metropolis.Models.QuizData;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
import com.neuronimbus.metropolis.activity.LMS.CoursesDetailActivity;
import com.neuronimbus.metropolis.activity.LMS.LMSQuiz.LMSQuestionFragment;
import com.neuronimbus.metropolis.adapters.OptionAdapter;
import com.neuronimbus.metropolis.adapters.QuestionPagerAdapter;
import com.neuronimbus.metropolis.AuthModels.QuizQueAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.Question;
import com.neuronimbus.metropolis.Models.QuizQueResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    RecyclerView recy_question;
    OptionAdapter adapter;
    RelativeLayout rel_back, rel_progress_bar;

    ArrayList<Question> quetions;
    ProgressBar progressbar_completed;
    double progrss_value;
    String quiz_id;
    ViewPager viewPager;
    TextView txt_count, quiz_attempt_txt;
    CustomProgressDialog dialog;
    TextView txt_quiz_title;
    private QuestionPagerAdapter pagerAdapter;
    SPManager spManager;
    ArrayList<QuizData> quizdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = QuizActivity.this;
        spManager=new SPManager(context);

        dialog=new CustomProgressDialog(context);

        rel_progress_bar = (RelativeLayout) findViewById(R.id.rel_progress_bar);
        rel_back = (RelativeLayout) findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        txt_count = (TextView) findViewById(R.id.txt_count);
        quiz_attempt_txt = (TextView) findViewById(R.id.quiz_attempt_txt);
        txt_quiz_title=(TextView) findViewById(R.id.txt_quiz_title);

        progressbar_completed=(ProgressBar)findViewById(R.id.progressbar_completed);

        viewPager = findViewById(R.id.view_pager);

        getQuizQue();
    }

    public void getQuizQue(){
        dialog.show("");
        rel_progress_bar.setVisibility(View.GONE);

        QuizQueAuthModel model = new QuizQueAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getQuizQue(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QuizQueResponse>() {
                    @Override
                    public void onNext(QuizQueResponse quizQueResponse) {
                        String msg = quizQueResponse.getMsg();
                        dialog.dismiss("");
                        rel_progress_bar.setVisibility(View.VISIBLE);

                        if (msg.equals("success")){
                            quetions=quizQueResponse.getQuestion();

                            if (quetions.size() == 0){
                                progressbar_completed.setVisibility(View.GONE);
                                quiz_attempt_txt.setVisibility(View.VISIBLE);
                                txt_count.setVisibility(View.GONE);
                            }
                            init();

                        } else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
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

    public void init() {
        pagerAdapter = new QuestionPagerAdapter(getSupportFragmentManager(), quetions);
        txt_count.setText(getResources().getString(R.string.page_count_total, 0 + 1, pagerAdapter.getCount()));

        progrss_value=(double)1/pagerAdapter.getCount()*100;
        progressbar_completed.setProgress((int) progrss_value);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnTouchListener((view, motionEvent) -> true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                txt_count.setText(
                        getResources().getString(R.string.page_count_total, position + 1,
                                pagerAdapter.getCount()));

                int value=position+1;
                progrss_value=(double)value/pagerAdapter.getCount()*100;
                progressbar_completed.setProgress((int) progrss_value);


            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

    }

    public void resultPopup(){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.quiz_result);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.logout_popup));

        TextView percentage_txt = dialog.findViewById(R.id.percentage_txt);
        TextView title_txt = dialog.findViewById(R.id.title_txt);
        Button home_button = dialog.findViewById(R.id.home_button);
        Button btn_completed = dialog.findViewById(R.id.btn_completed);
        double percentage;
        int anwer,incorrect_answer,total;

        anwer = QuestionFragment.answer;
        incorrect_answer= QuestionFragment.incorect_answer;
        QuestionFragment.answer=0;
        QuestionFragment.incorect_answer=0;
        total=anwer+incorrect_answer;

        quizdata=QuestionFragment.quizdata;
        quiz_id=QuestionFragment.quiz_id;
        QuestionFragment.quiz_id="";

        percentage=anwer * 100 / total;

        percentage_txt.setText(percentage +"%"+" "+"Score");
        title_txt.setText("You answerd"+" "+anwer+" "+"out of"+" "+total+" "+"question correctly.\n To continue click on next quiz or click on home to go back");

        if (percentage < 25){
            percentage_txt.setTextColor(ContextCompat.getColor(context, R.color.red));
        }
        else if (percentage < 60){
            percentage_txt.setTextColor(ContextCompat.getColor(context, R.color.tsta_green_color));
        }
        else {

        }

        sendReply();

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        btn_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuizActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void sendReply()
    {
        dialog.show("");

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

                        }
                        else {
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

    public void nextQuestion() {
        if (viewPager.getCurrentItem() == pagerAdapter.getCount() - 1) {

            /*Intent intent = new Intent(this, ResultQuizActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();*/

            resultPopup();
        }
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);


    }



    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()) {
            finish();
        }
    }
}
package com.example.tooshytoask.activity.LMS.LMSQuiz;

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

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.AuthModels.ClearLMSQuizAuthModel;
import com.example.tooshytoask.AuthModels.LMSQuizAuthModel;
import com.example.tooshytoask.AuthModels.LessonUpdateAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.Courses.LMSQuiz.ClearLMSQuizesponse;
import com.example.tooshytoask.Models.Courses.LMSQuiz.LMSQuizesponse;
import com.example.tooshytoask.Models.Courses.LMSQuiz.LessonUpdateResponse;
import com.example.tooshytoask.Models.Courses.LMSQuiz.Question;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.activity.LMS.CoursesDetailActivity;
import com.example.tooshytoask.adapters.LMSQuestionPagerAdapter;
import com.example.tooshytoask.adapters.OptionAdapter;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LMSQuizActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    RelativeLayout rel_back, rel_progress_bar;
    ProgressBar progressbar_completed;
    double progrss_value;
    ArrayList<Question> quetions;
    ViewPager viewPager;
    CustomProgressDialog dialog;
    TextView txt_quiz_title, txt_count;
    private LMSQuestionPagerAdapter pagerAdapter;
    SPManager spManager;
    String courses_id ="",lesson_id="", quiz_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lmsquiz);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = LMSQuizActivity.this;
        spManager=new SPManager(context);

        quetions=new ArrayList<>();
        dialog=new CustomProgressDialog(context);
        rel_progress_bar =  findViewById(R.id.rel_progress_bar);
        rel_back =  findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        txt_count =  findViewById(R.id.txt_count);
        txt_quiz_title= findViewById(R.id.txt_quiz_title);
        progressbar_completed=findViewById(R.id.progressbar_completed);

        viewPager = findViewById(R.id.view_pager);
        lesson_id = getIntent().getStringExtra("lesson_id");
        courses_id = getIntent().getStringExtra("courses_id");

        getLMSQuiz();

    }

    public void getLMSQuiz(){
        dialog.show("");
        rel_progress_bar.setVisibility(View.GONE);

        LMSQuizAuthModel model = new LMSQuizAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setCourse_id(courses_id);
        model.setLesson_id(lesson_id);

        WebServiceModel.getRestApi().getLMSQuiz(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LMSQuizesponse>() {
                    @Override
                    public void onNext(LMSQuizesponse lmsQuizesponse) {
                        String msg = lmsQuizesponse.getMsg();
                        dialog.dismiss("");
                        rel_progress_bar.setVisibility(View.VISIBLE);

                        if (msg.equals("success")){

                            quetions =lmsQuizesponse.getQuestion();
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
        pagerAdapter = new LMSQuestionPagerAdapter(getSupportFragmentManager(), quetions);
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

    public void nextQuestion() {
        if (viewPager.getCurrentItem() == pagerAdapter.getCount() - 1) {
            /*Intent intent = new Intent(this, LMSResultQuizActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();*/
            resultPopup();
        }
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);


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

        anwer = LMSQuestionFragment.answer;
        incorrect_answer= LMSQuestionFragment.incorect_answer;
        LMSQuestionFragment.answer=0;
        LMSQuestionFragment.incorect_answer=0;
        total=anwer+incorrect_answer;

        percentage=anwer * 100 / total;

        percentage_txt.setText(percentage +"%"+" "+"Score");
        title_txt.setText("You answerd"+" "+anwer+" "+"out of"+" "+total+" "+"question correctly.\n To continue click on next quiz or click on home to go back");

        quiz_id=LMSQuestionFragment.quiz_id;
        LMSQuestionFragment.quiz_id="";

        if (percentage < 25){
            btn_completed.setVisibility(View.GONE);
            percentage_txt.setTextColor(ContextCompat.getColor(context, R.color.red));
            getClearLMSQuiz();
        }
        else if (percentage < 60){
            percentage_txt.setTextColor(ContextCompat.getColor(context, R.color.tsta_green_color));
        }
        else {

        }

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoursesDetailActivity.class);
                intent.putExtra("lesson_id", lesson_id);
                intent.putExtra("courses_id", courses_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        btn_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoursesDetailActivity.class);
                intent.putExtra("lesson_id", lesson_id);
                intent.putExtra("courses_id", courses_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                getLessonUpdate();
                dialog.dismiss();
            }
        });
        dialog.show();
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
        int id = view.getId();

        if (id == rel_back.getId()) {
            finish();
        }
    }
}
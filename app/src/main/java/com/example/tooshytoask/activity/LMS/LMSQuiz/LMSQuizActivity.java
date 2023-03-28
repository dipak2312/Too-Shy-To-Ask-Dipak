package com.example.tooshytoask.activity.LMS.LMSQuiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.AuthModels.LMSQuizAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.Courses.LMSQuiz.LMSQuizesponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.adapters.LMSQuestionPagerAdapter;
import com.example.tooshytoask.adapters.OptionAdapter;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LMSQuizActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    RecyclerView recy_question;
    OptionAdapter adapter;
    RelativeLayout rel_back, rel_progress_bar;


    ProgressBar progressbar_completed;
    double progrss_value;
    ArrayList<com.example.tooshytoask.Models.Courses.LMSQuiz.Question> quetions;
    ViewPager viewPager;
    TextView txt_count;
    CustomProgressDialog dialog;
    TextView txt_quiz_title;
    private LMSQuestionPagerAdapter pagerAdapter;
    SPManager spManager;
    String courses_id ="",lesson_id="";

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

            Intent intent = new Intent(this, LMSResultQuizActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
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
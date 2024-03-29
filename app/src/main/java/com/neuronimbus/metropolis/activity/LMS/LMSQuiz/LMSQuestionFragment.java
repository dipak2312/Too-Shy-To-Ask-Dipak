package com.neuronimbus.metropolis.activity.LMS.LMSQuiz;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.LMSQuizAttemptAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.Courses.LMSQuiz.LMSQuizAttemptesponse;
import com.neuronimbus.metropolis.Models.Courses.LMSQuiz.LMSQuizData;
import com.neuronimbus.metropolis.Models.Courses.LMSQuiz.Option;
import com.neuronimbus.metropolis.Models.Courses.LMSQuiz.Question;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.adapters.LMSOptionAdapter;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LMSQuestionFragment extends Fragment implements View.OnClickListener, LMSOptionAdapter.IOnClick {
    RecyclerView recy_question;
    Context context;
    SPManager spManager;
    Button btn_submit;
    ArrayList<com.neuronimbus.metropolis.Models.Courses.LMSQuiz.Question> quetions;
    public Question question;
    public Option selectedOption;
    private boolean isAnswerSelected;
    ArrayList<com.neuronimbus.metropolis.Models.Courses.LMSQuiz.Option>optionList;
    TextView txt_answer,txt_desc,txt_question_name,txt_question, txt_hint;
    ImageView img_answer,img_question;
    public static int answer=0,incorect_answer=0;
    public static String quiz_id, lesson_id,courses_id;

    public static ArrayList<LMSQuizData>quizdata =new ArrayList<>();
    ArrayList<Option> optlinlist;
    private long mLastClickTime = 0;
    private static final String ARG_QUESTION = "QUESTION";
    String quiz_answer;
    RelativeLayout rel_check_question,rel_question;
    public LMSQuestionFragment() {
        // Required empty public constructor
    }

    public static LMSQuestionFragment newInstance(Question question) {
        LMSQuestionFragment fragment = new LMSQuestionFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getParcelable(ARG_QUESTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_l_m_s_question, container, false);

        context = getActivity();
        spManager=new SPManager(context);
        txt_question=view.findViewById(R.id.txt_question);
        recy_question= view.findViewById(R.id.recy_question);
        rel_question=view.findViewById(R.id.rel_question);
        rel_check_question=view.findViewById(R.id.rel_check_question);
        txt_answer=view.findViewById(R.id.txt_answer);
        txt_desc=view.findViewById(R.id.txt_full_desc);
        txt_hint=view.findViewById(R.id.txt_hint);
        txt_question_name=view.findViewById(R.id.txt_question_name);
        img_answer= view.findViewById(R.id.img_answer);
        img_question= view.findViewById(R.id.img_question);
        btn_submit= view.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        optlinlist=new ArrayList<>();

        return view;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        if(id==btn_submit.getId())
        {
            if (btn_submit.getText().equals("SUBMIT")) {
                chekAnsPopup();
                getLMSQuizAttempt();
        }
            else {

            ((LMSQuizActivity) getActivity()).nextQuestion();

        }

            /*if (btn_submit.getText().equals("SUBMIT")) {
                img_question.setVisibility(View.GONE);
                txt_question.setVisibility(View.GONE);
                recy_question.setVisibility(View.GONE);
                rel_check_question.setVisibility(View.VISIBLE);
                btn_submit.setText("NEXT");

                checkAnswerState();
                getLMSQuizAttempt();
            } else {

                ((LMSQuizActivity) getActivity()).nextQuestion();

            }*/
        }
    }

    public void getLMSQuizAttempt(){

        LMSQuizAttemptAuthModel model = new LMSQuizAttemptAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setQuestion_id(quiz_id);
        model.setCourse_id(courses_id);
        model.setLesson_id(lesson_id);

        WebServiceModel.getRestApi().getLMSQuizAttempt(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LMSQuizAttemptesponse>() {
                    @Override
                    public void onNext(LMSQuizAttemptesponse lmsQuizAttemptesponse) {
                        String msg = lmsQuizAttemptesponse.getMsg();

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    public void init()
    {
        txt_question.setText(question.getQuestion());

        Glide.with(context).load(question.getQuestionImageUrl()).into(img_question);
        LMSOptionAdapter adapter = new LMSOptionAdapter(context,question.getOption(),this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recy_question.setLayoutManager(layoutManager);
        recy_question.setAdapter(adapter);
        btn_submit.setEnabled(false);
    }

    public void chekAnsPopup(){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.currect_wrong_answer);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.logout_popup));

        TextView txt_answer = dialog.findViewById(R.id.txt_answer);
        TextView txt_full_desc = dialog.findViewById(R.id.txt_full_desc);
        TextView txt_hint = dialog.findViewById(R.id.txt_hint);
        TextView txt_question_name = dialog.findViewById(R.id.txt_question_name);
        ImageView img_answer = dialog.findViewById(R.id.img_answer);
        Button btn_submit = dialog.findViewById(R.id.btn_submit);

        quizdata.add(new LMSQuizData(question.getQuestionId(),selectedOption.getOption()));
        quiz_id=question.getQuestionId();
        lesson_id = question.getLessonId();
        courses_id = question.getCourseId();
        txt_question_name.setText(question.getQuestion());

        for(int i=0;i<optlinlist.size();i++)
        {
            if(optlinlist.get(i).getOption().equals(question.getAnswerId()))
            {
                quiz_answer=optlinlist.get(i).getOption_serial();
            }
        }

        if (selectedOption.getOption().equals(question.getAnswerId())) {
            txt_answer.setText(R.string.correct_answer_title);
            img_answer.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.correct_answer));
            txt_answer.setTextColor(ContextCompat.getColor(context, R.color.tsta_green_color));
            txt_answer.setTextColor(context.getResources().getColor(R.color.tsta_green_color));
            txt_full_desc.setText(Html.fromHtml(question.getDescription()));
            txt_hint.setText(getString(R.string.correct_answer));
            txt_question_name.setVisibility(View.GONE);
            answer++;

        } else {
            txt_answer.setText(R.string.incorrect_answer_title);
            img_answer.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.wrong_answer));
            txt_answer.setTextColor(ContextCompat.getColor(context, R.color.red));
            txt_answer.setTextColor(context.getResources().getColor(R.color.red));
            txt_full_desc.setText(Html.fromHtml(question.getDescription()));
            String hint="<font color='black'>"+getString(R.string.correct_answer_is)+"</font>"+" "+question.getAnswerId()+"."+" "+quiz_answer;
            txt_hint.setText(Html.fromHtml(hint));
            incorect_answer++;
        }
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LMSQuizActivity) getActivity()).nextQuestion();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void checkAnswerState()
    {

        quizdata.add(new LMSQuizData(question.getQuestionId(),selectedOption.getOption()));
        quiz_id=question.getQuestionId();
        lesson_id = question.getLessonId();
        courses_id = question.getCourseId();
        txt_question_name.setText(question.getQuestion());

        for(int i=0;i<optlinlist.size();i++)
        {
            if(optlinlist.get(i).getOption().equals(question.getAnswerId()))
            {
                quiz_answer=optlinlist.get(i).getOption_serial();
            }
        }

        if (selectedOption.getOption().equals(question.getAnswerId())) {
            txt_answer.setText(R.string.correct_answer_title);
            img_answer.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.correct_answer));
            txt_answer.setTextColor(ContextCompat.getColor(context, R.color.tsta_green_color));
            txt_answer.setTextColor(context.getResources().getColor(R.color.tsta_green_color));
            txt_desc.setText(Html.fromHtml(question.getDescription()));
            txt_hint.setText(getString(R.string.correct_answer));
            txt_question_name.setVisibility(View.GONE);
            answer++;

        } else {
            txt_answer.setText(R.string.incorrect_answer_title);
            img_answer.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.wrong_answer));
            txt_answer.setTextColor(ContextCompat.getColor(context, R.color.red));
            txt_answer.setTextColor(context.getResources().getColor(R.color.red));
            txt_desc.setText(Html.fromHtml(question.getDescription()));
            String hint="<font color='black'>"+getString(R.string.correct_answer_is)+"</font>"+" "+question.getAnswerId()+"."+" "+quiz_answer;
            txt_hint.setText(Html.fromHtml(hint));
            incorect_answer++;

        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void optionSelected(Option option, ArrayList<Option> optionList) {
        if (!isAnswerSelected) {
            btn_submit.setEnabled(true);
            isAnswerSelected = true;
        }
        this.selectedOption = option;
        optlinlist=optionList;
    }
}
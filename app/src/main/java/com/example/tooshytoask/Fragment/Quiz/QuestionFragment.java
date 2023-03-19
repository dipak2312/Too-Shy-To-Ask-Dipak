package com.example.tooshytoask.Fragment.Quiz;

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

import com.example.tooshytoask.activity.Quiz.QuizActivity;
import com.example.tooshytoask.adapters.OptionAdapter;
import com.example.tooshytoask.Models.Option;
import com.example.tooshytoask.Models.Question;
import com.example.tooshytoask.Models.QuizData;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class QuestionFragment extends Fragment implements View.OnClickListener, OptionAdapter.IOnClick{
    RecyclerView recy_question;
    Context context;
    Button btn_submit;
    public Question question;
    public Option selectedOption;
    private boolean isAnswerSelected;

    //CardView card_img;
    ArrayList<Option> optionList;
    ArrayList<Question> quetions;
    TextView txt_answer,txt_desc,txt_question_name,txt_question, txt_hint;
    ImageView img_answer,img_question;
    public static int answer=0,incorect_answer=0;
    public static String quiz_id;

    public static ArrayList<QuizData>quizdata =new ArrayList<>();
    ArrayList<Option> optlinlist;
    private long mLastClickTime = 0;



    private static final String ARG_QUESTION = "QUESTION";
    String quiz_answer;

    RelativeLayout rel_check_question,rel_question;

    public QuestionFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question Parameter 1.
     * @return A new instance of fragment QuestionFragment.
     */

    public static QuestionFragment newInstance(Question question) {
        QuestionFragment fragment = new QuestionFragment();
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
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        txt_question=view.findViewById(R.id.txt_question);
        recy_question= view.findViewById(R.id.recy_question);
        rel_question=view.findViewById(R.id.rel_question);
        rel_check_question=view.findViewById(R.id.rel_check_question);
        //card_img= view.findViewById(R.id.card_img);
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
                img_question.setVisibility(View.GONE);
                txt_question.setVisibility(View.GONE);
                recy_question.setVisibility(View.GONE);
                rel_check_question.setVisibility(View.VISIBLE);
                btn_submit.setText("NEXT");

                checkAnswerState();
            } else {


                ((QuizActivity) getActivity()).nextQuestion();

            }


        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    public void init()
    {


        txt_question.setText(question.getQuestion());

        //Glide.with(context).load(question.getQuestionImageUrl()).placeholder(R.drawable.default_img).into(img_question);
        OptionAdapter adapter = new OptionAdapter(context,question.getOption(),this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recy_question.setLayoutManager(layoutManager);
        recy_question.setAdapter(adapter);
        btn_submit.setEnabled(false);
    }

    public void checkAnswerState()
    {

        quizdata.add(new QuizData(question.getQuestionId(),selectedOption.getOption()));
        quiz_id=question.getQuizId();
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
            txt_desc.setText(question.getDescription());
            txt_hint.setText(getString(R.string.correct_answer));
            txt_question_name.setVisibility(View.GONE);
            answer++;



        } else {
            txt_answer.setText(R.string.incorrect_answer_title);
            img_answer.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.wrong_answer));
            txt_answer.setTextColor(ContextCompat.getColor(context, R.color.red));
            txt_answer.setTextColor(context.getResources().getColor(R.color.red));
            txt_desc.setText(question.getDescription());
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
    public void optionSelected(Option option,ArrayList<Option>optionList) {
        if (!isAnswerSelected) {
            btn_submit.setEnabled(true);
            isAnswerSelected = true;
        }
        this.selectedOption = option;
        optlinlist=optionList;
    }
}
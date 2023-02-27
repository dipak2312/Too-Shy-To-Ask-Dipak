package com.example.tooshytoask.Fragment.Quiz;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tooshytoask.R;

import java.util.ArrayList;

public class QuestionFragment extends Fragment {
    RecyclerView recy_question;
    Button btn_next;
    //public Question question;
   // public Option selectedOption;
    private boolean isAnswerSelected;

    public static int answer=0,incorect_answer=0;
    public static String quiz_id;
    private long mLastClickTime = 0;

    private static final String ARG_QUESTION = "QUESTION";
    String quiz_answer;

    public QuestionFragment() {
        // Required empty public constructor
    }


//    public static QuestionFragment newInstance(Question question) {
//        QuestionFragment fragment = new QuestionFragment();
//        Bundle args = new Bundle();
//        args.putParcelable(ARG_QUESTION, question);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //question = getArguments().getParcelable(ARG_QUESTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }
}
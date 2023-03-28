package com.example.tooshytoask.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tooshytoask.Fragment.LMSQuiz.LMSQuestionFragment;
import com.example.tooshytoask.Fragment.Quiz.QuestionFragment;
import com.example.tooshytoask.Models.Courses.LMSQuiz.Question;

import java.util.ArrayList;

public class LMSQuestionPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<com.example.tooshytoask.Models.Courses.LMSQuiz.Question> questions;
    public LMSQuestionPagerAdapter(FragmentManager fm, ArrayList<Question> questions) {
        super(fm);
        this.questions = questions;
    }

    public LMSQuestionPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return LMSQuestionFragment.newInstance(questions.get(position));
    }

    @Override
    public int getCount() {
        return questions.size();
    }
}

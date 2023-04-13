package com.neuronimbus.metropolis.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.neuronimbus.metropolis.activity.LMS.LMSQuiz.LMSQuestionFragment;
import com.neuronimbus.metropolis.Models.Courses.LMSQuiz.Question;

import java.util.ArrayList;

public class LMSQuestionPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<com.neuronimbus.metropolis.Models.Courses.LMSQuiz.Question> questions;
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

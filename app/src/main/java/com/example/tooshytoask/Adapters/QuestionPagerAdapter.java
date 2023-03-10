package com.example.tooshytoask.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tooshytoask.Fragment.Quiz.QuestionFragment;
import com.example.tooshytoask.Models.Question;

import java.util.ArrayList;

public class QuestionPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Question> questions;
    public QuestionPagerAdapter(FragmentManager fm, ArrayList<Question> questions) {
        super(fm);
        this.questions = questions;
    }

    public QuestionPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return QuestionFragment.newInstance(questions.get(position));
    }

    @Override
    public int getCount() {
        return questions.size();
    }
}

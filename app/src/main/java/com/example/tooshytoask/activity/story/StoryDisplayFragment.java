package com.example.tooshytoask.activity.story;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.activity.Home.HomeActivity;
import com.example.tooshytoask.AuthModels.StoryLikeAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.StoryDetails;
import com.example.tooshytoask.Models.StoryLikeResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jp.shts.android.storiesprogressview.StoriesProgressView;

public class StoryDisplayFragment extends Fragment implements View.OnClickListener, StoriesProgressView.StoriesListener {

    StoriesProgressView story_progress_bar;
    View previous, skip;
    ImageView story_img, like_img, share_img;
    TextView day, link_name, story_title;
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    String story_ids = "",islike;
    boolean like = true;
    ArrayList<StoryDetails> storyDetails;

    int counter = 0;
    long pressTime = 0L;
    long limit = 500L;

    public StoryDisplayFragment(ArrayList<StoryDetails> storyDetails) {
        this.storyDetails = storyDetails;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_story_display, container, false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = getActivity();
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        story_progress_bar = view.findViewById(R.id.story_progress_bar);

        previous = view.findViewById(R.id.previous);
        previous.setOnClickListener(this);
        skip = view.findViewById(R.id.skip);
        skip.setOnClickListener(this);
        day = view.findViewById(R.id.day);
        story_title = view.findViewById(R.id.story_title);
        story_img = view.findViewById(R.id.story_img);
        link_name = view.findViewById(R.id.link_name);
        link_name.setOnClickListener(this);
        like_img = view.findViewById(R.id.like_img);
        like_img.setOnClickListener(this);
        share_img = view.findViewById(R.id.share_img);
        share_img.setOnClickListener(this);


        onTouch();
        return view;
    }

    public void onTouch(){
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        pressTime = System.currentTimeMillis();
                        story_progress_bar.pause();
                        return false;

                    case MotionEvent.ACTION_UP:
                        long now = System.currentTimeMillis();
                        story_progress_bar.resume();
                        return limit < now - pressTime;

                }

                return false;
            }

        };
        previous.setOnTouchListener(onTouchListener);
        skip.setOnTouchListener(onTouchListener);
    }

    public void getStoryLike(String type){
        dialog.show("");
        dialog.dismiss("");

        StoryLikeAuthModel model = new StoryLikeAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setStory_id(story_ids);
        model.setType(type);

        WebServiceModel.getRestApi().getStoryLike(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<StoryLikeResponse>() {
                    @Override
                    public void onNext(StoryLikeResponse storyLikeResponse) {
                        String msg = storyLikeResponse.getMsg();
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
    public void onClick(View view) {
        int id = view.getId();

        if (id == previous.getId()){
            story_progress_bar.reverse();
        }
        else if (id == skip.getId()){
            story_progress_bar.skip();
        }
        else if (id == like_img.getId()){
            if (like) {
                like_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.like_active));
                getStoryLike("like");
                like = false;

            } else    {
                like_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.like));
                getStoryLike("unlike");
                like = true;

            }
        }

    }

    @Override
    public void onNext() {
        if (storyDetails.size() <= counter + 1) {
            return;
        }

    }

    @Override
    public void onPrev() {
        if (counter - 1 < 0) {
            return;
        }

    }

    @Override
    public void onComplete() {

        Intent intent = new Intent(context, HomeActivity.class);
        startActivity(intent);
        getActivity().finish();

    }
}
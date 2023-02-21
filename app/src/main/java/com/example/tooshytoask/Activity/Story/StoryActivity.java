package com.example.tooshytoask.Activity.Story;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Home.HomeActivity;
import com.example.tooshytoask.Activity.Landing.SignInActivity;
import com.example.tooshytoask.Adapters.StoryDetailAdapter;
import com.example.tooshytoask.AuthModels.StoryAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.StoryDetails;
import com.example.tooshytoask.Models.StoryResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jp.shts.android.storiesprogressview.StoriesProgressView;
import pt.tornelas.segmentedprogressbar.SegmentedProgressBar;
import pt.tornelas.segmentedprogressbar.SegmentedProgressBarListener;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener, StoriesProgressView.StoriesListener {
    Context context;
    SPManager spManager;
    ImageView share_img, like_img,story_img;
    RelativeLayout back_img;
    LinearLayout swipe_up;
    CustomProgressDialog dialog;
    SegmentedProgressBar story_progress_bar;
    String story_id = "";
    TextView story_title,day, link_name;
    View previous,skip;
    VideoView story_video;
    String story_count = "";
    ArrayList<StoryDetails>storyDetails;
    StoryDetailAdapter adapter;
    int counter = 0;
    long pressTime = 0L;
    long limit = 500L;

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    pressTime = System.currentTimeMillis();
                    story_progress_bar.pause();
                    return false;

                case MotionEvent.ACTION_UP:
                    long now = System.currentTimeMillis();
                    story_progress_bar.start();
                    return limit < now - pressTime;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        context = StoryActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        story_video = findViewById(R.id.story_video);

        swipe_up = findViewById(R.id.swipe_up);
        swipe_up.setOnClickListener(this);
        swipe_up.setOnTouchListener(onTouchListener);
        skip = findViewById(R.id.skip);
        skip.setOnClickListener(this);
        previous = findViewById(R.id.previous);
        previous.setOnClickListener(this);

        story_progress_bar = findViewById(R.id.story_progress_bar);

        story_title = findViewById(R.id.story_title);
        day = findViewById(R.id.day);
        story_img = findViewById(R.id.story_img);
        link_name = findViewById(R.id.link_name);
        link_name.setOnClickListener(this);
        like_img = findViewById(R.id.like_img);
        like_img.setOnClickListener(this);
        share_img = findViewById(R.id.share_img);
        share_img.setOnClickListener(this);
        back_img = findViewById(R.id.back_img);
        back_img.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent != null) {

            story_id = intent.getStringExtra("story_id");

        }
        getStory();



    }

    public void getStory(){
        dialog.show("");
        dialog.dismiss("");

        StoryAuthModel model = new StoryAuthModel();
        model.setCategory_id(story_id);

        WebServiceModel.getRestApi().getStory(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<StoryResponse>() {
                    @Override
                    public void onNext(StoryResponse storyResponse) {
                        String msg = storyResponse.getMsg();

                        if (msg.equals("success")) {
                            storyDetails = storyResponse.getStoryDetails();
                            story_progress_bar.setSegmentCount(storyDetails.size());
                            story_progress_bar.start();

                            for (int i = 0; i < storyDetails.size(); i++) {

                                story_title.setText(storyResponse.getStoryDetails().get(i).getStory_title());
                                day.setText(storyResponse.getStoryDetails().get(i).getStory_date());
                                link_name.setText(storyResponse.getStoryDetails().get(i).getStory_link());
                                Glide.with(context).load(storyResponse.getStoryDetails().get(i).getStory_img()).into(story_img);
                                //story_video.setVideoPath(storyResponse.getStoryDetails().get(i).getStory_video());
                                //story_video.start();

                            }



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

        if (id == like_img.getId()) {

            like_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.like_active));

        }
        else if (id == share_img.getId()){
            shareTheApp();

        }

        else if (id == back_img.getId()){
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else if (id == previous.getId()){
            story_progress_bar.previous();

        }
        previous.setOnTouchListener(onTouchListener);
         if (id == skip.getId()){
            story_progress_bar.next();


        }
        skip.setOnTouchListener(onTouchListener);
    }

    @Override
    public void onNext() {
        Glide.with(context).load(storyDetails.get(++counter).getStory_img()).into(story_img);
    }

    @Override
    public void onPrev() {
        if ((counter - 1) < 0)
            return;
        Glide.with(context).load(storyDetails.get(--counter).getStory_img()).into(story_img);
    }

    @Override
    public void onComplete() {
        story_progress_bar.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        story_progress_bar.pause();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        story_progress_bar.start();
        super.onRestart();
    }

    public void shareTheApp() {

    }
}
package com.example.tooshytoask.Activity.Story;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Home.HomeActivity;
import com.example.tooshytoask.Adapters.StoryViewPagerAdapter;
import com.example.tooshytoask.AuthModels.StoryAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.StoryDetails;
import com.example.tooshytoask.Models.StoryResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jp.shts.android.storiesprogressview.StoriesProgressView;
import pt.tornelas.segmentedprogressbar.SegmentedProgressBar;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    RelativeLayout back_img;
    ViewPager story_view_pager;
    CustomProgressDialog dialog;
    String story_id = "";
    ArrayList<StoryDetails>storyDetails;
    StoryViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        context = StoryActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        story_view_pager = findViewById(R.id.story_view_pager);

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


                            adapter = new StoryViewPagerAdapter(getSupportFragmentManager(), storyDetails);
                            story_view_pager.setAdapter(adapter);

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

        if (id == back_img.getId()){
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

    }
}
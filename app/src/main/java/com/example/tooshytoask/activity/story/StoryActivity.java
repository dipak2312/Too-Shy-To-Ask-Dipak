package com.example.tooshytoask.activity.story;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.AuthModels.StoryLikeAuthModel;
import com.example.tooshytoask.AuthModels.StoryShareAuthModel;
import com.example.tooshytoask.Models.StoryLikeResponse;
import com.example.tooshytoask.Models.StoryShareResponse;
import com.example.tooshytoask.activity.Home.HomeActivity;


import com.example.tooshytoask.adapters.StoryViewPagerAdapter;
import com.example.tooshytoask.AuthModels.StoryAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.StoryDetails;
import com.example.tooshytoask.Models.StoryResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jp.shts.android.storiesprogressview.StoriesProgressView;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener, StoriesProgressView.StoriesListener {
    StoriesProgressView story_progress_bar;
    View previous, skip;
    ImageView story_img, like_img, share_img;
    RelativeLayout back_img;
    TextView day, link_name, story_title;
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    String story_id, story_like_share_ids, islike, share_link, story_name;
    ArrayList<StoryDetails>storyDetails;
    StoryViewPagerAdapter adapter;
    boolean like = true;
    int counter = 0;
    long pressTime = 0L;
    long limit = 500L;
    int position=0;
    PlayerView videoPlayer;
    SimpleExoPlayer player;
    ConcatenatingMediaSource concatenatingMediaSource;
    LinearLayout swipe_up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        context = StoryActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        story_progress_bar = findViewById(R.id.story_progress_bar);
        story_progress_bar.setStoriesListener(this);
        previous = findViewById(R.id.previous);
        previous.setOnClickListener(this);
        skip = findViewById(R.id.skip);
        skip.setOnClickListener(this);
        day = findViewById(R.id.day);
        story_title = findViewById(R.id.story_title);
        story_img = findViewById(R.id.story_img);
        link_name = findViewById(R.id.link_name);
        link_name.setOnClickListener(this);
        like_img = findViewById(R.id.like_img);
        like_img.setOnClickListener(this);
        share_img = findViewById(R.id.share_img);
        share_img.setOnClickListener(this);
        swipe_up=findViewById(R.id.swipe_up);

        videoPlayer=findViewById(R.id.videoPlayer);


        back_img = findViewById(R.id.back_img);
        back_img.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent != null) {

            story_id = intent.getStringExtra("story_id");

        }


        getStory();
        onTouch();

    }

    public void onTouch(){
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        pressTime = System.currentTimeMillis();
                        story_progress_bar.pause();
                        if(player != null)
                        {
                            player.pause();
                        }
                        return false;

                    case MotionEvent.ACTION_UP:
                        long now = System.currentTimeMillis();
                        if(player != null)
                        {
                            player.play();
                        }
                        story_progress_bar.resume();
                        return limit < now - pressTime;



                }

                return false;
            }

        };
        previous.setOnTouchListener(onTouchListener);
        skip.setOnTouchListener(onTouchListener);
    }

    public void getStory(){
        dialog.show("");

        StoryAuthModel model = new StoryAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setCategory_id(story_id);

        WebServiceModel.getRestApi().getStory(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<StoryResponse>() {
                    @Override
                    public void onNext(StoryResponse storyResponse) {
                        String msg = storyResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")) {
                           storyDetails = storyResponse.getStoryDetails();

                            story_progress_bar.setStoriesCount(storyDetails.size());
                            story_progress_bar.setStoryDuration(10000L);
                            story_progress_bar.startStories();

                            if(storyDetails !=null)
                            {
                                setStoryValue(storyDetails);
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

    public void getStoryLike(String type){
        StoryLikeAuthModel model = new StoryLikeAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setStory_id(story_like_share_ids);
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

    public void getStoryShare(){
        StoryShareAuthModel model = new StoryShareAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setStory_id(story_like_share_ids);

        WebServiceModel.getRestApi().getStoryShare(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<StoryShareResponse>() {
                    @Override
                    public void onNext(StoryShareResponse storyShareResponse) {
                        String msg = storyShareResponse.getCode();
                        //if (msg.equals(""))
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setStoryValue(ArrayList<StoryDetails>storyDetails) {


        story_title.setText(Html.fromHtml(storyDetails.get(position).getStory_title()));
        story_name = storyDetails.get(position).getStory_title();
        link_name.setText((storyDetails.get(position).getStory_link()));
        share_link = storyDetails.get(position).getStory_link();
        if(storyDetails.get(position).getStory_link() != null && !storyDetails.get(position).getStory_link().equals(""))
        {
            swipe_up.setVisibility(View.VISIBLE);
        }else {
            swipe_up.setVisibility(View.GONE);
        }
        day.setText(storyDetails.get(position).getStory_date());
        story_like_share_ids = storyDetails.get(position).getStory_id();

        if(storyDetails.get(position).getStory_img() != null && !storyDetails.get(position).getStory_img().equals(""))
        {
            story_img.setVisibility(View.VISIBLE);
            videoPlayer.setVisibility(View.GONE);
            Glide.with(context).load(storyDetails.get(position).getStory_img()).into(story_img);
        }
        else if(storyDetails.get(position).getStory_video() != null && !storyDetails.get(position).getStory_video().equals(""))
        {

            story_img.setVisibility(View.GONE);
            videoPlayer.setVisibility(View.VISIBLE);
            videoPlay(storyDetails.get(position).getStory_video());

        }

        islike = storyDetails.get(position).getLiked();
        setIslike();
    }

    public void setIslike() {

        if (islike.equals("1")) {
            like_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.like_active));
            like = false;

        } else {
            like_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.like));
            like = true;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == back_img.getId()) {
            if(player != null)
            {
                player.release();
                player.stop();
            }
            finish();
        } else if (id == previous.getId()) {
            story_progress_bar.reverse();

        } else if (id == skip.getId()) {
            story_progress_bar.skip();

        }
        else if (id == share_img.getId()){
            sharevalue();
        }
        else if (id == like_img.getId()) {
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

    public void sharevalue() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Too Shy Too Ask App");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, story_name +
                "\n\n" + share_link);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

        getStoryShare();
    }

    @Override
    public void onNext() {
      position=position+1;
      if(storyDetails !=null)
      {
          setStoryValue(storyDetails);
      }

    }

    @Override
    public void onPrev() {
      position=position-1;
        if(storyDetails !=null)
        {
            setStoryValue(storyDetails);
        }

    }

    @Override
    public void onComplete() {
        if(player != null)
        {
            player.release();
            player.stop();
        }
        finish();
    }


    public void videoPlay(String videoPlayLink){

        String videoPath = videoPlayLink;
        Uri videoUri = Uri.parse(videoPath);
        MediaItem mediaItem = MediaItem.fromUri(videoUri);

        player = new SimpleExoPlayer.Builder(this).build();

        DefaultDataSourceFactory defaultExtractorsFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "TSTA" ));
        concatenatingMediaSource = new ConcatenatingMediaSource();

        MediaSource mediaSource = new ProgressiveMediaSource.Factory(defaultExtractorsFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(String.valueOf(videoUri))));
        concatenatingMediaSource.addMediaSource(mediaSource);
        videoPlayer.setPlayer(player);
        videoPlayer.setKeepScreenOn(true);

        player.setMediaItem(mediaItem);

        player.prepare(concatenatingMediaSource);
        player.play();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null)
        {
            player.release();
        }

        //styledPlayerView.setPlayer(null);
        //player.release();

    }
}
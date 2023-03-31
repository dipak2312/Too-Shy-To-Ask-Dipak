package com.example.tooshytoask.activity.LMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.AuthModels.CoursesDetailAuthModel;
import com.example.tooshytoask.AuthModels.CoursesEnrollAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.Courses.CoursesDetailResponse;
import com.example.tooshytoask.Models.Courses.CoursesEnrollResponse;
import com.example.tooshytoask.Models.Courses.data;
import com.example.tooshytoask.Models.Courses.lesson;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.adapters.LessonAdapter;
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

public class CoursesDetailActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    LessonAdapter adapter;
    ArrayList<lesson> lesson;
    ArrayList<data> data;
    TextView txt_title, blog_headline, courses_time, lessions, courses_description;
    ImageView courses_img;
    PlayerView styledPlayerView;
    SimpleExoPlayer player;
    ConcatenatingMediaSource concatenatingMediaSource;
    String courses_id = "", video_link = "";
    RecyclerView all_lessons;
    RelativeLayout rel_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_detail);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = CoursesDetailActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        txt_title = findViewById(R.id.txt_title);
        blog_headline = findViewById(R.id.blog_headline);
        courses_time = findViewById(R.id.courses_time);
        lessions = findViewById(R.id.lessions);
        courses_description = findViewById(R.id.courses_description);
        courses_img = findViewById(R.id.courses_img);
        styledPlayerView = findViewById(R.id.styled_player_view);

        courses_id = getIntent().getStringExtra("courses_id");

        all_lessons = findViewById(R.id.all_lessons);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        all_lessons.setLayoutManager(linearLayoutManager);

        getCoursesDetail();
        getCoursesEnroll();
    }

    public void getCoursesEnroll(){

        CoursesEnrollAuthModel model = new CoursesEnrollAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setCourse_id(courses_id);

        WebServiceModel.getRestApi().getCoursesEnroll(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CoursesEnrollResponse>() {
                    @Override
                    public void onNext(CoursesEnrollResponse coursesEnrollResponse) {
                        String msg = coursesEnrollResponse.getMsg();

                        if (msg.equals("Course Enrolled")){

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

    public void getCoursesDetail(){
        dialog.show("");

        CoursesDetailAuthModel model = new CoursesDetailAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setCourse_id(courses_id);

        WebServiceModel.getRestApi().getCoursesDetail(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CoursesDetailResponse>() {
                    @Override
                    public void onNext(CoursesDetailResponse coursesDetailResponse) {
                        String msg = coursesDetailResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")) {
                            data = coursesDetailResponse.getData();
                            lesson = coursesDetailResponse.getData().get(0).getLesson();

                            if (data != null) {
                                txt_title.setText(Html.fromHtml(data.get(0).getTitle()));
                                blog_headline.setText(Html.fromHtml(data.get(0).getTitle()));
                                courses_description.setText(Html.fromHtml(data.get(0).getDescription()));
                                courses_description.setMovementMethod(LinkMovementMethod.getInstance());
                                courses_time.setText(Html.fromHtml(data.get(0).getTiming()));
                                lessions.setText(Html.fromHtml(data.get(0).getCurrentcourse()));
                                video_link = data.get(0).getVideolink();


                                if (data.get(0).getImage() != null) {
                                    courses_img.setVisibility(View.VISIBLE);
                                    styledPlayerView.setVisibility(View.GONE);
                                    Glide.with(context).load(data.get(0).getImage()).into(courses_img);

                                }
                                if (data.get(0).getVideolink() != null) {
                                    styledPlayerView.setVisibility(View.VISIBLE);
                                    courses_img.setVisibility(View.GONE);
                                    videoPlay();
                                }

                                /*if (data.get(0).getVideolink() != null && data.get(0).getImage() != null) {
                                    styledPlayerView.setVisibility(View.VISIBLE);
                                    courses_img.setVisibility(View.GONE);
                                }*/
                            }
                            if (lesson != null){
                                adapter = new LessonAdapter(context, lesson);
                                all_lessons.setAdapter(adapter);
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
    public void videoPlay(){

        String videoPath = video_link;
        Uri videoUri = Uri.parse(videoPath);
        MediaItem mediaItem = MediaItem.fromUri(videoUri);

        player = new SimpleExoPlayer.Builder(this).build();

        DefaultDataSourceFactory defaultExtractorsFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "TSTA" ));
        concatenatingMediaSource = new ConcatenatingMediaSource();

        MediaSource mediaSource = new ProgressiveMediaSource.Factory(defaultExtractorsFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(String.valueOf(videoUri))));
        concatenatingMediaSource.addMediaSource(mediaSource);
        styledPlayerView.setPlayer(player);
        styledPlayerView.setKeepScreenOn(true);

        player.setMediaItem(mediaItem);

        player.prepare(concatenatingMediaSource);
        player.play();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){
            finish();
        }
    }
}
package com.example.tooshytoask.activity.LMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.AuthModels.LessonDetailAuthModel;
import com.example.tooshytoask.AuthModels.LessonEnrollAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.Courses.Lesson.LessonDetailResponse;
import com.example.tooshytoask.Models.Courses.Lesson.LessonEnrollResponse;
import com.example.tooshytoask.Models.Courses.Lesson.data;
import com.example.tooshytoask.Models.Courses.Lesson.upcominglesson;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.activity.Quiz.LMSQuizMainActivity;
import com.example.tooshytoask.adapters.UpcomingLessonAdapter;
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

public class LessonDetailActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    TextView txt_title, blog_headline, courses_description;
    ImageView courses_img;
    Button complete_btn, quiz_btn;
    PlayerView styledPlayerView;
    SimpleExoPlayer player;
    ConcatenatingMediaSource concatenatingMediaSource;
    String lesson_id = "", video_link = "", courses_id ="", quiz = "", less_id="" , course_id="";
    RecyclerView upcoming_lessons;
    RelativeLayout upcoming_lay;
    ArrayList<data> data;
    UpcomingLessonAdapter adapter;
    ArrayList<upcominglesson>upcominglesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);
        context = LessonDetailActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        complete_btn = findViewById(R.id.complete_btn);
        complete_btn.setOnClickListener(this);
        quiz_btn = findViewById(R.id.quiz_btn);
        quiz_btn.setOnClickListener(this);
        upcoming_lay = findViewById(R.id.upcoming_lay);
        txt_title = findViewById(R.id.txt_title);
        blog_headline = findViewById(R.id.blog_headline);
        courses_description = findViewById(R.id.courses_description);
        courses_img = findViewById(R.id.courses_img);
        styledPlayerView = findViewById(R.id.styled_player_view);

        lesson_id = getIntent().getStringExtra("lesson_id");
        courses_id = getIntent().getStringExtra("courses_id");

        upcoming_lessons = findViewById(R.id.upcoming_lessons);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        upcoming_lessons.setLayoutManager(linearLayoutManager);

        getLessonDetail();
        getLessonEnroll();
    }

    public void getLessonEnroll(){

        LessonEnrollAuthModel model = new LessonEnrollAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setLesson_id(lesson_id);
        model.setCourse_id(courses_id);

        WebServiceModel.getRestApi().getLessonEnroll(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LessonEnrollResponse>() {
                    @Override
                    public void onNext(LessonEnrollResponse lessonEnrollResponse) {

                        String msg = lessonEnrollResponse.getMsg();

                        if (msg.equals("Lesson Enrolled")){

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

    public void getLessonDetail(){
        dialog.show("");

        LessonDetailAuthModel model = new LessonDetailAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setLesson_id(lesson_id);

        WebServiceModel.getRestApi().getLessonDetail(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LessonDetailResponse>() {
                    @Override
                    public void onNext(LessonDetailResponse lessonDetailResponse) {
                        String msg = lessonDetailResponse.getMsg();
                        dialog.dismiss("");

                        if (msg.equals("success")){
                            data = lessonDetailResponse.getData();
                            upcominglesson = lessonDetailResponse.getData().get(0).getUpcominglesson();

                            if (data != null) {
                                txt_title.setText(Html.fromHtml(data.get(0).getTitle()));
                                blog_headline.setText(Html.fromHtml(data.get(0).getTitle()));
                                courses_description.setText(Html.fromHtml(data.get(0).getDescription()));
                                video_link = data.get(0).getVideo();
                                quiz = data.get(0).getQuiz();

                                if (quiz.equals("0")){
                                    quiz_btn.setVisibility(View.GONE);
                                    complete_btn.setVisibility(View.VISIBLE);
                                }
                                else {
                                    quiz_btn.setVisibility(View.VISIBLE);
                                    complete_btn.setVisibility(View.GONE);
                                }

                                if (data.get(0).getImage() != null) {
                                    courses_img.setVisibility(View.VISIBLE);
                                    Glide.with(context).load(data.get(0).getImage()).into(courses_img);

                                }
                                if (data.get(0).getImage() == null) {
                                    courses_img.setVisibility(View.GONE);
                                }
                                if (data.get(0).getVideo() != null) {
                                    styledPlayerView.setVisibility(View.VISIBLE);
                                    videoPlay();
                                }
                                if (data.get(0).getVideo() == null) {
                                    styledPlayerView.setVisibility(View.GONE);
                                }
                                if (data.get(0).getVideo() != null && data.get(0).getImage() != null) {
                                    styledPlayerView.setVisibility(View.VISIBLE);
                                    courses_img.setVisibility(View.GONE);

                                }
                            }
                            if (upcominglesson != null){
                                adapter = new UpcomingLessonAdapter(context, upcominglesson, data);
                                upcoming_lessons.setAdapter(adapter);
                            }
                            if (upcominglesson.size() == 0){
                                upcoming_lay.setVisibility(View.GONE);
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

        if (id == complete_btn.getId()){
            Intent intent = new Intent(context, CoursesDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if (id == quiz_btn.getId()){
            Intent intent = new Intent(context, LMSQuizMainActivity.class);
            intent.putExtra("lesson_id", lesson_id);
            intent.putExtra("courses_id", courses_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}
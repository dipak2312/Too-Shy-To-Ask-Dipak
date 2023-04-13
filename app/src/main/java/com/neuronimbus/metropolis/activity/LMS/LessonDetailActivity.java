package com.neuronimbus.metropolis.activity.LMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.LessonDetailAuthModel;
import com.neuronimbus.metropolis.AuthModels.LessonEnrollAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.Courses.Lesson.LessonDetailResponse;
import com.neuronimbus.metropolis.Models.Courses.Lesson.LessonEnrollResponse;
import com.neuronimbus.metropolis.Models.Courses.Lesson.data;
import com.neuronimbus.metropolis.Models.Courses.Lesson.upcominglesson;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.activity.LMS.LMSQuiz.LMSQuizMainActivity;
import com.neuronimbus.metropolis.adapters.UpcomingLessonAdapter;
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
    String lesson_id = "", video_link = "", courses_id ="", quiz = "", less_id="" , course_id="", lesson_status;
    RecyclerView upcoming_lessons;
    RelativeLayout upcoming_lay, rel_back, lesson_inner_screen;
    ArrayList<data> data;
    UpcomingLessonAdapter adapter;
    ArrayList<upcominglesson>upcominglesson;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);
        context = LessonDetailActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        lesson_inner_screen = findViewById(R.id.lesson_inner_screen);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
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
        lesson_inner_screen.setVisibility(View.GONE);

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
                        lesson_inner_screen.setVisibility(View.VISIBLE);

                        if (msg.equals("success")){
                            data = lessonDetailResponse.getData();
                            upcominglesson = lessonDetailResponse.getData().get(0).getUpcominglesson();

                            if (data != null) {
                                txt_title.setText(Html.fromHtml(data.get(position).getTitle()));
                                blog_headline.setText(Html.fromHtml(data.get(position).getTitle()));
                                courses_description.setText(Html.fromHtml(data.get(position).getDescription()));
                                courses_description.setMovementMethod(LinkMovementMethod.getInstance());
                                video_link = data.get(position).getVideo();
                                quiz = data.get(position).getQuiz();
                                lesson_status = data.get(position).getLesson_status();

                                if (quiz.equals("0")){
                                    quiz_btn.setVisibility(View.GONE);
                                    complete_btn.setVisibility(View.VISIBLE);
                                }
                                else {
                                    quiz_btn.setVisibility(View.VISIBLE);
                                    complete_btn.setVisibility(View.GONE);
                                }

                                if (lesson_status.equals("completed")){
                                    quiz_btn.setVisibility(View.GONE);
                                    complete_btn.setVisibility(View.VISIBLE);
                                }
                                else if (lesson_status.equals("pending")){
                                    quiz_btn.setVisibility(View.VISIBLE);
                                    complete_btn.setVisibility(View.GONE);
                                }

                                if (data.get(position).getVideo().equals("")) {
                                    courses_img.setVisibility(View.VISIBLE);
                                    styledPlayerView.setVisibility(View.GONE);
                                    Glide.with(context).load(data.get(0).getImage()).into(courses_img);

                                }
                                else if (data.get(position).getVideo() != null) {
                                    styledPlayerView.setVisibility(View.VISIBLE);
                                    courses_img.setVisibility(View.GONE);
                                    videoPlay();
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
            if(player != null)
            {
                player.release();
                player.stop();
            }
            Intent intent = new Intent(context, CoursesDetailActivity.class);
            intent.putExtra("courses_id", courses_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if (id == quiz_btn.getId()){
            if(player != null)
            {
                player.release();
                player.stop();
            }
            Intent intent = new Intent(context, LMSQuizMainActivity.class);
            intent.putExtra("lesson_id", lesson_id);
            intent.putExtra("courses_id", courses_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else  if (id == rel_back.getId()){
            if(player != null)
            {
                player.release();
                player.stop();
            }
            finish();
        }

    }
}
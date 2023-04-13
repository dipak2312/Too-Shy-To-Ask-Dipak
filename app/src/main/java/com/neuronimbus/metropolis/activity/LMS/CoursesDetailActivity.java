package com.neuronimbus.metropolis.activity.LMS;

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
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.CoursesDetailAuthModel;
import com.neuronimbus.metropolis.AuthModels.CoursesEnrollAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.Courses.CoursesDetailResponse;
import com.neuronimbus.metropolis.Models.Courses.CoursesEnrollResponse;
import com.neuronimbus.metropolis.Models.Courses.data;
import com.neuronimbus.metropolis.Models.Courses.lesson;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.adapters.LessonAdapter;
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
    TextView txt_title, blog_headline, course_time_hr, course_time_min, lessions, courses_description;
    ImageView courses_img;
    PlayerView styledPlayerView;
    SimpleExoPlayer player;
    ConcatenatingMediaSource concatenatingMediaSource;
    String courses_id = "", video_link = "", time, part1, part2;
    RecyclerView all_lessons;
    RelativeLayout rel_back, courses_inner_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_detail);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = CoursesDetailActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        courses_inner_screen = findViewById(R.id.courses_inner_screen);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        txt_title = findViewById(R.id.txt_title);
        blog_headline = findViewById(R.id.blog_headline);
        course_time_min = findViewById(R.id.course_time_min);
        course_time_hr = findViewById(R.id.course_time_hr);
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
        courses_inner_screen.setVisibility(View.GONE);

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
                        courses_inner_screen.setVisibility(View.VISIBLE);

                        if (msg.equals("success")) {
                            data = coursesDetailResponse.getData();
                            lesson = coursesDetailResponse.getData().get(0).getLesson();

                            if (data != null) {
                                txt_title.setText(Html.fromHtml(data.get(0).getTitle()));
                                blog_headline.setText(Html.fromHtml(data.get(0).getTitle()));
                                courses_description.setText(Html.fromHtml(data.get(0).getDescription()));
                                courses_description.setMovementMethod(LinkMovementMethod.getInstance());
                                time = data.get(0).getTiming();
                                String[] parts = time.split(":");
                                part1 = parts[0];
                                part2 = parts[1];
                                course_time_hr.setText(part1 + "h ");
                                course_time_min.setText(part2 + "m");
                                lessions.setText(Html.fromHtml(data.get(0).getCurrentcourse()));
                                video_link = data.get(0).getVideolink();


                                if (data.get(0).getVideolink().equals("")) {
                                    courses_img.setVisibility(View.VISIBLE);
                                    styledPlayerView.setVisibility(View.GONE);
                                    Glide.with(context).load(data.get(0).getImage()).into(courses_img);

                                }
                               else if (data.get(0).getVideolink() != null) {
                                    styledPlayerView.setVisibility(View.VISIBLE);
                                    courses_img.setVisibility(View.GONE);
                                    videoPlay();
                                }

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
            if(player != null)
            {
                player.release();
                player.stop();
            }
            finish();
        }
    }
}
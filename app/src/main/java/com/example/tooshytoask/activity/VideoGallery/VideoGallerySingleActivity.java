package com.example.tooshytoask.activity.VideoGallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.InsightScreen.video_gallery;
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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoGallerySingleActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    static String video_link ="", video_type = "";
    String url;
    PlayerView styledPlayerView;
    SimpleExoPlayer player;
    YouTubePlayerView youTubePlayerView;
    ConcatenatingMediaSource concatenatingMediaSource;
    ArrayList<video_gallery> video_gallery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gallery_single);
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        context = VideoGallerySingleActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        styledPlayerView = findViewById(R.id.styled_player_view);

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        video_gallery = new ArrayList<>();

        video_link = getIntent().getStringExtra("video_link");
        video_type = getIntent().getStringExtra("video_type");

        VideoPlayerSet();


    }

    public void VideoPlayerSet(){
            if (video_type.equals("Youtube")){
                youTubePlayerView.setVisibility(View.VISIBLE);
                styledPlayerView.setVisibility(View.GONE);
                getYouTubeId();
            }
            else if (video_type.equals("Media")){
                youTubePlayerView.setVisibility(View.GONE);
                styledPlayerView.setVisibility(View.VISIBLE);
                videoPlay();

        }
    }

    private String getYouTubeId () {
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "Cm2vzY728L0";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
        return null;
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
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
        //styledPlayerView.setPlayer(null);
        //player.release();

    }

    @Override
    public void onClick(View v) {

    }
}
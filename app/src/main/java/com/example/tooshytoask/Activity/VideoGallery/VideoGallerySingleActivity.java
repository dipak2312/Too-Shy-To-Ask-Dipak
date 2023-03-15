package com.example.tooshytoask.Activity.VideoGallery;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.VideoView;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.AllVideoGalleryAdapter;
import com.example.tooshytoask.AuthModels.AllVideoGalleryAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AllVideoGalleryResponse;
import com.example.tooshytoask.Models.insightvideo;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.potyvideo.library.AndExoPlayerView;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class VideoGallerySingleActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    String video_link ="";
    StyledPlayerView styledPlayerView;
    ExoPlayer player;


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
        video_link = getIntent().getStringExtra("video_link");
        videoPlay();

    }

    public void videoPlay(){

        String videoPath = video_link;
        Uri videoUri = Uri.parse(videoPath);
        MediaItem mediaItem = MediaItem.fromUri(videoUri);

        player = new ExoPlayer.Builder(this).build();

        styledPlayerView.setPlayer(player);

        player.setMediaItem(mediaItem);

        player.prepare();
        player.play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        styledPlayerView.setPlayer(null);
        player.release();

    }

    @Override
    public void onClick(View v) {

    }
}
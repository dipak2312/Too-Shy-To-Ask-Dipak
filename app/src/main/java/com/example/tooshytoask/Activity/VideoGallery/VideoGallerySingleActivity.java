package com.example.tooshytoask.Activity.VideoGallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.AllVideoGalleryAdapter;
import com.example.tooshytoask.AuthModels.AllVideoGalleryAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AllVideoGalleryResponse;
import com.example.tooshytoask.Models.insightvideo;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.potyvideo.library.AndExoPlayerView;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class VideoGallerySingleActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    PlayerView video_play;
    ArrayList<com.example.tooshytoask.Models.insightvideo> insightvideo;
    String video_link ="";
    SimpleExoPlayer exoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gallery_single);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        context = VideoGallerySingleActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        video_play = findViewById(R.id.video_play);

        video_play.setPlayer(exoPlayer);


        video_link = getIntent().getStringExtra("video_link");
        MediaItem mediaItem = MediaItem.fromUri(video_link);
        exoPlayer.addMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);
        //Uri videouri = Uri.parse(video_link);


        //getVideoGallery();
    }

    public void getVideoGallery(){
        dialog.show("");

        AllVideoGalleryAuthModel model = new AllVideoGalleryAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getVideoGallery(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AllVideoGalleryResponse>() {
                    @Override
                    public void onNext(AllVideoGalleryResponse allVideoGalleryResponse) {
                        String msg = allVideoGalleryResponse.getMsg();
                        dialog.dismiss("");

                        if (msg.equals("success")){
                            insightvideo = allVideoGalleryResponse.getInsightvideo();

                            //txt_title.setText(Html.fromHtml(singleblog.get(0).getBlog_title()));
                            //video_play.setPlayer(insightvideo.get(0).getLink());
                            Uri videoUrl = Uri.parse(insightvideo.get(0).getLink());


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
    public void onClick(View v) {

    }
}
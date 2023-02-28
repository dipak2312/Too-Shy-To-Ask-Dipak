package com.example.tooshytoask.Activity.VideoGallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Bookmark.BookmarkActivity;
import com.example.tooshytoask.Activity.Courses.AllCoursesActivity;
import com.example.tooshytoask.Adapters.AllCoursesAdapter;
import com.example.tooshytoask.Adapters.AllVideoGalleryAdapter;
import com.example.tooshytoask.AuthModels.AllCoursesAuthModel;
import com.example.tooshytoask.AuthModels.AllVideoGalleryAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AllCoursesResponse;
import com.example.tooshytoask.Models.AllVideoGalleryResponse;
import com.example.tooshytoask.Models.insightvideo;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AllVideoActivity extends AppCompatActivity implements View.OnClickListener{
    SPManager spManager;
    Context context;
    CustomProgressDialog dialog;
    RelativeLayout rel_back;
    ImageView bookmark_blog;
    RecyclerView video_gallery_recy;
    ArrayList<insightvideo> insightvideo;
    AllVideoGalleryAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_video);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = AllVideoActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        bookmark_blog = findViewById(R.id.bookmark_blog);
        bookmark_blog.setOnClickListener(this);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        video_gallery_recy = findViewById(R.id.video_gallery_recy);
        video_gallery_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));

        getVideoGallery();
    }

    public void getVideoGallery(){
        dialog.show("");

        AllVideoGalleryAuthModel model = new AllVideoGalleryAuthModel();

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

                            adapter = new AllVideoGalleryAdapter(context, insightvideo);
                            video_gallery_recy.setAdapter(adapter);
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

        if (id == rel_back.getId()) {
            finish();
        }
        else if (id == bookmark_blog.getId()) {
            Intent intent = new Intent(context, BookmarkActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}
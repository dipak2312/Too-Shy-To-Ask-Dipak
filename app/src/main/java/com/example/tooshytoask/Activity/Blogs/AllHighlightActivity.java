package com.example.tooshytoask.Activity.Blogs;

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
import com.example.tooshytoask.Adapters.AllHighlightAdapter;
import com.example.tooshytoask.AuthModels.AllHighlightAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AllHighlightResponse;
import com.example.tooshytoask.Models.insighthighlights;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AllHighlightActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView highlight_recy;
    SPManager spManager;
    Context context;
    ImageView bookmark_blog;
    RelativeLayout rel_back;
    CustomProgressDialog dialog;
    ArrayList<insighthighlights>insighthighlights;
    AllHighlightAdapter adapter;
    int selectedPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_highlight);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = AllHighlightActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        bookmark_blog = findViewById(R.id.bookmark_blog);
        bookmark_blog.setOnClickListener(this);
        highlight_recy = findViewById(R.id.highlight_recy);

        highlight_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));

        getHighlightBlogs();
    }

    public void getHighlightBlogs(){
        dialog.show("");

        AllHighlightAuthModel model = new AllHighlightAuthModel();

        WebServiceModel.getRestApi().getHighlightBlogs(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AllHighlightResponse>() {
                    @Override
                    public void onNext(AllHighlightResponse allHighlightResponse) {
                        String msg = allHighlightResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){
                            insighthighlights = allHighlightResponse.getInsighthighlights();

                            adapter = new AllHighlightAdapter(context, insighthighlights);
                            highlight_recy.setAdapter(adapter);
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
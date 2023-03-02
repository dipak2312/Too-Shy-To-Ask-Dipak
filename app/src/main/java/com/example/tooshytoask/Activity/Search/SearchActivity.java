package com.example.tooshytoask.Activity.Search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.BlogSearchAdapter;
import com.example.tooshytoask.Adapters.CoursesSearchAdapter;
import com.example.tooshytoask.Adapters.EventSearchAdapter;
import com.example.tooshytoask.Adapters.InfoStoreHouseSearchAdapter;
import com.example.tooshytoask.Adapters.VideoGallerySearchAdapter;
import com.example.tooshytoask.AuthModels.SearchAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.SearchResponse;
import com.example.tooshytoask.Models.blog_search;
import com.example.tooshytoask.Models.course_search;
import com.example.tooshytoask.Models.event_search;
import com.example.tooshytoask.Models.storehouse_search;
import com.example.tooshytoask.Models.video_search;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    EditText edit_search;
    RecyclerView recy_storeHouse_search,recy_blogs_search,recy_event_search,recy_courses_search,recy_video_search;
    RelativeLayout rel_lesson_search, rel_back, info_storehouse, blogs, events, courses, video;
    ProgressBar progress_bar;
    boolean isScrolling=true;
    int CurrentItem,totalitem,scrolledoutitem;
    int i=1;
    TextView txt_empty_search;
    InfoStoreHouseSearchAdapter adapter;
    EventSearchAdapter eventSearchAdapter;
    BlogSearchAdapter blogSearchAdapter;
    CoursesSearchAdapter coursesSearchAdapter;
    VideoGallerySearchAdapter videoGallerySearchAdapter;
    ArrayList<String> result = null;
    ArrayList<storehouse_search>storehouse_search;
    ArrayList<storehouse_search>Allstorehouse_search;
    ArrayList<video_search>video_search;
    ArrayList<video_search>Allvideo_search;
    ArrayList<course_search>course_search;
    ArrayList<course_search>Allcourse_search;
    ArrayList<blog_search>blog_search;
    ArrayList<blog_search>Allblog_search;
    ArrayList<event_search>event_search;
    ArrayList<event_search>Allevent_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = SearchActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        blogs = findViewById(R.id.blogs);
        events = findViewById(R.id.events);
        courses = findViewById(R.id.courses);
        video = findViewById(R.id.video);
        info_storehouse = findViewById(R.id.info_storehouse);
        txt_empty_search = findViewById(R.id.txt_empty_search);
        edit_search = findViewById(R.id.edit_search);
        progress_bar= findViewById(R.id.progress_bar);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        rel_lesson_search = findViewById(R.id.rel_lesson_search);
        rel_lesson_search.setOnClickListener(this);

        storehouse_search=new ArrayList<>();
        Allstorehouse_search=new ArrayList<>();

        blog_search=new ArrayList<>();
        Allblog_search=new ArrayList<>();

        event_search=new ArrayList<>();
        Allevent_search=new ArrayList<>();

        course_search=new ArrayList<>();
        Allcourse_search=new ArrayList<>();

        video_search=new ArrayList<>();
        Allvideo_search=new ArrayList<>();


        edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    InputMethodManager imm = (InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                    Allstorehouse_search.clear();
                    Allvideo_search.clear();
                    Allcourse_search.clear();
                    Allblog_search.clear();
                    Allevent_search.clear();
                    i=1;
                    getSearch(edit_search.getText().toString().trim(),"wrefresh");

                    return true;
                }
                return false;
            }
        });
        RecyView();

    }

    public void RecyView(){

        recy_storeHouse_search = findViewById(R.id.recy_storeHouse_search);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recy_storeHouse_search.setLayoutManager(linearLayoutManager);

        recy_blogs_search = findViewById(R.id.recy_blogs_search);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recy_blogs_search.setLayoutManager(linearLayoutManager1);

        recy_event_search = findViewById(R.id.recy_event_search);
        LinearLayoutManager lm = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recy_event_search.setLayoutManager(lm);

        recy_courses_search = findViewById(R.id.recy_courses_search);
        GridLayoutManager layout = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        recy_courses_search.setLayoutManager(layout);
        //recy_courses_search.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));

        recy_video_search = findViewById(R.id.recy_video_search);
        GridLayoutManager layout1 = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        recy_video_search.setLayoutManager(layout1);
        //recy_video_search.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));
        recy_video_search.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {//check for scroll down
                    //As many as are visible on the page
                    CurrentItem = layout1.getChildCount();
                    //all item
                    totalitem = layout1.getItemCount();
                    //scroll top up all item
                    scrolledoutitem = layout1.findFirstVisibleItemPosition();

                    if (isScrolling) {
                        if (CurrentItem + scrolledoutitem >= totalitem) {
                            isScrolling = false;
                            i++;
                            fetchData();
                        }
                    }
                }
            }
        });

        recy_courses_search.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {//check for scroll down
                    //As many as are visible on the page
                    CurrentItem = layout.getChildCount();
                    //all item
                    totalitem = layout.getItemCount();
                    //scroll top up all item
                    scrolledoutitem = layout.findFirstVisibleItemPosition();

                    if (isScrolling) {
                        if (CurrentItem + scrolledoutitem >= totalitem) {
                            isScrolling = false;
                            i++;
                            fetchData();
                        }
                    }
                }
            }
        });

        recy_storeHouse_search.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {//check for scroll down
                    //As many as are visible on the page
                    CurrentItem = linearLayoutManager.getChildCount();
                    //all item
                    totalitem = linearLayoutManager.getItemCount();
                    //scroll top up all item
                    scrolledoutitem = linearLayoutManager.findFirstVisibleItemPosition();

                    if (isScrolling) {
                        if (CurrentItem + scrolledoutitem >= totalitem) {
                            isScrolling = false;
                            i++;
                            fetchData();
                        }
                    }
                }
            }
        });

        recy_event_search.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {//check for scroll down
                    //As many as are visible on the page
                    CurrentItem = lm.getChildCount();
                    //all item
                    totalitem = lm.getItemCount();
                    //scroll top up all item
                    scrolledoutitem = lm.findFirstVisibleItemPosition();

                    if (isScrolling) {
                        if (CurrentItem + scrolledoutitem >= totalitem) {
                            isScrolling = false;
                            i++;
                            fetchData();
                        }
                    }
                }
            }
        });

        recy_blogs_search.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {//check for scroll down
                    //As many as are visible on the page
                    CurrentItem = linearLayoutManager1.getChildCount();
                    //all item
                    totalitem = linearLayoutManager1.getItemCount();
                    //scroll top up all item
                    scrolledoutitem = linearLayoutManager1.findFirstVisibleItemPosition();

                    if (isScrolling) {
                        if (CurrentItem + scrolledoutitem >= totalitem) {
                            isScrolling = false;
                            i++;
                            fetchData();
                        }
                    }
                }
            }
        });
    }

    public void fetchData()
    {
        progress_bar.setVisibility(View.VISIBLE);
        getSearch(edit_search.getText().toString().trim(),"refresh");

    }

    public void getSearch(String searchresult,String status){

        if(status.equals("wrefresh")) {

            dialog.show("");
        }

        SearchAuthModel model = new SearchAuthModel();
        model.setSearch_key(searchresult);

        WebServiceModel.getRestApi().getSearch(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SearchResponse>() {
                    @Override
                    public void onNext(SearchResponse searchResponse) {
                        if(status.equals("refresh")) {

                            progress_bar.setVisibility(View.GONE);
                        }
                        else
                        {
                            dialog.dismiss("");
                        }
                        String msg = searchResponse.getMsg();

                        if (msg.equals("success")) {
                            Allstorehouse_search = searchResponse.getStorehouse_search();
                            Allvideo_search = searchResponse.getVideo_search();
                            Allcourse_search = searchResponse.getCourse_search();
                            Allblog_search = searchResponse.getBlog_search();
                            Allevent_search = searchResponse.getEvent_search();
                            setAdapter(status);
                        }
                        else {
                            if(status.equals("wrefresh"))
                            {
                                if(adapter != null) {
                                    adapter.notifyDataSetChanged();
                                }
                                txt_empty_search.setVisibility(View.VISIBLE);
                            }
                             if(status.equals("wrefresh"))
                            {
                                if(blogSearchAdapter !=null) {
                                    blogSearchAdapter.notifyDataSetChanged();
                                }

                                txt_empty_search.setVisibility(View.VISIBLE);
                            }

                            if(status.equals("wrefresh"))
                            {
                                if(eventSearchAdapter !=null) {
                                    eventSearchAdapter.notifyDataSetChanged();
                                }
                                txt_empty_search.setVisibility(View.VISIBLE);
                            }

                            if(status.equals("wrefresh"))
                            {
                                if(coursesSearchAdapter !=null) {
                                    coursesSearchAdapter.notifyDataSetChanged();
                                }
                                txt_empty_search.setVisibility(View.VISIBLE);
                            }

                            if(status.equals("wrefresh"))
                            {
                                if(videoGallerySearchAdapter !=null) {
                                    videoGallerySearchAdapter.notifyDataSetChanged();
                                }
                                txt_empty_search.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        isScrolling=true;
                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();
                        if(status.equals("refresh")) {

                            progress_bar.setVisibility(View.GONE);
                        }
                        else
                        {
                            dialog.dismiss("");
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setAdapter(String status) {
        for (int i=0;i<storehouse_search.size();i++)
        {
            Allstorehouse_search.add(storehouse_search.get(i));
        }

        isScrolling=true;

        if(status.equals("refresh"))
        {
            adapter.notifyDataSetChanged();
        }
        else
        {
            if (Allstorehouse_search.size() == 0){
                info_storehouse.setVisibility(View.GONE);
            }
            else {
                adapter = new InfoStoreHouseSearchAdapter(context, Allstorehouse_search);
                recy_storeHouse_search.setAdapter(adapter);
                info_storehouse.setVisibility(View.VISIBLE);
            }
        }
        for (int i=0;i<blog_search.size();i++)
        {
            Allblog_search.add(blog_search.get(i));
        }
        isScrolling=true;

        if(status.equals("refresh"))
        {
            blogSearchAdapter.notifyDataSetChanged();
        }
        else
        {
            if (Allblog_search.size() == 0){
                blogs.setVisibility(View.GONE);
            }
            else {
                blogSearchAdapter = new BlogSearchAdapter(context, Allblog_search);
                recy_blogs_search.setAdapter(blogSearchAdapter);
                blogs.setVisibility(View.VISIBLE);
            }
        }

        for (int i=0;i<event_search.size();i++)
        {
            Allevent_search.add(event_search.get(i));
        }
        isScrolling=true;

        if(status.equals("refresh"))
        {
            eventSearchAdapter.notifyDataSetChanged();
        }
        else
        {
            if (Allevent_search.size() == 0){
                events.setVisibility(View.GONE);
            }
            else {
                eventSearchAdapter = new EventSearchAdapter(context, Allevent_search);
                recy_event_search.setAdapter(eventSearchAdapter);
                events.setVisibility(View.VISIBLE);
            }
        }

        for (int i=0;i<course_search.size();i++)
        {
            Allcourse_search.add(course_search.get(i));
        }
        isScrolling=true;

        if(status.equals("refresh"))
        {
            coursesSearchAdapter.notifyDataSetChanged();
        }
        else
        {
            if (Allcourse_search.size() == 0){
                courses.setVisibility(View.GONE);
            }
            else {
                coursesSearchAdapter = new CoursesSearchAdapter(context, Allcourse_search);
                recy_courses_search.setAdapter(coursesSearchAdapter);
                courses.setVisibility(View.VISIBLE);
            }
        }

        for (int i=0;i<video_search.size();i++)
        {
            Allvideo_search.add(video_search.get(i));
        }
        isScrolling=true;

        if(status.equals("refresh"))
        {
            videoGallerySearchAdapter.notifyDataSetChanged();
        }
        else
        {
            if (Allvideo_search.size() == 0){
                video.setVisibility(View.GONE);
            }
            else {
                videoGallerySearchAdapter = new VideoGallerySearchAdapter(context, Allvideo_search);
                recy_video_search.setAdapter(videoGallerySearchAdapter);
                video.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        if(id==rel_lesson_search.getId())
        {
            Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

            if(intent.resolveActivity(getPackageManager())!=null) {
                startActivityForResult(intent, 5);
            }
            else {
                Toast.makeText(view.getContext(),"Your Device Doesn't Support Speech Intent", Toast.LENGTH_SHORT).show();
            }

        }
        else if (id == rel_back.getId()){
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==5) {
            if(resultCode==RESULT_OK && data!=null) {
                result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String text=result.get(0);
                edit_search.setText(text);
                Allstorehouse_search.clear();
                Allvideo_search.clear();
                Allcourse_search.clear();
                Allblog_search.clear();
                Allevent_search.clear();
                i=1;
                getSearch(text,"wrefresh");

            }
        }
    }
}
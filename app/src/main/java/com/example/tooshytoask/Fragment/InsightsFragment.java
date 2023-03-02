package com.example.tooshytoask.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Blogs.AllBlogsActivity;
import com.example.tooshytoask.Activity.Blogs.AllEventActivity;
import com.example.tooshytoask.Activity.Blogs.AllHighlightActivity;
import com.example.tooshytoask.Activity.Blogs.AllRecentlyAddedActivity;
import com.example.tooshytoask.Activity.Courses.AllCoursesActivity;
import com.example.tooshytoask.Activity.Game.GameMainPageActivity;
import com.example.tooshytoask.Activity.Quiz.QuizActivity;
import com.example.tooshytoask.Activity.VideoGallery.AllVideoActivity;
import com.example.tooshytoask.Adapters.BlogAdapter;
import com.example.tooshytoask.Adapters.CoursesAdapter;
import com.example.tooshytoask.Adapters.EventBlogAdapter;
import com.example.tooshytoask.Adapters.HighlightBlogAdapter;
import com.example.tooshytoask.Adapters.JustAddedBlogAdapter;
import com.example.tooshytoask.Adapters.StoreHouseAdapter;
import com.example.tooshytoask.Adapters.VideoGalleryAdapter;
import com.example.tooshytoask.AuthModels.InsightScreenAuthModel;
import com.example.tooshytoask.AuthModels.UserProfileAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.InsightScreen.storeHouse;
import com.example.tooshytoask.Models.InsightScreen.InsightScreenResponse;
import com.example.tooshytoask.Models.InsightScreen.blogs;
import com.example.tooshytoask.Models.InsightScreen.courses;
import com.example.tooshytoask.Models.InsightScreen.events;
import com.example.tooshytoask.Models.InsightScreen.higlights;
import com.example.tooshytoask.Models.InsightScreen.new_blogs;
import com.example.tooshytoask.Models.InsightScreen.video_gallery;
import com.example.tooshytoask.Models.UserProfileResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class InsightsFragment extends Fragment implements View.OnClickListener{
    Context context;
    SPManager spManager;
    RecyclerView recy_storehouse, recy_blogs, recy_highlight_blogs, recy_event_blogs,
                 recy_just_added_blogs, recy_courses, recy_video_gallery;
    ArrayList<blogs>blogs;
    ArrayList<storeHouse> storeHouse;
    ArrayList<higlights>higlights;
    ArrayList<events>events;
    ArrayList<new_blogs>new_blogs;
    ArrayList<courses>courses;
    ArrayList<video_gallery>video_gallery;
    VideoGalleryAdapter videoGalleryAdapter;
    CoursesAdapter coursesAdapter;
    EventBlogAdapter eventBlogAdapter;
    JustAddedBlogAdapter justAddedBlogAdapter;
    StoreHouseAdapter storeHouseAdapter;
    HighlightBlogAdapter highlightBlogAdapter;
    BlogAdapter blogAdapter;
    ShapeableImageView game_banner,take_a_quiz_banner;
    TextView see_all, see_all1, see_all2, see_all3, see_all4, see_all5;
    CustomProgressDialog dialog;
    CircleImageView update_profile;
    RelativeLayout insight_lay;
    NestedScrollView insight_scroll;
    ImageView bookmark, search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insights, container, false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = getActivity();
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        take_a_quiz_banner = view.findViewById(R.id.take_a_quiz_banner);
        take_a_quiz_banner.setOnClickListener(this);
        game_banner = view.findViewById(R.id.game_banner);
        game_banner.setOnClickListener(this);
        insight_scroll = view.findViewById(R.id.insight_scroll);
        insight_lay = view.findViewById(R.id.insight_lay);
        update_profile = view.findViewById(R.id.update_profile);
        update_profile.setOnClickListener(this);
        recy_storehouse = view.findViewById(R.id.recy_storehouse);
        see_all = view.findViewById(R.id.see_all);
        see_all.setOnClickListener(this);
        see_all1 = view.findViewById(R.id.see_all1);
        see_all1.setOnClickListener(this);
        see_all2 = view.findViewById(R.id.see_all2);
        see_all2.setOnClickListener(this);
        see_all3 = view.findViewById(R.id.see_all3);
        see_all3.setOnClickListener(this);
        see_all4 = view.findViewById(R.id.see_all4);
        see_all4.setOnClickListener(this);
        see_all5 = view.findViewById(R.id.see_all5);
        see_all5.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_storehouse.setLayoutManager(linearLayoutManager);

        recy_blogs = view.findViewById(R.id.recy_blogs);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_blogs.setLayoutManager(linearLayoutManager1);

        recy_highlight_blogs = view.findViewById(R.id.recy_highlight_blogs);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_highlight_blogs.setLayoutManager(linearLayoutManager2);

        recy_event_blogs = view.findViewById(R.id.recy_event_blogs);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_event_blogs.setLayoutManager(linearLayoutManager3);

        recy_just_added_blogs = view.findViewById(R.id.recy_just_added_blogs);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_just_added_blogs.setLayoutManager(linearLayoutManager4);

        recy_courses = view.findViewById(R.id.recy_courses);
        LinearLayoutManager linearLayoutManager5 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_courses.setLayoutManager(linearLayoutManager5);

        recy_video_gallery = view.findViewById(R.id.recy_video_gallery);
        LinearLayoutManager linearLayoutManager6 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_video_gallery.setLayoutManager(linearLayoutManager6);

        getInsightScreenResponse();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserData();

    }

    public void getInsightScreenResponse() {
        dialog.show("");
        insight_scroll.setVisibility(View.GONE);

        InsightScreenAuthModel model = new InsightScreenAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().insightScreenResponse(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<InsightScreenResponse>() {
                    @Override
                    public void onNext(InsightScreenResponse insightScreenResponse) {
                        String msg = insightScreenResponse.getMsg();

                        if (msg.equals("success")) {
                            storeHouse = insightScreenResponse.getBlog_category();
                            blogs = insightScreenResponse.getBlogs();
                            higlights = insightScreenResponse.getHiglights();
                            events = insightScreenResponse.getEvents();
                            new_blogs = insightScreenResponse.getNew_blogs();
                            courses = insightScreenResponse.getCourses();
                            video_gallery = insightScreenResponse.getVideo_gallery();

                            if(storeHouse.size() !=0) {

                                storeHouseAdapter = new StoreHouseAdapter(context , storeHouse);
                                recy_storehouse.setAdapter(storeHouseAdapter);
                            }
                            if(blogs.size() !=0) {

                                blogAdapter = new BlogAdapter(context ,blogs);
                                recy_blogs.setAdapter(blogAdapter);
                            }
                            if(higlights.size() !=0) {

                                highlightBlogAdapter = new HighlightBlogAdapter(context ,higlights);
                                recy_highlight_blogs.setAdapter(highlightBlogAdapter);
                            }
                            if(events.size() !=0) {

                                eventBlogAdapter = new EventBlogAdapter(context ,events);
                                recy_event_blogs.setAdapter(eventBlogAdapter);
                            }
                            if(new_blogs.size() !=0) {

                                justAddedBlogAdapter = new JustAddedBlogAdapter(context ,new_blogs);
                                recy_just_added_blogs.setAdapter(justAddedBlogAdapter);
                            }
                            if(courses.size() !=0) {

                                coursesAdapter = new CoursesAdapter(context ,courses);
                                recy_courses.setAdapter(coursesAdapter);
                            }
                            if(video_gallery.size() !=0) {

                                videoGalleryAdapter = new VideoGalleryAdapter(context ,video_gallery);
                                recy_video_gallery.setAdapter(videoGalleryAdapter);
                            }
                        }
                        insight_scroll.setVisibility(View.VISIBLE);
                        dialog.dismiss("");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getUserData(){
        //dialog.show("");

        UserProfileAuthModel model = new UserProfileAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getUserData(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UserProfileResponse>() {
                    @Override
                    public void onNext(UserProfileResponse userProfileResponse) {
                        String msg = userProfileResponse.getMsg();
                        //dialog.dismiss("");
                        if (msg.equals("success")){
                            Glide.with(context).load(userProfileResponse.getProfile_pic()).into(update_profile);
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

        if (id == see_all.getId()) {
            Intent intent = new Intent(context, AllBlogsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if (id == see_all1.getId()) {
            Intent intent = new Intent(context, AllHighlightActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if (id == see_all2.getId()) {
            Intent intent = new Intent(context, AllEventActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if (id == see_all3.getId()) {
            Intent intent = new Intent(context, AllRecentlyAddedActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if (id == see_all4.getId()) {
            Intent intent = new Intent(context, AllCoursesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if (id == see_all5.getId()) {
            Intent intent = new Intent(context, AllVideoActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if (id == game_banner.getId()) {
            Intent intent = new Intent(context, GameMainPageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == take_a_quiz_banner.getId()) {
            Intent intent = new Intent(context, QuizActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}
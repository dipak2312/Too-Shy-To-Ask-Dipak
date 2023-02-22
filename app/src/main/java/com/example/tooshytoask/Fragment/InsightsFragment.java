package com.example.tooshytoask.Fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.BlogAdapter;
import com.example.tooshytoask.Adapters.CoursesAdapter;
import com.example.tooshytoask.Adapters.EventBlogAdapter;
import com.example.tooshytoask.Adapters.HighlightBlogAdapter;
import com.example.tooshytoask.Adapters.JustAddedBlogAdapter;
import com.example.tooshytoask.Adapters.StoreHouseAdapter;
import com.example.tooshytoask.Adapters.VideoGalleryAdapter;
import com.example.tooshytoask.AuthModels.InsightScreenAuthModel;
import com.example.tooshytoask.AuthModels.UpdateProfileAuthModel;
import com.example.tooshytoask.AuthModels.UserProfileAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.BlogItems;
import com.example.tooshytoask.Models.Blogs;
import com.example.tooshytoask.Models.CoursesItems;
import com.example.tooshytoask.Models.EventBlogItems;
import com.example.tooshytoask.Models.HighlightBlogItems;
import com.example.tooshytoask.Models.InsightScreen.blog_category;
import com.example.tooshytoask.Models.InsightScreen.InsightScreenResponse;
import com.example.tooshytoask.Models.InsightScreen.blogs;
import com.example.tooshytoask.Models.InsightScreen.courses;
import com.example.tooshytoask.Models.InsightScreen.events;
import com.example.tooshytoask.Models.InsightScreen.higlights;
import com.example.tooshytoask.Models.InsightScreen.new_blogs;
import com.example.tooshytoask.Models.InsightScreen.video_gallery;
import com.example.tooshytoask.Models.JustAddedBlogItems;
import com.example.tooshytoask.Models.StoreHouseItems;
import com.example.tooshytoask.Models.UpdateProfile.UpdateProfileResponse;
import com.example.tooshytoask.Models.UserProfileResponse;
import com.example.tooshytoask.Models.VideoGalleryItems;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Locale;

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
    ArrayList<blog_category>blog_category;
    ArrayList<higlights>higlights;
    ArrayList<events>events;
    ArrayList<new_blogs>new_blogs;
    ArrayList<courses>courses;
    ArrayList<VideoGalleryItems>videoGalleryItems;
    ArrayList<video_gallery>video_gallery;
    VideoGalleryAdapter videoGalleryAdapter;
    CoursesAdapter coursesAdapter;
    EventBlogAdapter eventBlogAdapter;
    JustAddedBlogAdapter justAddedBlogAdapter;
    StoreHouseAdapter storeHouseAdapter;
    HighlightBlogAdapter highlightBlogAdapter;
    BlogAdapter blogAdapter;
    TextView see_all;
    CustomProgressDialog dialog;
    CircleImageView update_profile;
    RelativeLayout insight_lay;
    NestedScrollView insight_scroll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insights, container, false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = getActivity();
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        insight_scroll = view.findViewById(R.id.insight_scroll);
        insight_lay = view.findViewById(R.id.insight_lay);
        update_profile = view.findViewById(R.id.update_profile);
        update_profile.setOnClickListener(this);
        recy_storehouse = view.findViewById(R.id.recy_storehouse);
        see_all = view.findViewById(R.id.see_all);
        see_all.setOnClickListener(this);
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
                            blog_category = insightScreenResponse.getBlog_category();
                            blogs = insightScreenResponse.getBlogs();
                            higlights = insightScreenResponse.getHiglights();
                            events = insightScreenResponse.getEvents();
                            new_blogs = insightScreenResponse.getNew_blogs();
                            courses = insightScreenResponse.getCourses();
                            video_gallery = insightScreenResponse.getVideo_gallery();

                            if(blog_category.size() !=0) {

                                storeHouseAdapter = new StoreHouseAdapter(context ,blog_category);
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
                        dialog.dismiss("");
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
            /*Intent intent = new Intent(context, AllBlogsActivity.class);
            startActivity(intent);*/
        }

    }
}
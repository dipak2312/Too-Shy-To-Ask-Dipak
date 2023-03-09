package com.example.tooshytoask.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Game.GameMainPageActivity;
import com.example.tooshytoask.Activity.Search.SearchActivity;
import com.example.tooshytoask.Activity.Notification.NotificationsActivity;
import com.example.tooshytoask.Activity.Setting.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.RecentlyBlogAdapter;
import com.example.tooshytoask.Adapters.RecommendedBlogAdapter;
import com.example.tooshytoask.Adapters.SliderBannerAdapter;
import com.example.tooshytoask.Adapters.StatusAdapter;
import com.example.tooshytoask.AuthModels.BookmarkBlogAuthModel;
import com.example.tooshytoask.AuthModels.HomeScreenAuthModel;
import com.example.tooshytoask.AuthModels.UpdateProfileAuthModel;
import com.example.tooshytoask.AuthModels.UserProfileAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.Bannerist;
import com.example.tooshytoask.Models.Blogs;
import com.example.tooshytoask.Models.BookmarkBlogResponse;
import com.example.tooshytoask.Models.HomeScreenResponse;
import com.example.tooshytoask.Models.RecommendedBlogs;
import com.example.tooshytoask.Models.StoryCategory;
import com.example.tooshytoask.Models.UpdateProfile.UpdateProfileResponse;
import com.example.tooshytoask.Models.UserProfileResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.OnClickListner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment implements View.OnClickListener, RecommendedBlogAdapter.onSavedClicked, RecentlyBlogAdapter.onSavedClicked {
    RecyclerView recy_recommended_blogs, recy_status, recy_recently_blogs, recy_blogs;
    ArrayList<StoryCategory> StoryCategory;
    ArrayList<Bannerist> Bannerist;
    ArrayList<Blogs> Blogs;
    ArrayList<RecommendedBlogs> RecommendedBlogs;
    RecommendedBlogAdapter recommendedBlogAdapter;
    RecentlyBlogAdapter recentlyBlogAdapter;
    StatusAdapter statusAdapter;
    SliderBannerAdapter sliderBannerAdapter;
    RelativeLayout blog;
    Context context;
    SPManager spManager;
    CircleImageView update_profile;
    ImageView search, notification, select_Language;
    ShapeableImageView game_banner;
    RadioButton eng_lang, hindi_lang, marathi_lang;
    Button btn_select;
    RelativeLayout back_arrow, home_lay;
    NestedScrollView home_scroll;
    LinearLayout recommended_blogs_lay;
    ViewPager2 viewPager2;
    Handler handler = new Handler();
    DotsIndicator mBarLayout;
    BottomSheetDialog bottomSheetDialog;
    CustomProgressDialog dialog;
    String action = "language", blog_id = "", type = "blog";
    RelativeLayout stories_rel_lay;
    OnClickListner onclicklistener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = getActivity();
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        game_banner = view.findViewById(R.id.game_banner);
        game_banner.setOnClickListener(this);
        home_scroll = view.findViewById(R.id.home_scroll);
        home_lay = view.findViewById(R.id.home_lay);
        stories_rel_lay = view.findViewById(R.id.stories_rel_lay);
        blog = view.findViewById(R.id.blog);
        recommended_blogs_lay = view.findViewById(R.id.recommended_blogs_lay);
        recommended_blogs_lay.setOnClickListener(this);
        update_profile = view.findViewById(R.id.update_profile);
        update_profile.setOnClickListener(this);
        search = view.findViewById(R.id.search);
        search.setOnClickListener(this);
        notification = view.findViewById(R.id.notification);
        notification.setOnClickListener(this);
        select_Language = view.findViewById(R.id.select_Language);
        select_Language.setOnClickListener(this);

        recy_status = view.findViewById(R.id.recy_status);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_status.setLayoutManager(linearLayoutManager);

        recy_blogs = view.findViewById(R.id.recy_blogs);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_blogs.setLayoutManager(linearLayoutManager3);

        recy_recommended_blogs = view.findViewById(R.id.recy_recommended_blogs);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_recommended_blogs.setLayoutManager(linearLayoutManager1);

        recy_recently_blogs = view.findViewById(R.id.recy_recently_blogs);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_recently_blogs.setLayoutManager(linearLayoutManager2);

        viewPager2 = view.findViewById(R.id.view_pager_img);
        viewPager2.setOnClickListener(this);
        mBarLayout = view.findViewById(R.id.indicator_layout);

        getHomePageResponse();
        getUserData();

        return view;
    }

    public void getBookmarkBlogs(String action){
        dialog.show("");
        dialog.dismiss("");

        BookmarkBlogAuthModel model = new BookmarkBlogAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setPost_id(blog_id);
        model.setType(type);
        model.setAction(action);

        WebServiceModel.getRestApi().getBookmarkBlogs(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<BookmarkBlogResponse>() {
                    @Override
                    public void onNext(BookmarkBlogResponse bookmarkBlogResponse) {
                        String msg = bookmarkBlogResponse.getMsg();
                        dialog.dismiss("");

                        if (msg.equals("Article Bookmarked")) {

                        }
                        else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getUserData(){
        dialog.show("");

        UserProfileAuthModel model = new UserProfileAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getUserData(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UserProfileResponse>() {
                    @Override
                    public void onNext(UserProfileResponse userProfileResponse) {
                        String msg = userProfileResponse.getMsg();

                        if (msg.equals("success")){
                            //Glide.with(context).load(profile_pic).placeholder(R.drawable.demo).into(profile_image);
                            Glide.with(context).load(userProfileResponse.getProfile_pic()).into(update_profile);
                        }
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

    public void getHomePageResponse() {
        //dialog.show("");
        home_scroll.setVisibility(View.GONE);

        HomeScreenAuthModel model = new HomeScreenAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().homePageResponse(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HomeScreenResponse>() {
                    @Override
                    public void onNext(HomeScreenResponse homeScreenResponse) {

                        String msg = homeScreenResponse.getMsg();
                        if (msg.equals("success")) {

                            StoryCategory = homeScreenResponse.getStoryCategory();
                            Bannerist = homeScreenResponse.getBannerist();
                            Blogs = homeScreenResponse.getBlogs();
                            RecommendedBlogs = homeScreenResponse.getRecommendedBlogs();

                            if(Bannerist.size() !=0)
                            {
                                sliderBannerAdapter = new SliderBannerAdapter(Bannerist, viewPager2, context);
                                viewPager2.setAdapter(sliderBannerAdapter);
                                viewPager();
                            }

                             if(StoryCategory.size() !=0)
                            {
                                statusAdapter = new StatusAdapter(context ,StoryCategory);
                                recy_status.setAdapter(statusAdapter);
                            }
                             if (StoryCategory.size() == 0){
                                 stories_rel_lay.setVisibility(View.GONE);
                             }
                            if(RecommendedBlogs.size() !=0)
                            {
                                recommended_blogs_lay.setVisibility(View.VISIBLE);
                                recommendedBlogAdapter = new RecommendedBlogAdapter(context, RecommendedBlogs, HomeFragment.this);
                                recy_recommended_blogs.setAdapter(recommendedBlogAdapter);

                            }
                            if (RecommendedBlogs.size() == 0){
                                recommended_blogs_lay.setVisibility(View.GONE);
                            }

                            if (Blogs.size() !=0){

                                recentlyBlogAdapter = new RecentlyBlogAdapter(context, Blogs, HomeFragment.this);
                                recy_recently_blogs.setAdapter(recentlyBlogAdapter);
                            }
                            home_scroll.setVisibility(View.VISIBLE);
                            dialog.dismiss("");
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

    public void viewPager(){
        mBarLayout.setViewPager2(viewPager2);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(8));
        //mBarLayout.setViewPager2(viewPager2);
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 0 - Math.abs(position);
//                page.setScaleY(0.90f + r* 0.10f);//this will reduce next position height during transformation
                page.setScaleY(1f);   // this will set both height same during transformation
                page.setScaleX(0.90f + 0.10f);// it is 1.00f value is default

            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (getitem(0) < Bannerist.size()) {

                }
                handler.removeCallbacks(sliderRunnable);
                handler.postDelayed(sliderRunnable, 3000);
            }
        });
    }

    public Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    private int getitem(int i) {

        return viewPager2.getCurrentItem() + i;

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == update_profile.getId()) {
            Intent intent = new Intent(context, UpdateProfileActivity.class);
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
        else if (id == notification.getId()) {
            Intent intent = new Intent(context, NotificationsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if(id==select_Language.getId()) {

            openLanguagePopup();
        }
        else if(id==search.getId()) {
            Intent intent = new Intent(context, SearchActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if(id==eng_lang.getId())
        {
            setLocale("en");
            btn_select.setText(R.string.select);
            eng_lang.setBackgroundResource(R.drawable.language_background_active);
            hindi_lang.setBackgroundResource(R.drawable.language_background);
            marathi_lang.setBackgroundResource(R.drawable.language_background);
        }
        else if(id==hindi_lang.getId())
        {
            setLocale("hi");
            btn_select.setText(R.string.चुनें);
            hindi_lang.setBackgroundResource(R.drawable.language_background_active);
            eng_lang.setBackgroundResource(R.drawable.language_background);
            marathi_lang.setBackgroundResource(R.drawable.language_background);
        }
        else if(id==marathi_lang.getId())
        {
            setLocale("mr");
            btn_select.setText(R.string.निवडा);
            marathi_lang.setBackgroundResource(R.drawable.language_background_active);
            eng_lang.setBackgroundResource(R.drawable.language_background);
            hindi_lang.setBackgroundResource(R.drawable.language_background);
        }
        else if(id==btn_select.getId())
        {
            getUserProfileUpdate();
            bottomSheetDialog.dismiss();
            refreshFragment();
        }
        else if(id==back_arrow.getId())
        {
            bottomSheetDialog.dismiss();
        }

    }

    public void openLanguagePopup()
    {

        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.select_language_view);
        bottomSheetDialog.setCancelable(false);

        eng_lang=bottomSheetDialog.findViewById(R.id.eng_lang);
        eng_lang.setOnClickListener(this);
        hindi_lang=bottomSheetDialog.findViewById(R.id.hindi_lang);
        hindi_lang.setOnClickListener(this);
        marathi_lang=bottomSheetDialog.findViewById(R.id.marathi_lang);
        marathi_lang.setOnClickListener(this);
        back_arrow = bottomSheetDialog.findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(this);
        btn_select = bottomSheetDialog.findViewById(R.id.btn_select);
        btn_select.setOnClickListener(this);


        bottomSheetDialog.show();
        selectLanguage();
        getUserProfileUpdate();
    }

    public void selectLanguage(){

        String selectValue=spManager.getLanguage();

        if(selectValue.equals("en"))
        {
            eng_lang.setChecked(true);
            eng_lang.setBackgroundResource(R.drawable.language_background_active);
            eng_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
            hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        else if(selectValue.equals("hi"))
        {
            hindi_lang.setChecked(true);
            hindi_lang.setBackgroundResource(R.drawable.language_background_active);
            hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
            eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        else if(selectValue.equals("mr"))
        {
            marathi_lang.setChecked(true);
            marathi_lang.setBackgroundResource(R.drawable.language_background_active);
            marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
            hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
    }

    public void radioButtonClickEvent(View view){
        boolean isChecked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.eng_lang:
                if(isChecked){

                    setLocale("en");
                    btn_select.setText(R.string.select);
                    eng_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    selectLanguage();
                }
                break;
            case R.id.hindi_lang:
                if(isChecked){

                    setLocale("hi");
                    btn_select.setText(R.string.चुनें);
                    hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    selectLanguage();
                }
                break;
            case R.id.marathi_lang:
                if(isChecked){

                    setLocale("mr");
                    btn_select.setText(R.string.निवडा);
                    marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
                    selectLanguage();
                }
                break;
        }
    }

    public void getUserProfileUpdate(){
        dialog.show("");

        UpdateProfileAuthModel model = new UpdateProfileAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setAction(action);
        model.setLanguage(spManager.getLanguage());

        WebServiceModel.getRestApi().getUserProfile(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UpdateProfileResponse>() {
                    @Override
                    public void onNext(UpdateProfileResponse updateProfileResponse) {
                        String msg = updateProfileResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("Profile Updated")){


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



    public void refreshFragment()
    {
        HomeFragment fragment1=new HomeFragment();
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.rootLayout,fragment1);
        ft.commit();
    }

    private void setLocale(String lang) {

        Locale locale=new Locale(lang);
        Locale.setDefault(locale);

        Configuration config=new Configuration();
        config.locale=locale;
        getActivity().getResources().updateConfiguration(config,getActivity().getResources().getDisplayMetrics());
        spManager.setLanguage(lang);

    }
    public void getFcmToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {

                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        //sendToken(token);


                    }
                });
    }

    @Override
    public void onSavedButtonClick(int position, String Blog_id) {
        blog_id = Blog_id;
        getBookmarkBlogs("save");
        getBookmarkBlogs("remove");
    }
}
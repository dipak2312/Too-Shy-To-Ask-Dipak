package com.neuronimbus.metropolis.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.activity.Bookmark.BookmarkActivity;
import com.neuronimbus.metropolis.activity.FAQ.FAQActivity;
import com.neuronimbus.metropolis.activity.Feedback.FeedbackActivity;
import com.neuronimbus.metropolis.activity.Landing.SignInActivity;
import com.neuronimbus.metropolis.activity.Help.HelpActivity;
import com.neuronimbus.metropolis.activity.Notification.NotificationsActivity;
import com.neuronimbus.metropolis.activity.Setting.Setting.UpdateProfileActivity;
import com.neuronimbus.metropolis.AuthModels.UpdateProfileAuthModel;
import com.neuronimbus.metropolis.AuthModels.UserProfileAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.UpdateProfile.UpdateProfileResponse;
import com.neuronimbus.metropolis.Models.UserProfileResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SettingsFragment extends Fragment implements View.OnClickListener{
    CircleImageView profile_image;
    RelativeLayout update_profile, notification_setting, bookmarks, faq, help,
            feedback, select_Language, refer_friends, logout;
    Context context;
    SPManager spManager;
    RadioButton eng_lang, hindi_lang, marathi_lang;
    BottomSheetDialog bottomSheetDialog;
    Button btn_select;
    RelativeLayout back_arrow;
    CustomProgressDialog dialog;
    TextView profile_status,txt_name, app_version;
    CircularProgressBar progress_circular;
    double progrss_value;
    String action = "language", profile_pic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = getActivity();
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        progress_circular = view.findViewById(R.id.progress_circular);
        app_version = view.findViewById(R.id.app_version);
        PackageManager pm = context.getPackageManager();
        String pkgName = context.getPackageName();
        PackageInfo pkgInfo = null;
        try {
            pkgInfo = pm.getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String ver = pkgInfo.versionName;
        app_version.setText("" +ver);
        profile_status = view.findViewById(R.id.profile_status);
        txt_name = view.findViewById(R.id.txt_name);
        profile_image = view.findViewById(R.id.profile_image);
        update_profile = view.findViewById(R.id.update_profile);
        update_profile.setOnClickListener(this);
        notification_setting = view.findViewById(R.id.notification_setting);
        notification_setting.setOnClickListener(this);
        bookmarks = view.findViewById(R.id.bookmarks);
        bookmarks.setOnClickListener(this);
        faq = view.findViewById(R.id.faq);
        faq.setOnClickListener(this);
        help = view.findViewById(R.id.help);
        help.setOnClickListener(this);
        feedback = view.findViewById(R.id.feedback);
        feedback.setOnClickListener(this);
        select_Language = view.findViewById(R.id.select_Language);
        select_Language.setOnClickListener(this);
        refer_friends = view.findViewById(R.id.refer_friends);
        refer_friends.setOnClickListener(this);
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(this);

        //getUserData();

        return view;
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
                            profile_status.setText(userProfileResponse.getProfile_percent());
                            progrss_value= Double.parseDouble(userProfileResponse.getProfile_percent());
                            progress_circular.setProgress((int) progrss_value);
                            txt_name.setText(userProfileResponse.getUser_name());
                            Glide.with(context).load(userProfileResponse.getProfile_pic()).placeholder(R.drawable.demo).into(profile_image);
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

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == update_profile.getId()) {
            Intent intent = new Intent(context, UpdateProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == notification_setting.getId()) {
            Intent intent = new Intent(context, NotificationsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == bookmarks.getId()) {
            Intent intent = new Intent(context, BookmarkActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == feedback.getId()) {
            Intent intent = new Intent(context, FeedbackActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == faq.getId()) {
            Intent intent = new Intent(context, FAQActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == select_Language.getId()) {
            openLanguagePopup();
        }
        else if (id == refer_friends.getId()) {
            shareTheApp();
        }
        else if (id == logout.getId()) {
            LogOut();
        }
        else if (id == help.getId()) {
            Intent intent = new Intent(context, HelpActivity.class);
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

    public void shareTheApp()
    {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "https://play.google.com/store/apps/details?id=" + context.getPackageName();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Too Shy To Ask App");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

    }

    public void LogOut() {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.logout_popup);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.logout_popup));

        TextView cancel = dialog.findViewById(R.id.cancel);
        TextView login = dialog.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spManager.setTstaLoginStatus("false");
                Intent intent = new Intent(context, SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                dialog.dismiss();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();

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

    }
    public void getUserProfileUpdate(){
        dialog.show("");
        dialog.dismiss("");

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

    public void refreshFragment()
    {
        SettingsFragment fragment1=new SettingsFragment();
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

    @Override
    public void onResume() {
        super.onResume();

        getUserData();
        profile_pic = spManager.getUserPhoto();
        if (!profile_pic.equals("")) {

            Glide.with(context).load(profile_pic).placeholder(R.drawable.demo).into(profile_image);
        }
    }

    }
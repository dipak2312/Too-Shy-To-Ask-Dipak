package com.example.tooshytoask.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Activity.Bookmark.BookmarkActivity;
import com.example.tooshytoask.Activity.Landing.SignInActivity;
import com.example.tooshytoask.Activity.Help.HelpActivity;
import com.example.tooshytoask.Activity.Notification.NotificationsActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Locale;

public class SettingsFragment extends Fragment implements View.OnClickListener{
    ImageView profile_image;
    RelativeLayout update_profile, notification_setting, bookmarks, faq, help,
            feedback, select_Language, refer_friends, logout;
    Context context;
    SPManager spManager;
    RadioButton eng_lang, hindi_lang, marathi_lang;
    BottomSheetDialog bottomSheetDialog;
    Button btn_select;
    RelativeLayout back_arrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        context = getActivity();
        spManager = new SPManager(context);

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


        return view;
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
            /*Intent intent = new Intent(context, BookmarkActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);*/
        }
        else if (id == feedback.getId()) {
            /*Intent intent = new Intent(context, UpdateProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);*/
        }
        else if (id == faq.getId()) {
           /* Intent intent = new Intent(context, UpdateProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);*/
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

        Button yes_btn = dialog.findViewById(R.id.yes_btn);
        Button no_btn = dialog.findViewById(R.id.no_btn);

        yes_btn.setOnClickListener(new View.OnClickListener() {
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

        no_btn.setOnClickListener(new View.OnClickListener() {
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


        String selectValue=spManager.getLanguage();

        if(selectValue.equals("en"))
        {

            eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            eng_lang.setTextColor(context.getResources().getColor(R.color.black));
        }
        else if(selectValue.equals("hi"))
        {

            hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            hindi_lang.setTextColor(context.getResources().getColor(R.color.black));

        }
        else if(selectValue.equals("mr"))
        {

            marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            marathi_lang.setTextColor(context.getResources().getColor(R.color.black));

        }

        bottomSheetDialog.show();
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
    }

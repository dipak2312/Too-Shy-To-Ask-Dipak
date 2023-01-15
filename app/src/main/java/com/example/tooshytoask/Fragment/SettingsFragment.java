package com.example.tooshytoask.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.Activity.Landing.OtpVerificationActivity;
import com.example.tooshytoask.Activity.Landing.SignInActivity;
import com.example.tooshytoask.Activity.Setting.HelpActivity;
import com.example.tooshytoask.Activity.Setting.NotificationsActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SettingsFragment extends Fragment implements View.OnClickListener{
    ImageView profile_image;
    RelativeLayout update_profile, notification_setting, bookmarks, faq, help,
            feedback, select_Language, refer_friends, logout;
    Context context;
    SPManager spManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
            startActivity(intent);
        }
        else if (id == notification_setting.getId()) {
            Intent intent = new Intent(context, NotificationsActivity.class);
            startActivity(intent);
        }
        else if (id == bookmarks.getId()) {
            Intent intent = new Intent(context, UpdateProfileActivity.class);
            startActivity(intent);
        }
        else if (id == feedback.getId()) {
            Intent intent = new Intent(context, UpdateProfileActivity.class);
            startActivity(intent);
        }
        else if (id == faq.getId()) {
            Intent intent = new Intent(context, UpdateProfileActivity.class);
            startActivity(intent);
        }
        else if (id == select_Language.getId()) {
            Intent intent = new Intent(context, UpdateProfileActivity.class);
            startActivity(intent);
        }
        else if (id == refer_friends.getId()) {
            Intent intent = new Intent(context, UpdateProfileActivity.class);
            startActivity(intent);
        }
        else if (id == logout.getId()) {
            LogOut();
        }
        else if (id == help.getId()) {
            Intent intent = new Intent(context, HelpActivity.class);
            startActivity(intent);
        }

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
    }

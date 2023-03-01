package com.example.tooshytoask.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tooshytoask.Activity.Expert.ExpertActivity;
import com.example.tooshytoask.Activity.FAQ.FAQActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

public class AskExpertFragment extends Fragment implements View.OnClickListener{
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RecyclerView issues_recy;
    ImageView expert_img;
    TextView faq_msg, yes_txt, no_txt;
    LinearLayout faq_lin_lay, helpful_lin_lay, yes_no_lay;
    String yes_no ="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ask_expert, container, false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = getActivity();
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        faq_lin_lay = view.findViewById(R.id.faq_lin_lay);
        helpful_lin_lay = view.findViewById(R.id.helpful_lin_lay);
        yes_no_lay = view.findViewById(R.id.yes_no_lay);
        yes_txt = view.findViewById(R.id.yes_txt);
        yes_txt.setOnClickListener(this);
        no_txt = view.findViewById(R.id.no_txt);
        no_txt.setOnClickListener(this);
        issues_recy = view.findViewById(R.id.issues_recy);
        expert_img = view.findViewById(R.id.expert_img);
        expert_img.setOnClickListener(this);
        faq_msg = view.findViewById(R.id.faq_msg);
        faq_msg.setOnClickListener(this);
        SpannableString ss = new SpannableString(getString(R.string.please_check_out_the_faq_section_to_know_more_about_diet_and_nutrition));

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(context, FAQActivity.class);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };


        ss.setSpan(clickableSpan, 21, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        faq_msg.setText(ss);
        faq_msg.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == expert_img.getId()){
            Intent intent = new Intent(context, ExpertActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == yes_txt.getId()){
            yes_no = "Yes";
        }
        else if (id == no_txt.getId()){
            yes_no = "No";
        }

    }
}
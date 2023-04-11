package com.example.tooshytoask.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.activity.Expert.ExpertActivity;
import com.example.tooshytoask.activity.FAQ.FAQActivity;
import com.example.tooshytoask.activity.Feedback.FeedbackActivity;
import com.example.tooshytoask.activity.InformationStoreHouse.InformationStorehouseActivity;
import com.example.tooshytoask.adapters.ExpertIssuesAdapter;
import com.example.tooshytoask.AuthModels.AskIssuesAuthModel;
import com.example.tooshytoask.AuthModels.AskIssuesFeedbackAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AskExpert.AskIssuesResponse;
import com.example.tooshytoask.Models.AskExpert.data;
import com.example.tooshytoask.Models.AskIssuesFeedbackResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.onStoreHouseClick;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AskExpertFragment extends Fragment implements View.OnClickListener, onStoreHouseClick {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RecyclerView issues_recy;
    ImageView expert_img, like_yes, like_no;
    TextView faq_msg, yes_txt, no_txt, faq_text, hi_msg, yes_no_text, category_msg, ask_expert_activity;
    LinearLayout faq_lin_lay, helpful_lin_lay, yes_no_lay, chatt_lin_lay, issues_titles,
            welcome_title_lay, thanku_lay, ask_expert_no_lay;
    RelativeLayout yes_no_reply_msg, rel_issues_recy;
    NestedScrollView expert_scroll_view;
    String yes_no ="", title_id = "", title ="";
    ArrayList<data>data;
    ExpertIssuesAdapter expertIssuesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ask_expert, container, false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = getActivity();
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        ask_expert_activity = view.findViewById(R.id.ask_expert_activity);
        ask_expert_activity.setOnClickListener(this);
        like_no = view.findViewById(R.id.like_no);
        like_no.setOnClickListener(this);
        like_yes = view.findViewById(R.id.like_yes);
        like_yes.setOnClickListener(this);
        ask_expert_no_lay = view.findViewById(R.id.ask_expert_no_lay);
        thanku_lay = view.findViewById(R.id.thanku_lay);
        expert_scroll_view = view.findViewById(R.id.expert_scroll_view);
        welcome_title_lay = view.findViewById(R.id.welcome_title_lay);
        issues_titles = view.findViewById(R.id.issues_titles);
        category_msg = view.findViewById(R.id.category_msg);
        chatt_lin_lay = view.findViewById(R.id.chatt_lin_lay);
        yes_no_reply_msg = view.findViewById(R.id.yes_no_reply_msg);
        rel_issues_recy = view.findViewById(R.id.rel_issues_recy);
        faq_lin_lay = view.findViewById(R.id.faq_lin_lay);
        helpful_lin_lay = view.findViewById(R.id.helpful_lin_lay);
        yes_no_lay = view.findViewById(R.id.yes_no_lay);
        yes_no_text = view.findViewById(R.id.yes_no_text);
        yes_txt = view.findViewById(R.id.yes_txt);
        yes_txt.setOnClickListener(this);
        no_txt = view.findViewById(R.id.no_txt);
        no_txt.setOnClickListener(this);
        expert_img = view.findViewById(R.id.expert_img);
        expert_img.setOnClickListener(this);
        faq_text = view.findViewById(R.id.faq_text);
        faq_text.setOnClickListener(this);
        faq_msg = view.findViewById(R.id.faq_msg);
        faq_msg.setOnClickListener(this);

        data = new ArrayList<>();

        hi_msg = view.findViewById(R.id.hi_msg);
        hi_msg.setText("Hi "+ spManager.getFirstName() +","+" Welcome to TSTA Chat Support");

        issues_recy = view.findViewById(R.id.issues_recy);
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.VERTICAL);
        issues_recy.setLayoutManager(lm);


        getAskIssues();
        return view;
    }

    public void getAskIssues() {
        dialog.show("");
        expert_scroll_view.setVisibility(View.GONE);

        AskIssuesAuthModel model = new AskIssuesAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getAskIssues(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AskIssuesResponse>() {
                    @Override
                    public void onNext(AskIssuesResponse askIssuesResponse) {
                        String msg = askIssuesResponse.getMsg();

                        if (msg.equals("success")) {
                            data = askIssuesResponse.getData();

                            expertIssuesAdapter = new ExpertIssuesAdapter(context, data, AskExpertFragment.this);
                            issues_recy.setAdapter(expertIssuesAdapter);
                        }
                        dialog.dismiss("");
                        expert_scroll_view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAskIssuesFeedback(){

        AskIssuesFeedbackAuthModel model = new AskIssuesFeedbackAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setStatus(yes_no);

        WebServiceModel.getRestApi().getAskIssuesFeedback(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AskIssuesFeedbackResponse>() {
                    @Override
                    public void onNext(AskIssuesFeedbackResponse askIssuesFeedbackResponse) {
                        String msg = askIssuesFeedbackResponse.getMsg();

                        if (msg.equals("success")){

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

        if (id == expert_img.getId()){
            Intent intent = new Intent(context, ExpertActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == yes_txt.getId()){
            yes_no = "Yes";
            yes_no_reply_msg.setVisibility(View.VISIBLE);
            yes_no_text.setText("Yes");
            ask_expert_no_lay.setVisibility(View.GONE);
            thanku_lay.setVisibility(View.VISIBLE);
            getAskIssuesFeedback();
        }
        else if (id == no_txt.getId()){
            yes_no = "No";
            yes_no_reply_msg.setVisibility(View.VISIBLE);
            yes_no_text.setText("No");
            thanku_lay.setVisibility(View.GONE);
            ask_expert_no_lay.setVisibility(View.VISIBLE);
            getAskIssuesFeedback();
        }
        else if (id == faq_text.getId()){
            Intent intent = new Intent(context, FAQActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == ask_expert_activity.getId()){
            Intent intent = new Intent(context, ExpertActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        else if (id == like_yes.getId()){
            Intent intent = new Intent(context, FeedbackActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        else if (id == like_no.getId()){
            Intent intent = new Intent(context, FeedbackActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void OnStoreHouseButtonClick(int position, String id, String text) {
        title_id = id;
        title = text;

        StorehouseMessage(position);
        TextClickable(position);
    }

    public void StorehouseMessage(int position){
        title = data.get(position).getTitle();
        category_msg.setText("I have questions related to " + title);
        chatt_lin_lay.setVisibility(View.VISIBLE);
        faq_lin_lay.setVisibility(View.VISIBLE);
        rel_issues_recy.setVisibility(View.GONE);
        issues_titles.setVisibility(View.GONE);
        welcome_title_lay.setVisibility(View.GONE);
    }

    public void TextClickable(int position){
        SpannableString ss = new SpannableString("Please check out the Information Storehouse section to know more about " + title);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Bundle bundle = new Bundle();

                bundle.putString("title_id",data.get(position).getTitle_id());
                Intent intent = new Intent(context, InformationStorehouseActivity.class);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                yes_no_lay.setVisibility(View.VISIBLE);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };


        ss.setSpan(clickableSpan, 21, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        faq_msg.setText(ss);
        faq_msg.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
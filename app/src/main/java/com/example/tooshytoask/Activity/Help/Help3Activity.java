package com.example.tooshytoask.Activity.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.Help2Adapter;
import com.example.tooshytoask.Adapters.HelpContentDiscAdapter;
import com.example.tooshytoask.AuthModels.HelpContentAuthModel;
import com.example.tooshytoask.AuthModels.HelpFeedbackAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.Help.HelpContentResponse;
import com.example.tooshytoask.Models.Help.HelpFeedbackResponse;
import com.example.tooshytoask.Models.Help.helpcontent;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Help3Activity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RelativeLayout rel_back;
    Button yes, no, submit, contact_us;
    RecyclerView recy_help_desc;
    ArrayList<com.example.tooshytoask.Models.Help.helpcontent> helpcontent;
    HelpContentDiscAdapter helpContentDiscAdapter;
    String helpcontent_id = "", feedback = "", feedbacktype = "", category_id = "";
    TextView txt_title, topic_title, description, thanku_msg;
    CardView card_parent_subtitle;
    LinearLayout lin_help_lay;
    TextInputEditText edit_description;
    RadioButton radio_btn, radio_btn2, radio_btn3, radio_btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help3);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = Help3Activity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        Intent intent = getIntent();
        if (intent != null) {

            category_id = intent.getStringExtra("helpcontent_catid");

        }

        Intent intent1 = getIntent();
        if (intent1 != null) {

            helpcontent_id = intent1.getStringExtra("helpcontent_id");

        }

        edit_description = findViewById(R.id.edit_description);
        radio_btn = findViewById(R.id.radio_btn);
        radio_btn2 = findViewById(R.id.radio_btn2);
        radio_btn3 = findViewById(R.id.radio_btn3);
        radio_btn4 = findViewById(R.id.radio_btn4);
        lin_help_lay = findViewById(R.id.lin_help_lay);
        card_parent_subtitle = findViewById(R.id.card_parent_subtitle);
        card_parent_subtitle.setOnClickListener(this);
        contact_us = findViewById(R.id.contact_us);
        contact_us.setOnClickListener(this);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        thanku_msg = findViewById(R.id.thanku_msg);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
        yes = findViewById(R.id.yes);
        yes.setOnClickListener(this);
        no = findViewById(R.id.no);
        no.setOnClickListener(this);
        txt_title = findViewById(R.id.txt_title);
        topic_title = findViewById(R.id.topic_title);
        description = findViewById(R.id.description);
        recy_help_desc = findViewById(R.id.recy_help_desc);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recy_help_desc.setLayoutManager(linearLayoutManager);
        getHelpContent();
    }

    public void getHelpContent(){
        dialog.show("");

        HelpContentAuthModel model = new HelpContentAuthModel();
        model.setHelpcontent_id(helpcontent_id);

        WebServiceModel.getRestApi().getHelpContent(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HelpContentResponse>() {
                    @Override
                    public void onNext(HelpContentResponse helpContentResponse) {
                        String msg = helpContentResponse.getMsg();
                        if (msg.equals("success")) {
                            helpcontent = helpContentResponse.getHelpcontent();

                            if (helpcontent != null){
                                helpContentDiscAdapter = new HelpContentDiscAdapter(helpcontent, context);
                                recy_help_desc.setAdapter(helpContentDiscAdapter);
                            }//topic_title, description
                            txt_title.setText(helpContentResponse.getTitle());
                            topic_title.setText(helpContentResponse.getHelpcontent_title());
                            description.setText(helpContentResponse.getHelpcontent_desc());
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

    public void radioButtobClick(View view){
        boolean isChecked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radio_btn:
                if(isChecked){
                    feedbacktype = "1";
                    radio_btn.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    radio_btn2.setTextColor(ContextCompat.getColor(context, R.color.black));
                    radio_btn3.setTextColor(ContextCompat.getColor(context, R.color.black));
                    radio_btn4.setTextColor(ContextCompat.getColor(context, R.color.black));
                }
                break;
            case R.id.radio_btn2:
                if(isChecked){
                    feedbacktype = "2";
                    radio_btn2.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    radio_btn.setTextColor(ContextCompat.getColor(context, R.color.black));
                    radio_btn3.setTextColor(ContextCompat.getColor(context, R.color.black));
                    radio_btn4.setTextColor(ContextCompat.getColor(context, R.color.black));
                }
                break;
            case R.id.radio_btn3:
                if(isChecked){
                    feedbacktype = "3";
                    radio_btn3.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    radio_btn2.setTextColor(ContextCompat.getColor(context, R.color.black));
                    radio_btn.setTextColor(ContextCompat.getColor(context, R.color.black));
                    radio_btn4.setTextColor(ContextCompat.getColor(context, R.color.black));
                }
                break;
            case R.id.radio_btn4:
                if(isChecked){
                    feedbacktype = "4";
                    radio_btn4.setTextColor(ContextCompat.getColor(context, R.color.purple));
                    radio_btn2.setTextColor(ContextCompat.getColor(context, R.color.black));
                    radio_btn3.setTextColor(ContextCompat.getColor(context, R.color.black));
                    radio_btn.setTextColor(ContextCompat.getColor(context, R.color.black));
                }
        }
    }

    public void getHelpFeedback(){
        dialog.show("");

        HelpFeedbackAuthModel model = new HelpFeedbackAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setHelpcontent_id(helpcontent_id);
        model.setHelpcontent_catid(category_id);
        model.setFeedback(feedback);
        model.setFeedbacktype(feedbacktype);
        model.setFeedbackreason(edit_description.getText().toString().trim());

        WebServiceModel.getRestApi().getHelpFeedback(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HelpFeedbackResponse>() {

                    @Override
                    public void onNext(HelpFeedbackResponse helpFeedbackResponse) {
                        String msg = helpFeedbackResponse.getMsg();

                        if (msg.equals("success")){

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

        if (id == contact_us.getId()){
            Intent intent = new Intent(context, ContactUsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else if (id == rel_back.getId()){
            Intent intent = new Intent(context, HelpActivity2.class);
            intent.putExtra("category_id",category_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == yes.getId()){
            feedback = "Yes";
            thanku_msg.setVisibility(View.VISIBLE);
            lin_help_lay.setVisibility(View.GONE);
            yes.setBackgroundResource(R.drawable.gender_border_active);
            yes.setTextColor(ContextCompat.getColor(context, R.color.white));
            no.setBackgroundResource(R.drawable.gender_border_inactive);
            no.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        else if (id == no.getId()){
            feedback = "No";
            lin_help_lay.setVisibility(View.VISIBLE);
            thanku_msg.setVisibility(View.GONE);
            no.setBackgroundResource(R.drawable.gender_border_active);
            no.setTextColor(ContextCompat.getColor(context, R.color.white));
            yes.setBackgroundResource(R.drawable.gender_border_inactive);
            yes.setTextColor(ContextCompat.getColor(context, R.color.black));

        }
        else if (id == card_parent_subtitle.getId()){
            Intent intent = new Intent(context, HelpActivity2.class);
            intent.putExtra("category_id",category_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else if (id == submit.getId()) {

            if (feedbacktype.equals("")) {
                Toast.makeText(context, "Select One Options", Toast.LENGTH_SHORT).show();
            }
            else {
                getHelpFeedback();
                lin_help_lay.setVisibility(View.GONE);
                thanku_msg.setVisibility(View.GONE);
                Toast.makeText(context, "Thank you for reaching out and providing us with valuable feedback.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
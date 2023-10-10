package com.neuronimbus.metropolis.activity.Feedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.ProcessingFeedbackChatAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.FeedbackChating;
import com.neuronimbus.metropolis.Models.HelpArticleFeedbackChating;
import com.neuronimbus.metropolis.Models.OldFeedbackChattingResponse;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.activity.Help.ContactUsActivity;
import com.neuronimbus.metropolis.adapters.OldChattingFeedbackAdapter;
import com.neuronimbus.metropolis.adapters.OldChattingFeedbackAdapterSecond;
import com.neuronimbus.metropolis.adapters.StatusAdapter;
import com.neuronimbus.metropolis.databinding.ActivityOldFeedbackChattingBinding;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class OldFeedbackChattingActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivityOldFeedbackChattingBinding binding;
    OldChattingFeedbackAdapter adapter;
    OldChattingFeedbackAdapterSecond adapterSecond;
    ArrayList<FeedbackChating> feedbackChating;
    ArrayList<com.neuronimbus.metropolis.Models.HelpArticleFeedbackChating>helpArticleFeedbackChating;
    String feedback_id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityOldFeedbackChattingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = OldFeedbackChattingActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        feedbackChating = new ArrayList<>();
        helpArticleFeedbackChating = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.feedbackExpertChattingRecy.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.feedbackChattingRecy.setLayoutManager(linearLayoutManager1);

        feedback_id = getIntent().getStringExtra("feedback_id");

        getOldFeedback();
        onClick();
    }

    private void onClick() {
        binding.relBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactUsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void getOldFeedback(){
        dialog.show();
        binding.oldFeedbackLay.setVisibility(View.GONE);

        ProcessingFeedbackChatAuthModel model = new ProcessingFeedbackChatAuthModel();
//        model.setUser_id("25738");
//        model.setFeedback_id("191");
        model.setUser_id(spManager.getUserId());
        model.setFeedback_id(feedback_id);

        WebServiceModel.getRestApi().getOldFeedback(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<OldFeedbackChattingResponse>() {
                    @Override
                    public void onNext(OldFeedbackChattingResponse oldFeedbackChattingResponse) {
                        String msg = oldFeedbackChattingResponse.getMsg();
                        dialog.dismiss();
                        binding.oldFeedbackLay.setVisibility(View.VISIBLE);
                        if (msg.equals("success")){
                            if (oldFeedbackChattingResponse != null) {
                                feedbackChating = oldFeedbackChattingResponse.getResolvedFeedback().getFeedbackChating();
                                helpArticleFeedbackChating = oldFeedbackChattingResponse.getResolvedFeedback().getHelpArticleFeedbackChating();

                                binding.feedbackType.setText(oldFeedbackChattingResponse.getResolvedFeedback().getAssistanceType());
                                binding.feedbackDate.setText(oldFeedbackChattingResponse.getResolvedFeedback().getFeedbackDate());
                                binding.feedbackMsg.setText(oldFeedbackChattingResponse.getResolvedFeedback().getFeedbackDesc());
                                if (oldFeedbackChattingResponse.getResolvedFeedback().getFeedbackImg() == null
                                        || oldFeedbackChattingResponse.getResolvedFeedback().getFeedbackImg().isEmpty()){
                                    binding.feedbackImage.setVisibility(View.GONE);
                                }
                                else {
                                    binding.feedbackImage.setVisibility(View.VISIBLE);
                                    Glide.with(context).load(oldFeedbackChattingResponse.getResolvedFeedback().getFeedbackImg()).into(binding.feedbackImage);

                                }

                                if (feedbackChating != null ) {
                                    if (feedbackChating.size() != 0){
                                        adapter = new OldChattingFeedbackAdapter(context, feedbackChating);
                                        binding.feedbackExpertChattingRecy.setAdapter(adapter);
                                    }

                                }
                                if (helpArticleFeedbackChating != null) {
                                    if (helpArticleFeedbackChating.size() != 0){
                                        adapterSecond = new OldChattingFeedbackAdapterSecond(context, helpArticleFeedbackChating);
                                        binding.feedbackChattingRecy.setAdapter(adapterSecond);
                                    }
                                }
                            }
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
}
package com.neuronimbus.metropolis.activity.Feedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.AskFeedbackAuthModel;
import com.neuronimbus.metropolis.AuthModels.FeedbackAuthModel;
import com.neuronimbus.metropolis.AuthModels.ProcessingFeedbackChatAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.CommonResponse;
import com.neuronimbus.metropolis.Models.ExpertReplyResponse;
import com.neuronimbus.metropolis.Models.FeedbackResponse;
import com.neuronimbus.metropolis.Models.ProcessingFeedback;
import com.neuronimbus.metropolis.Models.ProcessingFeedbackChatResponse;
import com.neuronimbus.metropolis.Models.chats;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.RecyclerViewOnClickListner;
import com.neuronimbus.metropolis.activity.Landing.SignInActivity;
import com.neuronimbus.metropolis.adapters.ChattingAdapter;
import com.neuronimbus.metropolis.adapters.FeedbackChattingAdapter;
import com.neuronimbus.metropolis.databinding.ActivityFeedbackListBinding;
import com.neuronimbus.metropolis.databinding.ActivityRecentFeedbackChattingBinding;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RecentFeedbackChattingActivity extends AppCompatActivity implements RecyclerViewOnClickListner {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivityRecentFeedbackChattingBinding binding;
    FeedbackChattingAdapter adapter;
    ArrayList<com.neuronimbus.metropolis.Models.ProcessingFeedback> processingFeedback;
    ArrayList<com.neuronimbus.metropolis.Models.ProcessingFeedback> processingChat;
    String refresh_status = "open", chat_ref_status = "no", feedback_id ="",
            mainFeedback_Id,assistanceType,feedbackReplyIdMain,feedbackIdType, reply;
    int chatSize, select_chat_size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityRecentFeedbackChattingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = RecentFeedbackChattingActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.feedbackChattingRecy.setLayoutManager(linearLayoutManager);

        processingFeedback = new ArrayList<>();

        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.VERTICAL);
        binding.feedbackChattingRecy.setLayoutManager(lm);
        binding.feedbackChattingRecy.getLayoutManager().scrollToPosition(processingFeedback.size() - 1);

        feedback_id = getIntent().getStringExtra("feedback_id");

        binding.swipeRefresh.setColorSchemeResources(R.color.purple);
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getChatting("swipe");
            }
        });

        binding.relBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getChatting(String refresh){

        ProcessingFeedbackChatAuthModel model = new ProcessingFeedbackChatAuthModel();
//        model.setUser_id("25738");
//        model.setFeedback_id("209");
        model.setUser_id(spManager.getUserId());
        model.setFeedback_id(feedback_id);

        if (refresh.equals("w_swipe")) {
            dialog.show("");
        }

        WebServiceModel.getRestApi().getProcessingFeedback(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ProcessingFeedbackChatResponse>() {
                    @Override
                    public void onNext(ProcessingFeedbackChatResponse processingFeedbackChatResponse) {

                        if (refresh.equals("w_swipe")) {
                            dialog.dismiss("");
                        } else {
                            binding.swipeRefresh.setRefreshing(false);
                        }

                        String msg = processingFeedbackChatResponse.getMsg();


                        if (msg.equals("success")){
                            processingFeedback = processingFeedbackChatResponse.getProcessingFeedback();

                            if (processingFeedback.size() != 0 || !processingFeedback.isEmpty()){
                                binding.swipeRefresh.setVisibility(View.VISIBLE);
                                adapter = new FeedbackChattingAdapter(context, processingFeedback, RecentFeedbackChattingActivity.this);
                                binding.feedbackChattingRecy.setAdapter(adapter);
                                binding.feedbackChattingRecy.getLayoutManager().smoothScrollToPosition(binding.feedbackChattingRecy, null, adapter.getItemCount() - 1);
                            }
                            if (processingFeedback.size() == 0 && processingFeedback.isEmpty()){
                                binding.feedbackChattingRecy.setVisibility(View.GONE);
                            }

                            if (chatSize != select_chat_size) {
                                select_chat_size = chatSize;
                                processingFeedback = processingChat;


                                if(chat_ref_status.equals("no")) {
                                    adapter = new FeedbackChattingAdapter(context, processingFeedback, RecentFeedbackChattingActivity.this);
                                    binding.feedbackChattingRecy.setAdapter(adapter);
                                    binding.feedbackChattingRecy.getLayoutManager().smoothScrollToPosition(binding.feedbackChattingRecy, null, adapter.getItemCount() - 1);
                                }
                                else {
                                    chat_ref_status="no";
                                }
                            }
                        }
                        else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh_status = "open";
        getChatting("w_swipe");
        chat_ref_status = "no";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refresh_status = "close";
    }

    @Override
    protected void onPause() {
        super.onPause();
        refresh_status = "close";
        //Toast.makeText(context,"onPause",Toast.LENGTH_SHORT).show();
    }

    public void askFeedback(){
        dialog.show("");

        AskFeedbackAuthModel model = new AskFeedbackAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setFeedback_id(feedback_id);
        model.setAssistance_type(assistanceType);
        model.setFeedback_reply_id_main(feedbackReplyIdMain);
        model.setFeedbackid_type(feedbackIdType);
        model.setMainfeedbackId(mainFeedback_Id);
        model.setReply(reply);

        WebServiceModel.getRestApi().askFeedback(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CommonResponse>() {
                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        String msg = commonResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){
                            getChatting("swipe");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void userReplyPopup() {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.expert_reply_popup);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.logout_popup));

        TextInputEditText userReply = dialog.findViewById(R.id.userReply);
        Button btnSubmit = dialog.findViewById(R.id.btnSubmit);
        RelativeLayout back_arrow = dialog.findViewById(R.id.back_arrow);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reply = userReply.getText().toString().trim();
                askFeedback();
                dialog.dismiss();

            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void onClickData(int position, String mainfeedbackId, String assistance_type, String feedback_reply_id_main,
                            String feedbackid_type) {
        userReplyPopup();
        mainFeedback_Id = mainfeedbackId;
        assistanceType = assistance_type;
        feedbackReplyIdMain = feedback_reply_id_main;
        feedbackIdType = feedbackid_type;
    }
}
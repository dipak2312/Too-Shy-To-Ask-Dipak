package com.neuronimbus.metropolis.activity.Feedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.FeedbackListAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.FeedbackListResponse;
import com.neuronimbus.metropolis.Models.OldestFeedback;
import com.neuronimbus.metropolis.Models.RecentFeedback;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.activity.Complaint.ComplaintActivity;
import com.neuronimbus.metropolis.adapters.Help2Adapter;
import com.neuronimbus.metropolis.adapters.OldFeedbackListAdapter;
import com.neuronimbus.metropolis.adapters.RecentFeedbackListAdapter;
import com.neuronimbus.metropolis.databinding.ActivityComplaintBinding;
import com.neuronimbus.metropolis.databinding.ActivityFeedbackListBinding;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FeedbackListActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivityFeedbackListBinding binding;
    RecentFeedbackListAdapter recentFeedbackListAdapter;
    OldFeedbackListAdapter oldFeedbackListAdapter;
    ArrayList<com.neuronimbus.metropolis.Models.RecentFeedback> recentFeedback;
    ArrayList<com.neuronimbus.metropolis.Models.OldestFeedback> oldestFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityFeedbackListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = FeedbackListActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.recentFeedbackRecy.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.oldFeedbackRecy.setLayoutManager(linearManager);

        binding.btnFeedbackList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Bundle bundle = new Bundle();

                    bundle.putString("settingActivity","settingActivity");
                    Intent intent = new Intent(context, NewFeedbackActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
            }
        });

        binding.relBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getFeedbackList();
    }

    private void getFeedbackList(){
        dialog.show("");
        binding.rootLay.setVisibility(View.GONE);

        FeedbackListAuthModel model = new FeedbackListAuthModel();
        model.setUser_id(spManager.getUserId());
        //model.setUser_id("23");

        WebServiceModel.getRestApi().getFeedbackList(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<FeedbackListResponse>() {
                    @Override
                    public void onNext(FeedbackListResponse feedbackListResponse) {
                        String msg = feedbackListResponse.getMsg();
                        if (msg.equals("success")){
                            recentFeedback = feedbackListResponse.getRecentFeedback();
                            oldestFeedback = feedbackListResponse.getOldestFeedback();

                            if (oldestFeedback.size() == 0 && recentFeedback.size() == 0) {
                                Bundle bundle = new Bundle();

                                bundle.putString("settingActivity","settingActivity");
                                Intent intent = new Intent(context, NewFeedbackActivity.class);
                                intent.putExtras(bundle);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else {

                                binding.rootLay.setVisibility(View.VISIBLE);

                                if (recentFeedback != null) {
                                    binding.newFeedbackLaay.setVisibility(View.VISIBLE);
                                    binding.view2.setVisibility(View.VISIBLE);
                                    recentFeedbackListAdapter = new RecentFeedbackListAdapter(recentFeedback, context);
                                    binding.recentFeedbackRecy.setAdapter(recentFeedbackListAdapter);

                                }

                                if (recentFeedback.size() == 0) {
                                    binding.newFeedbackLaay.setVisibility(View.GONE);
                                    binding.view2.setVisibility(View.GONE);
                                }

                                if (oldestFeedback.size() == 0) {
                                    binding.oldFeedbackLaay.setVisibility(View.GONE);
                                }

                                if (oldestFeedback != null) {
                                    binding.oldFeedbackLaay.setVisibility(View.VISIBLE);
                                    oldFeedbackListAdapter = new OldFeedbackListAdapter(oldestFeedback, context);
                                    binding.oldFeedbackRecy.setAdapter(oldFeedbackListAdapter);

                                }
                            }

                        }
                        dialog.dismiss("");

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
}
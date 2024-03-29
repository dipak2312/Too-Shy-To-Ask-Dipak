package com.neuronimbus.metropolis.activity.Complaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.CommonAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.ComplaintHistoryChat;
import com.neuronimbus.metropolis.Models.ComplaintListResponse;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.adapters.ComplaintListAdapter;
import com.neuronimbus.metropolis.databinding.ActivityComplaintListBinding;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ComplaintListActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivityComplaintListBinding binding;
    ArrayList<ComplaintHistoryChat> complaintHistoryChat;
    ComplaintListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityComplaintListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = ComplaintListActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        onClick();
        getControl();

    }

    private void onClick() {
        binding.relBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ComplaintActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(context, ComplaintActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void getControl(){
        complaintHistoryChat = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.recyComplaintList.setLayoutManager(linearLayoutManager);
        getComplaintList();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    private void getComplaintList(){
        dialog.show("");

        CommonAuthModel model = new CommonAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getComplaintList(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ComplaintListResponse>() {
                    @Override
                    public void onNext(ComplaintListResponse complaintListResponse) {
                        String msg = complaintListResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){
                            complaintHistoryChat = complaintListResponse.getComplaintHistoryChat();

                            adapter = new ComplaintListAdapter(context, complaintHistoryChat);
                            binding.recyComplaintList.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss("");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
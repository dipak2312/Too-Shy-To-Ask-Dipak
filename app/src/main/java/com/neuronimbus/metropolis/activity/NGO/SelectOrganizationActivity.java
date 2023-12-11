package com.neuronimbus.metropolis.activity.NGO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.CommonAuthModel;
import com.neuronimbus.metropolis.AuthModels.HealthIssueModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.HealthIssueResponse;
import com.neuronimbus.metropolis.Models.ProjectList;
import com.neuronimbus.metropolis.Models.SelectOrganisationResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.OnClickListner;
import com.neuronimbus.metropolis.activity.Complaint.ComplaintActivity;
import com.neuronimbus.metropolis.adapters.HealthAdapter;
import com.neuronimbus.metropolis.adapters.SelectOrganisationAdapter;
import com.neuronimbus.metropolis.databinding.ActivitySelectOrganizationBinding;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SelectOrganizationActivity extends AppCompatActivity implements OnClickListner {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivitySelectOrganizationBinding binding;
    SelectOrganisationAdapter adapter;
    ArrayList<String> selectedOrgIds=new ArrayList<>();
    ArrayList<com.neuronimbus.metropolis.Models.ProjectList> projectList;
    String projectStatus="", projectId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivitySelectOrganizationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        onClick();
        getController();
    }
    private void onClick() {

        binding.yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.selectOrgRelLay.setVisibility(View.VISIBLE);
                for(int i=0;i<projectList.size();i++)
                {
                    projectList.get(i).isSelected=false;
                    adapter.notifyDataSetChanged();
                }
                binding.yesBtn.setBackgroundResource(R.drawable.gender_border_active);
                binding.yesBtn.setTextColor(ContextCompat.getColor(context, R.color.white));
                binding.noBtn.setBackgroundResource(R.drawable.gender_border_inactive);
                binding.noBtn.setTextColor(ContextCompat.getColor(context, R.color.black));
            }
        });

        binding.noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.selectOrgRelLay.setVisibility(View.GONE);
                binding.noBtn.setBackgroundResource(R.drawable.gender_border_active);
                binding.noBtn.setTextColor(ContextCompat.getColor(context, R.color.white));
                binding.yesBtn.setBackgroundResource(R.drawable.gender_border_inactive);
                binding.yesBtn.setTextColor(ContextCompat.getColor(context, R.color.black));
            }
        });
    }
    private void getController() {
        context = SelectOrganizationActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.recySelectOrganization.setLayoutManager(linearLayoutManager);

        binding.relBack.setVisibility(View.GONE);

        selectOrganisation();
    }

    public void selectOrganisation() {
        dialog.show("");

        CommonAuthModel model = new CommonAuthModel();
        model.setUser_id("25797");


        WebServiceModel.getRestApi().ngoRegister(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SelectOrganisationResponse>() {
                    @Override
                    public void onNext(SelectOrganisationResponse selectOrganisationResponse) {

                        String msg = selectOrganisationResponse.getMsg();

                        if (msg.equals("success")) {

                            projectList = selectOrganisationResponse.getProjectList();

                            if (projectStatus == null){
                                binding.selectOrgRelLay.setVisibility(View.GONE);
                                binding.yesBtn.setBackgroundResource(R.drawable.gender_border_inactive);
                                binding.yesBtn.setTextColor(ContextCompat.getColor(context, R.color.black));
                                binding.noBtn.setBackgroundResource(R.drawable.gender_border_inactive);
                                binding.noBtn.setTextColor(ContextCompat.getColor(context, R.color.black));
                            }

                            else if (projectStatus.equals("Yes")){
                                binding.selectOrgRelLay.setVisibility(View.VISIBLE);
                                binding.yesBtn.setBackgroundResource(R.drawable.gender_border_active);
                                binding.yesBtn.setTextColor(ContextCompat.getColor(context, R.color.white));
                            }
                            else if (projectStatus.equals("No")){
                                binding.selectOrgRelLay.setVisibility(View.GONE);
                                binding.noBtn.setBackgroundResource(R.drawable.gender_border_active);
                                binding.noBtn.setTextColor(ContextCompat.getColor(context, R.color.white));
                            }

                            if (projectList != null) {
                                CallAdapter();
                            }

                        } else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                        dialog.dismiss("");

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void CallAdapter(){
        adapter = new SelectOrganisationAdapter(context,this, projectList);
        binding.recySelectOrganization.setAdapter(adapter);

    }
    @Override
    public void onClickData(int position, String id) {
        projectId = id;
        if(projectList.get(position).isSelected)
        {
            selectedOrgIds.add(projectId);
        }else
        {
            selectedOrgIds.add(projectId);
        }
    }
}
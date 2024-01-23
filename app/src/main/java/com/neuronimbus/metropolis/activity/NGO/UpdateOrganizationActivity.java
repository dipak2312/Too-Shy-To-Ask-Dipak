package com.neuronimbus.metropolis.activity.NGO;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.CommonAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.CommonResponse;
import com.neuronimbus.metropolis.Models.SelectOrganisationResponse;
import com.neuronimbus.metropolis.Models.UpdateNGOProfile;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.ClickListener;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.Utils.OnClickListner;
import com.neuronimbus.metropolis.adapters.SelectOrganisationAdapter;
import com.neuronimbus.metropolis.databinding.ActivityUpdateOrganizationBinding;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class UpdateOrganizationActivity extends AppCompatActivity implements OnClickListner, ClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ClickListener clickListener;
    ActivityUpdateOrganizationBinding binding;
    SelectOrganisationAdapter adapter;
    ArrayList<String> selectedOrgIds=new ArrayList<>();
    ArrayList<com.neuronimbus.metropolis.Models.ProjectList> projectList;
    String projectStatus="", projectId="", no_health_issues="";
    boolean isSelected= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityUpdateOrganizationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        onClick();
        getController();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    private void onClick() {

        binding.relBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.selectOrgRelLay.setVisibility(View.VISIBLE);
                no_health_issues ="yes";
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
                no_health_issues ="no";
                binding.selectOrgRelLay.setVisibility(View.GONE);
                binding.otherBtn.setBackgroundResource(R.drawable.health_inactive);
                binding.otherProjectName.setVisibility(View.GONE);
                binding.otherBtn.setTextColor(ContextCompat.getColor(context, R.color.black));
                isSelected = false;
                binding.noBtn.setBackgroundResource(R.drawable.gender_border_active);
                binding.noBtn.setTextColor(ContextCompat.getColor(context, R.color.white));
                binding.yesBtn.setBackgroundResource(R.drawable.gender_border_inactive);
                binding.yesBtn.setTextColor(ContextCompat.getColor(context, R.color.black));
            }
        });

        binding.updateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNGOProfile();

            }
        });
        binding.otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected) {
                    binding.otherBtn.setBackgroundResource(R.drawable.health_active);
                    binding.otherBtn.setTextColor(ContextCompat.getColor(context, R.color.white));
                    isSelected = false;
                    binding.otherProjectName.setVisibility(View.VISIBLE);

                }else {
                    binding.otherBtn.setBackgroundResource(R.drawable.health_inactive);
                    binding.otherBtn.setTextColor(ContextCompat.getColor(context, R.color.black));
                    isSelected = true;
                    binding.otherProjectName.setVisibility(View.GONE);
                }

            }
        });
    }
    private void getController() {
        binding.otherProjectName.setVisibility(View.GONE);
        context = UpdateOrganizationActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        clickListener=(ClickListener)context;
        clickListener.onClick(false);
        binding.selectOrgRelLay.setVisibility(View.GONE);
        binding.editProjectName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Called to notify you that somewhere within `charSequence`, the characters in the range [start, start + before) are about to be replaced with new text with a length of `count`.
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Called to notify you that somewhere within `charSequence`, the characters in the range [start, start + before) have been replaced with new text with a length of `count`.
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newText = editable.toString();

            }
        });

        selectOrganisation();
    }

    public void selectOrganisation() {
        dialog.show("");

        CommonAuthModel model = new CommonAuthModel();
        model.setUser_id(spManager.getUserId());


        WebServiceModel.getRestApi().ngoRegister(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SelectOrganisationResponse>() {
                    @Override
                    public void onNext(SelectOrganisationResponse selectOrganisationResponse) {

                        String msg = selectOrganisationResponse.getMsg();

                        if (msg.equals("success")) {

                            projectList = selectOrganisationResponse.getProjectList();
                            projectStatus = selectOrganisationResponse.getIsMetropolisPartner();

                            if (projectStatus == null){
                                binding.selectOrgRelLay.setVisibility(View.GONE);
                                binding.yesBtn.setBackgroundResource(R.drawable.gender_border_inactive);
                                binding.yesBtn.setTextColor(ContextCompat.getColor(context, R.color.black));
                                binding.noBtn.setBackgroundResource(R.drawable.gender_border_inactive);
                                binding.noBtn.setTextColor(ContextCompat.getColor(context, R.color.black));
                            }

                            else if (projectStatus.equals("Yes")){
                                clickListener.onClick(true);
                                no_health_issues ="yes";
                                binding.selectOrgRelLay.setVisibility(View.VISIBLE);
                                binding.yesBtn.setBackgroundResource(R.drawable.gender_border_active);
                                binding.yesBtn.setTextColor(ContextCompat.getColor(context, R.color.white));
                            }
                            else if (projectStatus.equals("No")){
                                clickListener.onClick(true);
                                no_health_issues ="no";
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

    public void updateNGOProfile(){
        dialog.show("");

        UpdateNGOProfile model = new UpdateNGOProfile();
        model.setUser_id(spManager.getUserId());
        model.setNgo_project_name(selectedOrgIds);
        model.setAction("ngoproject");

        WebServiceModel.getRestApi().updateNGOProfile(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CommonResponse>() {
                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        String msg = commonResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("Profile Updated")){

                            finish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss("");
                        Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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

    @Override
    public void onClick(Boolean status) {

    }
}
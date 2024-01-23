package com.neuronimbus.metropolis.activity.NGO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.CommonAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.CommonResponse;
import com.neuronimbus.metropolis.Models.NGOProfileResponse;
import com.neuronimbus.metropolis.Models.UpdateNGOProfile;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.Utils.MyValidator;
import com.neuronimbus.metropolis.activity.Setting.Setting.UpdateAvatarActivity;
import com.neuronimbus.metropolis.databinding.ActivityNgoProfileUpdateBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NgoProfileUpdateActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivityNgoProfileUpdateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String savedLanguage = LocaleHelper.getLanguage(this);
//        LocaleHelper.setLocale(this, savedLanguage);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityNgoProfileUpdateBinding.inflate(getLayoutInflater());
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

        binding.changeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateAvatarActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        binding.updateOrganisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateOrganizationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editName.getText().toString().trim().equals("")){
                    Toast.makeText(context, "NGO Name is required", Toast.LENGTH_SHORT).show();
                }
                else if (!MyValidator.isValidName(binding.editName.getText().toString().trim())) {
                    Toast.makeText(context, "Please enter valid name", Toast.LENGTH_SHORT).show();
                }
                else if (binding.registrationNumber.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Registration Number is required", Toast.LENGTH_SHORT).show();
                }
                else if (!MyValidator.isValidAlphaNumber(binding.registrationNumber.getText().toString().trim())) {
                    Toast.makeText(context, "Please enter valid Registration Number", Toast.LENGTH_SHORT).show();
                }
                else if (binding.editEmailEnter.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Email id is required", Toast.LENGTH_SHORT).show();
                }
                else if (!MyValidator.isValidEmail(binding.editEmailEnter.getText().toString().trim())) {
                    Toast.makeText(context, "Please enter valid email id", Toast.LENGTH_SHORT).show();
                }
                else if (binding.editMobileNumber.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Mobile Number is required", Toast.LENGTH_SHORT).show();
                }
                else if (binding.majorActivity.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Please enter major activities", Toast.LENGTH_SHORT).show();
                }
                else if (!MyValidator.isValidAlphaNumber(binding.majorActivity.getText().toString().trim())) {
                    Toast.makeText(context, "Please enter valid major activities", Toast.LENGTH_SHORT).show();
                }
                else if (binding.workExp.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Please enter work experience", Toast.LENGTH_SHORT).show();
                }
                else if (!MyValidator.isValidAlphaNumber(binding.workExp.getText().toString().trim())) {
                    Toast.makeText(context, "Please enter valid work experience", Toast.LENGTH_SHORT).show();
                }
                else if (binding.editCountryEnter.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Please enter your Country", Toast.LENGTH_SHORT).show();
                }
                else if (!MyValidator.isValidName(binding.editCountryEnter.getText().toString().trim())) {
                    Toast.makeText(context, "Please enter valid Country", Toast.LENGTH_SHORT).show();
                }
                else if (binding.editStateEnter.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Please enter your State", Toast.LENGTH_SHORT).show();
                }
                else if (!MyValidator.isValidName(binding.editStateEnter.getText().toString().trim())) {
                    Toast.makeText(context, "Please enter valid state", Toast.LENGTH_SHORT).show();
                }
                else if (binding.editCityEnter.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Please enter your City", Toast.LENGTH_SHORT).show();
                }
                else if (!MyValidator.isValidName(binding.editCityEnter.getText().toString().trim())) {
                    Toast.makeText(context, "Please enter valid city", Toast.LENGTH_SHORT).show();
                }
                else {
                    updateNGOProfile();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNGOProfile();
    }

    private void getController() {
        binding.pageTitle.setText(getString(R.string.update_your_profile));
        context = NgoProfileUpdateActivity.this;
        spManager = new SPManager(context);


        dialog = new CustomProgressDialog(context);
        binding.editMobileNumber.setClickable(false);
        binding.editMobileNumber.setFocusable(false);
        binding.editName.setText(spManager.getNgoName());
        binding.registrationNumber.setText(spManager.getNgoRegistrationNumber());
        binding.editEmailEnter.setText(spManager.getEmail());
        binding.editMobileNumber.setText(spManager.getPhone());
        binding.majorActivity.setText(spManager.getMajorActivities());
        binding.workExp.setText(spManager.getWorkExp());
        binding.editCountryEnter.setText(spManager.getCountry());
        binding.editStateEnter.setText(spManager.getState());
        binding.editCityEnter.setText(spManager.getCity());

    }

    public void updateNGOProfile(){
        dialog.show("");

        UpdateNGOProfile model = new UpdateNGOProfile();
        model.setUser_id(spManager.getUserId());
        model.setNgo_name(binding.editName.getText().toString().trim());
        model.setNgo_registration_no(binding.registrationNumber.getText().toString().trim());
        model.setPhone(binding.editMobileNumber.getText().toString().trim());
        model.setEmail_id(binding.editEmailEnter.getText().toString().trim());
        model.setOrganization_activities(binding.majorActivity.getText().toString().trim());
        model.setWork_experience(binding.workExp.getText().toString().trim());
        model.setCountry(binding.editCountryEnter.getText().toString().trim());
        model.setState(binding.editStateEnter.getText().toString().trim());
        model.setCity(binding.editCityEnter.getText().toString().trim());
        model.setAction("profile");

        WebServiceModel.getRestApi().updateNGOProfile(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CommonResponse>() {
                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        String msg = commonResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("Profile Updated")){
                            spManager.setNgoName(binding.editName.getText().toString().trim());
                            spManager.setNgoRegistrationNumber(binding.registrationNumber.getText().toString().trim());
                            spManager.setEmail(binding.editEmailEnter.getText().toString().trim());
                            spManager.setWorkExp(binding.workExp.getText().toString().trim());
                            spManager.setPhone(binding.editMobileNumber.getText().toString().trim());
                            spManager.setCountry(binding.editCountryEnter.getText().toString().trim());
                            spManager.setState(binding.editStateEnter.getText().toString().trim());
                            spManager.setCity(binding.editCityEnter.getText().toString().trim());
                            spManager.setMajorActivities(binding.majorActivity.getText().toString().trim());
                            spManager.setUser("ngo");
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

    private void getNGOProfile(){
        dialog.show("");
        //binding.updateProScrollView.setVisibility(View.GONE);

        CommonAuthModel model = new CommonAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getNGOProfile(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NGOProfileResponse>() {
                    @Override
                    public void onNext(NGOProfileResponse ngoProfileResponse) {
                        String msg = ngoProfileResponse.getMsg();
                        dialog.dismiss("");

                        if (msg.equals("success")){
                            if (ngoProfileResponse != null){
                                Glide.with(context).load(ngoProfileResponse.getProfile_pic()).placeholder(R.drawable.demo).into(binding.profileImage);

//                                if (ngoProfileResponse.getProfiledetails() != null && ngoProfileResponse.getProfiledetails().getProfile() != null){
//                                    binding.editName.setText(ngoProfileResponse.getProfiledetails().getProfile().getNgo_name());
//                                    binding.registrationNumber.setText(ngoProfileResponse.getProfiledetails().getProfile().getNgo_registration_no());
//                                    binding.editEmailEnter.setText(ngoProfileResponse.getProfiledetails().getProfile().getEmail());
//                                    binding.editMobileNumber.setText(ngoProfileResponse.getProfiledetails().getProfile().getPhone());
//                                    binding.majorActivity.setText(ngoProfileResponse.getProfiledetails().getProfile().getOrganization_activities());
//                                    binding.workExp.setText(ngoProfileResponse.getProfiledetails().getProfile().getWork_experience());
//                                    binding.editCountryEnter.setText(ngoProfileResponse.getProfiledetails().getProfile().getCountry());
//                                    binding.editStateEnter.setText(ngoProfileResponse.getProfiledetails().getProfile().getState());
//                                    binding.editCityEnter.setText(ngoProfileResponse.getProfiledetails().getProfile().getCity());
//                                    binding.updateProScrollView.setVisibility(View.VISIBLE);
//                                }

                            }

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
}
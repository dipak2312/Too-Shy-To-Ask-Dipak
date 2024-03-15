package com.neuronimbus.metropolis.activity.NGO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.NGOSignupAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.SignupResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.MyValidator;
import com.neuronimbus.metropolis.activity.Landing.SignInActivity;
import com.neuronimbus.metropolis.databinding.ActivityNgoSignUpBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NgoSignUpActivity extends AppCompatActivity{
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivityNgoSignUpBinding binding;
    String phone = "";
    InstallReferrerClient mInstallReferrerClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityNgoSignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        onClick();
        getController();
        installReferrer();
    }

    private void getController() {
        context = NgoSignUpActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        binding.editMobileNumber.setText(phone);
        binding.editMobileNumber.setClickable(false);
        binding.editMobileNumber.setFocusable(false);

    }

    private void onClick() {
        binding.relBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
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
//                else if (binding.editReferralCodeEnter.getText().toString().trim().equals("")
//                && binding.editReferralCodeEnter.getText().toString().trim().equals("utm_source=google-play&utm_medium=organic")){
//                    Toast.makeText(context, getString(R.string.you_have_entered_wrong_referral_code), Toast.LENGTH_SHORT).show();
//                }
                else {
                    ngoSignUp();
                }
            }
        });
    }

    public void ngoSignUp() {
        dialog.show("");

        NGOSignupAuthModel signupmodel = new NGOSignupAuthModel();
        signupmodel.setNgo_name(binding.editName.getText().toString().trim());
        signupmodel.setNgo_registration_no(binding.registrationNumber.getText().toString().trim());
        signupmodel.setEmail_id(binding.editEmailEnter.getText().toString().trim());
        signupmodel.setPhone(binding.editMobileNumber.getText().toString().trim());
        signupmodel.setOrganization_activities(binding.majorActivity.getText().toString().trim());
        signupmodel.setLanguage(spManager.getLanguage());
        signupmodel.setUsertype("ngo");
        signupmodel.setWork_experience(binding.workExp.getText().toString().trim());
        signupmodel.setCountry(binding.editCountryEnter.getText().toString().trim());
        signupmodel.setState(binding.editStateEnter.getText().toString().trim());
        signupmodel.setCity(binding.editCityEnter.getText().toString().trim());
        signupmodel.setReferral_code(binding.editReferralCodeEnter.getText().toString().trim());

        WebServiceModel.getRestApi().ngoRegister(signupmodel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SignupResponse>() {
                    @Override
                    public void onNext(SignupResponse signupResponse) {
                        String msg = signupResponse.getMsg();

                        if (msg.equals("NGO Registered Successfully")) {

                            spManager.setNgoName(binding.editName.getText().toString().trim());
                            spManager.setNgoRegistrationNumber(binding.registrationNumber.getText().toString().trim());
                            spManager.setEmail(binding.editEmailEnter.getText().toString().trim());
                            spManager.setWorkExp(binding.workExp.getText().toString().trim());
                            spManager.setPhone(binding.editMobileNumber.getText().toString().trim());
                            spManager.setUserId(signupResponse.getUser_id());
                            spManager.setCountry(binding.editCountryEnter.getText().toString().trim());
                            spManager.setState(binding.editStateEnter.getText().toString().trim());
                            spManager.setCity(binding.editCityEnter.getText().toString().trim());
                            spManager.setTstaLoginStatus("true");
                            spManager.setMajorActivities(binding.majorActivity.getText().toString().trim());
                            spManager.setLanguage(spManager.getLanguage());
                            spManager.setUser("ngo");


                            Intent intent = new Intent(context, SelectOrganizationActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                        dialog.dismiss("");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context,e.toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void installReferrer() {
        mInstallReferrerClient = InstallReferrerClient.newBuilder(context).build();
        mInstallReferrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        try {
//                            ReferrerDetails referrerDetails = mInstallReferrerClient.getInstallReferrer();
//                            String referrerUrl = referrerDetails.getInstallReferrer();
//                            long referrerClickTime = referrerDetails.getReferrerClickTimestampSeconds();
//                            long appInstallTime = referrerDetails.getInstallBeginTimestampSeconds();

                            ReferrerDetails response = mInstallReferrerClient.getInstallReferrer();
                            String referrerUrl = response.getInstallReferrer();
                            long referrerClickTime = response.getReferrerClickTimestampSeconds();
                            long appInstallTime = response.getInstallBeginTimestampSeconds();
                            boolean instantExperienceLaunched = response.getGooglePlayInstantParam();

                            if (referrerUrl.contains("TSTA")){
                                binding.editReferralCodeEnter.setText(referrerUrl);
                                binding.editReferralCodeEnter.setClickable(false);
                                binding.editReferralCodeEnter.setFocusable(false);
                            }
                            else {
                                binding.editReferralCodeEnter.setText("");
                                binding.editReferralCodeEnter.setClickable(true);
                                binding.editReferralCodeEnter.setFocusable(true);
                            }

                            Log.d("dipaksReferell", "Referrer URL: " + referrerUrl);
                            Log.d("dipaksReferell", "Referrer Click Time: " + referrerClickTime);
                            Log.d("dipaksReferell", "App Install Time: " + appInstallTime);

                            mInstallReferrerClient.endConnection();
                            // Handle referrer information
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        // API not supported by the current Play Store app
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        // Play Store service is not available now
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
                // Retry to establish connection
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mInstallReferrerClient != null){
            mInstallReferrerClient.endConnection();
        }

    }
}
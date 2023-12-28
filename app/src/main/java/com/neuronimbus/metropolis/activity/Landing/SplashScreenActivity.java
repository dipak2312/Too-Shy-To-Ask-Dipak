package com.neuronimbus.metropolis.activity.Landing;
import static android.content.ContentValues.TAG;

import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.CommonAuthModel;
import com.neuronimbus.metropolis.AuthModels.SignupAuthModel;
import com.neuronimbus.metropolis.Models.SignupResponse;
import com.neuronimbus.metropolis.Models.SplashScreenResponse;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.R;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.neuronimbus.metropolis.activity.NGO.AdminApprovalActivity;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SplashScreenActivity extends AppCompatActivity {

    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    SPManager spManager;
    CustomProgressDialog dialog;
    Context context;
    BiometricManager biometricManager;
    private final int UPDATE_CODE = 22;
    AppUpdateManager appUpdateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setStatusBarColor(getResources().getColor(R.color.tsta_splash));

        context = SplashScreenActivity.this;
        spManager=new SPManager(context);
        biometricManager = BiometricManager.from(this);
        dialog = new CustomProgressDialog(context);
        //openFingerPrint();
        checkPreviousActivityStatus();

    }

    public void openFingerPrint()
    {
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "Device doesn't have fingerprint", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "Not working", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this, "No fingerprint assigned", Toast.LENGTH_SHORT).show();
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                checkPreviousActivityStatus();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setDescription("Use Fingerprint to Login")
                .setDeviceCredentialAllowed(true)
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

    private void checkUpdate(){
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(context);

        Task<AppUpdateInfo> task = appUpdateManager.getAppUpdateInfo();
        task.addOnSuccessListener((AppUpdateInfo appUpdateInfo) -> {

            if (appUpdateInfo.updateAvailability()== UpdateAvailability.UPDATE_AVAILABLE &&
                    appUpdateInfo.isUpdateTypeAllowed(IMMEDIATE)){
                try
                {
                    appUpdateManager.startUpdateFlowForResult(appUpdateInfo, IMMEDIATE,
                            SplashScreenActivity.this,UPDATE_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                    Log.d(TAG, "OnSuccess: "+ e.toString());
                }
            }
        });
        appUpdateManager.registerListener(listener);
    }
    InstallStateUpdatedListener listener = installState -> {
        if (installState.installStatus() == InstallStatus.DOWNLOADED){
            popup();
        }
    };

    @Override
    protected void onStop() {
        if (appUpdateManager!=null) appUpdateManager.unregisterListener(listener);
        super.onStop();
    }

    private void popup() {

        Snackbar snackbar = Snackbar.make(
                findViewById(android.R.id.content),
                "App update almost done",
                Snackbar.LENGTH_INDEFINITE
        );

        snackbar.setAction("Reload", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                appUpdateManager.completeUpdate();
            }
        });
        snackbar.setTextColor(Color.parseColor("#FF0000"));
        snackbar.show();

    }
    @Override
    public void onResume() {
        super.onResume();
        setLocale(spManager.getLanguage());
    }

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        spManager.setLanguage(lang);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;

        if (requestCode == UPDATE_CODE){
            Toast.makeText(context, "Downloading Start", Toast.LENGTH_SHORT).show();

            if (requestCode == RESULT_OK){
                Log.d(TAG, "onActivityResult: Update flow failed" + resultCode);

            }
        }
    }

    private void checkPreviousActivityStatus() {
        Completable.complete()
                .delay(0, TimeUnit.SECONDS)
                .doOnComplete(() -> {
                    //Do your stuff here

                    if (spManager.getTstaLoginStatus().equals("true")) {
                        adminApproval();
                    }
//                    else if (spManager.getTstaguestLoginStatus().equals("true")) {
//                        Intent intent = new Intent(this, SignInActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        checkUpdate();
//                        finish();
//                    }
                        else {
                        Intent intent = new Intent(this, SliderImagesActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        checkUpdate();
                        finish();
                    }

                })
                .subscribe();
    }

    public void adminApproval() {
//        dialog.show("");

        CommonAuthModel model = new CommonAuthModel();
        model.setUser_id(spManager.getUserId());


        WebServiceModel.getRestApi().splashScreen(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SplashScreenResponse>() {
                    @Override
                    public void onNext(SplashScreenResponse splashScreenResponse) {
                        String msg = splashScreenResponse.getMsg();

                        if (msg.equals("success")) {

                            if (splashScreenResponse != null){
                                spManager.setUser(splashScreenResponse.getUsertype().toString());

                                if (splashScreenResponse.getAdmin_approval().equals("approved")){
                                    Intent intent = new Intent(context, HomeActivity.class);
                                    spManager.setTstaLoginStatus("true");
                                    spManager.setTstaguestLoginStatus("false");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    checkUpdate();
                                    finish();

                                }
                                else if (splashScreenResponse.getAdmin_approval().equals("pending")){
                                    Intent intent = new Intent(context, AdminApprovalActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Intent intent = new Intent(context, SignInActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            }


                        } else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                       // dialog.dismiss("");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context,e.toString(), Toast.LENGTH_SHORT).show();
                        //dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
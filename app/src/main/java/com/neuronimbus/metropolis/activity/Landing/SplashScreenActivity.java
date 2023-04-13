package com.neuronimbus.metropolis.activity.Landing;
import static android.content.ContentValues.TAG;

import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
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

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;

public class SplashScreenActivity extends AppCompatActivity {

    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    SPManager spManager;
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
        //openFingerPrint();
        checkUpdate();
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
                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();
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
                .delay(2, TimeUnit.SECONDS)
                .doOnComplete(() -> {
                    //Do your stuff here

                    if (spManager.getTstaLoginStatus().equals("true")) {
                        Intent intent = new Intent(this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else if (spManager.getTstaguestLoginStatus().equals("true")) {
                        Intent intent = new Intent(this, SignInActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                        else {
                        Intent intent = new Intent(this, SliderImagesActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }

                })
                .subscribe();
    }
}
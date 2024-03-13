package com.neuronimbus.metropolis.activity.Landing;

import static java.util.Objects.requireNonNull;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
import com.neuronimbus.metropolis.AuthModels.SignInAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.SignInResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    SPManager spManager;
    TextInputEditText etMobile;
    Context context;
    Button btn_signin;
    CustomProgressDialog dialog;
    TextView guest_login, terms_conditions, privacy_policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = SignInActivity.this;

        privacy_policy = findViewById(R.id.privacy_policy);
        privacy_policy.setOnClickListener(this);
        terms_conditions = findViewById(R.id.terms_conditions);
        terms_conditions.setOnClickListener(this);
        btn_signin = findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(this);
        guest_login = findViewById(R.id.guest_login);
        guest_login.setOnClickListener(this);

        etMobile = findViewById(R.id.etMobile);

        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == btn_signin.getId()) {

                loginWithOTP();
        } else if (id == guest_login.getId()) {
            if (spManager.getTstaLoginStatus().equals("false")) {
                Intent intent = new Intent(context, HomeActivity.class);
                spManager.setTstaguestLoginStatus("true");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
            else {
                guest_login.setClickable(false);
            }
        }
        else if(id==terms_conditions.getId())
        {
            Uri uri = Uri.parse("https://tooshytoask.org/terms-and-conditions/");
            Intent intent= new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);

            /*Intent intent = new Intent(context, WebViewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("link1", "https://tsta.hodemoserver.in/terms-and-conditions/");
            intent.putExtra("title", "Terms & Conditions");
            startActivity(intent);*/

        }
        else if (id == privacy_policy.getId()) {
            Uri uri = Uri.parse("https://tooshytoask.org/privacy-policy/");
            Intent intent= new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);

            /*Intent intent = new Intent(context, WebViewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("link1", "https://tsta.hodemoserver.in/privacy-policy");
            intent.putExtra("title", "privacy policy");
            startActivity(intent);*/
        }
    }



    private void loginWithOTP() {

        dialog.show("");

        SignInAuthModel signinmodel = new SignInAuthModel();
        signinmodel.setMobile_no (etMobile.getText().toString().trim());

        WebServiceModel.getRestApi().sendOtp(signinmodel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SignInResponse>() {
                    @Override
                    public void onNext(SignInResponse signInResponse) {
                        String msg = signInResponse.getMsg();
                        if (msg.equals("OTP Send Successfully")){
                            Intent intent = new Intent(context, OtpVerificationActivity.class);
                            intent.putExtra("phone", etMobile.getText().toString().trim());
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss("");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss("");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    }
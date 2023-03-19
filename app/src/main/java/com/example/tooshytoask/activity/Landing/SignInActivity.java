package com.example.tooshytoask.activity.Landing;

import static java.util.Objects.requireNonNull;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.activity.Home.HomeActivity;
import com.example.tooshytoask.AuthModels.SignInAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.SignInResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
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
    TextView guest_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = SignInActivity.this;

        btn_signin = findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(this);
        guest_login = findViewById(R.id.guest_login);
        guest_login.setOnClickListener(this);

        etMobile = findViewById(R.id.etMobile);

        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == btn_signin.getId()) {
            /*Intent intent = new Intent(context, OtpVerificationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();*/
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
                            startActivityForResult(intent, 1);
                            finish();
                        }
                        else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss("");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "The mobile no field is required.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss("");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
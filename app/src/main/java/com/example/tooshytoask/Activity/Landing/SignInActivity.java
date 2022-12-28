package com.example.tooshytoask.Activity.Landing;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Home.HomeActivity;
import com.example.tooshytoask.AuthModels.SignInAuthModel;
import com.example.tooshytoask.Fragment.HomeFragment;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.SignInResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    SPManager spManager;
    EditText etMobile;
    Context context;
    Button btn_signin;
    CustomProgressDialog dialog;
    Dialog dialog1;
    TextView guest_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = SignInActivity.this;

        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(this);
        guest_login = findViewById(R.id.guest_login);
        guest_login.setOnClickListener(this);

        etMobile = (EditText) findViewById(R.id.etMobile);

        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == btn_signin.getId()) {

            Intent intent = new Intent(context, OtpVerificationActivity.class);
            startActivity(intent);

            /*if (etMobile.getText().toString().trim().equals("")) {
                etMobile.requestFocus();
                etMobile.setError("Mobile number id is required");
            }*/ } else if (id == guest_login.getId()){
            Intent intent = new Intent(context, HomeActivity.class);
            startActivity(intent);
        }
        else {
                //loginWithOTP();
            }



    }

    private void loginWithOTP() {

        dialog.dismiss("");
        dialog.show("");

        SignInAuthModel signinmodel = new SignInAuthModel();
        signinmodel.setMobile_no (etMobile.getText().toString().replace("+91","").trim());
        //signinmodel.setDevice_type("android");


        WebServiceModel.getRestApi().signIn(signinmodel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SignInResponse>() {
                    @Override
                    public void onNext(SignInResponse signinResponse) {
                        //CourseEnrolledResponse commentResponse = new Gson().fromJson(signinResponse.toString(), CourseEnrolledResponse.class);
                        String msg = signinResponse.getMsg();
                        if (msg.equals("OTP Send Successfully.")) {
                            //Open OTP Screen
                            Intent intent = new Intent(context, OtpVerificationActivity.class);
                            intent.putExtra("phone", etMobile.getText().toString().trim());
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivityForResult(intent, 1);
                        } else if (msg.equalsIgnoreCase("User not exits.")) {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, SignUpActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }
}
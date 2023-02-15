package com.example.tooshytoask.Activity.Landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;


import com.chaos.view.PinView;
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Home.HomeActivity;
import com.example.tooshytoask.AuthModels.OtpAuthModel;
import com.example.tooshytoask.AuthModels.SignInAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.OtpInResponse;
import com.example.tooshytoask.Models.SignInResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.google.gson.Gson;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class OtpVerificationActivity extends AppCompatActivity implements View.OnClickListener {

    String phone = "",  user_otp = "";
    TextView btn_resend_otp, progress_text, mobile_no;
    Button btn_submit;
    ImageView otp_img;
    PinView otp_view;
    Context context;
    RelativeLayout rel_back;
    SPManager spManager;
    CircularProgressBar progress_circular;
    CustomProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        context = OtpVerificationActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        phone = getIntent().getStringExtra("phone");

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        btn_resend_otp = findViewById(R.id.btn_resend_otp);
        btn_resend_otp.setOnClickListener(this);
        progress_text = findViewById(R.id.progress_text);
        progress_text.setOnClickListener(this);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        otp_img = findViewById(R.id.otp_img);
        mobile_no = findViewById(R.id.mobile_no);
        Intent intent = getIntent();
        String str = intent.getStringExtra("phone");
        mobile_no.setText(str);

        progress_circular = findViewById(R.id.progress_circular);
        progressBar();
        otp_view = findViewById(R.id.otp_view);

        pinView();

        /*otpTextView = (OtpTextView) findViewById(R.id.otp_view);
        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(String otp) {
                user_otp = otp;

            }
        });*/
        countDownTimer();
    }

    private void pinView() {

        otp_view.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        otp_view.setTextColor(
                ResourcesCompat.getColor(getResources(), R.color.black, getTheme()));
        otp_view.setTextColor(
                ResourcesCompat.getColorStateList(getResources(), R.color.black, getTheme()));
        otp_view.setLineColor(
                ResourcesCompat.getColor(getResources(), R.color.purple, getTheme()));
        otp_view.setLineColor(
                ResourcesCompat.getColorStateList(getResources(), R.color.line_colors, getTheme()));
        otp_view.setItemCount(4);
        otp_view.setAnimationEnable(false);// start animation when adding text

        otp_view.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {

                user_otp=editable.toString();

            }
        });

        otp_view.setAnimationEnable(true);
        otp_view.setItemBackground(getResources().getDrawable(R.drawable.pin_border));
        otp_view.setItemBackgroundResources(R.drawable.pin_border);
        otp_view.setHideLineWhenFilled(false);
        //otp_view.setPasswordHidden(false);

        //otp_view.setTransformationMethod(new PasswordTransformationMethod());
    }



    private void progressBar() {
        progress_circular.setProgress(0f);
        progress_circular.setProgressMax(60f);
        progress_circular.setProgressWithAnimation( 60f, 60000L);
        progress_circular.setProgressBarColor(ContextCompat.getColor(context, R.color.progressbar_color));
        progress_circular.setBackgroundProgressBarColor(ContextCompat.getColor(context, R.color.resend_color));
    }

    private void countDownTimer() {


        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                String value = " " + millisUntilFinished / 1000;
                    btn_resend_otp.setClickable(false);
                    btn_resend_otp.setText("RESEND IN "+ millisUntilFinished / 1000);
                    btn_resend_otp.setTextColor(ContextCompat.getColor(context, R.color.resend_color));
                    progress_text.setText(value);
                    //progress_text.setVisibility(View.VISIBLE);
                   // progress_circular.setVisibility(View.VISIBLE);
                //progress_circular.setProgressBarColor(ContextCompat.getColor(context, R.color.progressbar_color));
                //progress_circular.setBackgroundProgressBarColor(ContextCompat.getColor(context, R.color.resend_color));
                    //otp_img.setVisibility(View.GONE);

                }
            @Override
            public void onFinish() {

                btn_resend_otp.setText(getResources().getString(R.string.resend_otp));
                btn_resend_otp.setClickable(true);
                otp_view.setText("");
                btn_resend_otp.setTextColor(ContextCompat.getColor(context, R.color.purple));
                progress_text.setVisibility(View.GONE);
                progress_circular.setVisibility(View.GONE);
                otp_img.setVisibility(View.VISIBLE);
                user_otp = "";

            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == btn_submit.getId()) {

            verifyOTP();
        }
        else if (id == rel_back.getId()) {
                Intent intent1 = new Intent(context, SignInActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                finish();
            } else if (id == btn_resend_otp.getId()) {
                sendOtpApi();
                //progressBar();
            }
            else {
                verifyOTP();

            }



    }

    private void sendOtpApi() {
        dialog.show();

        SignInAuthModel model=new SignInAuthModel();
        model.setMobile_no(phone);

        WebServiceModel.getRestApi().sendOtp(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SignInResponse>() {
                    @Override
                    public void onNext(SignInResponse signInResponse) {

                        String msg = signInResponse.getMsg();

                        if (msg.equals("OTP Send Successfully")) {

                            countDownTimer();
                            Toast.makeText(context, msg,  Toast.LENGTH_SHORT).show();


                        } else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void verifyOTP() {
        dialog.show("");

        OtpAuthModel model=new OtpAuthModel();
        model.setMobile_no(phone);
        model.setOtp_no(user_otp);


        WebServiceModel.getRestApi().signIn(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<OtpInResponse>() {
                    @Override
                    public void onNext(OtpInResponse otpInResponse) {

                        String msg = otpInResponse.getMsg();

                       if (msg.equals("Success")){

                           spManager.setFirstName(otpInResponse.getData().getFirst_name());
                           spManager.setLastName(otpInResponse.getData().getLast_name());
                           spManager.setDob(otpInResponse.getData().getDob());
                           spManager.setGender(otpInResponse.getData().getGender());
                           spManager.setPhone(otpInResponse.getData().getPhone());
                           spManager.setUserId(otpInResponse.getData().getUser_id());
                           spManager.setTstaLoginStatus("true");
                           spManager.setUserPhoto(otpInResponse.getData().getProfilePic());

                           Intent intent = new Intent(context, HomeActivity.class);
                           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(intent);
                           finish();

                       }

                        else if (msg.equals("User not exits.")) {
                           // Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, SignUpActivity.class);
                            intent.putExtra("phone", phone);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                        else if(msg.equals("Please enter valid OTP."))
                        {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                        else if(msg.equalsIgnoreCase("Your OTP Expired."))
                        {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss("");

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
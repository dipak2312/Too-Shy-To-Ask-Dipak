package com.example.tooshytoask.Activity.Setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Home.HomeActivity;
import com.example.tooshytoask.Activity.Landing.OtpVerificationActivity;
import com.example.tooshytoask.Activity.Landing.SignInActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfile.UpdateAvatarActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfile.UpdateHealthActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfile.UpdateInterestActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfile.UpdatePersonalInfoActivity;
import com.example.tooshytoask.AuthModels.SignupAuthModel;
import com.example.tooshytoask.AuthModels.UpdateProfileAuthModel;
import com.example.tooshytoask.Fragment.InfoCard.FourFragment;
import com.example.tooshytoask.Fragment.InfoCard.OneFragment;
import com.example.tooshytoask.Fragment.InfoCard.ThreeFragment;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.UpdateProfile.UpdateProfileResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.ozcanalasalvar.library.utils.DateUtils;
import com.ozcanalasalvar.library.view.datePicker.DatePicker;
import com.ozcanalasalvar.library.view.popup.DatePickerPopup;

import java.util.Calendar;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{
    RelativeLayout update_interest, update_personal_info, update_health, rel_back;
    SPManager spManager;
    TextView edit_age, change_avatar;
    Button update_pro, male, female, other;
    TextInputEditText edit_name, edit_surname, etMobile, edit_email_enter,edit_country_enter,
            edit_state_enter, edit_city_enter;
    Context context;
    int year, month, day;
    private DatePickerPopup datePickerPopup;
    CustomProgressDialog dialog;
    String gender ="";
    String yearnew = "", profile_pic="";
    ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = UpdateProfileActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        edit_name = findViewById(R.id.edit_name);
        edit_name.setText(spManager.getFirstName());
        edit_surname = findViewById(R.id.edit_surname);
        edit_surname.setText(spManager.getLastName());
        etMobile = findViewById(R.id.etMobile);
        etMobile.setText(spManager.getPhone());
        etMobile.setClickable(false);
        etMobile.setFocusable(false);
        edit_city_enter = findViewById(R.id.edit_city_enter);
        edit_city_enter.setText(spManager.getCity());
        edit_country_enter = findViewById(R.id.edit_country_enter);
        edit_country_enter.setText(spManager.getCountry());
        edit_state_enter = findViewById(R.id.edit_state_enter);
        edit_state_enter.setText(spManager.getState());
        edit_email_enter = findViewById(R.id.edit_email_enter);
        edit_email_enter.setText(spManager.getEmail());

        update_interest = findViewById(R.id.update_interest);
        update_interest.setOnClickListener(this);
        update_personal_info = findViewById(R.id.update_personal_info);
        update_personal_info.setOnClickListener(this);
        update_health = findViewById(R.id.update_health);
        update_health.setOnClickListener(this);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        edit_age = findViewById(R.id.edit_age);
        edit_age.setText(spManager.getDob());
        edit_age.setOnClickListener(this);
        profile_image = findViewById(R.id.profile_image);

//        profile_pic=spManager.getUserPhoto();
//
//        if(!profile_pic.equals("")) {
//
//            Glide.with(context).load(profile_pic).into(profile_image);
//        }

        change_avatar = findViewById(R.id.change_avatar);
        change_avatar.setOnClickListener(this);
        male = findViewById(R.id.male);
        male.setOnTouchListener(this);


        if (spManager.getGender().equals("Male")){
            male.setBackgroundResource(R.drawable.gender_border_active);
            male.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        else if (spManager.getGender().equals("Female")){
            female.setBackgroundResource(R.drawable.gender_border_active);
            female.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        else if (spManager.getGender().equals("Other")){
            other.setBackgroundResource(R.drawable.gender_border_active);
            other.setTextColor(ContextCompat.getColor(context, R.color.white));
        }

        female = findViewById(R.id.female);
        female.setOnTouchListener(this);
        other = findViewById(R.id.other);
        other.setOnTouchListener(this);
        //gender.setText(spManager.getDob());
        update_pro = findViewById(R.id.update_pro);
        update_pro.setOnClickListener(this);

        Calendar now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH); // Note: zero based!
        day = now.get(Calendar.DAY_OF_MONTH);

        datePickerPopup = new DatePickerPopup.Builder()
                .from(context)
                .offset(3)
                .darkModeEnabled(true)
                .pickerMode(DatePicker.MONTH_ON_FIRST)
                .textSize(19)
                .endDate(DateUtils.getTimeMiles(2030, 0, 1))
                .currentDate(DateUtils.getCurrentTime())
                .startDate(DateUtils.getTimeMiles(1950, 0, 1))
                .listener(new DatePickerPopup.OnDateSelectListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSelected(DatePicker dp, long date, int day, int month, int year) {
                        yearnew=year+"-"+(month + 1)+"-"+day;
                        edit_age.setText("" + day + "/" + (month + 1) + "/" + year);
                    }
                })
                .build();
    }

    private void openDatePicker() {
        datePickerPopup.show();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();

        if (id == male.getId()) {
            male.setBackgroundResource(R.drawable.gender_border_active);
            male.setTextColor(ContextCompat.getColor(context, R.color.white));
            female.setBackgroundResource(R.drawable.gender_border_inactive);
            female.setTextColor(ContextCompat.getColor(context, R.color.black));
            other.setBackgroundResource(R.drawable.gender_border_inactive);
            other.setTextColor(ContextCompat.getColor(context, R.color.black));
            gender = "Male";

        } else if (id == female.getId()){
            female.setBackgroundResource(R.drawable.gender_border_active);
            female.setTextColor(ContextCompat.getColor(context, R.color.white));
            male.setBackgroundResource(R.drawable.gender_border_inactive);
            male.setTextColor(ContextCompat.getColor(context, R.color.black));
            other.setBackgroundResource(R.drawable.gender_border_inactive);
            other.setTextColor(ContextCompat.getColor(context, R.color.black));
            gender = "Female";

        } else if (id == other.getId()){
            other.setBackgroundResource(R.drawable.gender_border_active);
            other.setTextColor(ContextCompat.getColor(context, R.color.white));
            male.setBackgroundResource(R.drawable.gender_border_inactive);
            male.setTextColor(ContextCompat.getColor(context, R.color.black));
            female.setBackgroundResource(R.drawable.gender_border_inactive);
            female.setTextColor(ContextCompat.getColor(context, R.color.black));
            gender = "Other";

        }
        return true;
    }

    public void getUserProfileUpdate(){
        dialog.show("");
        dialog.dismiss("");

        UpdateProfileAuthModel model = new UpdateProfileAuthModel();
        model.setFirst_name(edit_name.getText().toString().trim());
        model.setFirst_name(edit_surname.getText().toString().trim());
        model.setFirst_name(etMobile.getText().toString().trim());
        model.setFirst_name(edit_email_enter.getText().toString().trim());
        model.setDob(edit_age.getText().toString().trim());
        model.setGender(gender);
        model.setDob(yearnew);
        model.setCountry(edit_country_enter.getText().toString().trim());
        model.setState(edit_state_enter.getText().toString().trim());
        model.setCity(edit_city_enter.getText().toString().trim());

        WebServiceModel.getRestApi().getUserProfile(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UpdateProfileResponse>() {
                    @Override
                    public void onNext(UpdateProfileResponse updateProfileResponse) {
                        String msg = updateProfileResponse.getMsg();

                        if (msg.equals("Profile Updated")){

                            spManager.setFirstName(edit_name.getText().toString().trim());
                            spManager.setLastName(edit_surname.getText().toString().trim());
                            spManager.setEmail(edit_email_enter.getText().toString().trim());
                            spManager.setDob(edit_age.getText().toString().trim());
                            spManager.setUserId(spManager.getUserId());
                            spManager.setTstaLoginStatus("true");
                            spManager.setGender(gender);
                            spManager.setCountry(edit_country_enter.getText().toString().trim());
                            spManager.setState(edit_state_enter.getText().toString().trim());
                            spManager.setCity(edit_city_enter.getText().toString().trim());
                            spManager.setLanguage(spManager.getLanguage());

                            /*Intent intent = new Intent(context, UpdateProfileActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);*/
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == update_interest.getId()) {

            Intent intent = new Intent(context, UpdateInterestActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }
        else if (id == update_personal_info.getId()) {
            Intent intent = new Intent(context, UpdatePersonalInfoActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();


        }
        else if (id == update_health.getId()) {
            Intent intent = new Intent(context, UpdateHealthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        } else if (id == change_avatar.getId()){
            Intent intent = new Intent(context, UpdateAvatarActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else if (id == edit_age.getId()) {
            openDatePicker();
        }
        else if (id == update_pro.getId()) {
            getUserProfileUpdate();
            finish();
        }
        else if (id == rel_back.getId()){
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

    }
}
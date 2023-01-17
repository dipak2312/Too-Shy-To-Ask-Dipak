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

import com.example.tooshytoask.Activity.Home.HomeActivity;
import com.example.tooshytoask.Activity.Landing.OtpVerificationActivity;
import com.example.tooshytoask.Activity.Landing.SignInActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfile.UpdateAvatarActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfile.UpdateHealthActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfile.UpdateInterestActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfile.UpdatePersonalInfoActivity;
import com.example.tooshytoask.Fragment.InfoCard.FourFragment;
import com.example.tooshytoask.Fragment.InfoCard.OneFragment;
import com.example.tooshytoask.Fragment.InfoCard.ThreeFragment;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.ozcanalasalvar.library.utils.DateUtils;
import com.ozcanalasalvar.library.view.datePicker.DatePicker;
import com.ozcanalasalvar.library.view.popup.DatePickerPopup;

import java.util.Calendar;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{
    RelativeLayout update_interest, update_personal_info, update_health, profile_lay;
    SPManager spManager;
    TextView edit_age, change_avatar;
    Button btn_next, male, female, other;
    Context context;
    int year, month, day;
    ImageView back_btn;
    private DatePickerPopup datePickerPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = UpdateProfileActivity.this;
        spManager = new SPManager(context);
        update_interest = findViewById(R.id.update_interest);
        update_interest.setOnClickListener(this);
        update_personal_info = findViewById(R.id.update_personal_info);
        update_personal_info.setOnClickListener(this);
        update_health = findViewById(R.id.update_health);
        update_health.setOnClickListener(this);
        edit_age = findViewById(R.id.edit_age);
        edit_age.setOnClickListener(this);
        change_avatar = findViewById(R.id.change_avatar);
        change_avatar.setOnClickListener(this);
        male = findViewById(R.id.male);
        male.setOnTouchListener(this);
        female = findViewById(R.id.female);
        female.setOnTouchListener(this);
        other = findViewById(R.id.other);
        other.setOnTouchListener(this);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(this);

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
        } else if (id == female.getId()){
            female.setBackgroundResource(R.drawable.gender_border_active);
            female.setTextColor(ContextCompat.getColor(context, R.color.white));
            male.setBackgroundResource(R.drawable.gender_border_inactive);
            male.setTextColor(ContextCompat.getColor(context, R.color.black));
            other.setBackgroundResource(R.drawable.gender_border_inactive);
            other.setTextColor(ContextCompat.getColor(context, R.color.black));
        } else if (id == other.getId()){
            other.setBackgroundResource(R.drawable.gender_border_active);
            other.setTextColor(ContextCompat.getColor(context, R.color.white));
            male.setBackgroundResource(R.drawable.gender_border_inactive);
            male.setTextColor(ContextCompat.getColor(context, R.color.black));
            female.setBackgroundResource(R.drawable.gender_border_inactive);
            female.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == update_interest.getId()) {

            Intent intent = new Intent(context, UpdateInterestActivity.class);
            startActivity(intent);
            finish();

        }
        else if (id == update_personal_info.getId()) {
            Intent intent = new Intent(context, UpdatePersonalInfoActivity.class);
            startActivity(intent);
            finish();


        }
        else if (id == update_health.getId()) {
            Intent intent = new Intent(context, UpdateHealthActivity.class);
            startActivity(intent);
            finish();

        } else if (id == change_avatar.getId()){
            Intent intent = new Intent(context, UpdateAvatarActivity.class);
            startActivity(intent);
            finish();
        }
        else if (id == edit_age.getId()) {
            openDatePicker();
        }
        else if (id == back_btn.getId()) {
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else if (id == btn_next.getId()) {
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

    }
}
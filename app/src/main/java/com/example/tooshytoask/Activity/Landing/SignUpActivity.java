package com.example.tooshytoask.Activity.Landing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.ozcanalasalvar.library.utils.DateUtils;
import com.ozcanalasalvar.library.view.datePicker.DatePicker;
import com.ozcanalasalvar.library.view.popup.DatePickerPopup;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, ClickListener{
    int year, month, day;
    RelativeLayout rel_back;
    String select_birtct_date = "", encodedImage = "";
    CustomProgressDialog dialog;
    Button btn_next, male, female, other;
    Context context;
    SPManager spManager;
    EditText edit_first_name, edit_last_name, edit_email_id;
    TextView edit_age;
    private DatePickerPopup datePickerPopup;
    BottomSheetDialog bottomSheetDialog;
    Button btn_submit;
    ClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = SignUpActivity.this;
        spManager = new SPManager(context);

        clickListener=(ClickListener)context;
        clickListener.onClick(false);
        userPopup();

        edit_first_name = findViewById(R.id.edit_first_name);
        edit_last_name = findViewById(R.id.edit_last_name);
        edit_email_id = findViewById(R.id.edit_email_id);
        edit_age = findViewById(R.id.edit_age);
        edit_age.setOnClickListener(this);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        male = findViewById(R.id.male);
        male.setOnTouchListener(this);
        female = findViewById(R.id.female);
        female.setOnTouchListener(this);
        other = findViewById(R.id.other);
        other.setOnTouchListener(this);

        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        int id = view.getId();


        if (id == rel_back.getId()) {
            Intent intent = new Intent(context, OtpVerificationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        } else if (id == edit_age.getId()) {
            openDatePicker();
        } else if (id == btn_next.getId()) {
            Intent intent = new Intent(context, InfoCardCategoryActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
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

    private void userPopup(){

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.parent_user_popup);

        bottomSheetDialog.setCancelable(false);

        LinearLayout user = bottomSheetDialog.findViewById(R.id.user);
        LinearLayout parent = bottomSheetDialog.findViewById(R.id.parent);
        btn_submit = bottomSheetDialog.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        ImageView user_icon = bottomSheetDialog.findViewById(R.id.user_icon);
        ImageView parent_icon = bottomSheetDialog.findViewById(R.id.parent_icon);
        TextView user_text = bottomSheetDialog.findViewById(R.id.user_text);
        TextView parent_text = bottomSheetDialog.findViewById(R.id.parent_text);

        //String selectValue=spManager.getLanguage();

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setBackgroundResource(R.drawable.gender_border_active);
                user_icon.setImageResource(R.drawable.account_inactive);
                user_text.setTextColor(ContextCompat.getColor(context, R.color.white));
                parent.setBackgroundResource(R.drawable.gender_border_inactive);
                parent_icon.setImageResource(R.drawable.family_inactive);
                parent_text.setTextColor(ContextCompat.getColor(context, R.color.black));
                clickListener.onClick(true);


            }
        });

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.setBackgroundResource(R.drawable.gender_border_active);
                parent_icon.setImageResource(R.drawable.family_active);
                parent_text.setTextColor(ContextCompat.getColor(context, R.color.white));
                user.setBackgroundResource(R.drawable.gender_border_inactive);
                user_icon.setImageResource(R.drawable.account_active);
                user_text.setTextColor(ContextCompat.getColor(context, R.color.black));
                clickListener.onClick(true);


            }
        });



        bottomSheetDialog.show();

    }

    @Override
    public void onClick(Boolean status) {

        if(status)
        {
            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spManager.setUser("true");
                    bottomSheetDialog.dismiss();

                }
            });

        }else
        {

        }

    }
}

package com.neuronimbus.metropolis.activity.Landing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.SignupAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.SignupResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.ClickListener;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.MyValidator;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.ozcanalasalvar.library.utils.DateUtils;
import com.ozcanalasalvar.library.view.datePicker.DatePicker;
import com.ozcanalasalvar.library.view.popup.DatePickerPopup;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, ClickListener{
    int year, month, day;
    RelativeLayout rel_back;
    CustomProgressDialog dialog;
    Button btn_next, male, female, other;

    String gender ="";
    String selectValue = "";
    String yearnew = "";
    Context context;
    SPManager spManager;
    TextInputEditText edit_name, edit_surname, edit_email_enter, edit_mobile_number,edit_country_enter,
                      edit_state_enter, edit_city_enter;
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
        dialog = new CustomProgressDialog(context);

        clickListener=(ClickListener)context;
        clickListener.onClick(false);
        edit_age = findViewById(R.id.edit_age);
        edit_age.setOnClickListener(this);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        male = findViewById(R.id.male);
        male.setOnClickListener(this);
        female = findViewById(R.id.female);
        female.setOnClickListener(this);
        other = findViewById(R.id.other);
        other.setOnClickListener(this);
        edit_name = findViewById(R.id.edit_name);
        edit_name.setOnClickListener(this);
        edit_surname = findViewById(R.id.edit_surname);
        edit_surname.setOnClickListener(this);
        edit_email_enter = findViewById(R.id.edit_email_enter);
        edit_email_enter.setOnClickListener(this);
        edit_country_enter = findViewById(R.id.edit_country_enter);
        edit_country_enter.setOnClickListener(this);
        edit_state_enter = findViewById(R.id.edit_state_enter);
        edit_state_enter.setOnClickListener(this);
        edit_city_enter = findViewById(R.id.edit_city_enter);
        edit_city_enter.setOnClickListener(this);
        edit_mobile_number = findViewById(R.id.edit_mobile_number);
        edit_mobile_number.setOnClickListener(this);
        Intent intent = getIntent();
        String str = intent.getStringExtra("phone");
        edit_mobile_number.setText(str);
        edit_mobile_number.setClickable(false);
        edit_mobile_number.setFocusable(false);

        userPopup();

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
                         yearnew=year+"-"+(month + 1)+"-"+day;
                        edit_age.setText("" + day + "/" + (month + 1) + "/" + year);
                    }
                })
                .build();

    }


    @Override
    public void onClick(View view) {
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
        else if (id == rel_back.getId()) {
            Intent intent = new Intent(context, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        } else if (id == edit_age.getId()) {
            openDatePicker();
        } else if (id == btn_next.getId()) {

            if (edit_name.getText().toString().trim().equals("")) {
                Toast.makeText(context, "First Name is required", Toast.LENGTH_SHORT).show();
            }
            else if (!MyValidator.isValidName(edit_name.getText().toString().trim())) {
                Toast.makeText(context, "Please enter valid name", Toast.LENGTH_SHORT).show();
            }
            else if (edit_surname.getText().toString().trim().equals("")) {
                Toast.makeText(context, "Last Name is required", Toast.LENGTH_SHORT).show();
            }
            else if (!MyValidator.isValidName(edit_surname.getText().toString().trim())) {
                Toast.makeText(context, "Please enter valid surname", Toast.LENGTH_SHORT).show();
            }
            else if (!MyValidator.isValidEmail(edit_email_enter.getText().toString().trim())) {
                Toast.makeText(context, "Please enter valid email id", Toast.LENGTH_SHORT).show();
            }
            else if (edit_mobile_number.getText().toString().trim().equals("")) {
                Toast.makeText(context, "Mobile Number is required", Toast.LENGTH_SHORT).show();
            }
            else if (edit_age.getText().toString().trim().equals("")) {
                Toast.makeText(context, "Please select your Date Of Birth", Toast.LENGTH_SHORT).show();
            }
            else if (edit_country_enter.getText().toString().trim().equals("")) {
                Toast.makeText(context, "Please enter your Country", Toast.LENGTH_SHORT).show();
            }
            else if (!MyValidator.isValidName(edit_country_enter.getText().toString().trim())) {
                Toast.makeText(context, "Please enter valid Country", Toast.LENGTH_SHORT).show();
            }
            else if (edit_state_enter.getText().toString().trim().equals("")) {
                Toast.makeText(context, "Please enter your State", Toast.LENGTH_SHORT).show();
            }
            else if (!MyValidator.isValidName(edit_state_enter.getText().toString().trim())) {
                Toast.makeText(context, "Please enter valid sate", Toast.LENGTH_SHORT).show();
            }
            else if (edit_city_enter.getText().toString().trim().equals("")) {
                Toast.makeText(context, "Please enter your City", Toast.LENGTH_SHORT).show();
            }
            else if (!MyValidator.isValidName(edit_city_enter.getText().toString().trim())) {
                Toast.makeText(context, "Please enter valid city", Toast.LENGTH_SHORT).show();
            }
            else if (gender.equals("")){
                Toast.makeText(context, "Please select your Gender", Toast.LENGTH_SHORT).show();
            }
            else {
                Usersignup();
            }
        }

            }

    private void openDatePicker() {
        datePickerPopup.show();
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


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectValue ="user";
                user.setBackgroundResource(R.drawable.gender_border_active);
                user_icon.setImageResource(R.drawable.account_inactive);
                user_text.setTextColor(ContextCompat.getColor(context, R.color.white));
                parent.setBackgroundResource(R.drawable.gender_border_inactive);
                parent_icon.setImageResource(R.drawable.family_inactive);
                parent_text.setTextColor(ContextCompat.getColor(context, R.color.black));
                btn_submit.setBackgroundResource(R.drawable.active_con_btn);
                btn_submit.setTextColor(ContextCompat.getColor(context, R.color.white));
                clickListener.onClick(true);

            }
        });

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectValue ="parent";
                parent.setBackgroundResource(R.drawable.gender_border_active);
                parent_icon.setImageResource(R.drawable.family_active);
                parent_text.setTextColor(ContextCompat.getColor(context, R.color.white));
                user.setBackgroundResource(R.drawable.gender_border_inactive);
                user_icon.setImageResource(R.drawable.account_active);
                user_text.setTextColor(ContextCompat.getColor(context, R.color.black));
                btn_submit.setBackgroundResource(R.drawable.active_con_btn);
                btn_submit.setTextColor(ContextCompat.getColor(context, R.color.white));
                clickListener.onClick(true);

            }
        });

        bottomSheetDialog.show();

    }

    public void Usersignup() {
        dialog.show("");

        SignupAuthModel signupmodel = new SignupAuthModel();
        signupmodel.setFirst_name(edit_name.getText().toString().trim());
        signupmodel.setLast_name(edit_surname.getText().toString().trim());
        signupmodel.setEmail_id(edit_email_enter.getText().toString().trim());
        signupmodel.setPhone(edit_mobile_number.getText().toString().trim());
        signupmodel.setGender(gender);
        signupmodel.setLanguage(spManager.getLanguage());
        signupmodel.setUsertype(selectValue);
        signupmodel.setDob(yearnew);
        signupmodel.setCountry(edit_country_enter.getText().toString().trim());
        signupmodel.setState(edit_state_enter.getText().toString().trim());
        signupmodel.setCity(edit_city_enter.getText().toString().trim());

        WebServiceModel.getRestApi().signup(signupmodel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SignupResponse>() {
                    @Override
                    public void onNext(SignupResponse signupResponse) {
                        String msg = signupResponse.getMsg();

                        /*if (signupResponse!=null && signupResponse.getUser_id()!=null && signupResponse.getUser_id().length() == 1) {
                            Toast.makeText(context, signupResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        }*/

                         if (msg.equals("User Registered Successfully")) {

                            spManager.setFirstName(edit_name.getText().toString().trim());
                            spManager.setLastName(edit_surname.getText().toString().trim());
                            spManager.setEmail(edit_email_enter.getText().toString().trim());
                            spManager.setDob(edit_age.getText().toString().trim());
                            spManager.setPhone(edit_mobile_number.getText().toString().trim());
                            spManager.setUserId(signupResponse.getUser_id());
                            spManager.setCountry(edit_country_enter.getText().toString().trim());
                            spManager.setState(edit_state_enter.getText().toString().trim());
                            spManager.setCity(edit_city_enter.getText().toString().trim());
                            spManager.setTstaLoginStatus("true");
                            spManager.setGender(gender);
                            spManager.setLanguage(spManager.getLanguage());
                            spManager.setUser(selectValue);


                            Intent intent = new Intent(context, InfoCardCategoryActivity.class);
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
                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onClick(Boolean status) {

        if(status)
        {
            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//user, parent
                    bottomSheetDialog.dismiss();

                }
            });

        }

    }
}

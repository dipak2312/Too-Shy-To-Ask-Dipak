package com.example.tooshytoask.Activity.Setting.UpdateProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Home.HomeActivity;
import com.example.tooshytoask.Activity.Landing.SignInActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.AuthModels.UpdateProfileAuthModel;
import com.example.tooshytoask.AuthModels.UserDetailAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.UpdateProfile.UpdateProfileResponse;
import com.example.tooshytoask.Models.UserDetailResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.ozcanalasalvar.library.view.popup.DatePickerPopup;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class UpdatePersonalInfoActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    SPManager spManager;
    Spinner  spinner_blood;
    RelativeLayout rel_back;
    Button next_btn;
    CustomProgressDialog dialog;
    EditText etHeight,etWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_personal_info);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = UpdatePersonalInfoActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
        spinner_blood = findViewById(R.id.spinner_blood);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);

        listener();
        OpenBloodGrp();
    }

    private void listener(){
        etHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                next_btn.setBackgroundResource(R.drawable.circle_button_active);


            }

            @Override
            public void afterTextChanged(Editable editable) {
                next_btn.setBackgroundResource(R.drawable.circle_button_active);

            }
        });

        etWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                next_btn.setBackgroundResource(R.drawable.circle_button_active);


            }

            @Override
            public void afterTextChanged(Editable editable) {
                next_btn.setBackgroundResource(R.drawable.circle_button_active);

            }
        });
        spinner_blood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (spinner_blood.getSelectedItem().toString().equals("Select Your Blood Group")) {
                    next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
                } else {

                    next_btn.setBackgroundResource(R.drawable.circle_button_active);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
            }
        });
    }
    public void getUserProfile(){
        dialog.show("");
        dialog.dismiss("");

        UpdateProfileAuthModel model = new UpdateProfileAuthModel();
        model.setBloodgroup(spinner_blood.getSelectedItem().toString());
        model.setHeight(etHeight.getText().toString().trim());
        model.setWeight(etWeight.getText().toString().trim());
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getUserProfile(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UpdateProfileResponse>() {
                    @Override
                    public void onNext(UpdateProfileResponse updateProfileResponse) {
                        String msg = updateProfileResponse.getMsg();

                        if (msg.equals("Profile Updated")){

                            spManager.setBloodgroup(spinner_blood.getSelectedItem().toString());
                            spManager.setHeight(etHeight.getText().toString().trim());
                            spManager.setWeight(etWeight.getText().toString().trim());
                            spManager.setUserId(spManager.getUserId());

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

         if (id == spinner_blood.getId()){
            OpenBloodGrp();
        } else if (id == rel_back.getId()){

            Intent intent = new Intent(context, UpdateProfileActivity.class);
            startActivity(intent);
            finish();
            dialog.show("");
        }
         else if (id == next_btn.getId()) {

             if (spinner_blood.getSelectedItem().toString().equals("Select Your Blood Group")){
                 Toast.makeText(context, "Select Blood Group", Toast.LENGTH_SHORT).show();
             }
             else if (etHeight.getText().toString().trim().equals("")){
                 Toast.makeText(context, "Height is required", Toast.LENGTH_SHORT).show();
                 //etHeight.requestFocus();
                 //etHeight.setError("Height is required");
             }
             else if (etWeight.getText().toString().trim().equals("")){
                 Toast.makeText(context, "Weight is required", Toast.LENGTH_SHORT).show();
             }
             else {
                 getUserProfile();
             }

         } else if (id == spinner_blood.getId()) {
             OpenBloodGrp();
         }
    }

    public void OpenBloodGrp()
    {


        String[] countries = new String[]{"Select Your Blood Group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, R.id.spinnerTarget, countries);
        spinner_blood.setAdapter(countryAdapter);

    }
}
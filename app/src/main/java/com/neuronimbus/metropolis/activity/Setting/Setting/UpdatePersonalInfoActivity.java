package com.neuronimbus.metropolis.activity.Setting.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.UpdateProfileAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.UpdateProfile.UpdateProfileResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;

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
    String action = "personalinfo";

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
        etHeight.setText(spManager.getHeight());
        etWeight = findViewById(R.id.etWeight);
        etWeight.setText(spManager.getWeight());

        String[] countries = new String[]{"Select Your Blood Group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, R.id.spinnerTarget, countries);
        spinner_blood.setAdapter(countryAdapter);

        spinner_blood.setSelection(((ArrayAdapter<String>)spinner_blood.getAdapter()).getPosition(spManager.getBloodgroup()));
    }
    public void getUserProfileUpdate(){
        dialog.show("");
        dialog.dismiss("");

        UpdateProfileAuthModel model = new UpdateProfileAuthModel();
        model.setBloodgroup(spinner_blood.getSelectedItem().toString());
        model.setHeight(etHeight.getText().toString().trim());
        model.setWeight(etWeight.getText().toString().trim());
        model.setUser_id(spManager.getUserId());
        model.setAction(action);

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
                        else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
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
             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(intent);
             finish();
        }
         else if (id == next_btn.getId()) {

             /*if (spinner_blood.getSelectedItem().toString().equals("Select Your Blood Group")){
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
             else {*/
             getUserProfileUpdate();
             Intent intent = new Intent(context, UpdateProfileActivity.class);
             startActivity(intent);
             finish();


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
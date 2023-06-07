package com.neuronimbus.metropolis.Fragment.InfoCard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
import com.neuronimbus.metropolis.AuthModels.UserDetailAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.UserDetailResponse;
import com.neuronimbus.metropolis.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class PersonalInfoFragment extends Fragment implements View.OnClickListener {
    Context context;
    SPManager spManager;
    Spinner spinner_blood;
    TextView skip_btn;
    ImageButton next_btn;
    EditText etHeight;
    EditText etWeight;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, container, false);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        context = getActivity();
        spManager = new SPManager(context);
        skip_btn = view.findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(this);
        next_btn = view.findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);

        etHeight = view.findViewById(R.id.etHeight);
        etWeight = view.findViewById(R.id.etWeight);
        spinner_blood = view.findViewById(R.id.spinner_blood);

        OpenBloodGrp();
        listener();

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == skip_btn.getId()) {
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        }
        else if (id == next_btn.getId()) {

            if (spinner_blood.getSelectedItem().toString().equals("Select Your Blood Group")){
                Toast.makeText(context, "Select Blood Group", Toast.LENGTH_SHORT).show();
            }
            else if (etHeight.getText().toString().trim().equals("")){
                Toast.makeText(context, "Height is required", Toast.LENGTH_SHORT).show();

            }
            else if (etWeight.getText().toString().trim().equals("")){
                Toast.makeText(context, "Weight is required", Toast.LENGTH_SHORT).show();
            }
            else {
                userDetails();
            }

        } else if (id == spinner_blood.getId()) {
            OpenBloodGrp();
        }

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

    public void userDetails() {

        UserDetailAuthModel model = new UserDetailAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setBloodgroup(spinner_blood.getSelectedItem().toString());
        model.setHeight(etHeight.getText().toString().trim());
        model.setWeight(etWeight.getText().toString().trim());

        WebServiceModel.getRestApi().userDetails(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UserDetailResponse>() {
                    @Override
                    public void onNext(UserDetailResponse userDetailResponse) {
                        String msg = userDetailResponse.getMsg();

                        if (msg.equals("User Details updated.")) {

                            spManager.setBloodgroup(spinner_blood.getSelectedItem().toString());
                            spManager.setHeight(etHeight.getText().toString().trim());
                            spManager.setWeight(etWeight.getText().toString().trim());
                            spManager.setUserId(spManager.getUserId());

                            Intent intent = new Intent(context, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
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

    public void OpenBloodGrp() {


        String[] countries = new String[]{getResources().getString(R.string.select_blood_grp), "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout,
                R.id.spinnerTarget, countries);

        spinner_blood.setAdapter(countryAdapter);

    }

    }

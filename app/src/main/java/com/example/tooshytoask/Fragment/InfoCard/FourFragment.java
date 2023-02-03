package com.example.tooshytoask.Fragment.InfoCard;

import android.annotation.SuppressLint;
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

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Home.HomeActivity;
import com.example.tooshytoask.Activity.Landing.SignUpActivity;
import com.example.tooshytoask.AuthModels.UserDetailAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.UserDetailResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Utils.OnClickListner;
import com.google.android.material.navigation.NavigationBarView;
import com.ozcanalasalvar.library.utils.DateUtils;
import com.ozcanalasalvar.library.view.datePicker.DatePicker;
import com.ozcanalasalvar.library.view.popup.DatePickerPopup;

import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FourFragment extends Fragment implements View.OnClickListener, OnClickListner, ClickListener {
    Context context;
    SPManager spManager;
    Spinner spinner_blood;
    TextView skip_btn;
    ImageButton next_btn;
    ClickListener clickListener;
    EditText etHeight, etWeight;


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
        clickListener = (ClickListener) context;
        clickListener.onClick(false);
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
            clickListener.onClick(true);
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
                //etHeight.requestFocus();
                //etHeight.setError("Height is required");
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
                clickListener.onClick(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                next_btn.setBackgroundResource(R.drawable.circle_button_active);
                clickListener.onClick(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {
                next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
                clickListener.onClick(false);

            }
        });

        etWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
                clickListener.onClick(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                next_btn.setBackgroundResource(R.drawable.circle_button_active);
                clickListener.onClick(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {
                next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
                clickListener.onClick(false);

            }
        });
        spinner_blood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                 if (spinner_blood.getSelectedItem().toString().equals("Select Your Blood Group")) {
                     next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
                     clickListener.onClick(false);
                } else {

                     next_btn.setBackgroundResource(R.drawable.circle_button_active);
                     clickListener.onClick(true);
                 }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
                clickListener.onClick(false);
            }
        });
    }

    public void userDetails() {

        UserDetailAuthModel model = new UserDetailAuthModel();
        model.setBloodgroup(spinner_blood.getSelectedItem().toString());
        model.setHeight(etHeight.getText().toString().trim());
        model.setWeight(etWeight.getText().toString().trim());
        model.setUserId(spManager.getUserId());

        WebServiceModel.getRestApi().userDetails(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UserDetailResponse>() {
                    @Override
                    public void onNext(UserDetailResponse userDetailResponse) {
                        String msg = userDetailResponse.getMsg();

                        if (msg.equals("User Details updated.")) {
                            Intent intent = new Intent(context, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            getActivity().finish();
                            clickListener.onClick(true);
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


        String[] countries = new String[]{"Select Your Blood Group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, R.id.spinnerTarget, countries);
        spinner_blood.setAdapter(countryAdapter);

    }

    @Override
    public void onClickData(int position, String id) {

        ArrayList<Boolean> myvalue = new ArrayList<Boolean>();

        boolean ans = myvalue.contains(true);

        if (ans) {
            next_btn.setBackgroundResource(R.drawable.circle_button_active);


        } else {
            next_btn.setBackgroundResource(R.drawable.circle_button_active);
        }

    }

    @Override
    public void onClick(Boolean status) {
        if(status)
        {

            next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//user, parent


                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    getActivity().finish();
                    //userDetails();
                    clickListener.onClick(true);
                }
            });

        }
    }
}
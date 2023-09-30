package com.neuronimbus.metropolis.activity.Complaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.AddComplaintAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.AddComplaintResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.MyValidator;
import com.neuronimbus.metropolis.activity.Help.ContactUsActivity;
import com.neuronimbus.metropolis.activity.Landing.SignInActivity;
import com.neuronimbus.metropolis.databinding.ActivityComplaintBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ComplaintActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivityComplaintBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityComplaintBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = ComplaintActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        onClick();
        openSelectTopic();
    }

    private void onClick(){

        binding.relBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.previousChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ComplaintListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        binding.submitReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editEmailEnter.getText().toString().trim().equals("")) {
                    binding.editEmailEnter.requestFocus();
                    binding.editEmailEnter.setError("Email Id can't be blank");
                } else if (!MyValidator.isValidEmail(binding.editEmailEnter.getText().toString().trim())) {
                    binding.editEmailEnter.requestFocus();
                    binding.editEmailEnter.setError("Please Check your email");

                } else if (binding.editSubEnter.getText().toString().trim().equals("")) {
                    binding.editSubEnter.requestFocus();
                    binding.editSubEnter.setError("Enter your subject");
                }
                else if (!MyValidator.isValidName(binding.editSubEnter.getText().toString().trim())) {
                    Toast.makeText(context, "Please enter valid Subject", Toast.LENGTH_SHORT).show();
                }
                else if (binding.description.getText().toString().trim().equals("")) {
                    binding.description.requestFocus();
                    binding.description.setError(getString(R.string.enter_your_description));
                }
                else {
                    getAddComplaint();
                }
            }
        });


    }

    private void getAddComplaint(){

        AddComplaintAuthModel model = new AddComplaintAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setEmail(binding.editEmailEnter.getText().toString());
        model.setAssistance_type(binding.spinnerSelectTopic.getSelectedItem().toString());
        model.setDescription(binding.description.getText().toString());
        model.setSubject(binding.editSubEnter.getText().toString());

        WebServiceModel.getRestApi().getAddComplaint(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AddComplaintResponse>() {
                    @Override
                    public void onNext(AddComplaintResponse addComplaintResponse) {
                        String msg = addComplaintResponse.getMsg();

                        if (msg.equals("success")){
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

    public void openSelectTopic()
    {

        String[] countries = new String[]{getString(R.string.select_topic),getString(R.string.ui_feedback) , getString(R.string.content) , getString(R.string.bugs) , getString(R.string.game) , getString(R.string.quiz) , getString(R.string.other) };

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, R.id.spinnerTarget, countries);
        binding.spinnerSelectTopic.setAdapter(countryAdapter);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == binding.spinnerSelectTopic.getId()){
            openSelectTopic();
        }
    }
}
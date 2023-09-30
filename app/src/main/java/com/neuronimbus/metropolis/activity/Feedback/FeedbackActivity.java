package com.neuronimbus.metropolis.activity.Feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.FeedbackAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.FeedbackResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    TextInputEditText description_enter;
    Button btn_feedback_sub;
    RelativeLayout rel_back, spinnerLay;
    String title, setting;
    Spinner spinner_feedback_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        context = FeedbackActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        spinnerLay = findViewById(R.id.spinnerLay);
        spinner_feedback_type = findViewById(R.id.spinner_feedback_type);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        btn_feedback_sub = findViewById(R.id.btn_feedback_sub);
        btn_feedback_sub.setOnClickListener(this);
        description_enter = findViewById(R.id.description_enter);
        title = getIntent().getStringExtra("title_id");
        setting = getIntent().getStringExtra("settingActivity");

        if (setting != null && setting.equals("settingActivity")){
            spinnerLay.setVisibility(View.VISIBLE);
            feedbackType();
        }
        else {
            spinnerLay.setVisibility(View.GONE);
        }
    }

    public void getFeedback(){
        dialog.show("");

        FeedbackAuthModel model = new FeedbackAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setFeedbackreason(description_enter.getText().toString().trim());
        model.setAssistanceType(spinner_feedback_type.getSelectedItem().toString());
        model.setTitle(title);
        //model.setFeedbackImage();

        WebServiceModel.getRestApi().getFeedback(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<FeedbackResponse>() {
                    @Override
                    public void onNext(FeedbackResponse feedbackResponse) {
                        String msg = feedbackResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){

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

        if (id == spinner_feedback_type.getId()){
            feedbackType();
        }

        else if (id == btn_feedback_sub.getId()){
            if (description_enter.getText().toString().trim().equals("")) {
                description_enter.requestFocus();
                description_enter.setError(getString(R.string.enter_your_description));
            }
            else {
                getFeedback();
                description_enter.setText("");
                finish();
            }
        }
        else if (id == rel_back.getId()){
            finish();
        }
    }

    public void feedbackType()
    {

        String[] countries = new String[]{getString(R.string.select_type),getString(R.string.ui_feedback) , getString(R.string.content) , getString(R.string.bugs) , getString(R.string.game) , getString(R.string.quiz) , getString(R.string.other) };

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, R.id.spinnerTarget, countries);
        spinner_feedback_type.setAdapter(countryAdapter);

    }
}
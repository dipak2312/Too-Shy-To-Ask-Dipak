package com.neuronimbus.metropolis.activity.Feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

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
    RelativeLayout rel_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        context = FeedbackActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        btn_feedback_sub = findViewById(R.id.btn_feedback_sub);
        btn_feedback_sub.setOnClickListener(this);
        description_enter = findViewById(R.id.description_enter);
    }

    public void getFeedback(){
        dialog.show("");

        FeedbackAuthModel model = new FeedbackAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setFeedbackreason(description_enter.getText().toString().trim());

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

        if (id == btn_feedback_sub.getId()){
            if (description_enter.getText().toString().trim().equals("")) {
                description_enter.requestFocus();
                description_enter.setError("Enter your description");
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
}
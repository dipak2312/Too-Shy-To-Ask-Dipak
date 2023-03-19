package com.example.tooshytoask.activity.Feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.AuthModels.FeedbackAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.FeedbackResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        context = FeedbackActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

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
            if (btn_feedback_sub.getText().toString().trim().equals("")) {
                btn_feedback_sub.requestFocus();
                btn_feedback_sub.setError("Enter your description");
            }
            else {
                getFeedback();
            }
        }
    }
}
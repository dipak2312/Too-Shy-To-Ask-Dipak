package com.example.tooshytoask.Activity.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.AuthModels.ContactFormAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.Help.ContactFormResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.MyValidator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RelativeLayout add_file, rel_back;
    TextInputEditText edit_email_enter, edit_sub_enter, edit_age;
    Button submit_req;
    String[] permissions = new String[]{

            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = ContactUsActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        add_file = findViewById(R.id.add_file);
        add_file.setOnClickListener(this);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        edit_email_enter = findViewById(R.id.edit_email_enter);
        edit_email_enter.setOnClickListener(this);
        edit_sub_enter = findViewById(R.id.edit_sub_enter);
        edit_sub_enter.setOnClickListener(this);
        edit_age = findViewById(R.id.edit_age);
        edit_age.setOnClickListener(this);
        submit_req = findViewById(R.id.submit_req);
        submit_req.setOnClickListener(this);

        checkPermissions();

    }

    private void checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
        }
    }

    public void getContact() {
        dialog.show("");
        dialog.dismiss("");

        ContactFormAuthModel model = new ContactFormAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setEmail(model.getEmail());
        model.setUser_id(model.getSubject());
        model.setUser_id(model.getDescription());
        model.setUser_id(model.getImg());

        WebServiceModel.getRestApi().getContact(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ContactFormResponse>() {
                    @Override
                    public void onNext(ContactFormResponse contactFormResponse) {
                        String msg = contactFormResponse.getMsg();
                        if (msg.equals("success")) {

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

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()) {
            finish();
        } else if (id == add_file.getId()) {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
            intent.setType("*/*");
            startActivity(intent);
        } else if (id == submit_req.getId()) {

            if (edit_email_enter.getText().toString().trim().equals("")) {
                edit_email_enter.requestFocus();
                edit_email_enter.setError("Email Id can't be blank");
            } else if (!MyValidator.isValidEmail(edit_email_enter.getText().toString().trim())) {
                edit_email_enter.requestFocus();
                edit_email_enter.setError("Please Check your email");

            } else if (edit_sub_enter.getText().toString().trim().equals("")) {
                edit_sub_enter.requestFocus();
                edit_sub_enter.setError("Enter your subject");
            } else if (edit_age.getText().toString().trim().equals("")) {
                edit_age.requestFocus();
                edit_age.setError("Enter your description");
            } else {
                getContact();
                Toast.makeText(context, "Thank you for taking the time.", Toast.LENGTH_SHORT).show();
                finish();

            }
        }

    }
}
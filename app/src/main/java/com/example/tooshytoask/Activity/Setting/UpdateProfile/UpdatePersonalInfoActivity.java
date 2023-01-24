package com.example.tooshytoask.Activity.Setting.UpdateProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.tooshytoask.Activity.Landing.SignInActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.ozcanalasalvar.library.view.popup.DatePickerPopup;

public class UpdatePersonalInfoActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    SPManager spManager;
    Spinner  spinner_blood;
    RelativeLayout rel_back;
    Button next_btn;
    CustomProgressDialog dialog;
    EditText etBlood_group,etHeight,etWeight;

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

        //spinner_country = findViewById(R.id.spinner_country);
        spinner_blood = findViewById(R.id.spinner_blood);


        //OpenAllCountry();
        OpenBloodGrp();
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
        else if (id == next_btn.getId()){

            Intent intent = new Intent(context, UpdateProfileActivity.class);
            startActivity(intent);
            finish();
            dialog.show("");
        }
    }

    public void OpenBloodGrp()
    {


        String[] countries = new String[]{"Select", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, R.id.spinnerTarget, countries);
        spinner_blood.setAdapter(countryAdapter);

    }
}
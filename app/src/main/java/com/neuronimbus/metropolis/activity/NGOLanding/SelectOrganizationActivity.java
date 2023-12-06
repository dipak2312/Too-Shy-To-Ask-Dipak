package com.neuronimbus.metropolis.activity.NGOLanding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.adapters.SelectOrganisationAdapter;
import com.neuronimbus.metropolis.databinding.ActivityNgoSignUpBinding;
import com.neuronimbus.metropolis.databinding.ActivitySelectOrganizationBinding;

import java.util.ArrayList;

public class SelectOrganizationActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivitySelectOrganizationBinding binding;
    SelectOrganisationAdapter adapter;
    ArrayList<String> selectedOrgIds=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivitySelectOrganizationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        onClick();
        getController();
    }
    private void onClick() {
    }
    private void getController() {
    }
}
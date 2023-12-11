package com.neuronimbus.metropolis.activity.NGO;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
import com.neuronimbus.metropolis.databinding.ActivityAdminApprovalBinding;

public class AdminApprovalActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;
    ActivityAdminApprovalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityAdminApprovalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        onClick();
        getController();
    }

    private void onClick() {

    }

    private void getController() {
        context = AdminApprovalActivity.this;
        spManager = new SPManager(context);
        pendingPopup();
    }

    public void pendingPopup() {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.admin_approval_popup);
        dialog.setCancelable(false);

        RelativeLayout exploreTstaLay = dialog.findViewById(R.id.exploreTstaLay);


        exploreTstaLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, HomeActivity.class);
                spManager.setTstaguestLoginStatus("true");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });

        dialog.show();

    }
}
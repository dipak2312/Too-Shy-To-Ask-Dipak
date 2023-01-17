package com.example.tooshytoask.Activity.Setting.UpdateProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.HealthAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.HealthIssues;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.ImagePickUtils;
import com.example.tooshytoask.Utils.ImagePickUtilsCamera;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateAvatarActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    RelativeLayout rel_back;
    Button next_btn;
    CustomProgressDialog dialog;
    CircleImageView avatar1,avatar2,avatar3,avatar4,avatar5,avatar6,avatar7,avatar8;
    private static final int TAKE_PICTURE = 1;
    public static final int SELECT_FILE = 2754;
    String[] permissions = new String[]{

            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_avatar);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = UpdateAvatarActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
        avatar1 = findViewById(R.id.avatar1);
        avatar1.setOnClickListener(this);
        avatar2 = findViewById(R.id.avatar2);
        avatar2.setOnClickListener(this);
        avatar3 = findViewById(R.id.avatar3);
        avatar3.setOnClickListener(this);
        avatar4 = findViewById(R.id.avatar4);
        avatar4.setOnClickListener(this);
        avatar5 = findViewById(R.id.avatar5);
        avatar5.setOnClickListener(this);
        avatar6 = findViewById(R.id.avatar6);
        avatar6.setOnClickListener(this);
        avatar7 = findViewById(R.id.avatar7);
        avatar7.setOnClickListener(this);
        avatar8 = findViewById(R.id.avatar8);
        avatar8.setOnClickListener(this);
    }

    private boolean checkPermissions() {
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
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){

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
        else if (id == avatar1.getId()){
            boolean status=checkPermissions();
            if(status)
            {
                ImagePickUtils.selectImage(context);
            }
            else
            {
                checkPermissions();
            }
        }
        else if (id == avatar2.getId()){
            avatar2.setBackgroundResource(R.drawable.circle_active_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar3.getId()){
            avatar3.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar4.getId()){
            avatar4.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar5.getId()){
            avatar5.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar6.getId()){
            avatar6.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar7.getId()){
            avatar7.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar8.getId()){
            avatar8.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
        }
    }
}
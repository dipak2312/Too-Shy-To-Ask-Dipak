package com.example.tooshytoask.Activity.Setting.UpdateProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.ProfileAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AvatarResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.ImagePickUtils;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class UpdateAvatarActivity extends AppCompatActivity implements View.OnClickListener, OnClickListner {
    Context context;
    SPManager spManager;
    RelativeLayout rel_back;
    Button next_btn;
    CustomProgressDialog dialog;
    RecyclerView profile_recy;
    CircleImageView avatar1;
    ProfileAdapter adapter;
    OnClickListner onclicklistener;
    ArrayList<com.example.tooshytoask.Models.avatarList>avatarList;
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
        profile_recy = findViewById(R.id.profile_recy);

        profile_recy.setLayoutManager(new GridLayoutManager(context,4, GridLayoutManager.VERTICAL, false));
        getProfile();
    }

    public void getProfile() {
        dialog.show("");

        WebServiceModel.getRestApi().getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AvatarResponse>() {
                    @Override
                    public void onNext(AvatarResponse avatarResponse) {

                        String msg = avatarResponse.getMsg();

                        if (msg.equals("success")) {

                            avatarList = avatarResponse.getAvatarList();
                            for(int i=0;i<avatarList.size();i++)
                            {
                                avatarList.get(i).isSelected=false;
                            }

                            if (avatarList != null) {
                                CallAdapter();

                            }
                        } else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                        dialog.dismiss("");

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void CallAdapter()

    {
        adapter = new ProfileAdapter(avatarList, this, context);
        profile_recy.setAdapter(adapter);
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

    }

    @Override
    public void onClickData(int position, String id) {

    }
}
package com.example.tooshytoask.activity.Setting.Setting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.adapters.ProfileAdapter;
import com.example.tooshytoask.AuthModels.RemoveProfileAuthModel;
import com.example.tooshytoask.AuthModels.UpdateProfileAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AvatarResponse;
import com.example.tooshytoask.Models.RemoveProfileResponse;
import com.example.tooshytoask.Models.UpdateProfile.UpdateProfileResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.ImagePickUtils;
import com.example.tooshytoask.Utils.OnClickListner;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;
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
    CircleImageView profile_img, profile_img_see;
    ProfileAdapter adapter;
    OnClickListner onclicklistener;
    String action = "avtarchange", avtarImage = "", status ="";
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
        profile_img_see = findViewById(R.id.profile_img_see);
        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
        profile_img = findViewById(R.id.profile_img);
        profile_img.setOnClickListener(this);
        profile_recy = findViewById(R.id.profile_recy);

        profile_recy.setLayoutManager(new GridLayoutManager(context,4, GridLayoutManager.VERTICAL, false));
        getProfile();
    }

    public void RemoveProfile(){
        dialog.show("");

        RemoveProfileAuthModel model = new RemoveProfileAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().RemoveProfile(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RemoveProfileResponse>() {
                    @Override
                    public void onNext(RemoveProfileResponse removeProfileResponse) {
                        String msg = removeProfileResponse.getMsg();
                            dialog.dismiss("");
                        if (msg.equals("Profile Image Updated Successfully")){

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

    public void getUserProfileUpdate(){
        dialog.show("");
        dialog.dismiss("");

        UpdateProfileAuthModel model = new UpdateProfileAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setAction(action);
        model.setImage(avtarImage);

        WebServiceModel.getRestApi().getUserProfile(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UpdateProfileResponse>() {
                    @Override
                    public void onNext(UpdateProfileResponse updateProfileResponse) {
                        String msg = updateProfileResponse.getMsg();

                        if (msg.equals("Profile Updated")){


                        }
                        else {
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
                                //avatarList.get(i).isSelected=false;
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

    public void profilePopup(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.select_image_dialog_layout);
        bottomSheetDialog.setCancelable(false);


        final Button camera1 = (Button) bottomSheetDialog.findViewById(R.id.select_image_dialog_btn_camera_btn);
        Button gallery = (Button) bottomSheetDialog.findViewById(R.id.select_image_dialog_btn_gallery_btn);
        RelativeLayout btnCancel = (RelativeLayout) bottomSheetDialog.findViewById(R.id.select_image_dialog_close_btn);
        Button btnRemoveProfile = (Button) bottomSheetDialog.findViewById(R.id.remove_image_dialog_button);

        bottomSheetDialog.show();


        camera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Activity activity = (Activity) context;

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                activity.startActivityForResult(intent, TAKE_PICTURE);
                bottomSheetDialog.dismiss();

            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) context;

                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                activity.startActivityForResult(galleryIntent, SELECT_FILE);
                bottomSheetDialog.dismiss();
            }
        });

        btnRemoveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveProfile();
                bottomSheetDialog.dismiss();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == TAKE_PICTURE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                status = "yes";
                Toast.makeText(context, "Permission accepted", Toast.LENGTH_SHORT).show();
            }
            else {
                status = "no";
                Toast.makeText(context, "Permission cancel", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_FILE) {
            if (data != null) {

                Uri uri = data.getData();

                String path = String.valueOf(uri);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    File choosedFile = ImagePickUtils.getPickedFile(context, data.getData());

                    Bitmap compressedImageBitmap = new Compressor(this).compressToBitmap(choosedFile);

                    profile_img_see.setImageBitmap(null);
                    profile_img_see.setImageBitmap(compressedImageBitmap);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    compressedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();

                    avtarImage = android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT);
                    System.out.println(avtarImage);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } else if (requestCode == TAKE_PICTURE) {

            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");

                profile_img_see.setImageBitmap(null);
                profile_img_see.setImageBitmap(bp);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imageInByte = stream.toByteArray();
                avtarImage = android.util.Base64.encodeToString(imageInByte, android.util.Base64.DEFAULT);


            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }

            }

        }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){

            Intent intent = new Intent(context, UpdateProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            dialog.show("");
        }
        else if (id == next_btn.getId()){

            getUserProfileUpdate();
            Intent intent = new Intent(context, UpdateProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            dialog.show("");
        }
        else if (id == profile_img.getId()){
            profilePopup();
        }

    }

    @Override
    public void onClickData(int position, String base64Image) {
        avtarImage=base64Image;
    }
}
package com.neuronimbus.metropolis.activity.NGO;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

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
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.SaveProfilePicAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.AvatarResponse;
import com.neuronimbus.metropolis.Models.SaveProfilePicResponse;
import com.neuronimbus.metropolis.Models.avatarList;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.ClickListener;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.ImagePickUtil;
import com.neuronimbus.metropolis.Utils.OnClickListner;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
import com.neuronimbus.metropolis.adapters.NGOProfileAdapter;
import com.neuronimbus.metropolis.adapters.ProfileAdapter;
import com.neuronimbus.metropolis.databinding.ActivityNgoAvatarBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NgoAvatarActivity extends AppCompatActivity implements OnClickListner,ClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivityNgoAvatarBinding binding;
    ArrayList<avatarList> ngoAvatarList;
    NGOProfileAdapter adapter;
    String avtarImage="";
    ClickListener clickListener;
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
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityNgoAvatarBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        onClick();
        getController();
        checkPermissions();
    }

    private void onClick() {
        binding.relBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkPermissions();

                Activity activity = (Activity) context;

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                activity.startActivityForResult(intent, TAKE_PICTURE);
            }
        });

        binding.file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkPermissions();
                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, SELECT_FILE);
            }
        });

        binding.updateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (avtarImage.equals("")) {
                    Toast.makeText(context, "Please select your profile", Toast.LENGTH_SHORT).show();
                }
                else {
                    saveProfilePic();
                    clickListener.onClick(true);
                }
            }
        });
    }

    private void getController() {
        context = NgoAvatarActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        clickListener=(ClickListener)context;
        clickListener.onClick(false);
        binding.updateBtn2.setBackgroundResource(R.drawable.inactive_con_btn);
        binding.recyNgoProfile.setLayoutManager(new GridLayoutManager(context,4, GridLayoutManager.VERTICAL, false));


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

                            ngoAvatarList = avatarResponse.getAvatarList();
                            for(int i=0;i<ngoAvatarList.size();i++)
                            {
                                ngoAvatarList.get(i).isSelected=false;
                            }

                            if (ngoAvatarList != null) {
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
        adapter = new NGOProfileAdapter(context,ngoAvatarList, this);
        binding.recyNgoProfile.setAdapter(adapter);
    }

    public void saveProfilePic(){
        dialog.show("");

        SaveProfilePicAuthModel model = new SaveProfilePicAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setImage(avtarImage);

        WebServiceModel.getRestApi().saveProfilePic(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SaveProfilePicResponse>() {
                    @Override
                    public void onNext(SaveProfilePicResponse saveProfilePicResponse) {
                        String msg = saveProfilePicResponse.getMsg();

                        if (msg.equals("Profile Image Updated Successfully")){
                            Intent intent = new Intent(context, AdminApprovalActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }
                        dialog.dismiss("");
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
    public void onClickData(int position, String base64Image) {

        avtarImage=base64Image;
        binding.updateBtn2.setBackgroundResource(R.drawable.active_con_btn);
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
                Toast.makeText(context, "Permission accepted", Toast.LENGTH_SHORT).show();
            }
            else {
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

                    File choosedFile = ImagePickUtil.getPickedFile(context, data.getData());

                    Bitmap compressedImageBitmap = new Compressor(context).compressToBitmap(choosedFile);

                    //File compressedImageFile = new Compressor(this).compressToFile(choosedFile);

                    binding.profileImgSee.setImageBitmap(null);
                    binding.profileImgSee.setImageBitmap(compressedImageBitmap);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    compressedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();

                    avtarImage = android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT);
                    System.out.println(avtarImage);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } else if (requestCode==TAKE_PICTURE) {

            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imageInByte = stream.toByteArray();
                avtarImage = android.util.Base64.encodeToString(imageInByte, android.util.Base64.DEFAULT);


            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(context, "Picture Not taken", Toast.LENGTH_LONG);
                 /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getActivity().getPackageName()));
                    startActivity(i);
                    return;
                }*/
            }
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    public void onClick(Boolean status) {

    }
}
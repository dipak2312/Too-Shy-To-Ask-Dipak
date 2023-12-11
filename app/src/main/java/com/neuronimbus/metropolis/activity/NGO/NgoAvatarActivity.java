package com.neuronimbus.metropolis.activity.NGO;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.ImagePickUtil;
import com.neuronimbus.metropolis.Utils.OnClickListner;
import com.neuronimbus.metropolis.adapters.NGOProfileAdapter;
import com.neuronimbus.metropolis.databinding.ActivityNgoAvatarBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;

public class NgoAvatarActivity extends AppCompatActivity implements OnClickListner {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivityNgoAvatarBinding binding;
    ArrayList<String> ngoAvatarList;
    NGOProfileAdapter adapter;
    String avtarImage="";
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
    }

    private void getController() {
        context = NgoAvatarActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        ngoAvatarList = new  ArrayList<>();

        ngoAvatarList.add("test");
        ngoAvatarList.add("check");
        ngoAvatarList.add("jhvjhsvhjb");
        ngoAvatarList.add("jhvjhsvhjb");

        adapter = new NGOProfileAdapter(ngoAvatarList,this, context);
        binding.recyNgoProfile.setAdapter(adapter);

    }

    @Override
    public void onClickData(int position, String id) {

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
}
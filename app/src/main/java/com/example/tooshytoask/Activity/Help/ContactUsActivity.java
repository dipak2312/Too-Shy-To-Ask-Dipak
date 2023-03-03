package com.example.tooshytoask.Activity.Help;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.AuthModels.ContactFormAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.Help.ContactFormResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.ImagePickUtils;
import com.example.tooshytoask.Utils.ImagePickUtilsFile;
import com.example.tooshytoask.Utils.MyValidator;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RelativeLayout add_file, rel_back;
    TextInputEditText edit_email_enter, edit_sub_enter, description;
    TextView img_name;
    Button submit_req;
    String image = "";
    ImageView profile_img, remove_img;
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
        setContentView(R.layout.activity_contact_us);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = ContactUsActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        remove_img = findViewById(R.id.remove_img);
        remove_img.setOnClickListener(this);
        img_name = findViewById(R.id.img_name);
        img_name.setOnClickListener(this);
        profile_img = findViewById(R.id.profile_img);
        add_file = findViewById(R.id.add_file);
        add_file.setOnClickListener(this);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        edit_email_enter = findViewById(R.id.edit_email_enter);
        edit_email_enter.setOnClickListener(this);
        edit_sub_enter = findViewById(R.id.edit_sub_enter);
        edit_sub_enter.setOnClickListener(this);
        description = findViewById(R.id.description);
        description.setOnClickListener(this);
        submit_req = findViewById(R.id.submit_req);
        submit_req.setOnClickListener(this);

        checkPermissions();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_FILE) {
            if (data != null) {

                Uri uri = data.getData();

                String path = String.valueOf(uri);
                String[] filepath = {MediaStore.Images.Media.DATA};

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    File choosedFile = ImagePickUtilsFile.getPickedFile(context, data.getData());

                    Bitmap compressedImageBitmap = new Compressor(this).compressToBitmap(choosedFile);
                    Cursor cursor = getContentResolver().query(uri, filepath, null,null,null);
                    cursor.moveToFirst();
                    int colmneIndex = cursor.getColumnIndex(filepath[0]);
                    String picturepath = cursor.getString(colmneIndex);
                    cursor.close();

                    String filename = picturepath.substring(picturepath.lastIndexOf("/")+1);

                    img_name.setText(filename);
                    remove_img.setVisibility(View.VISIBLE);
                    profile_img.setImageBitmap(null);
                    profile_img.setImageBitmap(compressedImageBitmap);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    compressedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();

                    image = android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT);
                    System.out.println(image);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }  else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }

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

    public void getContact() {
        dialog.show("");
        dialog.dismiss("");

        ContactFormAuthModel model = new ContactFormAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setEmail(edit_email_enter.getText().toString().trim());
        model.setSubject(edit_sub_enter.getText().toString().trim());
        model.setDescription(description.getText().toString().trim());
        model.setImg(image);

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
        }
        else if (id == remove_img.getId()){
            remove_img.setVisibility(View.GONE);
            image = "";
            img_name.setText("");
            profile_img.setImageBitmap(null);
        }
        else if (id == add_file.getId()) {
            boolean status=checkPermissions();
            if(status)
            {
                ImagePickUtilsFile.selectImage(context);
            }
            else
            {
                checkPermissions();
            }

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
            } else if (description.getText().toString().trim().equals("")) {
                description.requestFocus();
                description.setError("Enter your description");
            } else {
                getContact();
                Toast.makeText(context, "Thank you for taking the time.", Toast.LENGTH_SHORT).show();
                finish();

            }
        }

    }
}
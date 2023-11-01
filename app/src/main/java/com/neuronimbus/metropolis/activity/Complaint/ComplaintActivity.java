package com.neuronimbus.metropolis.activity.Complaint;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewbinding.ViewBinding;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.AddComplaintAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.AddComplaintResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.ImagePickUtil;
import com.neuronimbus.metropolis.Utils.MyValidator;
import com.neuronimbus.metropolis.activity.Help.ContactUsActivity;
import com.neuronimbus.metropolis.activity.Landing.SignInActivity;
import com.neuronimbus.metropolis.databinding.ActivityComplaintBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ComplaintActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivityComplaintBinding binding;
    String image = "";

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
        binding = ActivityComplaintBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = ComplaintActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        onClick();
        openSelectTopic();
        checkPermissions();
    }

    private void onClick(){

        binding.relBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.previousChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ComplaintListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        binding.submitReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editEmailEnter.getText().toString().trim().equals("")) {
                    binding.editEmailEnter.requestFocus();
                    binding.editEmailEnter.setError("Email Id can't be blank");
                } else if (!MyValidator.isValidEmail(binding.editEmailEnter.getText().toString().trim())) {
                    binding.editEmailEnter.requestFocus();
                    binding.editEmailEnter.setError("Please Check your email");

                } else if (binding.editSubEnter.getText().toString().trim().equals("")) {
                    binding.editSubEnter.requestFocus();
                    binding.editSubEnter.setError("Enter your subject");
                }
                
                else if (binding.description.getText().toString().trim().equals("")) {
                    binding.description.requestFocus();
                    binding.description.setError(getString(R.string.enter_your_description));
                }
                else {
                    getAddComplaint();
                }
            }
        });

        binding.addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) context;

                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                activity.startActivityForResult(galleryIntent, SELECT_FILE);
            }
        });

        binding.imgName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) context;

                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                activity.startActivityForResult(galleryIntent, SELECT_FILE);
            }
        });
//        binding.removeImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.removeImg.setVisibility(View.GONE);
//                binding.attachmentImageLay.setVisibility(View.GONE);
//                image = "";
//                binding.imgName.setText("");
//                binding.attachmentImageName.setText("");
//                binding.attachmentImageDate.setText("");
//                binding.attachmentImageSize.setText("");
//                binding.attachmentImage.setImageBitmap(null);
//            }
//        });

        binding.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.attachmentImageLay.setVisibility(View.GONE);
                image = "";
                binding.attachmentImageName.setText("");
                binding.attachmentImageDate.setText("");
                binding.attachmentImageSize.setText("");
                binding.attachmentImage.setImageBitmap(null);
            }
        });

    }

    private void getAddComplaint(){
        dialog.show("");

        AddComplaintAuthModel model = new AddComplaintAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setEmail(binding.editEmailEnter.getText().toString());
        model.setAssistance_type(binding.spinnerSelectTopic.getSelectedItem().toString());
        model.setDescription(binding.description.getText().toString());
        model.setSubject(binding.editSubEnter.getText().toString());
        model.setImg(image);

        WebServiceModel.getRestApi().getAddComplaint(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AddComplaintResponse>() {
                    @Override
                    public void onNext(AddComplaintResponse addComplaintResponse) {
                        String msg = addComplaintResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){

                            Intent intent = new Intent(context, ComplaintListActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss("");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void openSelectTopic()
    {

        String[] countries = new String[]{getString(R.string.select_topic),getString(R.string.ui_feedback) , getString(R.string.content) , getString(R.string.bugs) , getString(R.string.game) , getString(R.string.quiz) , getString(R.string.other) };

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, R.id.spinnerTarget, countries);
        binding.spinnerSelectTopic.setAdapter(countryAdapter);


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

                    File choosedFile = ImagePickUtil.getPickedFile(context, data.getData());

                    Bitmap compressedImageBitmap = new Compressor(this).compressToBitmap(choosedFile);
                    Cursor cursor = getContentResolver().query(uri, filepath, null,null,null);
                    cursor.moveToFirst();
                    int colmneIndex = cursor.getColumnIndex(filepath[0]);
                    String picturepath = cursor.getString(colmneIndex);
                    cursor.close();

                        String filename = picturepath.substring(picturepath.lastIndexOf("/")+1);

                    double filesize= getFileSizeInMB(convertContentUriToFileUri(uri));

                    if (filesize >= 3.0){
                        binding.attachmentImageLay.setVisibility(View.GONE);
                        userReplyPopup();
                    }
                    else {
                        Date currentDate = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = dateFormat.format(currentDate);
                        binding.attachmentImageLay.setVisibility(View.VISIBLE);

                        //binding.imgName.setText(filename);
                        binding.attachmentImageName.setText(filename);
                        binding.attachmentImageDate.setText(formattedDate);
                        binding.attachmentImageSize.setText(filesize + " " + "MB");
                        //binding.removeImg.setVisibility(View.VISIBLE);
                        binding.attachmentImage.setImageBitmap(null);
                        binding.attachmentImage.setImageBitmap(compressedImageBitmap);
                        //image = String.valueOf(binding.attachmentImage);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        compressedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] b = baos.toByteArray();

                        image = android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT);
                        System.out.println(image);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }  else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
        }

    }

    public void userReplyPopup() {

        Dialog alertDialog = new Dialog(context);
        alertDialog.setContentView(R.layout.image_size_popup);
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.logout_popup));

        Button btnSubmit = alertDialog.findViewById(R.id.btnSubmit);
        RelativeLayout back_arrow = alertDialog.findViewById(R.id.back_arrow);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();

            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    private Uri convertContentUriToFileUri(Uri contentUri) {
        InputStream inputStream = null;
        try {
            inputStream = getContentResolver().openInputStream(contentUri);
            if (inputStream != null) {
                File outputFile = File.createTempFile("temp_", ".pdf", getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                try {
                    byte[] buffer = new byte[4 * 1024]; // 4k buffer size
                    int read;
                    while ((read = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, read);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    outputStream.close();
                }
                return Uri.fromFile(outputFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private double getFileSizeInMB(Uri uri) {
        double fileSizeInMB = 0.0;

        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);

            if (inputStream != null) {
                int fileSizeInBytes = inputStream.available();
                inputStream.close();

                if (fileSizeInBytes > 0) {
                    // Convert bytes to megabytes (MB)
                    fileSizeInMB = fileSizeInBytes / (1024.0 * 1024.0);
                    // Round to two decimal places
                    fileSizeInMB = Math.round(fileSizeInMB * 100.0) / 100.0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileSizeInMB;
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

        if (id == binding.spinnerSelectTopic.getId()){
            openSelectTopic();
        }
    }
}
package com.neuronimbus.metropolis.activity.Feedback;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.FeedbackAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.FeedbackResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.ImagePickUtil;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.databinding.ActivityNewFeedbackBinding;

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

public class NewFeedbackActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    String title = "", setting, assistanceType ="";
    String image = "";
    ActivityNewFeedbackBinding binding;
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
        binding = ActivityNewFeedbackBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = NewFeedbackActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        title = getIntent().getStringExtra("title_id");
        setting = getIntent().getStringExtra("settingActivity");

        if (title == null){
            title = "";
        }
        if (setting != null && setting.equals("settingActivity")){
            binding.spinnerLay.setVisibility(View.VISIBLE);
            feedbackType();
        }
        else {
            binding.spinnerLay.setVisibility(View.GONE);
        }
        checkPermissions();
        btnClick();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    private void btnClick() {

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


        binding.btnFeedbackSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.descriptionEnter.getText().toString().trim().equals("")) {
                    binding.descriptionEnter.requestFocus();
                    binding.descriptionEnter.setError(getString(R.string.enter_your_description));
                }
                else {
                    if (setting != null && setting.equals("settingActivity")){
                        assistanceType = binding.spinnerFeedbackType.getSelectedItem().toString().trim();
                        getFeedback();
                    }
                    else {
                        assistanceType = "";
                        getFeedback();
                    }


                }
            }
        });

        binding.relBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == binding.spinnerFeedbackType.getId()){
            feedbackType();
        }

    }

    public void getFeedback(){
        dialog.show("");

        FeedbackAuthModel model = new FeedbackAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setFeedbackreason(binding.descriptionEnter.getText().toString().trim());
        model.setAssistanceType(assistanceType);
        model.setTitle(title);
        model.setFeedbackImage(image);

        WebServiceModel.getRestApi().getFeedback(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<FeedbackResponse>() {
                    @Override
                    public void onNext(FeedbackResponse feedbackResponse) {
                        String msg = feedbackResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){
                            binding.descriptionEnter.setText("");
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

    public void feedbackType()
    {

        String[] countries = new String[]{getString(R.string.select_type),getString(R.string.ui_feedback) , getString(R.string.content) , getString(R.string.bugs) , getString(R.string.game) ,
                getString(R.string.quiz) , getString(R.string.faq) , getString(R.string.other)};

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, R.id.spinnerTarget, countries);
        binding.spinnerFeedbackType.setAdapter(countryAdapter);

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

                        binding.attachmentImageName.setText(filename);
                        binding.attachmentImageDate.setText(formattedDate);
                        binding.attachmentImageSize.setText(filesize + " " + "MB");
                        binding.attachmentImage.setImageBitmap(null);
                        binding.attachmentImage.setImageBitmap(compressedImageBitmap);
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

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.image_size_popup);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.logout_popup));

        Button btnSubmit = dialog.findViewById(R.id.btnSubmit);
        RelativeLayout back_arrow = dialog.findViewById(R.id.back_arrow);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();

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
}
package com.neuronimbus.metropolis.activity.NGO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.CommonAuthModel;
import com.neuronimbus.metropolis.AuthModels.QRCodeCountAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.CommonResponse;
import com.neuronimbus.metropolis.Models.QRCode.QRCodeResponse;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.adapters.QRCodeAdapter;
import com.neuronimbus.metropolis.databinding.ActivityQrcodeBinding;

import java.util.ArrayList;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class QRCodeActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    ActivityQrcodeBinding binding;
    String downloadUrl = "", action = "", referralUrl = "";
    ArrayList<com.neuronimbus.metropolis.Models.QRCode.question> question;
    QRCodeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityQrcodeBinding.inflate(getLayoutInflater());
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
        binding.downloadQRLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action= "download";
                downloadFile(downloadUrl);

            }
        });
        binding.referFriendsLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action= "refer";

                Drawable mDrawable = binding.qrCodeImage.getDrawable();
                Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), mBitmap, "Image I want to share", null);
                Uri uri = Uri.parse(path);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, referralUrl);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, "Share Image"));
                qrCodeCount();

            }
        });
    }

    public void downloadFile(String url) {
        Uri uri = Uri.parse(url);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI |
                        DownloadManager.Request.NETWORK_MOBILE
        );

        // Set title and description
        String fileName = getName("TooShyToAsk");
        request.setTitle(fileName);
        request.setDescription("File has been downloaded");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        // Show a dialog

        // Set the local destination for the downloaded file to a path within the application's external files directory
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName + ".png");
        request.setMimeType("*/*");

        // Enqueue the download request
        downloadManager.enqueue(request);

        qrCodeCount();
    }

    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        int index = filename.lastIndexOf('/');
        return filename.substring(index + 1);
    }

    private void getController() {
        context = QRCodeActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.recyQrCode.setLayoutManager(linearLayoutManager);
        qrCodeInfo();

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    private void qrCodeInfo(){
        dialog.show("");

        CommonAuthModel model = new CommonAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().qrCodeInfo(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QRCodeResponse>() {
                    @Override
                    public void onNext(QRCodeResponse qrCodeResponse) {
                        String msg = qrCodeResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){
                            question = qrCodeResponse.getQuestion();
                            downloadUrl = qrCodeResponse.getAndroid_qrcode_img();
                            referralUrl = qrCodeResponse.getAndroid_referral_url();
                            binding.ngoName.setText(qrCodeResponse.getNgo_name());
                            Glide.with(context).load(qrCodeResponse.getAndroid_qrcode_img()).into(binding.qrCodeImage);

                            if (question != null){
                                adapter = new QRCodeAdapter(context, question);
                                binding.recyQrCode.setAdapter(adapter);
                            }

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void qrCodeCount(){
        dialog.show("");

        QRCodeCountAuthModel model = new QRCodeCountAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setAction(action);

        WebServiceModel.getRestApi().qrCodeCount(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CommonResponse>() {
                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        String msg = commonResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("success")){


                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    @Override
    public void onResume() {
        super.onResume();
        setLocale(spManager.getLanguage());
    }

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        spManager.setLanguage(lang);

    }
}
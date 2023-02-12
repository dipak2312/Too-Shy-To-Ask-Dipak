package com.example.tooshytoask.Fragment.InfoCard;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.ProfileAdapter;
import com.example.tooshytoask.AuthModels.SaveProfilePicAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AvatarResponse;
import com.example.tooshytoask.Models.SaveProfilePicResponse;
import com.example.tooshytoask.Models.avatarList;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.ImagePickUtils;
import com.example.tooshytoask.Utils.ImagePickUtilsCamera;
import com.example.tooshytoask.Utils.ImagePickUtilsFile;
import com.example.tooshytoask.Utils.OnClickListner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AvtarFragment extends Fragment implements View.OnClickListener, OnClickListner {
    Context context;
    SPManager spManager;
    ClickListener clickListener;
    TextView skip_btn2;
    ImageButton next_btn2;
    RecyclerView profile_recy;
    ImageView camera, file;
    ProfileAdapter adapter;
    String encodedImage = "";
    OnClickListner onclicklistener;
    CustomProgressDialog dialog;
    ArrayList<avatarList>avatarList;

    String avtarImage="";
    private static final int TAKE_PICTURE = 1;
    public static final int SELECT_FILE = 2754;
    String[] permissions = new String[]{

            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two, container, false);

        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        context = getActivity();
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        camera = view.findViewById(R.id.camera);
        camera.setOnClickListener(this);
        file = view.findViewById(R.id.file);
        file.setOnClickListener(this);
        skip_btn2 = view.findViewById(R.id.skip_btn2);
        skip_btn2.setOnClickListener(this);
        next_btn2 = view.findViewById(R.id.next_btn2);
        next_btn2.setOnClickListener(this);

        clickListener=(ClickListener)context;
        clickListener.onClick(false);

        profile_recy = view.findViewById(R.id.profile_recy);

        profile_recy.setLayoutManager(new GridLayoutManager(context,4, GridLayoutManager.VERTICAL, false));

        checkPermissions();
        getProfile();
        return view;
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
                         clickListener.onClick(true);

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

        if (id == skip_btn2.getId()){
            clickListener.onClick(true);
        }
        else if (id == next_btn2.getId()){
            saveProfilePic();
        }


        if (id == camera.getId()) {
            adapter.singleitem_selection_position=-1;
            adapter.notifyDataSetChanged();
            avtarImage="";
            boolean status=checkPermissions();
            if(status)
            {
                ImagePickUtilsCamera.selectImage(context);
                camera.isClickable();
                adapter.notifyDataSetChanged();
                next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            }
            else
            {
                checkPermissions();
            }
        } else if (id == file.getId()) {
            adapter.singleitem_selection_position=-1;
            adapter.notifyDataSetChanged();
            avtarImage="";
            boolean status=checkPermissions();
            if(status)
            {
                ImagePickUtilsFile.selectImage(context);

                next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            }
            else
            {
                checkPermissions();
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
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                    File choosedFile = ImagePickUtils.getPickedFile(context, data.getData());

                    Bitmap compressedImageBitmap = new Compressor(context).compressToBitmap(choosedFile);

                    //File compressedImageFile = new Compressor(this).compressToFile(choosedFile);

                    file.setImageBitmap(null);
                    file.setImageBitmap(compressedImageBitmap);

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
                Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show();
            }

        }
    }


    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getActivity(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    @Override
    public void onClickData(int position, String base64Image) {

       avtarImage=base64Image;
       //clickListener.onClick(true);

    }

}
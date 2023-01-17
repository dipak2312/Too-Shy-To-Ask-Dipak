package com.example.tooshytoask.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.NonNull;


import com.example.tooshytoask.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImagePickUtilsCamera {

    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;

    private static final int SELECT_FILE = 2754;

    public static void selectImage(final Context context) {

        CameraIntent(context);

       /*AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.select_image_dialog_layout, null);
        dialogBuilder.setView(dialogView);

        //TextView title = (TextView) dialogView.findViewById(R.id.textView1);
        final Button camera1 = (Button) dialogView.findViewById(R.id.select_image_dialog_btn_camera_btn);
        Button gallery = (Button) dialogView.findViewById(R.id.select_image_dialog_btn_gallery_btn);
        Button btnCancel = (Button) dialogView.findViewById(R.id.select_image_dialog_close_btn);

        final AlertDialog b = dialogBuilder.create();
        b.show();


        camera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CameraIntent(context);
                b.dismiss();

            }


        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryIntent(context);
                b.dismiss();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });*/


    }

    private static void GalleryIntent(Context context) {

        try {
           // if (canDeviceHandleGallery(context)) {
                Activity activity = (Activity) context;

                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                activity.startActivityForResult(galleryIntent, SELECT_FILE);
//            } else {
//                Toast.makeText(context, "You haven't gallery app", Toast.LENGTH_SHORT).show();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void CameraIntent(Context context) {

        Activity activity = (Activity) context;

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        activity.startActivityForResult(intent, TAKE_PICTURE);


//        UpdateProfileActivity activity=new UpdateProfileActivity();
//        activity.openCamera();

    }


    public static boolean canDeviceHandleGallery(@NonNull Context context) {
        return plainGalleryPickerIntent().resolveActivity(context.getPackageManager()) != null;
    }

    private static Intent plainGalleryPickerIntent() {
        return new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    public static File getPickedFile(@NonNull Context context, Uri photoUri) throws IOException {
        InputStream pictureInputStream = context.getContentResolver().openInputStream(photoUri);
        File directory = tempImageDirectory(context);
        // File photoFile = new File(directory, UUID.randomUUID().toString() + "." + getMimeTypeByURI(context, photoUri));
        File photoFile = new File(directory, "pickedImage" + "." + "jpg");
        if (photoFile.exists()) //delete if pre exist for override
            photoFile.delete();

        photoFile.createNewFile();
       writeToFile(pictureInputStream, photoFile);
        return photoFile;
    }

    private static File tempImageDirectory(@NonNull Context context) {
        File privateTempDir = new File(context.getCacheDir(), context.getResources().getString(R.string.app_images_folderName));
        if (!privateTempDir.exists()) privateTempDir.mkdirs();
        return privateTempDir;
    }

    public static void writeToFile(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
